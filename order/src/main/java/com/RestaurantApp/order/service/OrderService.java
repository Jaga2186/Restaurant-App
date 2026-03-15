package com.RestaurantApp.order.service;

import com.RestaurantApp.order.dto.FoodItemsDTO;
import com.RestaurantApp.order.dto.OrderDTO;
import com.RestaurantApp.order.dto.OrderDTOFromFE;
import com.RestaurantApp.order.dto.UserDTO;
import com.RestaurantApp.order.entity.Order;
import com.RestaurantApp.order.entity.OrderStatus;
import com.RestaurantApp.order.exception.OrderNotFoundException;
import com.RestaurantApp.order.exception.OrderProcessingException;
import com.RestaurantApp.order.exception.UserServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import com.RestaurantApp.order.mapper.OrderMapper;
import com.RestaurantApp.order.repo.OrderRepo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
@Transactional
public class OrderService {
    private final OrderRepo orderRepo;
    private final SequenceGenerator sequenceGenerator;
    private final RestTemplate restTemplate;

    @Value("${service.user.url:http://USER-SERVICE/user/fetchUserById/}")
    private String userServiceUrl;

    @Value("${order.cancellation.allowed-hours:2}")
    private int allowedCancellationHours;

    public OrderService(OrderRepo orderRepo, SequenceGenerator sequenceGenerator, RestTemplate restTemplate) {
        this.orderRepo = orderRepo;
        this.sequenceGenerator = sequenceGenerator;
        this.restTemplate = restTemplate;
    }

    @Timed(value = "order.save", description = "Time taken to save an order")
    public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails) {
        try {
            validateOrderDetails(orderDetails);
            Integer newOrderId = sequenceGenerator.generateNextSequence();
            UserDTO userDTO = fetchUserDetailsFromUserDTO(orderDetails.getUserId());

            BigDecimal totalAmount = calculateTotalAmount(orderDetails.getFoodItemsDTOS());

            Order orderToBeSaved = new Order();
            orderToBeSaved.setOrderId(newOrderId);
            orderToBeSaved.setFoodItems(orderDetails.getFoodItemsDTOS());
            orderToBeSaved.setRestaurant(orderDetails.getRestaurantDTO());
            orderToBeSaved.setUser(userDTO);
            orderToBeSaved.setTotalAmount(totalAmount);
            orderToBeSaved.setOrderStatus(OrderStatus.PENDING);
            orderToBeSaved.setCreatedAt(LocalDateTime.now());

            Order savedOrder = orderRepo.save(orderToBeSaved);
            log.info("Order saved successfully with ID: {}", savedOrder.getOrderId());
            return OrderMapper.INSTANCE.mapOrderToOrderDTO(savedOrder);
        } catch (Exception e) {
            log.error("Failed to save order", e.getMessage(), e);
            throw new OrderProcessingException("Failed to process order", e);
        }
        
    }

    @Cacheable(value = "orders", key = "#orderId") 
    public OrderDTO getOrderById(Integer orderId) {
        log.debug("Fetching order with ID: {}", orderId); 
        return orderRepo.findById(orderId)
                .map(OrderMapper.INSTANCE::mapOrderToOrderDTO)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
    }

    @Cacheable(value = "userOrders", key = "#userId")
    public List<OrderDTO> genOrderByUserId(Integer userId) {
        log.debug("Fetching orders for user ID: {}", userId);
        List<Order> orders = orderRepo.findByUserId(userId);
        return orders.stream()
                .map(OrderMapper.INSTANCE::mapOrderToOrderDTO)
                .collect(Collectors.toList());
    } 

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackFetchUserDetails")
    @Retry(name = "userService")
    private UserDTO fetchUserDetailsFromUserDTO(Integer userId) {
        String url = userServiceUrl + "/user/fetchByUserId/" + userId;
        log.debug("Fetching user details from User Service for user ID: {}", userId);
        ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
        return response.getBody();
    }

    private UserDTO fallbackFetchUserDetails(Integer userId, Exception e) {
        log.warn("Fall back: unable to fetch user details for userId: {}", userId, e.getMessage());
        throw new UserServiceUnavailableException("User Service is currently unavailable. Please try again later.", e);
    }

    private void validateOrderDetails(OrderDTOFromFE orderDetails) {
        if(orderDetails == null) {
            throw new IllegalArgumentException("Order details cannot be null");
        }
        if(orderDetails.getUserId() == null || orderDetails.getUserId() <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        if(orderDetails.getFoodItemsDTOS() == null || orderDetails.getFoodItemsDTOS().isEmpty()) {
            throw new IllegalArgumentException("At least one food item must be included in the order");
        }
        if(orderDetails.getRestaurantDTO() == null) {
            throw new IllegalArgumentException("Restaurant information is required");
        }
    }

    private BigDecimal calculateTotalAmount(List<FoodItemsDTO> foodItems) {
        return foodItems.stream()
                .map(item -> BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    } 
    
}

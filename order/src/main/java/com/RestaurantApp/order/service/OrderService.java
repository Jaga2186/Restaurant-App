package com.RestaurantApp.order.service;

import com.RestaurantApp.order.dto.OrderDTO;
import com.RestaurantApp.order.dto.OrderDTOFromFE;
import com.RestaurantApp.order.dto.UserDTO;
import com.RestaurantApp.order.entity.Order;
import com.RestaurantApp.order.mapper.OrderMapper;
import com.RestaurantApp.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private RestTemplate restTemplate;

    public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails) {
        Integer newOrderId = sequenceGenerator.generateNextSequence();
        UserDTO userDTO = fetchUserDetailsFromUserDTO(orderDetails.getUserId());
        Order orderToBeSaved = new Order(newOrderId,orderDetails.getFoodItemsDTOS(), orderDetails.getRestaurantDTO(), userDTO);
        orderRepo.save(orderToBeSaved);
        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);
    }

    private UserDTO fetchUserDetailsFromUserDTO(Integer userId) {
        return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/" + userId, UserDTO.class);
    }
}

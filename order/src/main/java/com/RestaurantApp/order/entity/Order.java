package com.RestaurantApp.order.entity;

import com.RestaurantApp.order.dto.FoodItemsDTO;
import com.RestaurantApp.order.dto.RestaurantDTO;
import com.RestaurantApp.order.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("order")
@CompoundIndex(def = "{'userid' : 1, 'createdAt' : -1")
public class Order {
    @Id
    private Integer orderId;

    @NotEmpty(message = "Food items cannot be empty")
    private List<@Valid FoodItemsDTO> foodItems;

    @NotNull(message = "Restaurant information is required")
    @Valid
    private RestaurantDTO restaurant;

    @NotNull(message = "user information is required")
    @Valid
    private UserDTO user;

    // Bussiness logic fields
    @NotNull(message = "Total amount is required")
    private BigDecimal totalAmount;

    @NotNull(message = "Order status is required")
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @NotNull(message = "Order type is required")
    private OrderType orderType;

    // Timestamps and versioning for optimistic locking
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    // Additional fields for future enhancements
    private String specialInstructions;
    private LocalDateTime estimatedDeliveryTime;
    private LocalDateTime actualDeliveryTime;
    private String paymentMethod;
    private PaymentStatus paymentStatus;
    private Address deliveryAddress;
    private LocalDateTime requestedDeliveryTime;
    private String contactPhoneNumber;

    // Indexes for optimizing prefromance
    @Indexed
    private Integer userId;

    @Indexed
    private Integer restaurantId;
}





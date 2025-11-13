package com.RestaurantApp.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDTO {
    private Integer orderId;
    private List<FoodItemsDTO> foodItemsDTOS;
    private RestaurantDTO restaurantDTO;
    private UserDTO userDTO;
}

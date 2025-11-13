package com.RestaurantApp.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTOFromFE {
    private List<FoodItemsDTO> foodItemsDTOS;
    private Integer userId;
    private RestaurantDTO restaurantDTO;
}

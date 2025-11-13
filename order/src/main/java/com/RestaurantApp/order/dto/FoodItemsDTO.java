package com.RestaurantApp.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemsDTO {
    private Integer id;
    private String itemName;
    private String itemDescription;
    private boolean isVeg;
    private Double price;
    private Integer restaurantId;
    private Integer quantity;
}

package com.RestaurantApp.foodcatalogue.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDTO {
    private Integer id;
    private String itemName;
    private String itemDescription;
    private boolean veg;
    private Double price;
    private Integer restaurantId;
    private Integer quantity;
}

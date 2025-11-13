package com.RestaurantApp.foodcatalogue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String itemName;
    private String itemDescription;
    private boolean veg;
    private Double price;
    private Integer restaurantId;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer quantity;
}

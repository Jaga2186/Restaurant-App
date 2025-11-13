package com.RestaurantApp.foodcatalogue.controller;

import com.RestaurantApp.foodcatalogue.dto.FoodCataloguePage;
import com.RestaurantApp.foodcatalogue.dto.FoodItemDTO;
import com.RestaurantApp.foodcatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
public class FoodCatalogueController {
    @Autowired
    private FoodCatalogueService foodCatalogueService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO savedFoodItem = foodCatalogueService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(savedFoodItem, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchRestaurantAndFoodItemById(@PathVariable Integer restaurantId) {
        FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchRestaurantAndFoodItemById(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }
}

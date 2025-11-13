package com.RestaurantApp.restaurantListing.controller;

import com.RestaurantApp.restaurantListing.dto.RestaurantDTO;
import com.RestaurantApp.restaurantListing.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/findAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> findAllRestaurants() {
        List<RestaurantDTO> allRestaurants = restaurantService.findAll();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO restaurantAdded = restaurantService.addRestaurant(restaurantDTO);
        return new ResponseEntity<>(restaurantAdded, HttpStatus.CREATED);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<RestaurantDTO> fetchRestaurantById(@PathVariable Integer id) {
        return restaurantService.fetchRestaurantById(id);
    }
}

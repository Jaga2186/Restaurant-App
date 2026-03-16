package com.RestaurantApp.restaurantListing.controller;

import com.RestaurantApp.restaurantListing.dto.RestaurantDTO;
import com.RestaurantApp.restaurantListing.service.RestaurantService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/findAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> findAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.createRestaurant(restaurantDTO);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<RestaurantDTO> fetchRestaurantById(@PathVariable Integer id) {
        return restaurantService.getRestaurantById(id);
    }

    @PutMapping("/updateRestaurant/{id}")
    public ResponseEntity<RestaurantDTO> updaRestaurant (
            @PathVariable Integer id, 
            @RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.updateRestaurant(id, restaurantDTO);
    }

    @DeleteMapping("/deleteRestaurant/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Integer id) {
        return restaurantService.deleteRestaurant(id);
    }
}

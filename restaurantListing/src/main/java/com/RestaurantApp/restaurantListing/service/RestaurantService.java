package com.RestaurantApp.restaurantListing.service;

import com.RestaurantApp.restaurantListing.Mapper.RestaurantMapper;
import com.RestaurantApp.restaurantListing.dto.RestaurantDTO;
import com.RestaurantApp.restaurantListing.entity.Restaurant;
import com.RestaurantApp.restaurantListing.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    public List<RestaurantDTO> findAll() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants.stream().map(RestaurantMapper.INSTANCE::mapRestauranttoRestaurantDTO).toList();
    }


    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant savedRestaurant = restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOtoRestaurant(restaurantDTO));
        return RestaurantMapper.INSTANCE.mapRestauranttoRestaurantDTO(savedRestaurant);
    }

    public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {
        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
        return restaurant.map(value -> new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestauranttoRestaurantDTO(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}

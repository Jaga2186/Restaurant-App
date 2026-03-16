package com.RestaurantApp.restaurantListing.service;

import com.RestaurantApp.restaurantListing.Mapper.RestaurantMapper;
import com.RestaurantApp.restaurantListing.dto.RestaurantDTO;
import com.RestaurantApp.restaurantListing.entity.Restaurant;
import com.RestaurantApp.restaurantListing.exception.RestaurantNotFoundException;
import com.RestaurantApp.restaurantListing.repo.RestaurantRepo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantService.class);
    private final RestaurantRepo restaurantRepo;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantRepo restaurantRepo, RestaurantMapper restaurantMapper) {
        this.restaurantRepo = restaurantRepo;
        this.restaurantMapper = restaurantMapper;
    }

    @Transactional
    public ResponseEntity<RestaurantDTO> createRestaurant(RestaurantDTO restaurantDTO) {
        logger.info("Creating new restaurant with name: {}", restaurantDTO.getName());
        Restaurant restaurant = restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO);
        Restaurant savedRestaurant = restaurantRepo.save(restaurant);
        logger.info("Restaurant created successfully with id: {}", savedRestaurant.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restaurantMapper.mapRestaurantToRestaurantDTO(savedRestaurant));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<RestaurantDTO> getRestaurantById(Integer id) {
        logger.info("Fetching restaurant with id: {}", id);
        Restaurant restaurant = restaurantRepo.findById(id)
                    .orElseThrow(() -> new RestaurantNotFoundException(id));
        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(restaurant));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        logger.info("Fetching all restaurants");
        List<Restaurant> restaurants = restaurantRepo.findAll();
        List<RestaurantDTO> restaurantDTOs = restaurants.stream()
                .map(restaurantMapper::mapRestaurantToRestaurantDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurantDTOs);
    }

    @Transactional
    public ResponseEntity<RestaurantDTO> updateRestaurant(Integer id, RestaurantDTO restaurantDTO) {
        logger.info("Updating restaurant with id: {}", id);
        Restaurant existingRestaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        
        existingRestaurant.setName(restaurantDTO.getName());
        existingRestaurant.setAddress(restaurantDTO.getAddress());
        existingRestaurant.setPhone(restaurantDTO.getPhone());
        existingRestaurant.setDescription(restaurantDTO.getDescription());
        existingRestaurant.setEmail(restaurantDTO.getEmail());
        existingRestaurant.setRating(restaurantDTO.getRating());
        existingRestaurant.setActive(restaurantDTO.getActive());
        
        Restaurant updatedRestaurant = restaurantRepo.save(existingRestaurant);
        logger.info("Restaurant with id: {} updated successfully", id);
        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(updatedRestaurant));
    }

    @Transactional
    public ResponseEntity<?> deleteRestaurant(Integer id) {
        logger.info("Deleting restaurant with id: {}", id);
        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurantRepo.deleteById(id);
        logger.info("Restaurant with id: {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }
}

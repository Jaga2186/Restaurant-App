package com.RestaurantApp.restaurantListing.repo;

import com.RestaurantApp.restaurantListing.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findByNameContainingIgnoreCase(String name);
    List<Restaurant> findByActiveTrue();
    List<Restaurant> findByCity(String city);
    List<Restaurant> findByRatingGreaterThanEqual(Double rating);
}

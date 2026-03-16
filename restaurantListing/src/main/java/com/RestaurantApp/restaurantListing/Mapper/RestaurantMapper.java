package com.RestaurantApp.restaurantListing.Mapper;

import com.RestaurantApp.restaurantListing.dto.RestaurantDTO;
import com.RestaurantApp.restaurantListing.entity.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);
    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);
}

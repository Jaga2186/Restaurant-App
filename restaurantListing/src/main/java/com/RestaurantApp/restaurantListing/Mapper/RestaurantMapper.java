package com.RestaurantApp.restaurantListing.Mapper;

import com.RestaurantApp.restaurantListing.dto.RestaurantDTO;
import com.RestaurantApp.restaurantListing.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant mapRestaurantDTOtoRestaurant(RestaurantDTO restaurantDTO);
    RestaurantDTO mapRestauranttoRestaurantDTO(Restaurant restaurant);

}

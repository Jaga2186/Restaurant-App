package com.RestaurantApp.foodcatalogue.mapper;

import com.RestaurantApp.foodcatalogue.dto.FoodItemDTO;
import com.RestaurantApp.foodcatalogue.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {
    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItem mapFoodItemDTOToFoodItem(FoodItemDTO foodItemDTO);
    FoodItemDTO mapFoodItemToFoodItemDTO(FoodItem foodItem);
}

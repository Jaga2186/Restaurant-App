package com.RestaurantApp.foodcatalogue.service;

import com.RestaurantApp.foodcatalogue.dto.FoodCataloguePage;
import com.RestaurantApp.foodcatalogue.dto.FoodItemDTO;
import com.RestaurantApp.foodcatalogue.dto.Restaurant;
import com.RestaurantApp.foodcatalogue.entity.FoodItem;
import com.RestaurantApp.foodcatalogue.mapper.FoodItemMapper;
import com.RestaurantApp.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {
    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    private RestTemplate restTemplate;

//    this method is to save an entity into the dataBase (note that we are saving the entity not the dto,
//    we have saved an entity and returning the dto)
    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem savedFoodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(savedFoodItem);
    }

    public FoodCataloguePage fetchRestaurantAndFoodItemById(Integer id) {
        List<FoodItem> foodItemList =  fetchFoodItemList(id);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMicroService(id);
        return createFoodCataloguePage(restaurant, foodItemList);
    }

    private FoodCataloguePage createFoodCataloguePage(Restaurant restaurant, List<FoodItem> foodItemList) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMicroService(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/" + restaurantId, Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}

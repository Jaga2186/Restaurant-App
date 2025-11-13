package com.RestaurantApp.order.entity;

import com.RestaurantApp.order.dto.FoodItemsDTO;
import com.RestaurantApp.order.dto.RestaurantDTO;
import com.RestaurantApp.order.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("order")
public class Order {
    @Id
    private Integer orderId;
    private List<FoodItemsDTO> foodItemsDTOS;
    private RestaurantDTO restaurantDTO;
    private UserDTO userDTO;
}





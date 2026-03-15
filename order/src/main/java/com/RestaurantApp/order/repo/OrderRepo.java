package com.RestaurantApp.order.repo;

import com.RestaurantApp.order.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends MongoRepository<Order, Integer> {
    List<Order> findByUserId(Integer userId);
}

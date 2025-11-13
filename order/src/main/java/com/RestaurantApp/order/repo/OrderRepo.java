package com.RestaurantApp.order.repo;

import com.RestaurantApp.order.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface OrderRepo extends MongoRepository<Order, Integer> {
}

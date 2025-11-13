package com.RestaurantApp.order.controller;

import com.RestaurantApp.order.dto.OrderDTO;
import com.RestaurantApp.order.dto.OrderDTOFromFE;
import com.RestaurantApp.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTOFromFE orderDetails) {
        OrderDTO savedOrder = orderService.saveOrderInDb(orderDetails);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
}

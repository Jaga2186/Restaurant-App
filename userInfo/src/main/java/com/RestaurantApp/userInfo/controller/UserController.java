package com.RestaurantApp.userInfo.controller;

import com.RestaurantApp.userInfo.dto.UserDTO;
import com.RestaurantApp.userInfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.saveUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/fetchUserById/{id}")
    public ResponseEntity<UserDTO> fetchUserById(@PathVariable Integer id) {
        return userService.fetchUserById(id);
    }
}

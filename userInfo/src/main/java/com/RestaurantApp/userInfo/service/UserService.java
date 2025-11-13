package com.RestaurantApp.userInfo.service;

import com.RestaurantApp.userInfo.dto.UserDTO;
import com.RestaurantApp.userInfo.entity.User;
import com.RestaurantApp.userInfo.mapper.UserMapper;
import com.RestaurantApp.userInfo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserDTO saveUser(UserDTO userDTO) {
        User savedUser = userRepo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
        return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);
    }

    public ResponseEntity<UserDTO> fetchUserById(Integer id) {
        Optional<User> fetchedUser = userRepo.findById(id);
        return fetchedUser.map(user -> new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(user), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}

package com.RestaurantApp.userInfo.mapper;

import com.RestaurantApp.userInfo.dto.UserDTO;
import com.RestaurantApp.userInfo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserDTOToUser(UserDTO userDTO);
    UserDTO mapUserToUserDTO(User user);

}

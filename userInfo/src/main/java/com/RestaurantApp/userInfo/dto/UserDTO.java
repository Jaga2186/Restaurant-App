package com.RestaurantApp.userInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String userName;
    private String password;
    private String address;
    private String city;
}

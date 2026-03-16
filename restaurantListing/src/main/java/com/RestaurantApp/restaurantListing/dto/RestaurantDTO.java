package com.RestaurantApp.restaurantListing.dto;

import com.RestaurantApp.restaurantListing.entity.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDTO {

    private Integer id;

    @NotBlank(message = "Restaurant name cannot be blank")
    @Size(max = 100, message = "Restaurant name must be less than or equal to 100 characters")
    private String name;

    private Address address;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;

    private String description;

    @Email(message = "Email should be valid")
    private String email;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    private Double rating;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}

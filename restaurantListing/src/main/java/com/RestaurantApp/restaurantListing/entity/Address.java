package com.RestaurantApp.restaurantListing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    @NotBlank(message = "Street cannot be blank")
    @Column(length = 100)
    private String street;

    @NotBlank(message = "City cannot be blank")
    @Column(length = 50)
    private String city;

    @NotBlank(message = "State cannot be blank")
    @Column(length = 50)
    private String state;

    @Pattern(regexp = "^[0-9]{5}(-[0-9]{4})?$", message = "Invalid ZIP code format")
    private String zipCode;

    @NotBlank(message = "Country cannot be blank")
    @Column(length = 50)
    private String country;
}
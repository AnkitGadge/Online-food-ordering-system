package com.hashedin.foodapp.foodservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDto {
	private Long id;
	private String restaurantName;
	private String city;
	private String pincode;
	private String restaurantEmail;
	private String restaurantStatus;
	private String publicRestoId;
}

package com.hashedin.foodapp.foodservice.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddRestaurantRequest {
	private Long id;
	private String restaurantName;
	private String city;
	private String pincode;
	private String restaurantEmail;
	private String restaurantStatus;
	private String publicRestoId;

}

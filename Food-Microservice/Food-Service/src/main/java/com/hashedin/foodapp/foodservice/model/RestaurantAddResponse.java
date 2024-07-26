package com.hashedin.foodapp.foodservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantAddResponse {
	private Integer id;
	private String restaurantName;
	private String city;
	private String pincode;
	private String restaurantEmail;
	private String restaurantStatus;
	private String publicRestoId;

}

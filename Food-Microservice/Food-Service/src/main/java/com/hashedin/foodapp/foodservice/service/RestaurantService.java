package com.hashedin.foodapp.foodservice.service;

import com.hashedin.foodapp.foodservice.dtos.RestaurantDto;
import com.hashedin.foodapp.foodservice.entity.Restaurant;

public interface RestaurantService {
	
	RestaurantDto addNewRestaurant(RestaurantDto restaurantDto);
	
	//RestaurantDto findByRestaurantId (Integer restaurantId);
	
	Restaurant findByRestaurantId (Long restaurantId);
	
	Restaurant findByPublicRestoId(String restaurantId);

}

package com.hashedin.foodapp.foodservice.service;

import com.hashedin.foodapp.foodservice.dtos.FoodItemsDto;
import com.hashedin.foodapp.foodservice.entity.FoodItems;

public interface FoodItemsService {
	
	FoodItemsDto addNewFood (FoodItemsDto foodItemsDto);
	
	FoodItems getFoodById (Long foodId);
	
	FoodItems findByPublicFoodId(String fId);

}

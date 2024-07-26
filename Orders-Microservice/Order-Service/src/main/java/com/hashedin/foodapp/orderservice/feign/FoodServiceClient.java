package com.hashedin.foodapp.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "food-microservice")
public interface FoodServiceClient {
	
	@GetMapping("/food/fooditem/{foodId}")
	public FoodResponseModelFeign getFoodById(@PathVariable String foodId);
	
}

package com.hashedin.foodapp.foodservice.dtos;

import java.math.BigDecimal;

import com.hashedin.foodapp.foodservice.entity.Category;
import com.hashedin.foodapp.foodservice.entity.Restaurant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodItemsDto {
	private Long id;
	private String foodName;
	private String description;
	private BigDecimal price;
	private String status;
	private Long restaurantId;
	private Long categoryId;
	private String publicFoodId;


}

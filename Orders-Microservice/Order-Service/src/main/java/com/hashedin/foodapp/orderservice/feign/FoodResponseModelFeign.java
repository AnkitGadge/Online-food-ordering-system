package com.hashedin.foodapp.orderservice.feign;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodResponseModelFeign {
	private Long id;
	private String foodName;
	private String description;
	private BigDecimal price;
	private String status;
	private Long restaurantId;
	private Long categoryId;
	private String publicFoodId;

}

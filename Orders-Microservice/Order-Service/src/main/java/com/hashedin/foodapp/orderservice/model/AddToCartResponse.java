package com.hashedin.foodapp.orderservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartResponse {
	private Long id;
	private String userId;
	private String foodId;
	private Integer quantity;
	private String publicCartId;

}

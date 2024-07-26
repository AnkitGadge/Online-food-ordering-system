package com.hashedin.foodapp.orderservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
	private Long id;
	private String userId;
	private String foodId;
	private Integer quantity;
	private String publicCartId;

}

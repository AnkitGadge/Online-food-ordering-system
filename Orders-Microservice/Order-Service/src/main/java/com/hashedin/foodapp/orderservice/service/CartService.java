package com.hashedin.foodapp.orderservice.service;

import com.hashedin.foodapp.orderservice.dtos.CartDto;

public interface CartService {
	
	CartDto addToCart (CartDto cartDto, String userId, String foodId);

}

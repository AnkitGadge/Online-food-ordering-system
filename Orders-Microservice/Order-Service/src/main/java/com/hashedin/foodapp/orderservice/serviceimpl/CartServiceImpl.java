package com.hashedin.foodapp.orderservice.serviceimpl;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashedin.foodapp.orderservice.dtos.CartDto;
import com.hashedin.foodapp.orderservice.entity.Cart;
import com.hashedin.foodapp.orderservice.exceptions.ItemsAlreadyPresentException;
import com.hashedin.foodapp.orderservice.feign.FoodResponseModelFeign;
import com.hashedin.foodapp.orderservice.feign.FoodServiceClient;
import com.hashedin.foodapp.orderservice.feign.UserResponseModelFeign;
import com.hashedin.foodapp.orderservice.feign.UserServiceClient;
import com.hashedin.foodapp.orderservice.repository.CartRepository;
import com.hashedin.foodapp.orderservice.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserServiceClient userServiceClient;
	
	@Autowired
	FoodServiceClient foodServiceClient;

	@Override
	public CartDto addToCart(CartDto cartDto, String userId, String foodId) {
			
				ModelMapper modelMapper = new ModelMapper();
				Cart cart = modelMapper.map(cartDto, Cart.class);
				//cart.setFoodId(4L);
				UserResponseModelFeign userResponseModelFeign = this.userServiceClient.getUserById(userId);
				cart.setUserId(userResponseModelFeign.getPublicUserId());
				
				FoodResponseModelFeign foodResponseModelFeign = this.foodServiceClient.getFoodById(foodId);
				cart.setFoodId(foodResponseModelFeign.getPublicFoodId());
				cart.setPublicCartId(UUID.randomUUID().toString());
				//cart.setUserId(5L);
				this.cartRepository.save(cart);
				CartDto result = modelMapper.map(cart, CartDto.class);
				return result;
	}

}

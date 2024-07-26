package com.hashedin.foodapp.orderservice.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.foodapp.orderservice.dtos.CartDto;
import com.hashedin.foodapp.orderservice.model.AddToCartResponse;
import com.hashedin.foodapp.orderservice.model.CartRequestDto;
import com.hashedin.foodapp.orderservice.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping(path = "/addtocart/{userId}/{foodId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<AddToCartResponse> addToCart(@RequestBody CartRequestDto cartRequestDto, 
		@PathVariable String userId, @PathVariable String foodId){
		ModelMapper modelMapper = new ModelMapper();
		CartDto cartDto = modelMapper.map(cartRequestDto, CartDto.class);
		CartDto dtoResponse = this.cartService.addToCart(cartDto, userId, foodId);
		AddToCartResponse response = modelMapper.map(dtoResponse, AddToCartResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	
	}

}

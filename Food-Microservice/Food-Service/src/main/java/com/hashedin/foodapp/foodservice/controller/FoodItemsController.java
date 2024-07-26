package com.hashedin.foodapp.foodservice.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.foodapp.foodservice.dtos.FoodItemsDto;
import com.hashedin.foodapp.foodservice.entity.FoodItems;
import com.hashedin.foodapp.foodservice.entity.Restaurant;
import com.hashedin.foodapp.foodservice.model.AddFoodItemsRequest;
import com.hashedin.foodapp.foodservice.model.FoodItemsAddResponse;
import com.hashedin.foodapp.foodservice.model.RestaurantAddResponse;
import com.hashedin.foodapp.foodservice.service.FoodItemsService;

@RestController
@RequestMapping("/food/fooditem")
public class FoodItemsController {
	
	@Autowired
	private FoodItemsService foodItemsService;
	
	@PostMapping(path = "/addfooditem", consumes = {MediaType.APPLICATION_JSON_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<FoodItemsAddResponse> addFoodItems(@RequestBody AddFoodItemsRequest addFoodItemsRequest){
		ModelMapper modelMapper = new ModelMapper();
		FoodItemsDto foodItemsDto = modelMapper.map(addFoodItemsRequest, FoodItemsDto.class);
		FoodItemsDto dtoResponse = this.foodItemsService.addNewFood(foodItemsDto);
		FoodItemsAddResponse result = modelMapper.map(dtoResponse, FoodItemsAddResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	
	}
	
//	@GetMapping(path = "/{foodId}", produces = {MediaType.APPLICATION_JSON_VALUE})
//	public ResponseEntity<FoodItemsAddResponse> getFoodById(@PathVariable Long foodId){
//		ModelMapper modelMapper = new ModelMapper();
//		FoodItems foodItems = this.foodItemsService.getFoodById(foodId);
//		FoodItemsAddResponse result = modelMapper.map(foodItems, FoodItemsAddResponse.class);
//		return ResponseEntity.status(HttpStatus.OK).body(result);
//	}
	
	@GetMapping(path = "/{foodId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<FoodItemsAddResponse> getFoodById(@PathVariable String foodId){
		ModelMapper modelMapper = new ModelMapper();
		FoodItems foodItems = this.foodItemsService.findByPublicFoodId(foodId);
		FoodItemsAddResponse result = modelMapper.map(foodItems, FoodItemsAddResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}

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

import com.hashedin.foodapp.foodservice.dtos.RestaurantDto;
import com.hashedin.foodapp.foodservice.entity.Restaurant;
import com.hashedin.foodapp.foodservice.model.AddRestaurantRequest;
import com.hashedin.foodapp.foodservice.model.RestaurantAddResponse;
import com.hashedin.foodapp.foodservice.service.RestaurantService;

@RestController
@RequestMapping("/food/restaurant")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@PostMapping(path = "/addrestaurant", consumes = {MediaType.APPLICATION_JSON_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<RestaurantAddResponse> createRestaurant(@RequestBody AddRestaurantRequest addRestaurantRequest){
		ModelMapper modelMapper = new ModelMapper();
		RestaurantDto restaurantDto = modelMapper.map(addRestaurantRequest, RestaurantDto.class);
		RestaurantDto dtoResponse = this.restaurantService.addNewRestaurant(restaurantDto);
		RestaurantAddResponse result = modelMapper.map(dtoResponse, RestaurantAddResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	
	}
	
//	@GetMapping(path = "/resto/{restaurantId}", produces = {MediaType.APPLICATION_JSON_VALUE})
//	public ResponseEntity<RestaurantAddResponse> getRestaurantById(@PathVariable Long restaurantId){
//		ModelMapper modelMapper = new ModelMapper();
//		Restaurant restaurantDto = this.restaurantService.findByRestaurantId(restaurantId);
//		RestaurantAddResponse result = modelMapper.map(restaurantDto, RestaurantAddResponse.class);
//		return ResponseEntity.status(HttpStatus.OK).body(result);
//	}
	
	@GetMapping(path = "/resto/{restaurantId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<RestaurantAddResponse> getRestaurantById(@PathVariable String restaurantId){
		ModelMapper modelMapper = new ModelMapper();
		Restaurant restaurantDto = this.restaurantService.findByPublicRestoId(restaurantId);
		RestaurantAddResponse result = modelMapper.map(restaurantDto, RestaurantAddResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}

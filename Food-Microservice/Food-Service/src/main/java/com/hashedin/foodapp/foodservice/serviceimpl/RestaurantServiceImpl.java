package com.hashedin.foodapp.foodservice.serviceimpl;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashedin.foodapp.foodservice.constants.RestaurantConstants;
import com.hashedin.foodapp.foodservice.dtos.RestaurantDto;
import com.hashedin.foodapp.foodservice.entity.Restaurant;
import com.hashedin.foodapp.foodservice.exceptions.NoRestaurantFoundException;
import com.hashedin.foodapp.foodservice.repository.RestaurantRepository;
import com.hashedin.foodapp.foodservice.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	RestaurantRepository restaurantRepository;

	@Override
	public RestaurantDto addNewRestaurant(RestaurantDto restaurantDto) {
		ModelMapper modelMapper = new ModelMapper();
		restaurantDto.setRestaurantStatus(RestaurantConstants.RESTAURANT_OPEN);
		Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
		restaurant.setPublicRestoId(UUID.randomUUID().toString());
		this.restaurantRepository.save(restaurant);
		RestaurantDto result = modelMapper.map(restaurant, RestaurantDto.class);
		return result;
	}
	
	@Override
	public Restaurant findByRestaurantId(Long restaurantId) {
		//ModelMapper modelMapper = new ModelMapper();
		Optional<Restaurant> restaurant = this.restaurantRepository.findById(restaurantId);
		if(!restaurant.isPresent()) {
			throw new NoRestaurantFoundException("No Restaurant Found");
		}
		//RestaurantDto restaurantDto = modelMapper.map(restaurant, RestaurantDto.class);
		return restaurant.get();
	}

	@Override
	public Restaurant findByPublicRestoId(String restaurantId) {
		Restaurant restaurant = this.restaurantRepository.findByPublicRestoId(restaurantId);
		if (restaurant == null) {
			throw new NoRestaurantFoundException("No Restaurant Found");
		}
		return restaurant;
	}

//	@Override
//	public RestaurantDto findByRestaurantId(Integer restaurantId) {
//		ModelMapper modelMapper = new ModelMapper();
//		Restaurant restaurant = this.restaurantRepository.findById(restaurantId);
//		if(restaurant == null) {
//			throw new NoRestaurantFoundException("No Restaurant Found");
//		}
//		RestaurantDto restaurantDto = modelMapper.map(restaurant, RestaurantDto.class);
//		return restaurantDto;
//	}

}
package com.hashedin.foodapp.foodservice.serviceimpl;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashedin.foodapp.foodservice.constants.FoodItemsConstants;
import com.hashedin.foodapp.foodservice.dtos.FoodItemsDto;
import com.hashedin.foodapp.foodservice.dtos.RestaurantDto;
import com.hashedin.foodapp.foodservice.entity.Category;
import com.hashedin.foodapp.foodservice.entity.FoodItems;
import com.hashedin.foodapp.foodservice.entity.Restaurant;
import com.hashedin.foodapp.foodservice.exceptions.NoFoodFoundByIdException;
import com.hashedin.foodapp.foodservice.exceptions.NoRestaurantFoundException;
import com.hashedin.foodapp.foodservice.repository.FoodItemsRepository;
import com.hashedin.foodapp.foodservice.service.CategoryService;
import com.hashedin.foodapp.foodservice.service.FoodItemsService;
import com.hashedin.foodapp.foodservice.service.RestaurantService;

@Service
public class FoodItemsServiceImpl implements FoodItemsService {
	
	@Autowired
	private FoodItemsRepository foodItemsRepository;
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	CategoryService categoryService;

	@Override
	public FoodItemsDto addNewFood(FoodItemsDto foodItemsDto) {
		ModelMapper modelMapper = new ModelMapper();
		FoodItems foodItems = modelMapper.map(foodItemsDto, FoodItems.class);
		foodItemsDto.setStatus(FoodItemsConstants.FOOD_ACTIVE);
		
		Restaurant restaurant = this.restaurantService.findByRestaurantId(foodItemsDto.getRestaurantId());
		foodItems.setRestaurantId(restaurant);
		
		
		Category category = this.categoryService.findByCategoryId(foodItemsDto.getCategoryId());
		foodItems.setCategoryId(category);
		
		foodItems.setPublicFoodId(UUID.randomUUID().toString());
		this.foodItemsRepository.save(foodItems);
		FoodItemsDto result = modelMapper.map(foodItems, FoodItemsDto.class);
		return result;
	}

	@Override
	public FoodItems getFoodById(Long foodId) {
		Optional<FoodItems> foodItems = this.foodItemsRepository.findById(foodId);
		if(!foodItems.isPresent()) {
			throw new NoRestaurantFoundException("No Restaurant Found");
		}
		//RestaurantDto restaurantDto = modelMapper.map(restaurant, RestaurantDto.class);
		return foodItems.get();
		
	}

	@Override
	public FoodItems findByPublicFoodId(String fId) {
		FoodItems foodItems = this.foodItemsRepository.findByPublicFoodId(fId);
		if (foodItems == null) {
			throw new NoFoodFoundByIdException("No Foot Item Found!");
		}
		return foodItems;
	}

	

}

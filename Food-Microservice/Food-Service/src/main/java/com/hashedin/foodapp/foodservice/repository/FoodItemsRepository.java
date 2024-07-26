package com.hashedin.foodapp.foodservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.foodapp.foodservice.entity.FoodItems;

public interface FoodItemsRepository extends JpaRepository<FoodItems, Long> {
	
	Optional<FoodItems> findById(Long foodId);
	
	FoodItems findByPublicFoodId(String fId);

}

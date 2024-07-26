package com.hashedin.foodapp.foodservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.foodapp.foodservice.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	Optional<Restaurant> findById(Long restaurantId);
	
	Restaurant findByPublicRestoId(String restaurantId);

}

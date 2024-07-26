package com.hashedin.foodapp.foodservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.foodapp.foodservice.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Optional<Category> findById (Long categoryId);
	
	Category findByPublicCategoryId(String cId);

}

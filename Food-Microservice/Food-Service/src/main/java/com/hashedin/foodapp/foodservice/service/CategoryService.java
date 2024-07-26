package com.hashedin.foodapp.foodservice.service;

import com.hashedin.foodapp.foodservice.dtos.CategoryDto;
import com.hashedin.foodapp.foodservice.entity.Category;

public interface CategoryService {
	
	CategoryDto addCategory(CategoryDto categoryDto);
	
	Category findByCategoryId(Long categoryId);
	
	Category findByPublicCategoryId(String cId);

}

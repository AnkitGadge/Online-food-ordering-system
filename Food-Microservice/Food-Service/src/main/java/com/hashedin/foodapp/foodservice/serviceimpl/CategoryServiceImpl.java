package com.hashedin.foodapp.foodservice.serviceimpl;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashedin.foodapp.foodservice.constants.CategoryConstants;
import com.hashedin.foodapp.foodservice.dtos.CategoryDto;
import com.hashedin.foodapp.foodservice.dtos.RestaurantDto;
import com.hashedin.foodapp.foodservice.entity.Category;
import com.hashedin.foodapp.foodservice.entity.Restaurant;
import com.hashedin.foodapp.foodservice.exceptions.NoCategoryFoundException;
import com.hashedin.foodapp.foodservice.exceptions.NoRestaurantFoundException;
import com.hashedin.foodapp.foodservice.repository.CategoryRepository;
import com.hashedin.foodapp.foodservice.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		ModelMapper modelMapper = new ModelMapper();
		categoryDto.setStatus(CategoryConstants.CATEGORY_ACTIVATED);
		Category category = modelMapper.map(categoryDto, Category.class);
		category.setPublicCategoryId(UUID.randomUUID().toString());
		this.categoryRepository.save(category);
		CategoryDto result = modelMapper.map(category, CategoryDto.class);
		return result;
	}

	@Override
	public Category findByCategoryId(Long categoryId) {
		//ModelMapper modelMapper = new ModelMapper();
		Optional<Category> category = this.categoryRepository.findById(categoryId);
		if(!category.isPresent()) {
			throw new NoCategoryFoundException("No Category Found");
		}
		//CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return category.get();
	}

	@Override
	public Category findByPublicCategoryId(String cId) {
		//ModelMapper modelMapper = new ModelMapper();
		 Category category = this.categoryRepository.findByPublicCategoryId(cId);
		if (category == null) {
			throw new NoCategoryFoundException("Category not found");
		}
		//Category category = modelMapper.map(category, Category.class);
		return category;
	}

}

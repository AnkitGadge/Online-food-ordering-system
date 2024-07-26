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

import com.hashedin.foodapp.foodservice.dtos.CategoryDto;
import com.hashedin.foodapp.foodservice.dtos.RestaurantDto;
import com.hashedin.foodapp.foodservice.entity.Category;
import com.hashedin.foodapp.foodservice.model.CategoryAddRequest;
import com.hashedin.foodapp.foodservice.model.CategoryAddResponse;
import com.hashedin.foodapp.foodservice.model.RestaurantAddResponse;
import com.hashedin.foodapp.foodservice.service.CategoryService;

@RestController
@RequestMapping("/food/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping(path = "/addcategory", consumes = {MediaType.APPLICATION_JSON_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CategoryAddResponse> createCategory(@RequestBody CategoryAddRequest categoryAddRequest){
		ModelMapper modelMapper = new ModelMapper();
		CategoryDto categoryDto = modelMapper.map(categoryAddRequest, CategoryDto.class);
		CategoryDto result = this.categoryService.addCategory(categoryDto);
		CategoryAddResponse response = modelMapper.map(result, CategoryAddResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	
	}
	
//	@GetMapping(path = "/{categoryId}", produces = {MediaType.APPLICATION_JSON_VALUE})
//	public ResponseEntity<CategoryAddResponse> getCategoryById(@PathVariable Long categoryId){
//		ModelMapper modelMapper = new ModelMapper();
//		Category categoryDto = this.categoryService.findByCategoryId(categoryId);
//		CategoryAddResponse result = modelMapper.map(categoryDto, CategoryAddResponse.class);
//		return ResponseEntity.status(HttpStatus.OK).body(result);
//	}
	
	@GetMapping(path = "/{categoryId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CategoryAddResponse> getCategoryByPublicId(@PathVariable String categoryId){
		ModelMapper modelMapper = new ModelMapper();
		Category categoryDto = this.categoryService.findByPublicCategoryId(categoryId);
		CategoryAddResponse result = modelMapper.map(categoryDto, CategoryAddResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	

}

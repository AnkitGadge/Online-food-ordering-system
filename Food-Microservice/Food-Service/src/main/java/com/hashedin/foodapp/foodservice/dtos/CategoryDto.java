package com.hashedin.foodapp.foodservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
	private Long id;
	private String categoryName;
	private String description;
	private String status;
	private String publicCategoryId;
}

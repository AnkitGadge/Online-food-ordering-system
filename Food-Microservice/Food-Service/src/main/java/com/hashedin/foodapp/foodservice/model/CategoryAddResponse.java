package com.hashedin.foodapp.foodservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryAddResponse {
	private Long id;
	private String categoryName;
	private String description;
	private String status;
	private String publicCategoryId;

}

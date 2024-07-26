package com.hashedin.foodapp.foodservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoryAddRequest {
	private Long id;
	private String categoryName;
	private String description;
	private String status;
	private String publicCategoryId;
}

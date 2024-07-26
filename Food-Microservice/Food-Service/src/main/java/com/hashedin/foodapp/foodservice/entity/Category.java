package com.hashedin.foodapp.foodservice.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2002506010787158887L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String categoryName;
	private String description;
	private String status;
	@OneToMany(mappedBy = "categoryId")
	private List<FoodItems> foodItems = new ArrayList<>();
	private String publicCategoryId;

}

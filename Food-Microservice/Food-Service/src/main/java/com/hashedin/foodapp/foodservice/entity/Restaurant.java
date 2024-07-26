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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Restaurant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2002563335215242970L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String restaurantName;
	private String city;
	private String pincode;
	private String restaurantEmail;
	private String restaurantStatus;
	@OneToMany(mappedBy = "restaurantId")
	private List<FoodItems> foodItemsList = new ArrayList<>();
	private String publicRestoId;
	


}

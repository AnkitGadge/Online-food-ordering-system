package com.hashedin.foodapp.orderservice.feign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseModelFeign {
	private Long id;
	private String publicUserId;
	private String firstName;
	private String lastName;
	private String email;

}

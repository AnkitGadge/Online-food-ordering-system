package com.hashedin.foodapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseModel {
	private Long id;
	private String publicUserId;
	private String firstName;
	private String lastName;
	private String email;
}

package com.hashedin.foodapp.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8632449472371476907L;
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String publicUserId;
	private String encryptedPassword;
	private boolean userStatus;
	private String userActivationStatus;

}

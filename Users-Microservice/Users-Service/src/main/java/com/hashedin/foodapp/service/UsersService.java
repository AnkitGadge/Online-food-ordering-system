package com.hashedin.foodapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hashedin.foodapp.dtos.UsersDto;

public interface UsersService extends UserDetailsService {

	UsersDto createUsers(UsersDto usersDto);
	
	// get single user
	UsersDto getOneUser(String userId);
	
	// update user data
	UsersDto updateUserData(Integer userId, UsersDto usersDto);
	
	// delete user
	Boolean deleteUser(String userId);
	
	UsersDto getUserDetailsByEmail(String email);
}

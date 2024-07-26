package com.hashedin.foodapp.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.foodapp.dtos.UsersDto;
import com.hashedin.foodapp.model.CreateUserRequests;
import com.hashedin.foodapp.model.NewUserResponse;
import com.hashedin.foodapp.model.ReturnData;
import com.hashedin.foodapp.model.UpdateUserResponseModel;
import com.hashedin.foodapp.model.UserResponseModel;
import com.hashedin.foodapp.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@PostMapping(path = "/createusers", consumes = {MediaType.APPLICATION_JSON_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<NewUserResponse> createUsers(@RequestBody CreateUserRequests createUserRequests){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UsersDto usersDto = modelMapper.map(createUserRequests, UsersDto.class);
		UsersDto reisteredUser = this.usersService.createUsers(usersDto);
		NewUserResponse newUserResponse = modelMapper.map(reisteredUser, NewUserResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUserResponse);
	}
	
	@GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	//@PreAuthorize("principal == #userId")
	public ResponseEntity<UserResponseModel> getSingleUser(@PathVariable String userId){
		ModelMapper modelMapper = new ModelMapper();
		UsersDto usersDto = this.usersService.getOneUser(userId);
		UserResponseModel userResponseModel = modelMapper.map(usersDto, UserResponseModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
	}
	
	@PutMapping(path = "/updateuser/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UpdateUserResponseModel> updateUserData(@PathVariable Integer userId, @RequestBody UsersDto usersDto){
		UpdateUserResponseModel responseModel = new UpdateUserResponseModel();
		this.usersService.updateUserData(userId, usersDto);
		responseModel.setMessage("User details are updated successfully");
		return ResponseEntity.status(HttpStatus.OK).body(responseModel);
	}
	
	@DeleteMapping(path = "/deleteuser/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("principal == #userId")
	//@PreAuthorize("principal == #userId")
	public ResponseEntity<?> deleteUser(@PathVariable String userId){
		ReturnData returnData = new ReturnData();
		Boolean deleteUser = this.usersService.deleteUser(userId);
		returnData.setStatus(deleteUser);
		returnData.setMessage("User deleted successfully");
		return new ResponseEntity<ReturnData>(returnData, HttpStatus.OK);
	}
	
}

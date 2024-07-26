package com.hashedin.foodapp.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "users-microservice")
public interface UserServiceClient {
	
	@GetMapping("/users/{userId}")
	public UserResponseModelFeign getUserById(@PathVariable String userId);

}

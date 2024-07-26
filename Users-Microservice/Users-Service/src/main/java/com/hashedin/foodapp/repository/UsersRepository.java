package com.hashedin.foodapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.foodapp.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	Users findByEmail(String email);
	Users findByPublicUserId(String userId);
	Optional<Users> findById(Long id);
	
	Users findById(Integer userId);

}

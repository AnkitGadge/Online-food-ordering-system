package com.hashedin.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.foodapp.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	
	Authority findByAuthorityName(String authorityName);

}

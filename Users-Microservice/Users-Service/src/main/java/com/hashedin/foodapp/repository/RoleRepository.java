package com.hashedin.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.foodapp.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

	Roles findByRoleName(String roleName);
}

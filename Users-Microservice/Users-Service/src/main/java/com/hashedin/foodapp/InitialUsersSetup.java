package com.hashedin.foodapp;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.hashedin.foodapp.entity.Authority;
import com.hashedin.foodapp.entity.Roles;
import com.hashedin.foodapp.entity.Users;
import com.hashedin.foodapp.model.RolesEnum;
import com.hashedin.foodapp.repository.AuthorityRepository;
import com.hashedin.foodapp.repository.RoleRepository;
import com.hashedin.foodapp.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Component
public class InitialUsersSetup {
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	RoleRepository rolesRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UsersRepository usersRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Transactional
	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
		this.logger.info("From application ready event...");
		Authority readAuthority = createAuthority("READ");
		Authority writeAuthority = createAuthority("WRITE");
		Authority deleteAuthority = createAuthority("DELETE");

		createRole(RolesEnum.ROLE_USER.name(), Arrays.asList(readAuthority, writeAuthority));
		
		Roles roleAdmin = createRole(RolesEnum.ROLE_ADMIN.name(), Arrays.asList(readAuthority, writeAuthority, deleteAuthority));
		if (roleAdmin == null)
			return;
		
		Users users = new Users();
		users.setPublicUserId(UUID.randomUUID().toString());
		users.setFirstName("Swapnil96");
		users.setLastName("Mane");
		users.setEmail("admin@test.com");
		users.setPassword(this.bCryptPasswordEncoder.encode("1234"));
		users.setRoles(Arrays.asList(roleAdmin));
		
		Users storedAdminUser = this.usersRepository.findByEmail("admin@test.com");
		if (storedAdminUser == null) {
			this.usersRepository.save(users);
		}
	}
	
	@Transactional
	private Authority createAuthority(String authorityName) {
		Authority authorityEntity = this.authorityRepository.findByAuthorityName(authorityName);
		if (authorityEntity == null) {
			authorityEntity = new Authority(authorityName);
			this.authorityRepository.save(authorityEntity);
		}
		return authorityEntity;
	}
	
	@Transactional
	private Roles createRole(String roleName, Collection<Authority> authorities) {
		Roles roleEntity = this.rolesRepository.findByRoleName(roleName);
		if(roleEntity == null) {
			roleEntity = new Roles(roleName, authorities);
			this.rolesRepository.save(roleEntity);
		}
		return roleEntity;
	}
	
	
	
}

	package com.hashedin.foodapp.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hashedin.foodapp.dtos.UsersDto;
import com.hashedin.foodapp.entity.Authority;
import com.hashedin.foodapp.entity.Roles;
import com.hashedin.foodapp.entity.Users;
import com.hashedin.foodapp.exceptions.UserAlreadyPresentException;
import com.hashedin.foodapp.exceptions.UserNotFoundException;
import com.hashedin.foodapp.model.Constants;
import com.hashedin.foodapp.model.RolesEnum;
import com.hashedin.foodapp.repository.AuthorityRepository;
import com.hashedin.foodapp.repository.RoleRepository;
import com.hashedin.foodapp.repository.UsersRepository;
import com.hashedin.foodapp.service.UsersService;

import jakarta.transaction.Transactional;

@Service
public class UsersServiceImpl implements UsersService {
	
	//final String readAuthority;
	
	UsersRepository usersRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usersRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	RoleRepository rolesRepository;
	
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
	
	

	@Override
	public UsersDto createUsers(UsersDto usersDto) {
		
		Users users2 = this.usersRepository.findByEmail(usersDto.getEmail());
			if (users2 == null) {
				
				usersDto.setPublicUserId(UUID.randomUUID().toString());
				usersDto.setPassword(this.bCryptPasswordEncoder.encode(usersDto.getPassword()));
				usersDto.setUserStatus(true);
				usersDto.setUserActivationStatus(Constants.ACTIVATED);
				ModelMapper modelMapper = new ModelMapper();
				modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				Users users = modelMapper.map(usersDto, Users.class);
				
				Authority readAuthority = createAuthority("READ");
				Authority writeAuthority = createAuthority("WRITE");
				Roles roleUser = createRole(RolesEnum.ROLE_USER.name(), Arrays.asList(readAuthority, writeAuthority));
				users.setRoles(Arrays.asList(roleUser));
				this.usersRepository.save(users);
				modelMapper.map(users, UsersDto.class);
				
			} else {
				throw new UserAlreadyPresentException("User is already present pls login");
			}
		
			return usersDto;
	}

	@Override
	public UsersDto getOneUser(String userId) {
		ModelMapper modelMapper = new ModelMapper();
		Users userEntity = this.usersRepository.findByPublicUserId(userId);
		if (userEntity == null) {
			throw new UserNotFoundException("User not found pls register first!!");
		}
		UsersDto usersDto = modelMapper.map(userEntity, UsersDto.class);
		return usersDto;
	}

	@Override
	public UsersDto updateUserData(Integer userId, UsersDto usersDto) {
		Users user = this.usersRepository.findById(userId);
		if (user != null) {
			if (usersDto.getFirstName() != null) {
				user.setFirstName(usersDto.getFirstName());
			}
			if (usersDto.getLastName() != null) {
				user.setLastName(usersDto.getLastName());
			}
			if (usersDto.getEmail() != null) {
				user.setEmail(usersDto.getEmail());
			}
			if (usersDto.getPassword() != null) {
				user.setPassword(usersDto.getPassword());
			}
			
			}
			this.usersRepository.save(user);
		return usersDto;
		
	}

	@Override
	public Boolean deleteUser(String userId) {
		Boolean flag = true;
		Users usersData = this.usersRepository.findByPublicUserId(userId);
		if (usersData != null) {
			usersData.setUserStatus(false);
			usersData.setUserActivationStatus(Constants.DEACTIVATED);
			this.usersRepository.save(usersData);
			flag = false;
		}
		return flag;
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = this.usersRepository.findByEmail(username);
		if (users == null)
			throw new UsernameNotFoundException(username);
		
		//The user class accepts roles and authorities as a collection of granted authority objects.
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		Collection<Roles> roles = users.getRoles();
		roles.forEach((role) -> {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName())); // gives list of roles
			Collection<Authority> authorityEntities = role.getAuthorities();
			authorityEntities.forEach((authorityEntity) -> {
				authorities.add(new SimpleGrantedAuthority(authorityEntity.getAuthorityName()));
			});
		});
		return new User(users.getEmail(), 
				users.getPassword(),
				true, true, true,
				true, authorities);
	}

	@Override
	public UsersDto getUserDetailsByEmail(String email) {
		Users users = this.usersRepository.findByEmail(email);
		if (users == null)
			throw new UsernameNotFoundException(email);
		return new ModelMapper().map(users, UsersDto.class);
	}

}

package com.hashedin.foodapp.entity;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5196992981631394138L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 50)
	private String firstName;
	@Column(nullable = false, length = 50)
	private String lastName;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false, unique = true)
	private String publicUserId;
	@Column(nullable = false)
	private String Password;
	private boolean userStatus;
	private String userActivationStatus;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns =@JoinColumn(name = "users_id", referencedColumnName = "id"),
			inverseJoinColumns =@JoinColumn(name = "roles_id", referencedColumnName = "id") )
	private Collection<Roles> roles;
	
}

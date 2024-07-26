package com.hashedin.foodapp.entity;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.CascadeType;
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
public class Roles implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -446569626509007640L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String roleName;
	@ManyToMany(mappedBy = "roles")
	private Collection<Users> users;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "roles_authorities", joinColumns =@JoinColumn(name = "role_id", referencedColumnName = "id"),
			inverseJoinColumns =@JoinColumn(name = "authorities_id", referencedColumnName = "id") )
	private Collection<Authority> authorities;
	public Roles(String roleName, Collection<Authority> authorities) {
		super();
		this.roleName = roleName;
		this.authorities = authorities;
	}
	
	

}

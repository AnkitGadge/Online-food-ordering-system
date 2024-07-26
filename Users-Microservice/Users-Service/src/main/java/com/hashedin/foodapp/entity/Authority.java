package com.hashedin.foodapp.entity;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -587537472943628242L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String authorityName;
	@ManyToMany(mappedBy = "authorities")
	private Collection<Roles> roles;
	
	public Authority(String authorityName) {
		super();
		this.authorityName = authorityName;
	}
	
	
}

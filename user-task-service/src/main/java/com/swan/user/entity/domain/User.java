package com.swan.user.entity.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // note the community edition does not provide a robust generation value therefore will start with 1
	private Long id;
	private String username;
	private String firstName;
	private String lastName; 
	

}

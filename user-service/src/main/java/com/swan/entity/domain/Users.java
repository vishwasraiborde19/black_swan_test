package com.swan.entity.domain;


import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Users {
	
	private Long Id;
	private String username;
	private String firstName;
	private String lastName; 
	

}

package com.swan.user.entity.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class UserTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // note the community edition does not provide a robust generation value therefore will start with 1
	private Long id;
	
	@NotNull
	private Long userid;
	
	private String name;
	private String description;
	private Date dateTime; 
	private String status;
	

}

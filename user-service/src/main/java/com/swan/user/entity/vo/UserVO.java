package com.swan.user.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserVO {
	
	
	private Long id;
	private String username;
	private String firstName;
	private String lastName; 
	

}

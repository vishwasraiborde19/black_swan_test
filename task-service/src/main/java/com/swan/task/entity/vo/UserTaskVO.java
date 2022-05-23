package com.swan.task.entity.vo;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserTaskVO {
	
	private Long userid;
	private Long id;
	private String name;
	private String description;
	private Date dateTime; 
	private TaskStatus status;
	

}

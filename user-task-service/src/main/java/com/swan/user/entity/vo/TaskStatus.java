package com.swan.user.entity.vo;

public enum TaskStatus {
	
	  
	
	CREATED("CREATED"), ASSIGNED("ASSIGNED"), PENDING("PENDING"), COMPLETED("COMPLETED"),DELETED("DELETED"),UPDATED("UPDATED");

	TaskStatus(String status) {
		this.status =status;
	}
	
	private final String status; 
	
	

}

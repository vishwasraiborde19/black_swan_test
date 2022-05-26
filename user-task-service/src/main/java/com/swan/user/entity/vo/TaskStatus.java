package com.swan.user.entity.vo;

public enum TaskStatus {
  
	
	CREATED("CREATED"), ASSIGNED("ASSIGNED"), PENDING("PENDING"),DELETED("DELETED"),UPDATED("UPDATED"), DONE("DONE");

	TaskStatus(String status) {
		this.status =status;
	}

	private final String status; 
	
	public String getStatus() {
		return status;
	}
	
	

}

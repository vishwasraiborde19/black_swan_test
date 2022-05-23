package com.swan.user.service;

public class ServiceResponse <T> {
	
	T result;
	boolean isSuccessfull ;



	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	} 
	
	public Boolean isSuccessfull() {
		return isSuccessfull;
	}

	public void isSuccessfull(Boolean isSuccessfull) {
		this.isSuccessfull = isSuccessfull;
	}

}

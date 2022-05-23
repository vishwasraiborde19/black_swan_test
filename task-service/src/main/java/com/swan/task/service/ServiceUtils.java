package com.swan.task.service;

import org.springframework.beans.BeanUtils;

import com.swan.task.entity.domain.UserTask;
import com.swan.task.entity.vo.UserTaskVO;


/**
 * class to copy the domain entity to value and value to domain entity
 * */
public class ServiceUtils {
	
	private ServiceUtils() {
	}
	
	public static UserTask getEntity(UserTaskVO valueObject) {
		UserTask user = new UserTask();
		BeanUtils.copyProperties(valueObject, user);
		return user;
	}
	
	public static UserTaskVO getValue(UserTask domainObject) {
		UserTaskVO userVO = new UserTaskVO();
		BeanUtils.copyProperties(domainObject, userVO);
		return userVO;
	}

}

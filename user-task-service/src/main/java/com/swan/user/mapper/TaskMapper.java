package com.swan.user.mapper;

import org.springframework.beans.BeanUtils;

import com.swan.user.entity.domain.UserTask;
import com.swan.user.entity.vo.UserTaskVO;


/**
 * class to copy the domain entity to value and value to domain entity
 * */
public class TaskMapper extends SwanMapper{
	
	private TaskMapper() {
	}
	
	/*
	 *returns a new Entity Object from value object
	 * */
	public static UserTask getEntity(UserTaskVO valueObject) {
		UserTask user = new UserTask();
		BeanUtils.copyProperties(valueObject, user);
		return user;
	}	
	
	/*
	 *returns a new Value Object from Entiry object
	 * */
	public static UserTaskVO getValue(UserTask domainObject) {
		UserTaskVO userVO = new UserTaskVO();
		BeanUtils.copyProperties(domainObject, userVO);
		return userVO;
	}
	
	
	/*
	 *returns a Existing Entity Object from Entiry object and ignore any null properties from value object
	 * */
	public static UserTask mapProperties(UserTaskVO source,UserTask target) {
		BeanUtils.copyProperties(source, target,getNullPropertyNames(source));
		return target;
	}
	


}

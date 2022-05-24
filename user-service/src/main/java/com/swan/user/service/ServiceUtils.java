package com.swan.user.service;

import org.springframework.beans.BeanUtils;

import com.swan.user.entity.domain.User;
import com.swan.user.entity.vo.UserVO;


/**
 * class to copy the domain entity to value and value to domain entity
 * */
public class ServiceUtils {
	
	private ServiceUtils() {
	}
	
	public static User getEntity(UserVO valueObject) {
		User user = new User();
		BeanUtils.copyProperties(valueObject, user);
		return user;
	}
	
	public static UserVO getValue(User domainObject) {
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(domainObject, userVO);
		return userVO;
	}

}

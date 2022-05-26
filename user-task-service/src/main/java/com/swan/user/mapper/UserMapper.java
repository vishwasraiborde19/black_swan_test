package com.swan.user.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.swan.user.entity.domain.User;
import com.swan.user.entity.vo.UserVO;


/**
 * class to copy the domain entity to value and value to domain entity
 * */
public class UserMapper {
	
	private UserMapper() {
	}
	
	public static User getEntity(UserVO valueObject) {
		User user = new User();
		BeanUtils.copyProperties(valueObject, user);
		return user;
	}
	
	public static User mapProperties( UserVO valueObject,User user ) {
		BeanUtils. copyProperties(valueObject, user,getNullPropertyNames(valueObject));
		return user;
	}
	
	public static UserVO getValue(User domainObject) {
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(domainObject, userVO);
		return userVO;
	}
	
	
	private static String[] getNullPropertyNames (Object source) {
	     final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
	     java.beans.PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
	     Set emptyNames = new HashSet();
	     for(java.beans.PropertyDescriptor pd : pds) {
	       
	       Object srcValue = beanWrapper.getPropertyValue(pd.getName());
	       if (srcValue == null) emptyNames.add(pd.getName());
	     }
	     String[] result = new String[emptyNames.size()];
	     return (String[]) emptyNames.toArray(result);
	  }

}

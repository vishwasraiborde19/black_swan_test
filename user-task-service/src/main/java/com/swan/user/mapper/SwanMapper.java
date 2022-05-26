package com.swan.user.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class SwanMapper {
	
	protected static String[] getNullPropertyNames (Object source) {
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

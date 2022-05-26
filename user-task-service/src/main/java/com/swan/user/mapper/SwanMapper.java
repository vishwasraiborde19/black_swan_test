package com.swan.user.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class SwanMapper {
	
	/**
	 * delicate dance with the mapper therefore stricter control on usage
	 * */
	protected SwanMapper(){
		
	}
	
	protected static String[] getNullPropertyNames (Object source) {
	     final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
	     java.beans.PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
	     Set<String> emptyNames = new HashSet<String>();
	     for(java.beans.PropertyDescriptor pd : pds) {
	       
	       Object srcValue = beanWrapper.getPropertyValue(pd.getName());
	       if (srcValue == null) emptyNames.add(pd.getName());
	     }
	     String[] result = new String[emptyNames.size()];
	     return  emptyNames.toArray(result);
	  }

}

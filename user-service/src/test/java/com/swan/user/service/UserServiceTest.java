package com.swan.user.service;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swan.user.entity.vo.UserVO;


@SpringBootTest
public class UserServiceTest {
	
	private String username = "test_user_name";
	private String fistName = "test_first_name";
	private String lastName = "test_last_name";
	
	@Autowired
	private UserService userService;
	
	
	@Test
	public void testCreateUser() {
		
		UserVO user = new UserVO();
		user.setUsername(username);
		user.setFirstName(fistName);
		user.setLastName(lastName);
		
		ServiceUtils.getEntity(user);

		UserVO saved = userService.createUser(user);
		
		assert (("test_first_name").equals(saved.getFirstName()));
	}

	@Test
	public void testUpdateUser() {
		
		UserVO user = new UserVO();
		user.setUsername(username);
		user.setFirstName(fistName);
		user.setLastName(lastName);
		
		ServiceUtils.getEntity(user);
		
		UserVO saved =  userService.createUser(user);
		saved.setFirstName("updated_firstName");
		
		UserVO updated = userService.updateUser(saved);
		
		assert ("updated_firstName".equals(updated.getFirstName()));
	}
	
	@Test
	public void testListAllUsers() {
		
		UserVO userOne = new UserVO();
		userOne.setUsername(username);
		userOne.setFirstName(fistName);
		userOne.setLastName(lastName);
		
	    userService.createUser(userOne);
	    
		UserVO userTwo = new UserVO();
		userTwo.setUsername(username);
		userTwo.setFirstName(fistName);
		userTwo.setLastName(lastName);
		
        userService.createUser(userTwo);
		
		List<UserVO> users = userService.getAllUsers();
		assert (users != null);
	}
	
	@Test
	public void testUserInfo() {
		
		UserVO userOne = new UserVO();
		userOne.setUsername(username);
		userOne.setFirstName(fistName);
		userOne.setLastName(lastName);
		
		UserVO saved = userService.createUser(userOne);
		Optional<UserVO> fetched = userService.getUserInfo(saved.getId());
	    
		assert (fetched.get() != null);
	}

}

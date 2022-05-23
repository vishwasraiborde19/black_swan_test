package com.swan.user.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;


@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	
	@Test
	public void testCreateUser() {

	}

	@Test
	public void testUpdateUser() {
		assert (Boolean.FALSE);
	}
	
	@Test
	public void testListAllUsers() {
		assert (Boolean.FALSE);
	}
	
	@Test
	public void testUserInfo() {
		assert (Boolean.FALSE);
	}

}

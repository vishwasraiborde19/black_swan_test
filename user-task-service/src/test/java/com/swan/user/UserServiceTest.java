package com.swan.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swan.user.entity.vo.UserVO;
import com.swan.user.exception.UserExsistsException;
import com.swan.user.mapper.UserMapper;
import com.swan.user.service.UserService;

@SpringBootTest
class UserServiceTest {

	private String username = "test_user_name";
	private String fistName = "test_first_name";
	private String lastName = "test_last_name";

	@Autowired
	private UserService userService;

	@Test
	void testCreateUser() throws UserExsistsException {

		UserVO user = new UserVO();
		String usernameString = username + "" + Math.random();
		user.setUsername(usernameString);
		user.setFirstName(fistName);
		user.setLastName(lastName);

		UserMapper.getEntity(user);

		UserVO saved = userService.createUser(user);

		assertEquals(usernameString, saved.getUsername());
	}

	@Test
	void testUpdateUser() throws UserExsistsException {

		UserVO user = new UserVO();
		user.setUsername(username + new Date());
		user.setFirstName(fistName);
		user.setLastName(lastName);

		UserMapper.getEntity(user);

		UserVO saved = userService.createUser(user);
		saved.setFirstName("updated_firstName");

		UserVO updated = userService.updateUser(saved);

		assertEquals("updated_firstName", (updated.getFirstName()));
	}

	@Test
	void testListAllUsers() throws UserExsistsException {

		UserVO userOne = new UserVO();
		userOne.setUsername("usernameOne");
		userOne.setFirstName(fistName);
		userOne.setLastName(lastName);

		userService.createUser(userOne);

		UserVO userTwo = new UserVO();
		userTwo.setUsername("usernameTwo");
		userTwo.setFirstName(fistName);
		userTwo.setLastName(lastName);

		userService.createUser(userTwo);

		List<UserVO> users = userService.getAllUsers();
		assertNotNull(users);
	}

	@Test
	void testUserInfo() throws UserExsistsException {

		UserVO userOne = new UserVO();
		userOne.setUsername("testUserInfo");
		userOne.setFirstName(fistName);
		userOne.setLastName(lastName);

		UserVO saved = userService.createUser(userOne);
		Optional<UserVO> fetched = userService.getUserInfo(saved.getId());

		assertNotNull(fetched.get());
	}

}

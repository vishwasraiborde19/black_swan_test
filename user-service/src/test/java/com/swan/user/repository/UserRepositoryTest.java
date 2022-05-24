package com.swan.user.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swan.user.entity.domain.User;

@Ignore
@SpringBootTest
class UserRepositoryTest {

	// User user ;
	private String username = "test_user_name";
	private String fistName = "test_first_name";
	private String lastName = "test_last_name";

	@Autowired
	private UserRepository userRepository;

	@Test
	 void testCreateUser() {
		User user = new User();
		user.setUsername(username);
		user.setFirstName(fistName);
		user.setLastName(lastName);

		user = userRepository.save(user);

		assertNotNull(user);
	}

	@Test
	 void testUserInfo() {

		User user = new User();
		user.setUsername(username);
		user.setFirstName(fistName);
		user.setLastName(lastName);

		user = userRepository.save(user);

		Optional<User> foundUser = userRepository.findById(user.getId());
		assertEquals("test_user_name",(foundUser.get().getUsername()));
	}

	@Test
	 void testUpdateUser() {

		User user = new User();
		user.setUsername(username);
		user.setFirstName(fistName);
		user.setLastName(lastName);
		user = userRepository.save(user);

		User foundUser = userRepository.findById(user.getId()).get();
		foundUser.setFirstName("updated_user_name");
		User updatedUser = userRepository.save(foundUser);

		assertEquals("updated_user_name",(updatedUser.getFirstName()));
	}

	@Test
	 void testListAllUsers() {

		List<User> users = userRepository.findAll();

		assertTrue (users.size() > 0);
	}

}

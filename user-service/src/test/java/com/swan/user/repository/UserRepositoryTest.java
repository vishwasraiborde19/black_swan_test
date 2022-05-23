package com.swan.user.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swan.user.entity.domain.User;

@SpringBootTest
public class UserRepositoryTest {

	// User user ;
	private String username = "test_user_name";
	private String fistName = "test_first_name";
	private String lastName = "test_last_name";

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setUsername(username);
		user.setFirstName(fistName);
		user.setLastName(lastName);

		user = userRepository.save(user);

		assertTrue(user != null);
	}

	@Test
	public void testUserInfo() {

		User user = new User();
		user.setUsername(username);
		user.setFirstName(fistName);
		user.setLastName(lastName);

		user = userRepository.save(user);

		Optional<User> foundUser = userRepository.findById(user.getId());
		assertTrue("test_user_name".equals(foundUser.get().getUsername()));
	}

	@Test
	public void testUpdateUser() {

		User user = new User();
		user.setUsername(username);
		user.setFirstName(fistName);
		user.setLastName(lastName);
		user = userRepository.save(user);

		User foundUser = userRepository.findById(user.getId()).get();
		foundUser.setFirstName("updated_user_name");
		User updatedUser = userRepository.save(foundUser);

		assertTrue("updated_user_name".equals(updatedUser.getFirstName()));
	}

	@Test
	public void testListAllUsers() {

		List<User> users = userRepository.findAll();

		assert (users.size() > 0);
	}

}

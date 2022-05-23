package com.swan.task.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swan.task.entity.domain.UserTask;


@SpringBootTest
class UserTaskRepositoryTest {

	// User user ;
	private String taskName = "test_task_name";
	private String taskDescription = "test_task_descriptiom";	
	private Long userId = 1000l;

	@Autowired
	private UserRepository userRepository;

	@Test
	 void testCreateUser() {
		UserTask userTask = new UserTask();
		userTask.setName(taskName);
		userTask.setDescription(taskDescription);
		userTask.setDateTime(new Date());
		
		userTask.setUserid(userId);
		

		userTask = userRepository.save(userTask);

		assertNotNull(userTask);
	}

	@Test
	 void testUserInfo() {

		UserTask userTask = new UserTask();
		userTask.setName(taskName);
		userTask.setDescription(taskDescription);
		userTask.setDateTime(new Date());
		
		userTask.setUserid(userId);

		userTask = userRepository.save(userTask);

		Optional<UserTask> foundUser = userRepository.findById(userTask.getId());
		assertEquals("test_task_name",(foundUser.get().getName()));
	}

	@Test
	 void testUpdateUser() {

		UserTask userTask = new UserTask();
		userTask.setName(taskName);
		userTask.setDescription(taskDescription);
		userTask.setDateTime(new Date());
		
		userTask.setUserid(userId);
		userTask = userRepository.save(userTask);

		UserTask foundUserTask = userRepository.findById(userTask.getId()).get();
		foundUserTask.setName("updated_task_name");
		
		UserTask updatedUser = userRepository.save(foundUserTask);

		assertEquals("updated_task_name",(updatedUser.getName()));
	}

	@Test
	 void testListAllUsers() {

		List<UserTask> users = userRepository.findAll();

		assertTrue (users.size() > 0);
	}

}

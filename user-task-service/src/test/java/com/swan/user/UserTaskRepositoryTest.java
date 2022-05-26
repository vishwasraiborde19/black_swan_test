package com.swan.user;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swan.user.entity.domain.UserTask;
import com.swan.user.repository.UserTaskRepository;


@SpringBootTest
class UserTaskRepositoryTest {

	// User user ;
	private String taskName = "test_task_name";
	private String taskDescription = "test_task_descriptiom";	
	private Long userId = 1000l;

	@Autowired
	private UserTaskRepository userTaskRepository;

	@Test
	 void testCreateUser() {
		UserTask userTask = new UserTask();
		userTask.setName(taskName);
		userTask.setDescription(taskDescription);
		userTask.setDateTime(new Date());
		
		userTask.setUserid(userId);
		

		userTask = userTaskRepository.save(userTask);

		assertNotNull(userTask);
	}

	@Test
	 void testUserInfo() {

		UserTask userTask = new UserTask();
		userTask.setName(taskName);
		userTask.setDescription(taskDescription);
		userTask.setDateTime(new Date());
		
		userTask.setUserid(userId);

		userTask = userTaskRepository.save(userTask);

		Optional<UserTask> foundUser = userTaskRepository.findById(userTask.getId());
		assertEquals("test_task_name",(foundUser.get().getName()));
	}

	@Test
	 void testUpdateUser() {

		UserTask userTask = new UserTask();
		userTask.setName(taskName);
		userTask.setDescription(taskDescription);
		userTask.setDateTime(new Date());
		
		userTask.setUserid(userId);
		userTask = userTaskRepository.save(userTask);

		UserTask foundUserTask = userTaskRepository.findById(userTask.getId()).get();
		foundUserTask.setName("updated_task_name");
		
		UserTask updatedUser = userTaskRepository.save(foundUserTask);

		assertEquals("updated_task_name",(updatedUser.getName()));
	}

	@Test
	 void testListAllUsers() {

		List<UserTask> users = userTaskRepository.findAll();

		assertTrue (users.size() > 0);
	}

}

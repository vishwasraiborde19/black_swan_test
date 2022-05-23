package com.swan.task.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swan.task.entity.vo.TaskStatus;
import com.swan.task.entity.vo.UserTaskVO;


@SpringBootTest
class UserTaskServiceTest {
	
	private String taskName = "test_task_name_service";
	private String taskDescription = "test_task_description_service";	
	private TaskStatus taskStatus ;	
	private Long userId = 2000l;
	
	@Autowired
	private UserService userService;
	
	
	@Test
	 void testCreateUser() {
		
		UserTaskVO userTask = new UserTaskVO();
		userTask.setName(taskName);
		userTask.setDescription(taskDescription);
		userTask.setDateTime(new Date());
		userTask.setStatus(TaskStatus.CREATED);
		userTask.setUserid(userId);
		
		ServiceUtils.getEntity(userTask);

		UserTaskVO saved = userService.createTask(userTask);
		
		assertEquals (("test_task_name_service"),(saved.getName()));
	}

	@Test
	 void testUpdateUser() {
		
		UserTaskVO userTask = new UserTaskVO();
		userTask.setName(taskName);
		userTask.setDescription(taskDescription);
		userTask.setDateTime(new Date());
		userTask.setStatus(taskStatus);
		userTask.setUserid(userId);
		
		ServiceUtils.getEntity(userTask);
		
		UserTaskVO saved =  userService.createTask(userTask);
		saved.setName("updated_Name");
		
		UserTaskVO updated = userService.updateUserTask(saved);
		
		assertEquals("updated_Name",(updated.getName()));
	}
	
	@Test
	 void testListAllUsers() {
		
		UserTaskVO userTaskOne = new UserTaskVO();
		userTaskOne.setName(taskName);
		userTaskOne.setDescription(taskDescription);
		userTaskOne.setDateTime(new Date());
		userTaskOne.setStatus(taskStatus);
		userTaskOne.setUserid(userId);
		
	    userService.createTask(userTaskOne);
	    
		UserTaskVO userTaskTwo = new UserTaskVO();
		userTaskTwo.setName(taskName);
		userTaskTwo.setDescription(taskDescription);
		userTaskTwo.setDateTime(new Date());
		userTaskOne.setStatus(taskStatus);
		userTaskOne.setUserid(userId);
		
        userService.createTask(userTaskTwo);
		
		List<UserTaskVO> users = userService.getAllUsersTasks();
		assertNotNull(users);
	}
	
	@Test
	 void testUserInfo() {
		
		UserTaskVO userTaskOne = new UserTaskVO();
		userTaskOne.setName(taskName);
		userTaskOne.setDescription(taskDescription);
		userTaskOne.setDateTime(new Date());
		userTaskOne.setStatus(taskStatus);
		userTaskOne.setUserid(userId);
		
		UserTaskVO saved = userService.createTask(userTaskOne);
		Optional<UserTaskVO> fetched = userService.getUserTaskInfo(saved.getId(),userId);
	    
		assertNotNull (fetched.get());
	}

}

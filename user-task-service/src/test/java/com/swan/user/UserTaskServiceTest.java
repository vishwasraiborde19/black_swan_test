package com.swan.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swan.user.entity.vo.TaskStatus;
import com.swan.user.entity.vo.UserTaskVO;
import com.swan.user.mapper.TaskMapper;
import com.swan.user.service.UserTaskService;

@SpringBootTest
class UserTaskServiceTest {

	private String taskName = "test_task_name_service";
	private String taskDescription = "test_task_description_service";
	private TaskStatus taskStatus;
	private Long userId = 2000l;

	@Autowired
	private UserTaskService userTaskService;

	@Test
	void testCreateUser() {

		UserTaskVO userTask = new UserTaskVO();
		userTask.setName(taskName);
		userTask.setDescription(taskDescription);
		userTask.setDateTime(new Date());
		userTask.setStatus(TaskStatus.CREATED.name());
		userTask.setUserid(userId);

		TaskMapper.getEntity(userTask);

		UserTaskVO saved = userTaskService.createTask(userTask);

		assertEquals(("test_task_name_service"), (saved.getName()));
	}

	@Test
	void testUpdateUser() {

		UserTaskVO userTask = new UserTaskVO();
		userTask.setName(taskName);
		userTask.setDescription(taskDescription);
		userTask.setDateTime(new Date());
		userTask.setStatus(taskStatus.UPDATED.name());
		userTask.setUserid(userId);

		TaskMapper.getEntity(userTask);

		UserTaskVO saved = userTaskService.createTask(userTask);
		saved.setName("updated_Name");

		UserTaskVO updated = userTaskService.updateUserTask(saved);

		assertEquals("updated_Name", (updated.getName()));
	}

	@Test
	void testListAllUsers() {

		UserTaskVO userTaskOne = new UserTaskVO();
		userTaskOne.setName(taskName);
		userTaskOne.setDescription(taskDescription);
		userTaskOne.setDateTime(new Date());
		userTaskOne.setStatus(taskStatus.CREATED.name());
		userTaskOne.setUserid(userId);

		userTaskService.createTask(userTaskOne);

		UserTaskVO userTaskTwo = new UserTaskVO();
		userTaskTwo.setName(taskName);
		userTaskTwo.setDescription(taskDescription);
		userTaskTwo.setDateTime(new Date());
		userTaskOne.setStatus(taskStatus.CREATED.name());
		userTaskOne.setUserid(userId);

		userTaskService.createTask(userTaskTwo);

		List<UserTaskVO> users = userTaskService.getAllUsersTasks();
		assertNotNull(users);
	}

	@Test
	void testUserInfo() {

		UserTaskVO userTaskOne = new UserTaskVO();
		userTaskOne.setName(taskName);
		userTaskOne.setDescription(taskDescription);
		userTaskOne.setDateTime(new Date());
		userTaskOne.setStatus(taskStatus.CREATED.name());
		userTaskOne.setUserid(userId);

		UserTaskVO saved = userTaskService.createTask(userTaskOne);
		Optional<UserTaskVO> fetched = userTaskService.getUserTaskInfo(saved.getId(), userId);

		assertNotNull(fetched.get());
	}

	@Test
	void testDeleteTask() {

		UserTaskVO userTaskOne = new UserTaskVO();
		userTaskOne.setName(taskName);
		userTaskOne.setDescription(taskDescription);
		userTaskOne.setDateTime(new Date());
		userTaskOne.setStatus(taskStatus.CREATED.name());
		userTaskOne.setUserid(userId);

		UserTaskVO saved = userTaskService.createTask(userTaskOne);
		UserTaskVO deleted = userTaskService.deleteTask(saved.getId());

		assertEquals(TaskStatus.DELETED.name(), deleted.getStatus());
	}

}

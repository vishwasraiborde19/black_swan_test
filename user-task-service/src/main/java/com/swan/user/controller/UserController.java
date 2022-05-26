package com.swan.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swan.user.entity.vo.TaskStatus;
import com.swan.user.entity.vo.UserTaskVO;
import com.swan.user.entity.vo.UserVO;
import com.swan.user.exception.UserExsistsException;
import com.swan.user.service.UserService;
import com.swan.user.service.UserTaskService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

	@Autowired
	private UserService userSevice;
	
	@Autowired
	private UserTaskService userTaskService;



	@PostMapping("/user")
	public UserVO createUser(@RequestBody UserVO user) {
		
		log.info("createUser");
		try {
			return userSevice.createUser(user); 
		} catch (UserExsistsException e) {
			log.error("UserController: createUser ",e.getMessage());
		}
		return user;

	}


	@PutMapping("/user/{id}")
	public UserVO updateUser(@PathVariable Long id, @RequestBody UserVO user) {
		log.info("updateUser");
		
		user.setId(id);
		return userSevice.updateUser(user);

	}


	@GetMapping("/user")
	public List<UserVO> listAllUsers() {
		log.info("listAllUsers");
		
		return userSevice.getAllUsers();

	}


	@GetMapping("/user/{id}")
	public Optional<UserVO> getUserInfo(@PathVariable Long id) {
		log.info("getUserInfo");
		
		return userSevice.getUserInfo(id);

	}
	
	@PostMapping("/user/{user_id}/task")
	public UserTaskVO createTask(@PathVariable(name = "user_id") Long userid, @RequestBody UserTaskVO task) {
		log.info("createTask");
		
		task.setUserid(userid);
		task.setStatus(TaskStatus.CREATED.name());
		return userTaskService.createTask(task);

	}


	@PutMapping("/user/{user_id}/task/{task_id}")
	public UserTaskVO updateTask(@PathVariable(name = "task_id") Long taskid,
			@PathVariable(name = "user_id") Long userid, @RequestBody UserTaskVO task) {
		log.info("updateTask");
		
		task.setId(taskid);
		task.setUserid(userid);

		return userTaskService.updateUserTask(task);

	}


	@DeleteMapping("/user/{user_id}/task/{task_id}")
	public UserTaskVO deleteTask(@PathVariable(name = "user_id") Long userid, @PathVariable(name = "task_id") Long taskid) {
		log.info("deleteTask");

		return userTaskService.deleteTask(taskid);

	}


	@GetMapping("/user/{user_id}/task/{task_id}")
	public Optional<UserTaskVO> getUserTaskInfo(@PathVariable(name = "user_id") Long userid , @PathVariable(name = "task_id") Long taskid) {
		log.info("getUserTaskInfo");
		
		return userTaskService.getUserTaskInfo(taskid,userid);

	}
	

	@GetMapping("/user/{user_id}/task")
	public List<UserTaskVO> getAllTaskOfUser(@PathVariable(name = "user_id") Long userid ) {
		log.info("getAllTaskOfUser");
		
		return userTaskService.getAllTaskOfUser(userid);

	}
	
	

}

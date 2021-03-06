package com.swan.task.controller;

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

import com.swan.task.entity.vo.TaskStatus;
import com.swan.task.entity.vo.UserTaskVO;
import com.swan.task.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/api")
@Slf4j
public class UserTaskController {

	@Autowired
	private UserService userSevice;


	@PostMapping("/user/{user_id}/task")
	public UserTaskVO createTask(@PathVariable(name = "user_id") Long userid, @RequestBody UserTaskVO userTask) {
		log.info("createTask");
		
		userTask.setUserid(userid);
		userTask.setStatus(TaskStatus.ASSIGNED);
		return userSevice.createTask(userTask);

	}


	@PutMapping("/user/{user_id}/task/{task_id}")
	public UserTaskVO updateTask(@PathVariable(name = "task_id") Long taskid,
			@PathVariable(name = "user_id") Long userid, @RequestBody UserTaskVO userTaskVO) {
		log.info("updateTask");
		
		userTaskVO.setId(taskid);
		userTaskVO.setUserid(userid);

		return userSevice.updateUserTask(userTaskVO);

	}


	@DeleteMapping("/user/{user_id}/task/{task_id}")
	public UserTaskVO deleteTask(@PathVariable(name = "user_id") Long userid, @PathVariable(name = "task_id") Long taskid) {
		log.info("deleteTask");

		return userSevice.deleteTask(taskid);

	}


	@GetMapping("/user/{user_id}/task/{task_id}")
	public Optional<UserTaskVO> getUserTaskInfo(@PathVariable(name = "user_id") Long userid , @PathVariable(name = "task_id") Long taskid) {
		log.info("getUserTaskInfo");
		
		return userSevice.getUserTaskInfo(taskid,userid);

	}
	

	@GetMapping("/user/{user_id}/task")
	public List<UserTaskVO> getAllTaskOfUser(@PathVariable(name = "user_id") Long userid ) {
		log.info("getAllTaskOfUser");
		
		return userSevice.getAllTaskOfUser(userid);

	}

}

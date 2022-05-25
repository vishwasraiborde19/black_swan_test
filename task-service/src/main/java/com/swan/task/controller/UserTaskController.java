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

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userSevice;

	// st:8080/api/user/{user_id}/task
	@PostMapping("/user/{user_id}/task")
	public UserTaskVO createTask(@PathVariable(name = "user_id") Long userid, @RequestBody UserTaskVO userTask) {
		userTask.setUserid(userid);
		userTask.setStatus(TaskStatus.ASSIGNED);
		return userSevice.createTask(userTask);

	}

	//st:8080/api/user/{user_id}/task/{task_id}
	@PutMapping("/user/{user_id}/task/{task_id}")
	public UserTaskVO updateTask(@PathVariable(name = "task_id") Long taskid,
			@PathVariable(name = "user_id") Long userid, @RequestBody UserTaskVO userTaskVO) {
		
		userTaskVO.setId(taskid);
		userTaskVO.setUserid(userid);

		return userSevice.updateUserTask(userTaskVO);

	}

	//lhost:8080/api/user/{user_id}/task/{task_id}
	@DeleteMapping("/user/{user_id}/task/{task_id}")
	public UserTaskVO deleteTask(@PathVariable(name = "user_id") Long userid, @PathVariable(name = "task_id") Long taskid) {

		return userSevice.deleteTask(taskid);

	}

	//st:8080/api/user/{user_id}/task/{task_id}
	@GetMapping("/user/{user_id}/task/{task_id}")
	public Optional<UserTaskVO> getUserTaskInfo(@PathVariable(name = "user_id") Long userid , @PathVariable(name = "task_id") Long taskid) {
		return userSevice.getUserTaskInfo(taskid,userid);

	}
	
	//http:///api/user/{user_id}/task 
	@GetMapping("/user/{user_id}/task")
	public List<UserTaskVO> getAllTaskOfUser(@PathVariable(name = "user_id") Long userid ) {
		return userSevice.getAllTaskOfUser(userid);

	}

}

package com.swan.task.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swan.task.entity.domain.UserTask;
import com.swan.task.entity.vo.TaskStatus;
import com.swan.task.entity.vo.UserTaskVO;
import com.swan.task.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserTaskVO createTask(UserTaskVO userTaskVO) {
		
		UserTask userTask = ServiceUtils.getEntity(userTaskVO);
		userTask.setStatus(TaskStatus.CREATED);
		userTask = userRepository.save(userTask);

		return ServiceUtils.getValue(userTask);

	}

	public Optional<UserTaskVO> getUserTaskInfo(Long userid, Long id) {

		//efficient way is to optimise at call instead on predicate , will go with query
		Optional<UserTask> user = userRepository.findByIdAndUserid(userid,id);
		if (user.isPresent()) {
			return user.map(ServiceUtils::getValue);
		} else {
			return Optional.empty();
		}

	}

	public UserTaskVO updateUserTask(UserTaskVO userVO) {

		UserTask userTask = ServiceUtils.getEntity(userVO);
		userTask.setStatus(TaskStatus.UPDATED);
		userTask = userRepository.save(userTask);

		return userVO;

	}

	public List<UserTaskVO> getAllUsersTasks() {
		return userRepository.findAll().stream().map(ServiceUtils::getValue).collect(Collectors.toList());
	}

	
	public UserTaskVO deleteTask(Long taskid) {
		
		UserTask userTask = new UserTask();
		userTask.setId(taskid);
		
		//going for hard delete instead of soft delete as no requirement to restore task
		userRepository.delete(userTask);
		UserTaskVO userTaskVO = new UserTaskVO();
		userTaskVO.setId(taskid);
		
		
		userTaskVO.setStatus(TaskStatus.DELETED);
		return userTaskVO;
	}

	public List<UserTaskVO> getAllTaskOfUser(Long userid) {
		List<UserTask> users = userRepository.findAllByUserid(userid);
		return users.stream().map(ServiceUtils::getValue).collect(Collectors.toList());
	
	}

}

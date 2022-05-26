package com.swan.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swan.user.entity.domain.UserTask;
import com.swan.user.entity.vo.TaskStatus;
import com.swan.user.entity.vo.UserTaskVO;
import com.swan.user.mapper.TaskMapper;
import com.swan.user.repository.UserTaskRepository;

@Service
public class UserTaskService {

	@Autowired
	private UserTaskRepository userTaskRepository;

	public UserTaskVO createTask(UserTaskVO userTaskVO) {
		
		

		UserTask userTask = TaskMapper.getEntity(userTaskVO);
		userTask.setStatus(TaskStatus.CREATED.toString());
		userTask = userTaskRepository.save(userTask);

		return TaskMapper.getValue(userTask);

	}

	public Optional<UserTaskVO> getUserTaskInfo(Long userid, Long id) {

		// efficient way is to optimise at call instead on predicate , will go with query
		Optional<UserTask> user = userTaskRepository.findByIdAndUserid(userid, id);
		if (user.isPresent()) {
			return user.map(TaskMapper::getValue);
		} else {
			return Optional.empty();
		}

	}

	public UserTaskVO updateUserTask(UserTaskVO userTaskVO) {

		Optional<UserTask> currentUserTask = userTaskRepository.findById(userTaskVO.getId());
		if (currentUserTask.isPresent()) {
			UserTask userTask = TaskMapper.mapProperties(userTaskVO,currentUserTask.get());
			userTask = userTaskRepository.save(userTask);
			return TaskMapper.getValue(userTask);
		} else {
			// instead shall I return a Service response userVO as a result ? revisit
			return new UserTaskVO();
		}

	}

	public List<UserTaskVO> getAllUsersTasks() {
		return userTaskRepository.findAll().stream().map(TaskMapper::getValue).collect(Collectors.toList());
	}

	public UserTaskVO deleteTask(Long taskid) {

		UserTask userTask = new UserTask();
		userTask.setId(taskid);

		// going for hard delete instead of soft delete as no requirement to restore task
		userTaskRepository.delete(userTask);
		UserTaskVO userTaskVO = new UserTaskVO();
		userTaskVO.setId(taskid);

		userTaskVO.setStatus(TaskStatus.DELETED.toString());
		return userTaskVO;
	}

	public List<UserTaskVO> getAllTaskOfUser(Long userid) {
		List<UserTask> users = userTaskRepository.findAllByUserid(userid);
		return users.stream().map(TaskMapper::getValue).collect(Collectors.toList());

	}

}

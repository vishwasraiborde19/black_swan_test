package com.swan.user.service.batch;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.swan.user.entity.domain.UserTask;
import com.swan.user.entity.vo.TaskStatus;
import com.swan.user.repository.IStatusUpdateJob;

import lombok.extern.slf4j.Slf4j;

//https://spring.io/guides/gs/scheduling-tasks/
@Component
@Slf4j
public class StatusUpdateJob {

	@Autowired
	IStatusUpdateJob iStatusUpdateJob;


	/*
	 * wanted to create this as a separate project though due to other pressing urgencies only able showcase the use case of Criteria builder here 
	 * hope this is acceptable
	 * */
	@Scheduled(fixedDelay = 15000)
	public void runUpdateStatusjob() {
		
		log.info("StatusUpdateJob: runUpdateStatusjob: RUNNING BATCH JOB: update all pending status to done !");

		UserTask task = new UserTask();
		task.setStatus(TaskStatus.PENDING.getStatus());
		
		List<UserTask> tasks = getAllPendingTasks(task);

		//Test request to print all pending tasks
		tasks.forEach(System.out::println);

		List<UserTask> pendingTaks = tasks.stream().map(x -> updateStatus(x, TaskStatus.DONE))
				.collect(Collectors.toList());
		
		iStatusUpdateJob.saveAll(pendingTaks);

	}

	public void updateAllPendingTasks(List<UserTask> tasks) {

		iStatusUpdateJob.saveAll(tasks);
	}
	
	private UserTask updateStatus(UserTask userTask, TaskStatus status) {
		userTask.setStatus(status.name());
		return userTask;
	}
	
	public List<UserTask> getAllPendingTasks(UserTask task) {
		
		

		return iStatusUpdateJob
				.findAll((Root<UserTask> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {

					Predicate predicate = criteriaBuilder.conjunction();
					predicate = criteriaBuilder.and(predicate,
							criteriaBuilder.like(root.get("status"), "%" + task.getStatus() + "%"));
					return predicate;
				});
	}

}

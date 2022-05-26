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

//https://spring.io/guides/gs/scheduling-tasks/
@Component
public class StatusUpdateJob {

	@Autowired
	IStatusUpdateJob iStatusUpdateJob;

	public List<UserTask> getAllPendingTasks(UserTask task) {

		return iStatusUpdateJob
				.findAll((Root<UserTask> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {

					Predicate predicate = criteriaBuilder.conjunction();
					predicate = criteriaBuilder.and(predicate,
							criteriaBuilder.like(root.get("status"), "%" + task.getStatus() + "%"));
					return predicate;
				});
	}

	public void updateAllPendingTasks(List<UserTask> tasks) {

		iStatusUpdateJob.saveAll(tasks);
	}

	//@Scheduled(fixedDelay = 10000)
	public void runUpdateStatusjob() {

		UserTask task = new UserTask();
		task.setStatus(TaskStatus.CREATED.toString());
		System.out.println("I run faster than Bolt");
		List<UserTask> tasks = getAllPendingTasks(task);

		//Test request to print all pending tasks
		tasks.forEach(System.out::println);

		List<UserTask> pendingTaks = tasks.stream().map(x -> updateStatus(x, TaskStatus.COMPLETED))
				.collect(Collectors.toList());
		iStatusUpdateJob.saveAll(pendingTaks);

	}

	private UserTask updateStatus(UserTask userTask, TaskStatus status) {
		userTask.setStatus(status.name());
		return userTask;
	}

}

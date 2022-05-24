package com.swan.user.task.job;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

@EnableBatchProcessing
public class UserTaskStatusUpdateJob {
	
	@Autowired
	private SimpleJobLauncher jobLauncher;
	
	@Scheduled(cron = "*/5 * * * * *")
	public void perform() throws Exception {

		System.out.println("Job Started at :" + new Date());
		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		JobExecution execution = jobLauncher.run(runJob(), param);

		System.out.println("Job finished with status :" + execution.getStatus());
	}
	
	@Bean
	public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(jobRepository);
		return launcher;
	}
	
	private Job runJob() {
		System.out.println("executed");
		return null;
		
	}
}

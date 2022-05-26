package com.swan.user.service.batch;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.swan.user.entity.domain.UserTask;

public interface IStatusUpdateJob extends CrudRepository<UserTask, Long>, JpaSpecificationExecutor<UserTask>{
 
}

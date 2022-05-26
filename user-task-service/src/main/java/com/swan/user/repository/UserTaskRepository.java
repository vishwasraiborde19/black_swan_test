package com.swan.user.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swan.user.entity.domain.UserTask;

@Repository
@Transactional
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {

	Optional<UserTask> findByIdAndUserid(Long id, Long userid);

	List<UserTask> findAllByUserid(Long userid);

}

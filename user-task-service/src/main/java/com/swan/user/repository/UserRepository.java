package com.swan.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swan.user.entity.domain.User;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

}

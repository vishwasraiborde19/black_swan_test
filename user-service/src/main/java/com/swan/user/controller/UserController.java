package com.swan.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swan.user.entity.vo.UserVO;
import com.swan.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

	@Autowired
	private UserService userSevice;


	@PostMapping("/user")
	public UserVO createUser(@RequestBody UserVO user) {
		log.info("createUser");
		return userSevice.createUser(user);

	}


	@PutMapping("/user/{id}")
	public UserVO updateUser(@PathVariable Long id, @RequestBody UserVO user) {
		log.info("updateUser");
		user.setId(id);
		return userSevice.updateUser(user);

	}


	@GetMapping("/user")
	public List<UserVO> listAllUsers() {
		log.info("listAllUsers");
		return userSevice.getAllUsers();

	}


	@GetMapping("/user/{id}")
	public Optional<UserVO> getUserInfo(@PathVariable Long id) {
		log.info("getUserInfo");
		return userSevice.getUserInfo(id);

	}

}

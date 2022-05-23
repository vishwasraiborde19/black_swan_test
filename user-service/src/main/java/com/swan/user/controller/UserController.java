package com.swan.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swan.user.entity.vo.UserVO;
import com.swan.user.service.UserService;

@Controller
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userSevice ;
	
	//http://localhost:8080/api/user
	@PostMapping("/user")
	public UserVO createUser(@RequestBody UserVO user) {
		return userSevice.createUser(user) ;
	
	}
	
	//http://localhost:8080/api/user/{id}
	@PutMapping("/{id}")
	public UserVO updateUser(@PathVariable Long id, @RequestBody UserVO user) {
		user.setId(id);
		return userSevice.updateUser(user);
		
	}
	
	//GET http://localhost:8080/api/user
	@GetMapping("/user")
	public List<UserVO> listAllUsers() {
		return userSevice.getAllUsers();
		
	}
	
	//GET http://localhost:8080/api/user/{id}
	@GetMapping("/{id}")
	public UserVO getUserInfo(@PathVariable Long id) {
		return userSevice.getUserInfo(id).get();
		
	}
	
	
	
	
	
	
	

}

package com.swan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swan.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

}

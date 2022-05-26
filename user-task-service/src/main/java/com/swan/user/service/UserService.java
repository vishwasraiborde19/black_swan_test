package com.swan.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swan.user.entity.domain.User;
import com.swan.user.entity.vo.UserVO;
import com.swan.user.exception.UserExsistsException;
import com.swan.user.mapper.UserMapper;
import com.swan.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserVO createUser(UserVO userVO) throws UserExsistsException {

		User existinguser = userRepository.findByUsername(userVO.getUsername()); 
		if (ObjectUtils.isNotEmpty(existinguser)) {
			throw new UserExsistsException("User name already taken"); 
		} else {

			User user = UserMapper.getEntity(userVO);
			user = userRepository.save(user);

			return UserMapper.getValue(user);
		}

	}

	public Optional<UserVO> getUserInfo(Long id) {

		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.map(UserMapper::getValue);
		} else {
			return Optional.empty();
		}

	}

	public UserVO updateUser(UserVO userVO) {

		Optional<User> currentUser = userRepository.findById(userVO.getId());
		if (currentUser.isPresent()) {
			
			User user = UserMapper.mapProperties(userVO,currentUser.get());
			user = userRepository.save(user);
			return UserMapper.getValue(user);

		} else {
			// TODO: instead shall I return a Service response uservo as a result ? revisit
			// TODO: provide a response back updating a non existing user
			return new UserVO();
		}

	}

	public List<UserVO> getAllUsers() {
		return userRepository.findAll().stream().map(UserMapper::getValue).collect(Collectors.toList());
	}

}

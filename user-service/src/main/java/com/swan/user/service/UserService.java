package com.swan.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swan.user.entity.domain.User;
import com.swan.user.entity.vo.UserVO;
import com.swan.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserVO createUser(UserVO userVO) {
		
		User user = ServiceUtils.getEntity(userVO);
		userRepository.save(user);

		return ServiceUtils.getValue(user);

	}

	public Optional<UserVO> getUserInfo(Long id) {

		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.map(ServiceUtils::getValue);
		} else {
			return user.empty();
		}

	}

	public UserVO updateUser(UserVO userVO) {

		User user = ServiceUtils.getEntity(userVO);
		user = userRepository.save(user);

		if (user.equals(userVO)) {
			// no update 
			// what does the the consumer want in this case
			// message / code ?
		}

		return userVO;

	}

	public List<UserVO> getAllUsers() {
		return userRepository.findAll().stream().map(ServiceUtils::getValue).collect(Collectors.toList());
	}

}

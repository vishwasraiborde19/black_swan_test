package com.swan.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.swan.user.controller.UserController;
import com.swan.user.entity.vo.UserTaskVO;
import com.swan.user.entity.vo.UserVO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
 class UserControllerTest {
	

	@LocalServerPort
	private int port;

	@Autowired
	private UserController controller;

	@Test
	 void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
//
//	///@PostMapping("/user")
//	//@Test
//	void testcreateUser() {
//		UserVO vo = new RestTemplate().getForObject("http://localhost:" + port + "/api/user", UserVO.class);
//		assertThat(vo);
//
//	}
//
//	///@PutMapping("/user/{id}")
//	//@Test
//	void testupdateUser() {
//		new RestTemplate().put("http://localhost:" + port + "/api/user/1", UserVO.class);
//		//controller.updateUser(id, user);
//
//	}
//
//	//@GetMapping("/user")
//	//@Test
//	void testlistAllUsers() {
//		UserVO vo = new RestTemplate().getForObject("http://localhost:" + port + "/api/user", UserVO.class);
//
//	}
//
//	//@GetMapping("/user/{id}")
//	//@Test
//	void testetUserInfo() {
//		UserVO vo = new RestTemplate().getForObject("http://localhost:" + port + "/api/user/1", UserVO.class);
//
//	}
//
//	//@PostMapping("/user/{user_id}/task")
//	//@Test
//	void testcreateTask() {
//
//		UserTaskVO vo = new RestTemplate().getForObject("http://localhost:" + port + "/api/user/1/tast/1", UserTaskVO.class);
//	}
//
//	//@PutMapping("/user/{user_id}/task/{task_id}")
//	//@Test
//	void testupdateTask() {
//		new RestTemplate().put("http://localhost:" + port + "/api/user/1/tast/1", UserTaskVO.class);
//
//	}
//
//	//@DeleteMapping("/user/{user_id}/task/{task_id}")
//	//@Test
//	void testdeleteTask() {
//
//		new RestTemplate().delete("http://localhost:" + port + "/api/user/1/tast/1", UserTaskVO.class);
//	}
//
//	//@GetMapping("/user/{user_id}/task/{task_id}")
//	//@Test
//	void testgetUserTaskInfo() {
//		new RestTemplate().delete("http://localhost:" + port + "/api/user/1/tast/1", UserTaskVO.class);
//	}
//
//	//@GetMapping("/user/{user_id}/task")
//	//@Test
//	void testgetAllTaskOfUser() {
//		new RestTemplate().delete("http://localhost:" + port + "/api/user/1/tast", UserTaskVO.class);
//
//	}

}

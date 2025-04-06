package com._7.bookinghospital.user_service;

import com._7.bookinghospital.user_service.application.dto.resquest.SignupRequest;
import com._7.bookinghospital.user_service.application.service.UserService;
import com._7.bookinghospital.user_service.domain.model.User;
import com._7.bookinghospital.user_service.domain.model.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	@Rollback(value = false)
	void test1(){
		SignupRequest dto = SignupRequest.builder()
				.nickName("nickname")
				.username("test")
				.email("test@test.com")
				.userRole(UserRole.ROLE_USER)
				.password("test")
				.build();

		userService.signUp(dto);
	}
}

package com._7.bookinghospital.user_service.presentation.controller;


import com._7.bookinghospital.user_service.application.dto.resquest.SignInRequest;
import com._7.bookinghospital.user_service.application.dto.resquest.SignupRequest;
import com._7.bookinghospital.user_service.application.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final Logger log = Logger.getLogger("user-service");

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid SignupRequest signupRequest) {
        log.info("signup 들어옴");

        userService.signUp(signupRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<Void> signIn(@RequestBody @Valid SignInRequest dto, HttpServletResponse response
                                                    /*BindingResult bindingResult*/) {
        // 1. 유효성 검증 에러 있으면 처리하는 코드 작성(예외 핸들러에서 에러 잡기)
        // 2. 로그인 데이터 서비스에 전달

        String token = userService.signIn(dto);
        response.setHeader("Authorization", token);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

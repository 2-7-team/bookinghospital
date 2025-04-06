package com._7.bookinghospital.user_service.presentation.controller;

import com._7.bookinghospital.user_service.application.dto.response.CommonResponse;
import com._7.bookinghospital.user_service.application.dto.resquest.SignInRequest;
import com._7.bookinghospital.user_service.application.dto.resquest.SignupRequest;
import com._7.bookinghospital.user_service.application.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final Logger log = Logger.getLogger("user-service");

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<?>> signup(@RequestBody @Valid SignupRequest signupRequest) {
        log.info("signup 들어옴");

        userService.signUp(signupRequest);

        return ResponseEntity.ok(CommonResponse.success(null,"회원가입 성공"));
    }

    @PostMapping("/signin")
    public ResponseEntity<CommonResponse<?>> signIn(@RequestBody @Valid SignInRequest dto, HttpServletResponse response
                                                    /*BindingResult bindingResult*/) {
        // 1. 유효성 검증 에러 있으면 처리하는 코드 작성(예외 핸들러에서 에러 잡기)
        // 2. 로그인 데이터 서비스에 전달

        String message = userService.signIn(dto,response);
        return ResponseEntity.ok().body(CommonResponse.success(null, message));
    }
}

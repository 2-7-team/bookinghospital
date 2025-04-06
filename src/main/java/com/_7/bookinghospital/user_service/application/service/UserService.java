package com._7.bookinghospital.user_service.application.service;

import com._7.bookinghospital.user_service.application.dto.resquest.SignInRequest;
import com._7.bookinghospital.user_service.application.dto.resquest.SignupRequest;
import com._7.bookinghospital.user_service.domain.model.User;
import com._7.bookinghospital.user_service.domain.repository.UserRepository;
import com._7.bookinghospital.user_service.infrastructure.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public void signUp(SignupRequest signupRequest) {
        if(userRepository.existsByUserName(signupRequest.getUsername())) throw new IllegalArgumentException("이미 존재하는 아이디입니다.");

        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        User user = User.createUser(signupRequest, encodedPassword);
        userRepository.save(user);

    }

    public String signIn(@Valid SignInRequest dto, HttpServletResponse response) {
        User user = userRepository.findByUserName(dto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("아이디를 다시 확인해주세요"));



        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            throw new IllegalArgumentException("비밀번호를 다시 확인해주세요");
        }

        // 토큰 생성후 반환
        String token = jwtTokenProvider.generateToken(user.getUserName(),user.getRole());

        response.setHeader("Authorization", token);

        return "로그인 성공";
    }


    // auth-service

}

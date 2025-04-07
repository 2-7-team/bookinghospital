package com._7.bookinghospital.user_service.application.service;

import com._7.bookinghospital.user_service.application.dto.resquest.SignInRequest;
import com._7.bookinghospital.user_service.application.dto.resquest.SignupRequest;
import com._7.bookinghospital.user_service.domain.model.User;

import com._7.bookinghospital.user_service.domain.repository.UserRepository;
import com._7.bookinghospital.user_service.domain.security.TokenProvider;
import com._7.bookinghospital.user_service.infrastructure.jwt.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Transactional
    public void signUp(SignupRequest signupRequest) {
        // if문 한줄로 작성하지 말 것, 관습에 깐깐한, 고지식한 자바
        if(userRepository.existsByUserName(signupRequest.getUsername()))
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");

        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        User user = User.createUser(signupRequest, encodedPassword);
        userRepository.save(user);

    }

    public String signIn(@Valid SignInRequest dto) {
        User user = userRepository.findByUserName(dto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("아이디를 다시 확인해주세요"));

        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            throw new IllegalArgumentException("비밀번호를 다시 확인해주세요");
        }

        // 토큰 생성후 반환
        return tokenProvider.generateToken(user.getUserName(),user.getRole());
    }



}

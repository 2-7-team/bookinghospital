package com._7.bookinghospital.user_service.application.dto.resquest;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInRequest {
    @NotBlank(message = "아이디 작성은 필수입니다.")
    private String username;
    @NotBlank(message = "비밀번호 작성은 필수입니다.")
    private String password;
}

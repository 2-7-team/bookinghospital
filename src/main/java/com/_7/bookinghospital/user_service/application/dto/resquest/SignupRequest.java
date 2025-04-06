package com._7.bookinghospital.user_service.application.dto.resquest;

import com._7.bookinghospital.user_service.domain.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    @NotBlank private String username;
    @NotBlank private String password;
    @NotBlank private String nickName;
    private UserRole userRole;
    private String email;
}

package com._7.bookinghospital.user_service.domain.security;

import com._7.bookinghospital.user_service.domain.model.UserRole;

public interface TokenProvider {
    String generateToken(String username, UserRole role);
}

package com._7.bookinghospital.user_service.domain.repository;

import com._7.bookinghospital.user_service.domain.model.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUserName(String username);
    Boolean existsByUserName(String userName);
    User save(User user);
}

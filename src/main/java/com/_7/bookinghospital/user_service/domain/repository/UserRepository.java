package com._7.bookinghospital.user_service.domain.repository;

import com._7.bookinghospital.user_service.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    Boolean existsByUserName(String userName);
}

package com._7.bookinghospital.user_service.infrastructure.repository;
import com._7.bookinghospital.user_service.domain.model.User;
import com._7.bookinghospital.user_service.domain.repository.UserRepository;
import com._7.bookinghospital.user_service.infrastructure.repository.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class UserRepositoryImpl implements UserRepository {
    // user 테이블과 db 연결
    private final UserJpaRepository userJpaRepository;
    @Override
    public Optional<User> findByUserName(String username) {
        return userJpaRepository.findByUserName(username);
    }
    @Override
    public Boolean existsByUserName(String userName){
        // return userJpaRepository.existsByUserName(userName);
        return userJpaRepository.findByUserName(userName).isPresent();
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

}

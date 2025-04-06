package com._7.bookinghospital.user_service.domain.model;

import com._7.bookinghospital.user_service.application.dto.resquest.SignupRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_user")
@Setter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="username")
    private String userName;
    @Column
    private String password;
    @Column(name = "nickname")
    private String nickName;
    @Column
    private String email;
    @Column
    private UserRole role;


    public static User createUser(SignupRequest user, String password){
        return User.builder()
                .userName(user.getUsername())
                .password(password)
                .email(user.getEmail())
                .nickName(user.getNickName())
                .role(user.getUserRole())
                .build();
    }
}

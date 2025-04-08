package com._7.bookinghospital.user_service.domain.model;

// application과 domain이 다른계층인데 application이 domain에 영향을 줄 수 있음 분리해야함.

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
// jpa와 proxy, Entity 클래스에 기본 생성자 어노테이션을 왜 붙여야하는가?
// SimpleJpaRepository의 save 동작 방식에 따라서
// 클래스 레벨에 @NoArgsConstructor, @AllArgsConstructor, @Builder, @Setter가 왜 붙으면 안되는가?
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 컬럼에 작성될 값의 조건을 명시적으로 작성해주기
    @Column(unique = true, nullable = false)
    private String userName;

    @Column
    private String password;

    private String nickName;

    @Column
    private String email;

    @Column
    private UserRole role;

    @Builder(builderMethodName = "createUserBuilder")
    public User(String username, String password, String nickName, String email, UserRole role) {
        this.userName = username;
        this.password = password;
        this.nickName = nickName;
        this.email = getEmail();
        this.role = role;
    }

}

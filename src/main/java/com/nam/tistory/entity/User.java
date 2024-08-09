package com.nam.tistory.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_birth", length = 8, nullable = false)
    private LocalDate userBirth;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "latest_login_at")
    private LocalDateTime latestLoginAt;

    @Column(name = "user_image")
    private String userImage;

    @Builder
    public User(Long userId, String userName, String userEmail, String userPassword, LocalDate userBirth, LocalDateTime createdAt, LocalDateTime latestLoginAt, String userImage) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
        this.createdAt = createdAt;
        this.latestLoginAt = latestLoginAt;
        this.userImage = userImage;
    }

}

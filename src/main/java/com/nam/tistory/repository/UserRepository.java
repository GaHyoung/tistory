package com.nam.tistory.repository;

import com.nam.tistory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserEmail(String userEmail);
    boolean existsUserByUserEmail(String userEmail);
}

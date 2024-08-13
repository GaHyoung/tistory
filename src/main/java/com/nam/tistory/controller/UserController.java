package com.nam.tistory.controller;

import com.nam.tistory.dto.UserDto;
import com.nam.tistory.entity.User;
import com.nam.tistory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserDto.Register userRegisterDto) {
        userService.saveUser(userRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserDto.Register userRegisterDto) {
        userService.updateUser(userRegisterDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader("X-USER-ID") String userEmail) {
        userService.deleteUser(userEmail);
        return ResponseEntity.ok().build();
    }
}

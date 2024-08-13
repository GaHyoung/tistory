package com.nam.tistory.service;

import com.nam.tistory.dto.UserDto;
import com.nam.tistory.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(UserDto.Register userRegisterDto);
    void updateUser(UserDto.Register userRegisterDto);
    void deleteUser(String userEmail);

}

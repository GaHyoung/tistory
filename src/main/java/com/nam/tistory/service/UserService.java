package com.nam.tistory.service;

import com.nam.tistory.dto.UserRegisterDto;
import com.nam.tistory.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(UserRegisterDto userRegisterDto);
    void updateUser(UserRegisterDto userRegisterDto);
    void deleteUser(String userEmail);

}

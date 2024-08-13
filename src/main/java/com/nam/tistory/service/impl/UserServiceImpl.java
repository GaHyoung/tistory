package com.nam.tistory.service.impl;

import com.nam.tistory.dto.UserDto;
import com.nam.tistory.entity.User;
import com.nam.tistory.exception.UserAlreadyExistException;
import com.nam.tistory.exception.UserNotFoundException;
import com.nam.tistory.repository.UserRepository;
import com.nam.tistory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(UserDto.Register userRegisterDto) {
        String userEmail = userRegisterDto.getUserEmail();
        if (userRepository.existsUserByUserEmail(userEmail)) {
            throw new UserAlreadyExistException("User already exist : " + userEmail);
        }

        LocalDate userBirth = formatUserBirth(userRegisterDto.getUserBirth());

        User user = User.builder()
                .userName(userRegisterDto.getUserName())
                .userEmail(userEmail)
                .userPassword(userRegisterDto.getUserPassword())
                .userBirth(userBirth)
                .createdAt(LocalDateTime.now())
                .userImage(userRegisterDto.getUserImage())
                .build();

        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDto.Register userRegisterDto) {
        String userEmail = userRegisterDto.getUserEmail();

        Optional<User> optionalUser = userRepository.findUserByUserEmail(userEmail);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User does not exist : " + userEmail);
        }
        User existUser = optionalUser.get();

        LocalDate userBirth = formatUserBirth(userRegisterDto.getUserBirth());

        User updateUser = User.builder()
                .userId(existUser.getUserId())
                .userName(userRegisterDto.getUserName())
                .userEmail(existUser.getUserEmail())
                .userPassword(userRegisterDto.getUserPassword())
                .userBirth(userBirth)
                .createdAt(existUser.getCreatedAt())
                .userImage(userRegisterDto.getUserImage())
                .build();

        userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(String userEmail) {
        Optional<User> existUser = userRepository.findUserByUserEmail(userEmail);

        if (existUser.isPresent()) {
            userRepository.delete(existUser.get());
        } else {
            throw new UserNotFoundException("User not found : " + userEmail);
        }
    }

    public LocalDate formatUserBirth(String birth) {
        LocalDate registerDate;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        registerDate = LocalDate.parse(birth, dateTimeFormatter);
        return registerDate;
    }

}

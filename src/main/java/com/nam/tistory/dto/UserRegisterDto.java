package com.nam.tistory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegisterDto {
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userBirth;
    private String userImage;
}

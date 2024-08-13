package com.nam.tistory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    @NoArgsConstructor
    public static class Register{
        private String userName;
        private String userEmail;
        private String userPassword;
        private String userBirth;
        private String userImage;
    }


}

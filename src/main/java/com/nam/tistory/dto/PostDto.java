package com.nam.tistory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class PostDto {

    @Getter
    @NoArgsConstructor
    public static class Register {
        private String title;
        private String content;
        private List<String> categoryNameList;
    }

    @Getter
    @NoArgsConstructor
    public static class Update {
        private Long postId;
        private String title;
        private String content;
        private List<String> categoryNameList;
    }

}

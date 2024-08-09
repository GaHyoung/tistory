package com.nam.tistory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private Long postId;
    private String title;
    private String content;
    private String categoryName;
}

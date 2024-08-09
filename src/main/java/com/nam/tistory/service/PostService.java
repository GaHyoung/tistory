package com.nam.tistory.service;

import com.nam.tistory.dto.PostRequestDto;

public interface PostService {
    void savePost(PostRequestDto postRequestDto, String userEmail, String blogName);
}

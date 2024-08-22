package com.nam.tistory.service;

import com.nam.tistory.dto.PostDto;
import com.nam.tistory.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts(Long blogId);

    Post getPost(Long blogId, Long postId);

    void savePost(PostDto.Register postRegisterDto, String userEmail, Long blogId);

    void updatePost(PostDto.Update postUpdateDto, String userEmail, Long blogId, Long postId);

    void deletePost(Long blogId, Long postId);

}

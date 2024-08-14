package com.nam.tistory.repository;

import com.nam.tistory.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByBlog_BlogId(Long blogId);

    Optional<Post> findPostByPostIdAndBlog_BlogId(Long postId, Long blogId);

    Optional<Post> findPostByPostId(Long postId);
}

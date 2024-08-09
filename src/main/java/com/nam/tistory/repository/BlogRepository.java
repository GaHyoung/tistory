package com.nam.tistory.repository;

import com.nam.tistory.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Blog findBlogByBlogName(String blogName);
}

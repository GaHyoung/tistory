package com.nam.tistory.repository;

import com.nam.tistory.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoryRepository extends JpaRepository<PostCategory, PostCategory.Pk> {

    void deleteByPost_PostId(Long postId);
}

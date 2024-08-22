package com.nam.tistory.repository;

import com.nam.tistory.dto.CategoryListRequestDto;
import com.nam.tistory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByCategoryName(String categoryName);
    List<Category> findByCategoryNameIn(List<String> categoryNames);

    @Query("SELECT new com.nam.tistory.dto.CategoryListRequestDto(c.categoryName) FROM Category c WHERE c.blog.blogId = :blogId")
    List<CategoryListRequestDto> findCategoryByBlog_BlogId(@Param("blogId")Long blogId);
}

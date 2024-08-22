package com.nam.tistory.service.impl;

import com.nam.tistory.dto.CategoryListRequestDto;
import com.nam.tistory.repository.CategoryRepository;
import com.nam.tistory.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryListRequestDto> getCategoryNameList(Long blogId) {
        return categoryRepository.findCategoryByBlog_BlogId(blogId);
    }
}

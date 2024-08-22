package com.nam.tistory.service;

import com.nam.tistory.dto.CategoryListRequestDto;

import java.util.List;

public interface CategoryService {
    List<CategoryListRequestDto> getCategoryNameList(Long blogId);
}

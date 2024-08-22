package com.nam.tistory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category/{blogId}")
@RequiredArgsConstructor
public class CategoryController {
}

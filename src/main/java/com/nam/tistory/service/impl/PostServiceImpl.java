package com.nam.tistory.service.impl;

import com.nam.tistory.dto.PostRequestDto;
import com.nam.tistory.entity.Blog;
import com.nam.tistory.entity.Category;
import com.nam.tistory.entity.Post;
import com.nam.tistory.entity.User;
import com.nam.tistory.repository.BlogRepository;
import com.nam.tistory.repository.CategoryRepository;
import com.nam.tistory.repository.PostRepository;
import com.nam.tistory.repository.UserRepository;
import com.nam.tistory.service.PostService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Override
    public void savePost(PostRequestDto postRequestDto, String userEmail, String blogName) {

        Category category = categoryRepository.findByCategoryName(postRequestDto.getCategoryName());
        Blog blog = blogRepository.findBlogByBlogName(blogName);
        Optional<User> user = userRepository.findUserByUserEmail(userEmail);

        Post post = Post.builder()
                .postId(postRequestDto.getPostId())
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .category(category)
                .user(user.get())
                .blog(blog)
                .build();

        postRepository.save(post);

    }
}

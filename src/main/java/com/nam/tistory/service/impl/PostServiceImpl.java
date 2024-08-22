package com.nam.tistory.service.impl;

import com.nam.tistory.dto.PostDto;
import com.nam.tistory.entity.*;
import com.nam.tistory.exception.BlogNotFoundException;
import com.nam.tistory.exception.CategoryNotFoundException;
import com.nam.tistory.exception.PostNotFoundException;
import com.nam.tistory.exception.UserNotFoundException;
import com.nam.tistory.repository.*;
import com.nam.tistory.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final PostCategoryRepository postCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(Long blogId) {
        return postRepository.findPostByBlog_BlogId(blogId);
    }

    @Override
    @Transactional(readOnly = true)
    public Post getPost(Long blogId, Long postId) {
        return postRepository.findPostByPostIdAndBlog_BlogId(postId, blogId)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));
    }

    @Override
    public void savePost(PostDto.Register postRegisterDto, String userEmail, Long blogId) {

        Blog blog = findBlogById(blogId);
        User user = findUserByEmail(userEmail);

        Post post = Post.builder()
                .title(postRegisterDto.getTitle())
                .content(postRegisterDto.getContent())
                .user(user)
                .blog(blog)
                .build();
        postRepository.save(post);

        List<Category> categoryList = checkCategoryList(postRegisterDto.getCategoryNameList());
        post.updateCategory(categoryList);

        postCategoryRepository.saveAll(post.getPostCategories());
    }

    @Override
    public void updatePost(PostDto.Update postUpdateDto, String userEmail, Long blogId, Long postId) {
        Post existPost = postRepository.findPostByPostId(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        findBlogById(blogId);
        findUserByEmail(userEmail);

        List<Category> categoryList = checkCategoryList(postUpdateDto.getCategoryNameList());
        existPost.updateTitleAndContent(postUpdateDto.getTitle(), postUpdateDto.getContent());
        existPost.updateCategory(categoryList);

        postCategoryRepository.deleteByPost_PostId(existPost.getPostId());
        postCategoryRepository.saveAll(existPost.getPostCategories());
    }

    @Override
    public void deletePost(Long blogId, Long postId) {
        if (postRepository.findPostByPostIdAndBlog_BlogId(postId, blogId).isPresent()) {
            postCategoryRepository.deleteByPost_PostId(postId);
            postRepository.deleteById(postId);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }


    private Blog findBlogById(Long blogId) {
        return blogRepository.findById(blogId)
                .orElseThrow(() -> new BlogNotFoundException("Blog Not Found"));
    }

    private User findUserByEmail(String userEmail) {
        return userRepository.findUserByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<Category> checkCategoryList(List<String> requestCategoryNameList) {
        List<Category> existCategoryNameList = categoryRepository.findByCategoryNameIn(requestCategoryNameList);
        if (existCategoryNameList.isEmpty() ||
                existCategoryNameList.size() != requestCategoryNameList.size()) {
            throw new CategoryNotFoundException("Category not found");
        }

        return existCategoryNameList;
    }

}

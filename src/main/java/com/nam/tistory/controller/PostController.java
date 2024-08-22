package com.nam.tistory.controller;

import com.nam.tistory.dto.PostDto;
import com.nam.tistory.entity.Post;
import com.nam.tistory.service.CategoryService;
import com.nam.tistory.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/{blogId}/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;

    @GetMapping("/list")
    public String postList(@PathVariable Long blogId,
                           Model model) {
        List<Post> postList = postService.getAllPosts(blogId);
        model.addAttribute("postList", postList);
        model.addAttribute("blogId", blogId);
        return "/post/main";
    }

    @GetMapping("/view/{postId}")
    public String viewPost(@PathVariable Long blogId,
                           @PathVariable Long postId,
                           Model model) {
        Post post = postService.getPost(blogId, postId);
        model.addAttribute("post", post);
        return "/post/view";
    }

    @GetMapping("/register")
    public String showRegisterPage(@PathVariable Long blogId, Model model) {
        model.addAttribute("blogId", blogId);
        model.addAttribute("categoryNameList", categoryService.getCategoryNameList(blogId));
        return "/post/register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute PostDto.Register postRegisterDto,
                               @PathVariable Long blogId,
                               @RequestHeader("X-USER-ID") String userEmail) {
        postService.savePost(postRegisterDto, userEmail, blogId);
        return "redirect:/api/{blogId}/post";
    }

    @PutMapping("/view/{postId}/update")
    public String updatePost(@ModelAttribute PostDto.Update postUpdateDto,
                             @RequestHeader("X-USER-ID") String userEmail,
                             @PathVariable Long blogId,
                             @PathVariable Long postId) {
        postService.updatePost(postUpdateDto, userEmail, blogId, postId);
        return "redirect:/post/list";
    }

    @DeleteMapping("/view/{postId}/delete")
    public String deletePost(@PathVariable Long blogId,
                             @PathVariable Long postId) {
        postService.deletePost(blogId, postId);
        return "redirect:/post/list";
    }

}

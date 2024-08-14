package com.nam.tistory.controller;

import com.nam.tistory.dto.PostDto;
import com.nam.tistory.entity.Post;
import com.nam.tistory.entity.User;
import com.nam.tistory.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post/{blogId}")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(@PathVariable Long blogId) {
        return ResponseEntity.ok(postService.getAllPosts(blogId));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long blogId,
                                        @PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(blogId, postId));
    }

    @PostMapping
    public ResponseEntity<User> savePost(@RequestBody PostDto.Register postRegisterDto,
                                         @RequestHeader("X-USER-ID") String userEmail,
                                         @PathVariable Long blogId) {
        postService.savePost(postRegisterDto, userEmail, blogId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updatePost(@RequestBody PostDto.Update postUpdateDto,
                                           @RequestHeader("X-USER-ID") String userEmail,
                                           @PathVariable Long blogId) {
        postService.updatePost(postUpdateDto, userEmail, blogId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long blogId,
                                           @PathVariable Long postId) {
        postService.deletePost(blogId, postId);
        return ResponseEntity.ok().build();
    }

}

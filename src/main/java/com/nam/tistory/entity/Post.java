package com.nam.tistory.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "post")
    private Set<PostCategory> postCategories = new HashSet<>(); //카테고리는 중복되면 안되니까 set

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @Builder
    public Post(Long postId, String title, String content, User user, Blog blog) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.user = user;
        this.blog = blog;
    }

    public void addCategory(Category category) {
        PostCategory postCategory = new PostCategory(
                new PostCategory.Pk(category.getCategoryId(), this.postId),
                category,
                this);
        this.postCategories.add(postCategory);
    }

    public void updateCategory(List<Category> categories) {
        this.postCategories.clear();
        for (Category category : categories) {
            this.addCategory(category);
        }
    }

    public void updateTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

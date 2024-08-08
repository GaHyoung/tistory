package com.nam.tistory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Table(name = "post_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCategory {

    @EmbeddedId
    private Pk pk;

    @MapsId("categoryId")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @MapsId("postId")
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pk implements Serializable {
        private Integer categoryId;
        private Long postId;
    }

}

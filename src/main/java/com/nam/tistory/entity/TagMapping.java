package com.nam.tistory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Table(name = "tagMapping")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagMapping {

    @EmbeddedId
    private TagMapping.Pk pk;

    @MapsId("postId")
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @MapsId("tagId")
    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pk implements Serializable {
        private Long postId;
        private Integer tagId;
    }
}

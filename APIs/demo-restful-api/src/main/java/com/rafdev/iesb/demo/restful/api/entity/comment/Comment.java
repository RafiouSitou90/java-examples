package com.rafdev.iesb.demo.restful.api.entity.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rafdev.iesb.demo.restful.api.entity.BaseEntity;
import com.rafdev.iesb.demo.restful.api.entity.post.Post;
import com.rafdev.iesb.demo.restful.api.entity.user.User;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tab_comments")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String content;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @JsonBackReference
    @ManyToOne(
            targetEntity = User.class,
            cascade = CascadeType.ALL,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;

    @JsonBackReference
    @ManyToOne(
            targetEntity = Post.class,
            cascade = CascadeType.ALL,
            optional = false
    )
    @JoinColumn(
            name = "post_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Post post;

    public Comment(String content, Post post, User user) {
        super();
        this.content = content;
        this.post = post;
        this.user = user;
        this.publishedAt = LocalDateTime.now();
    }
}

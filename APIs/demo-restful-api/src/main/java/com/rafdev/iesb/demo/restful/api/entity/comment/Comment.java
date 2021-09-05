package com.rafdev.iesb.demo.restful.api.entity.comment;

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
}

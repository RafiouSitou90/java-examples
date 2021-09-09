package com.rafdev.iesb.demo.restful.api.entity.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.slugify.Slugify;
import com.rafdev.iesb.demo.restful.api.entity.BaseEntity;
import com.rafdev.iesb.demo.restful.api.entity.category.Category;
import com.rafdev.iesb.demo.restful.api.entity.comment.Comment;
import com.rafdev.iesb.demo.restful.api.entity.tag.Tag;

import com.rafdev.iesb.demo.restful.api.entity.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(PostListener.class)
@Table(name = "tab_posts")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;

    @NotBlank
    @Column(nullable = false)
    private String summary;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String content;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @JsonBackReference
    @ManyToOne(
            targetEntity = User.class,
            cascade = { CascadeType.PERSIST }
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

    @JsonBackReference
    @ManyToOne(
            targetEntity = Category.class,
            cascade = { CascadeType.PERSIST }
    )
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    private Category category;

    @JsonBackReference
    @ManyToMany(
            targetEntity = Tag.class,
            cascade = { CascadeType.PERSIST },
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "tab_post_tags",
            joinColumns = @JoinColumn(
                    name = "post_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "id"
            )
    )
    @ToString.Exclude
    private Set<Tag> tags = new HashSet<>();

    @JsonManagedReference
    @OneToMany(
            targetEntity = Comment.class,
            mappedBy = "post",
            cascade = { CascadeType.PERSIST }
    )
    @ToString.Exclude
    private Set<Comment> comments = new HashSet<>();

    public Post(String title, String summary, String content, String imageUrl, LocalDateTime publishedAt, User user, Category category) {
        super();
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.imageUrl = imageUrl;
        this.publishedAt = publishedAt;
        this.user = user;
        this.category = category;
    }

    public void computeSlug() {
        Slugify slugify = new Slugify();
        this.slug = slugify.slugify(title);
    }
}

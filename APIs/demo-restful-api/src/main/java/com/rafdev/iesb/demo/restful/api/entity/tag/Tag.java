package com.rafdev.iesb.demo.restful.api.entity.tag;

import com.rafdev.iesb.demo.restful.api.entity.post.Post;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tab_tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String name;

    @ManyToMany(
            targetEntity = Post.class,
            mappedBy = "tags",
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    private List<Post> posts;

    public Tag(String tag) {
        this.name = tag;
    }
}

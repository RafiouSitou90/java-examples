package com.rafdev.iesb.demo.restful.api.entity.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.slugify.Slugify;
import com.rafdev.iesb.demo.restful.api.entity.BaseEntity;
import com.rafdev.iesb.demo.restful.api.entity.post.Post;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(CategoryListener.class)
@Table(name = "tab_categories")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @JsonManagedReference
    @OneToMany(
            targetEntity = Post.class,
            mappedBy = "category",
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    private Set<Post> posts = new HashSet<>();

    public void computeSlug() {
        Slugify slugify = new Slugify();
        this.slug = slugify.slugify(name);
    }
}

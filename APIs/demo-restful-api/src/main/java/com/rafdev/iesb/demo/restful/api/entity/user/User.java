package com.rafdev.iesb.demo.restful.api.entity.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rafdev.iesb.demo.restful.api.entity.BaseEntity;
import com.rafdev.iesb.demo.restful.api.entity.comment.Comment;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "tab_users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 50)
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 4)
    @Column(nullable = false)
    private String password;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tab_user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    @JsonManagedReference
    @OneToMany(
            targetEntity = Comment.class,
            mappedBy = "user",
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE }
    )
    @ToString.Exclude
    private Set<Comment> comments = new HashSet<>();

    public User(String username, String email, String passwordHashed) {
        super();
        this.username = username;
        this.email = email;
        this.password = passwordHashed;
    }
}

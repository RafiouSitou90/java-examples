package com.rafdev.prova.demo.blog.repository;

import com.rafdev.prova.demo.blog.entity.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PostRepository {

    private final List<Post> posts;

    public PostRepository() {
        this.posts = new ArrayList<>();
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post save(Post post) {
        posts.add(post);

        return post;
    }

    public Post update(Post post) {
        Post postBD = findById(post.getId());

        if (postBD != null) {
            posts.set(posts.indexOf(postBD), post);
        }

        return post;
    }

    public void delete(Long id) {
        posts.removeIf(post -> Objects.equals(post.getId(), id));
    }

    public Post findById(Long id) {
        for (Post post: posts) {
            if (post.getId().equals(id)) {
                return post;
            }
        }

        return null;
    }

    public Post findByEmail(String title) {
        for (Post post: posts) {
            if (post.getTitle().equals(title)) {
                return post;
            }
        }

        return null;
    }

    public boolean existsByTitle(String title) {
        for (Post post: posts) {
            if (post.getTitle().equals(title)) {
                return true;
            }
        }

        return false;
    }

    public boolean existsById(Long id) {
        for (Post post: posts) {
            if (post.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }
}

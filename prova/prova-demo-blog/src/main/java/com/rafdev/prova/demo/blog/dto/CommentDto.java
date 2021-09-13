package com.rafdev.prova.demo.blog.dto;

import com.rafdev.prova.demo.blog.entity.Post;
import com.rafdev.prova.demo.blog.entity.User;

import java.util.Date;

public class CommentDto {
    private Long id;
    private String content;
    private String user;
    private String post;
    private Date publishedAt;

    public CommentDto(Long id, String content, User user, Post post, Date publishedAt) {
        this.id = id;
        this.content = content;

        if (user != null) {
            this.user = "/users/" + user.getId();
        }

        if (post != null) {
            this.post = "/posts/" + post.getId();
        }

        this.publishedAt = publishedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}

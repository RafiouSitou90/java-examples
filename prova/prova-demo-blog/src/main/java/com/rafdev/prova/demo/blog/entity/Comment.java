package com.rafdev.prova.demo.blog.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Comment {

    private Long id;
    private String content;
    private User user;
    private Post post;
    private Date publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment(Long id, String content, User user, Post post, Date publishedAt) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.post = post;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

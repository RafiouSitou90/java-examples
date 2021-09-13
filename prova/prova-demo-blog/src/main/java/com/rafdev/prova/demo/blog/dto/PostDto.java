package com.rafdev.prova.demo.blog.dto;

import com.rafdev.prova.demo.blog.entity.Category;
import com.rafdev.prova.demo.blog.entity.User;

import java.time.LocalDateTime;
import java.util.Date;

public class PostDto {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String user = null;
    private String category = null;
    private Date publishedAt;

    public PostDto(Long id, String title, String content, String imageUrl, User user, Category category, Date publishedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;

        if (user != null) {
            this.user = "/users/" + user.getId();
        }

        if (category != null) {
            this.category = "/categories/" + category.getId();
        }

        this.publishedAt = publishedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}

package com.rafdev.prova.demo.blog.service;

import com.rafdev.prova.demo.blog.dto.PostDto;
import com.rafdev.prova.demo.blog.payload.request.PostCreationRequest;

import java.util.List;

public interface PostService {
    PostDto savePost(PostCreationRequest postRequest);

    PostDto updatePostById(Long id, PostCreationRequest postRequest);

    List<PostDto> getPosts();

    PostDto getPostById(Long id);

    void deletePostById(Long id);
}

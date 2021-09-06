package com.rafdev.iesb.demo.restful.api.service;

import com.rafdev.iesb.demo.restful.api.entity.post.Post;
import com.rafdev.iesb.demo.restful.api.entity.post.PostPage;
import com.rafdev.iesb.demo.restful.api.payload.request.PostRequest;
import org.springframework.data.domain.Page;

public interface PostService {
    Post savePost(PostRequest postRequest);

    Page<Post> getPosts(PostPage postPage);

    Post getPostById(Long id);

    Post updatePostById(Long id, PostRequest postRequest);

    void deletePostById(Long id);
}

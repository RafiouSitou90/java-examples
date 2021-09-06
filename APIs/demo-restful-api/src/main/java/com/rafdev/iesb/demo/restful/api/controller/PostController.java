package com.rafdev.iesb.demo.restful.api.controller;

import com.rafdev.iesb.demo.restful.api.entity.post.Post;
import com.rafdev.iesb.demo.restful.api.entity.post.PostPage;
import com.rafdev.iesb.demo.restful.api.payload.request.PostRequest;
import com.rafdev.iesb.demo.restful.api.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    public ResponseEntity<Post> savePost(@Valid @RequestBody PostRequest postRequest) {
        return new ResponseEntity<>(postService.savePost(postRequest), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Page<Post>> getPosts(PostPage postPage) {
        return new ResponseEntity<>(postService.getPosts(postPage), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePostById(@PathVariable("id") Long id,
                                               @Valid @RequestBody PostRequest postRequest) {
        return new ResponseEntity<>(postService.updatePostById(id, postRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") Long id) {
        postService.deletePostById(id);

        return new ResponseEntity<>("Post deleted successfully!", HttpStatus.OK);
    }
}

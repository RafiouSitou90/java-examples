package com.rafdev.prova.demo.blog.service.impl;

import com.rafdev.prova.demo.blog.dto.PostDto;
import com.rafdev.prova.demo.blog.entity.Category;
import com.rafdev.prova.demo.blog.entity.Post;
import com.rafdev.prova.demo.blog.entity.User;
import com.rafdev.prova.demo.blog.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.demo.blog.exception.ResourceNotFoundException;
import com.rafdev.prova.demo.blog.payload.request.PostCreationRequest;
import com.rafdev.prova.demo.blog.repository.CategoryRepository;
import com.rafdev.prova.demo.blog.repository.PostRepository;
import com.rafdev.prova.demo.blog.repository.UserRepository;
import com.rafdev.prova.demo.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public PostServiceImpl() {
        this.postRepository = new PostRepository();
        this.categoryRepository = new CategoryRepository();
        this.userRepository = new UserRepository();
    }

    @Override
    public PostDto savePost(PostCreationRequest postRequest) {
        if (postRepository.existsById(postRequest.getId())) {
            throw new ResourceAlreadyExistsException("Post", "Id", postRequest.getId());
        }

        if (postRepository.existsByTitle(postRequest.getTitle())) {
            throw new ResourceAlreadyExistsException("Post", "Title", postRequest.getTitle());
        }

        User user = userRepository.findById(postRequest.getUserId());

        if (user == null) {
            throw new ResourceNotFoundException("User", "Id", postRequest.getUserId());
        }

        Category category = null;
        if (postRequest.getCategoryId() != null) {
            category = categoryRepository.findById(postRequest.getCategoryId());

            if (category == null) {
                throw new ResourceNotFoundException("Category", "Id", postRequest.getCategoryId());
            }
        }

        Post post = new Post(postRequest.getId(), postRequest.getTitle(), postRequest.getContent(),
                postRequest.getImageUrl(), user, category, postRequest.getPublishedAt());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);

        Post postCreated = postRepository.save(post);

        return setPostDto(postCreated);
    }

    @Override
    public PostDto updatePostById(Long id, PostCreationRequest postRequest) {
        Post postFound = postRepository.findById(id);

        if (postFound == null) {
            throw new ResourceNotFoundException("Post", "Id", null);
        }

        Category category = postFound.getCategory();
        if (postRequest.getCategoryId() != null) {
            category = categoryRepository.findById(postRequest.getCategoryId());

            if (category == null) {
                throw new ResourceNotFoundException("Category", "Id", postRequest.getCategoryId());
            }
        }

        postFound.setTitle(postRequest.getTitle());
        postFound.setContent(postRequest.getContent());
        postFound.setImageUrl(postRequest.getImageUrl());
        postFound.setCategory(category);
        postFound.setUpdatedAt(LocalDateTime.now());

        Post postUpdated = postRepository.update(postFound);

        return setPostDto(postUpdated);
    }

    @Override
    public List<PostDto> getPosts() {
        List<PostDto> postsDto = new ArrayList<>();
        List<Post> posts = postRepository.findAll();

        for(Post post: posts) {
            postsDto.add(setPostDto(post));
        }

        return postsDto;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id);

        if (post == null) {
            throw new ResourceNotFoundException("Post", "Id", id);
        }

        return setPostDto(post);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id);

        if (post == null) {
            throw new ResourceNotFoundException("Post", "Id", id);
        }

        postRepository.delete(post.getId());
    }

    private PostDto setPostDto(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getImageUrl(), post.getUser(),
                post.getCategory(), post.getPublishedAt());
    }
}
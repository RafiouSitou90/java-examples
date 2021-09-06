package com.rafdev.iesb.demo.restful.api.service.impl;

import com.rafdev.iesb.demo.restful.api.entity.category.Category;
import com.rafdev.iesb.demo.restful.api.entity.post.Post;
import com.rafdev.iesb.demo.restful.api.entity.post.PostPage;
import com.rafdev.iesb.demo.restful.api.entity.tag.Tag;
import com.rafdev.iesb.demo.restful.api.entity.user.User;
import com.rafdev.iesb.demo.restful.api.exception.ResourceNotFoundException;
import com.rafdev.iesb.demo.restful.api.payload.request.PostRequest;
import com.rafdev.iesb.demo.restful.api.repository.CategoryRepository;
import com.rafdev.iesb.demo.restful.api.repository.PostRepository;
import com.rafdev.iesb.demo.restful.api.repository.TagRepository;
import com.rafdev.iesb.demo.restful.api.repository.UserRepository;
import com.rafdev.iesb.demo.restful.api.service.PostService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    private final String resourceName = "Post";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository,
                           CategoryRepository categoryRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    @Transactional
    @Override
    public Post savePost(PostRequest postRequest) {
        User user = userRepository.findById(postRequest.getUser())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", postRequest.getUser()));

        Category category = categoryRepository.findById(postRequest.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", postRequest.getCategory()));

        Post post = new Post(postRequest.getTitle(), postRequest.getSummary(), postRequest.getContent(),
                postRequest.getImageUrl(), postRequest.getPublishedAt(), user, category);

        Set<String> strTags = postRequest.getTags();
        Set<Tag> tags = new HashSet<>();

        if (strTags != null) {
            strTags.forEach(tag -> {
                Optional<Tag> aTag = tagRepository.findByName(tag);
                if (aTag.isEmpty()) {
                    Tag newTag = tagRepository.save(new Tag(tag));
                    tags.add(newTag);
                } else {
                    tags.add(aTag.get());
                }
            });

            post.setTags(tags);
        }

        return postRepository.save(post);
    }

    @Override
    public Page<Post> getPosts(PostPage postPage) {
        Sort sort = Sort.by(postPage.getSortDirection(), postPage.getSortBy());
        Pageable pageable = PageRequest.of(postPage.getPageNumber(), postPage.getPageSize(), sort);

        return postRepository.findAll(pageable);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(resourceName, "Id", id));
    }

    @Transactional
    @Override
    public Post updatePostById(Long id, PostRequest postRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, "Id", id));

        User user = userRepository.findById(postRequest.getUser())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", postRequest.getUser()));

        Category category = categoryRepository.findById(postRequest.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", postRequest.getCategory()));

        post.setTitle(postRequest.getTitle());
        post.setSummary(postRequest.getSummary());
        post.setContent(postRequest.getContent());
        post.setImageUrl(postRequest.getImageUrl());
        post.setPublishedAt(postRequest.getPublishedAt());
        post.setCategory(category);
        post.setUser(user);

        Set<String> strTags = postRequest.getTags();
        Set<Tag> tags = new HashSet<>();

        if (strTags != null) {
            if (!post.getTags().isEmpty()) {
                post.getTags().removeAll(post.getTags());
            }
            strTags.forEach(tag -> {
                Optional<Tag> aTag = tagRepository.findByName(tag);
                if (aTag.isEmpty()) {
                    Tag newTag = tagRepository.save(new Tag(tag));
                    tags.add(newTag);
                } else {
                    tags.add(aTag.get());
                }
            });

            post.setTags(tags);
        }

        return postRepository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, "Id", id));

        postRepository.delete(post);
    }
}

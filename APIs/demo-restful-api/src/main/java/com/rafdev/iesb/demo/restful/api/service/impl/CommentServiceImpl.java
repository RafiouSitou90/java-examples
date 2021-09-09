package com.rafdev.iesb.demo.restful.api.service.impl;

import com.rafdev.iesb.demo.restful.api.entity.comment.Comment;
import com.rafdev.iesb.demo.restful.api.entity.comment.CommentPage;
import com.rafdev.iesb.demo.restful.api.entity.post.Post;
import com.rafdev.iesb.demo.restful.api.entity.post.PostPage;
import com.rafdev.iesb.demo.restful.api.entity.user.User;
import com.rafdev.iesb.demo.restful.api.exception.ResourceNotFoundException;
import com.rafdev.iesb.demo.restful.api.payload.request.CommentRequest;
import com.rafdev.iesb.demo.restful.api.repository.CommentRepository;
import com.rafdev.iesb.demo.restful.api.service.CommentService;
import com.rafdev.iesb.demo.restful.api.service.PostService;
import com.rafdev.iesb.demo.restful.api.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final String resourceName = "Comment";
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, PostService postService, UserService userService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userService = userService;
    }

    @Override
    public Comment saveComment(CommentRequest commentRequest) {
        Post post = postService.getPostById(commentRequest.getPostId());
        User user = userService.getUserById(commentRequest.getUserId());

        Comment comment = new Comment(commentRequest.getContent(), post, user);

        return commentRepository.save(comment);
    }

    @Override
    public Page<Comment> getComments(CommentPage commentPage) {
        Sort sort = Sort.by(commentPage.getSortDirection(), commentPage.getSortBy());
        Pageable pageable = PageRequest.of(commentPage.getPageNumber(), commentPage.getPageSize(), sort);

        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(resourceName, "Id", id));
    }

    @Override
    public Comment updateCommentById(Long id, CommentRequest commentRequest) {
        Comment commentToUpdated = getCommentById(id);

        Post post = postService.getPostById(commentRequest.getPostId());
        User user = userService.getUserById(commentRequest.getUserId());

        commentToUpdated.setContent(commentRequest.getContent());
        commentToUpdated.setUser(user);
        commentToUpdated.setPost(post);

        return commentRepository.save(commentToUpdated);
    }

    @Override
    public void deleteCommentById(Long id) {
        Comment comment = getCommentById(id);

        commentRepository.delete(comment);
    }
}

package com.rafdev.iesb.demo.restful.api.controller;

import com.rafdev.iesb.demo.restful.api.entity.comment.Comment;
import com.rafdev.iesb.demo.restful.api.entity.comment.CommentPage;
import com.rafdev.iesb.demo.restful.api.payload.request.CommentRequest;
import com.rafdev.iesb.demo.restful.api.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("")
    public ResponseEntity<Comment> saveComment(@Valid @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(commentService.saveComment(commentRequest), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Page<Comment>> getComments(CommentPage commentPage) {
        return new ResponseEntity<>(commentService.getComments(commentPage), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateCommentById(@PathVariable("id") Long id,
                                                     @Valid @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(commentService.updateCommentById(id, commentRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("id") Long id) {
        commentService.deleteCommentById(id);

        return new ResponseEntity<>("Comment deleted successfully!", HttpStatus.CREATED);
    }
}

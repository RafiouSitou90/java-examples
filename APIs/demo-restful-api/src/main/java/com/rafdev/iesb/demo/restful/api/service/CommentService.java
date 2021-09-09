package com.rafdev.iesb.demo.restful.api.service;

import com.rafdev.iesb.demo.restful.api.entity.comment.Comment;
import com.rafdev.iesb.demo.restful.api.entity.comment.CommentPage;
import com.rafdev.iesb.demo.restful.api.payload.request.CommentRequest;
import org.springframework.data.domain.Page;

public interface CommentService {
    Comment saveComment(CommentRequest commentRequest);

    Page<Comment> getComments(CommentPage commentPage);

    Comment getCommentById(Long id);

    Comment updateCommentById(Long id, CommentRequest commentRequest);

    void deleteCommentById(Long id);
}

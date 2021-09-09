package com.rafdev.iesb.demo.restful.api.repository;

import com.rafdev.iesb.demo.restful.api.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

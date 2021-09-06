package com.rafdev.iesb.demo.restful.api.repository;

import com.rafdev.iesb.demo.restful.api.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

package com.vitanur.blogservice.models.repository;

import com.vitanur.blogservice.models.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
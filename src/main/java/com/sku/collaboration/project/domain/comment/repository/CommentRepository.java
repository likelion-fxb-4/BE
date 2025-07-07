package com.sku.collaboration.project.domain.comment.repository;

import com.sku.collaboration.project.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

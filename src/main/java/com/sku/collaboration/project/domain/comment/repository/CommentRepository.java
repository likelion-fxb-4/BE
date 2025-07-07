package com.sku.collaboration.project.domain.comment.repository;

import com.sku.collaboration.project.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteByPostId(Long postId);

    List<Comment> findAllByPostId(Long postId);
}

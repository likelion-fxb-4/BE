package com.sku.collaboration.project.domain.comment_likes.repository;

import com.sku.collaboration.project.domain.comment_likes.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
}

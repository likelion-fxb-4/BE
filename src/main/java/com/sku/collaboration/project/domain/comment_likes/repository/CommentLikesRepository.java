package com.sku.collaboration.project.domain.comment_likes.repository;

import com.sku.collaboration.project.domain.comment_likes.entity.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
}

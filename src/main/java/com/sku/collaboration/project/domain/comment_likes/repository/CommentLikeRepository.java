package com.sku.collaboration.project.domain.comment_likes.repository;

import com.sku.collaboration.project.domain.comment.entity.Comment;
import com.sku.collaboration.project.domain.comment_likes.entity.CommentLike;
import com.sku.collaboration.project.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
  boolean existsByCommentIdAndUserId(Long commentId, Long userId);
  int countByCommentAndIsDeletedFalse(Comment comment);
  Optional<CommentLike> findByCommentAndUser(Comment comment, User user);
}

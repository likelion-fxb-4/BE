package com.sku.collaboration.project.domain.comment_likes.service;

import com.sku.collaboration.project.domain.comment.entity.Comment;
import com.sku.collaboration.project.domain.comment.repository.CommentRepository;
import com.sku.collaboration.project.domain.comment_likes.dto.request.CommentLikeRequest;
import com.sku.collaboration.project.domain.comment_likes.dto.response.CommentLikeResponse;
import com.sku.collaboration.project.domain.comment_likes.entity.CommentLike;
import com.sku.collaboration.project.domain.comment_likes.repository.CommentLikeRepository;
import com.sku.collaboration.project.domain.post.entity.Post;
import com.sku.collaboration.project.domain.post.exception.PostErrorCode;
import com.sku.collaboration.project.domain.post.repository.PostRepository;
import com.sku.collaboration.project.domain.post_like.dto.request.PostLikeRequest;
import com.sku.collaboration.project.domain.post_like.dto.response.PostLikeResponse;
import com.sku.collaboration.project.domain.post_like.entity.PostLike;
import com.sku.collaboration.project.domain.post_like.repository.PostLikeRepository;
import com.sku.collaboration.project.domain.user.entity.User;
import com.sku.collaboration.project.domain.user.exception.UserErrorCode;
import com.sku.collaboration.project.domain.user.repository.UserRepository;
import com.sku.collaboration.project.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j

public class CommentLikeService {


  private final CommentLikeRepository commentLikeRepository;
  private final CommentRepository commentRepository;
  private final UserRepository userRepository;


  @Transactional
  public void  likeComment(Long userId, Long commentId, CommentLikeRequest request) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

    if (commentLikeRepository.existsByCommentIdAndUserId(commentId, userId)) {
      boolean requestIsLike= request.getLike();
      log.info("[서비스] 이미 좋아요를 누른 게시글에 대해 좋아요 요청: requestIsLike={}", requestIsLike);
      CommentLike commentLike = commentLikeRepository.findByCommentAndUser(comment, user)
          .orElseThrow(() -> new CustomException(PostErrorCode.POST_LIKE_NOT_FOUND));
      commentLike.setIsDeleted(!requestIsLike);
    } else {

      if (!request.getLike()) {
        log.info("[서비스] 댓글 좋아요 취소: userId= {}, commentId= {}", userId, commentId);
        return;
      }
      CommentLike commentLike = CommentLike.builder()
          .user(user)
          .comment(comment)
          .isDeleted(false)
          .build();

      commentLikeRepository.save(commentLike);
    }
  }

  public CommentLikeResponse getCommentLikeCount(Long commentId) {
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));
    return CommentLikeResponse.builder()
        .likeCount(commentLikeRepository.countByCommentAndIsDeletedFalse(comment))
        .build();
  }

}

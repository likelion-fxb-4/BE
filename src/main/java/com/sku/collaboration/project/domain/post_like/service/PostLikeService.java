package com.sku.collaboration.project.domain.post_like.service;

import com.sku.collaboration.project.domain.post.dto.request.CreatePostRequest;
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
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

  private final PostLikeRepository postLikeRepository;
  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Transactional
  public void  likePost(Long userId, Long postId, PostLikeRequest request) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

    if (postLikeRepository.existsByPostIdAndUserId(postId, userId)) {
      boolean requestIsLike= request.getLike();
      log.info("[서비스] 이미 좋아요를 누른 게시글에 대해 좋아요 요청: requestIsLike={}", requestIsLike);
      PostLike oldPostLike = postLikeRepository.findByPostAndUser(post, user)
          .orElseThrow(() -> new CustomException(PostErrorCode.POST_LIKE_NOT_FOUND));
      oldPostLike.setIsDelete(!requestIsLike);
    } else {

      if (!request.getLike()) {
        log.info("[서비스] 게시글 좋아요 취소: userId= {}, postId= {}", userId, postId);
        return;
      }
      PostLike postLike = PostLike.builder()
          .user(user)
          .post(post)
          .isDelete(false)
          .build();

      postLikeRepository.save(postLike);
    }
  }

  public PostLikeResponse getPostLikeCount(Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));
    return PostLikeResponse.builder()
        .likeCount(postLikeRepository.countByPostAndIsDeleteFalse(post))
        .build();
  }




}





package com.sku.collaboration.project.domain.post_like.repository;

import com.sku.collaboration.project.domain.post.entity.Post;
import com.sku.collaboration.project.domain.post_like.entity.PostLike;
import com.sku.collaboration.project.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
  boolean existsByPostIdAndUserId(Long postId, Long userId);
  int countByPostAndIsDeleteFalse(Post post);
  Optional<PostLike> findByPostAndUser(Post post, User user);
}

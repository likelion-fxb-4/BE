package com.sku.collaboration.project.domain.post.repository;

import com.sku.collaboration.project.domain.post.entity.Post;
import com.sku.collaboration.project.domain.post.enums.PostType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findTop3ByPostTypeOrderByCreatedAtDesc(PostType postType);

  List<Post> findAllByPostTypeOrderByCreatedAtDesc(PostType postType);
}

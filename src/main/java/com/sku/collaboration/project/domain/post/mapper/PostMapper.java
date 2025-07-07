package com.sku.collaboration.project.domain.post.mapper;

import com.sku.collaboration.project.domain.post.dto.response.PostDetailResponse;
import com.sku.collaboration.project.domain.post.dto.response.PostSummaryResponse;
import com.sku.collaboration.project.domain.post.entity.Post;

public class PostMapper {

  public static PostSummaryResponse toPostSummary(Post post) {
    return PostSummaryResponse.builder()
        .id(post.getId())
        .author(post.getUser().getName())
        .title(post.getTitle())
        .modifiedAt(post.getModifiedAt())
        .build();
  }

  public static PostDetailResponse toPostDetail(Post post) {
    return PostDetailResponse.builder()
        .id(post.getId())
        .author(post.getUser().getName())
        .title(post.getTitle())
        .modifiedAt(post.getModifiedAt())
        .build();
  }
}

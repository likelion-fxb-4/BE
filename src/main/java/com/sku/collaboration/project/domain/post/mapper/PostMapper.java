package com.sku.collaboration.project.domain.post.mapper;

import com.sku.collaboration.project.domain.post.dto.response.PostDetailResponse;
import com.sku.collaboration.project.domain.post.dto.response.PostListResponse;
import com.sku.collaboration.project.domain.post.dto.response.PostSummaryResponse;
import com.sku.collaboration.project.domain.post.entity.Post;

public class PostMapper {

  public static PostSummaryResponse toPostSummary(Post post) {
    return PostSummaryResponse.builder()
        .id(post.getId())
        .title(post.getTitle())
        .build();
  }

  public static PostDetailResponse toPostDetail(Post post) {
    return PostDetailResponse.builder()
        .id(post.getId())
        .author(post.getIsAnonymous() ? post.getUser().getName() : "익명")
        .title(post.getTitle())
        .content(post.getContent())
        .build();
  }

  public static PostListResponse toPostList(Post post) {
    return PostListResponse.builder()
        .id(post.getId())
        .author(post.getIsAnonymous() ? post.getUser().getName() : "익명")
        .title(post.getTitle())
        .summaryContent(post.getContent().length() > 50 ? post.getContent().substring(0, 50) + "..." : post.getContent())
        .build();
  }
}

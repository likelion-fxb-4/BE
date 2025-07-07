package com.sku.collaboration.project.domain.comment_likes.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Builder;


@Getter
@Builder
@Schema(title = "CommentLikeResponse DTO", description = "해당 댓글에 대한 총 좋아요 개수")

public class CommentLikeResponse {

  @Schema(description = "좋아요 총 개수", example = "5")
  private Integer likeCount;
}
  
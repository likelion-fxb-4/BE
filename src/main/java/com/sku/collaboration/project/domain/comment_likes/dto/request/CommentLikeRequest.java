package com.sku.collaboration.project.domain.comment_likes.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(title = "CommentLikeRequest DTO", description = "댓글 좋아요 요청")

public class CommentLikeRequest {

  @NotNull(message = "좋아요 여부는 필수입니다.")
  @Schema(description = "좋아요 여부", example = "true")
  private Boolean like;
}
  
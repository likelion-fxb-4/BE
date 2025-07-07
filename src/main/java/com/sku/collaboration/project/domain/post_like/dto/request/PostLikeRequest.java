package com.sku.collaboration.project.domain.post_like.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(title = "PostLikeRequest DTO", description = "게시글 좋아요 요청")

public class PostLikeRequest {

  @NotNull(message = "좋아요 여부는 필수입니다.")
  @Schema(description = "좋아요 여부", example = "true")
  private Boolean like;
}
  
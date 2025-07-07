package com.sku.collaboration.project.domain.post_like.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(title = "PostLikeRequest DTO", description = "게시글 w데이터 전송")

public class PostLikeRequest {

  @NotBlank(message = "게시글 제목 항목은 필수입니다.")
  @Schema(description = "게시글 제목", example = "제목입니다.")
  private String title;
}
  
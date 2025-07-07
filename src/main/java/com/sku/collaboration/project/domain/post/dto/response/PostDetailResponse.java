package com.sku.collaboration.project.domain.post.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Builder;


@Getter
@Builder
@Schema(title = "PostDetailResponse DTO", description = "게시글 상세 정보 응답 반환")

public class PostDetailResponse {

  @Schema(description = "게시글 ID", example = "1")
  private Long id;

  @Schema(description = "작성자 이름", example = "문재연")
  private String author;

  @Schema(description = "게시글 제목", example = "해커톤")
  private String title;

  @Schema(description="마지막 수정 시간", example = "2023-10-01T12:00:00")
  private LocalDateTime modifiedAt;


}
  
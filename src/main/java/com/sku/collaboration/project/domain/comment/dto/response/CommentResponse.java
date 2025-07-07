package com.sku.collaboration.project.domain.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "CommentResponse DTO", description = "댓글 조회에 대한 응답 반환")
public class CommentResponse {

    @Schema(description = "게시글 ID", example = "1")
    private Long postId;

    @Schema(description = "유저 ID", example = "1")
    private Long userId;

    @Schema(description = "댓글 내용", example = "안녕하세요")
    private String content;

    @Schema(description = "익명 여부", example = "true")
    private Boolean isAnonymous;
}

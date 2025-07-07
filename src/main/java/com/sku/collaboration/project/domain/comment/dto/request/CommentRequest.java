package com.sku.collaboration.project.domain.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "CreateCommentRequest DTO", description = "댓글 작성 요청용 데이터")
public class CommentRequest {
    @Schema(description = "내용", example = "글 잘 봤습니다")
    private String content;

    @Schema(description = "익명 여부", example = "true")
    private Boolean isAnonymous;
}

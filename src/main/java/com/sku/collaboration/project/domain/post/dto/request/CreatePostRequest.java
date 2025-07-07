package com.sku.collaboration.project.domain.post.dto.request;


import com.sku.collaboration.project.domain.post.enums.PostType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "CreatePostRequest DTO", description = "게시글 작성 요청용 데이터")
public class CreatePostRequest {

    @NotBlank(message = "제목 입력은 필수입니다.")
    @Schema(description = "제목", example = "해커톤")
    private String title;

    @NotBlank(message = "내용 입력은 필수입니다.")
    @Schema(description = "내용", example = "화이팅")
    private String content;

    @NotNull(message = "익명 여부 입력은 필수입니다.")
    @Schema(description = "익명 여부", example = "true")
    private Boolean isAnonymous;
    
    @NotNull(message = "게시글 종류 입력은 필수입니다.")
    @Schema(description = "게시글 종류", example = "free")
    private PostType postType;



}

package com.sku.collaboration.project.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "LanguageUpdateRequest DTO", description = "언어 변경을 위한 데이터 전송")
public class LanguageUpdateRequest {

  @NotBlank(message = "새로운 언어 항목은 필수입니다.")
  @Schema(description = "새로운 언어", example = "EN")
  private String newLanguage;
}

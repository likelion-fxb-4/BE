package com.sku.collaboration.project.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "LoginResponse DTO", description = "사용자 로그인에 대한 응답 반환")
public class LoginResponse {

  @Schema(description = "사용자 Access Token")
  private String accessToken;

  @Schema(description = "사용자 ID", example = "1")
  private Long userId;

  @Schema(description = "사용자 이메일", example = "user123@gmail.com")
  private String username;

  @Schema(description = "사용자 이름", example = "문재연")
  private String name;

  @Schema(description = "사용자 Access Token 만료 시간", example = "1800000")
  private Long expirationTime;
}

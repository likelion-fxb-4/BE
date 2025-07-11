package com.sku.collaboration.project.domain.post.exception;


import com.sku.collaboration.project.global.exception.model.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PostErrorCode implements BaseErrorCode {
  POST_NOT_FOUND("POST_4041", "해당 게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  NOT_POST_OWNER("POST_4031", "게시글 작성자가 아닙니다.", HttpStatus.FORBIDDEN),
  ALREADY_LIKED("POST_4091", "이미 좋아요를 누른 게시글입니다.", HttpStatus.BAD_REQUEST),
  POST_LIKE_NOT_FOUND("POST_4042", "해당 게시글에 대한 좋아요를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

  private final String code;
  private final String message;
  private final HttpStatus status;
}

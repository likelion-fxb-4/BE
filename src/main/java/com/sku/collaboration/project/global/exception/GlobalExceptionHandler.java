package com.sku.collaboration.project.global.exception;

import com.sku.collaboration.project.global.exception.model.BaseErrorCode;
import com.sku.collaboration.project.global.response.BaseResponse;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  // 커스텀 예외
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<BaseResponse<Object>> handleCustomException(CustomException ex) {
    BaseErrorCode errorCode = ex.getErrorCode();
    log.error("Custom 오류 발생: {}", ex.getMessage());
    return ResponseEntity
        .status(errorCode.getStatus())
        .body(BaseResponse.error(errorCode.getStatus().value(), ex.getMessage()));
  }


  // 권한 부족 예외
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<BaseResponse<Object>> handleAccessDeniedException(
      AccessDeniedException ex) {
    log.warn("권한 부족 오류 발생: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(BaseResponse.error(403, "권한이 없습니다."));
  }

  // SQL 예외
  @ExceptionHandler(SQLException.class)
  public ResponseEntity<BaseResponse<Object>> handleDatabaseExceptions(Exception ex) {
    log.error("DB 예외 발생: ", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(BaseResponse.error(500, "데이터베이스 처리 중 오류가 발생했습니다."));
  }


  // 지원하지 않는 HTTP 메서드 예외
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<BaseResponse<Object>> handleMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex) {
    log.warn("지원하지 않는 HTTP 메서드 오류 발생: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .body(BaseResponse.error(405, "지원하지 않는 HTTP 메서드입니다."));
  }


  // 예상치 못한 예외
  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseResponse<Object>> handleException(Exception ex) {
    log.error("Server 오류 발생: ", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(BaseResponse.error(500, "예상치 못한 서버 오류가 발생했습니다."));
  }
}

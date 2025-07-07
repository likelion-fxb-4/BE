package com.sku.collaboration.project.domain.post.controller;

import com.sku.collaboration.project.domain.post.dto.request.CreatePostRequest;
import com.sku.collaboration.project.domain.post.dto.response.PostDetailResponse;
import com.sku.collaboration.project.domain.post.service.PostService;
import com.sku.collaboration.project.global.response.BaseResponse;
import com.sku.collaboration.project.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/posts")
@Tag(name = "Post", description = "Post 관련 API")
public class PostController {

  private final PostService postService;

  @Operation(
      summary = "게시글 생성",
      description = "게시글 작성 후 생성 버튼을 눌렀을 때 요청되는 API")
  @PostMapping("/posts")
  public ResponseEntity<BaseResponse<PostDetailResponse>> createPost(
      @AuthenticationPrincipal CustomUserDetails userDetails,
      @Parameter(description = "게시글 작성 내용")
      @RequestBody @Valid CreatePostRequest createPostRequest) {
    PostDetailResponse response = postService.createPost(userDetails.getUser(),createPostRequest);
    return ResponseEntity.ok(BaseResponse.success("게시글 생성 성공", response));
  }


}

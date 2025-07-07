package com.sku.collaboration.project.domain.post.controller;

import com.sku.collaboration.project.domain.post.dto.request.CreatePostRequest;
import com.sku.collaboration.project.domain.post.dto.response.PostDetailResponse;
import com.sku.collaboration.project.domain.post.dto.response.PostListResponse;
import com.sku.collaboration.project.domain.post.dto.response.PostSummaryResponse;
import com.sku.collaboration.project.domain.post.service.PostService;
import com.sku.collaboration.project.global.response.BaseResponse;
import com.sku.collaboration.project.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/posts")
@Tag(name = "Post", description = "Post 관련 API")
public class PostController {

  private final PostService postService;

  @Operation(
      summary = "게시글 생성",
      description = "게시글 작성 후 생성 버튼을 눌렀을 때 요청되는 API")
  @PostMapping
  public ResponseEntity<BaseResponse<Void>> createPost(
      @AuthenticationPrincipal CustomUserDetails userDetails,
      @Parameter(description = "게시글 작성 내용")
      @RequestBody @Valid CreatePostRequest createPostRequest) {
    postService.createPost(userDetails.getUser().getId(),createPostRequest);
    return ResponseEntity.ok(BaseResponse.success("게시글 생성 성공", null));
  }

  @Operation(
      summary = "게시글 수정",
      description = "게시글 수정 후 수정 버튼을 눌렀을 때 요청되는 API")
  @PutMapping("/{postId}")
  public ResponseEntity<BaseResponse<Void>> updatePost(
      @AuthenticationPrincipal CustomUserDetails userDetails,
      @Parameter(description = "게시글 ID")
      @PathVariable Long postId,
      @Parameter(description = "게시글 수정 내용")
      @RequestBody @Valid CreatePostRequest updatePostRequest) {
    postService.updatePost(userDetails.getUser().getId(), postId, updatePostRequest);
    return ResponseEntity.ok(BaseResponse.success("게시글 수정 성공", null));
  }

  @Operation(
      summary = "게시글 삭제",
      description = "게시글 삭제 버튼을 눌렀을 때 요청되는 API")
  @DeleteMapping("/{postId}")
  public ResponseEntity<BaseResponse<Void>> deletePost(
      @AuthenticationPrincipal CustomUserDetails userDetails,
      @Parameter(description = "게시글 ID")
      @PathVariable Long postId) {
    postService.deletePost(userDetails.getUser().getId(), postId);
    return ResponseEntity.ok(BaseResponse.success("게시글 삭제 성공", null));
  }

  @Operation(
      summary = "자유게시판 게시글 요약 조회",
      description = "메인 화면에서 자유게시판 게시글 요약 정보를 조회하는 API")
  @GetMapping("/free/summary")
  public ResponseEntity<BaseResponse<List<PostSummaryResponse>>> getFreeSummaryPostList() {
    List<PostSummaryResponse> postSummaryList = postService.getFreeSummaryPostList();
    return ResponseEntity.ok(BaseResponse.success("게시글 요약 조회 성공", postSummaryList));
  }

  @Operation(
      summary = "비밀게시판 게시글 요약 조회",
      description = "메인 화면에서 비밀게시판 게시글 요약 정보를 조회하는 API")
  @GetMapping("/secret/summary")
  public ResponseEntity<BaseResponse<List<PostSummaryResponse>>> getSecretSummaryPostList() {
    List<PostSummaryResponse> postSummaryList = postService.getSecretSummaryPostList();
    return ResponseEntity.ok(BaseResponse.success("게시글 요약 조회 성공", postSummaryList));
  }

  @Operation(
      summary = "자유게시판 게시글 리스트 조회",
      description = "자유게시판 게시글 리스트를 조회하는 API")
  @GetMapping("/free")
  public ResponseEntity<BaseResponse<List<PostListResponse>>> getFreePostList() {
    List<PostListResponse> postList = postService.getFreePostList();
    return ResponseEntity.ok(BaseResponse.success("게시글 리스트 조회 성공", postList));
  }

  @Operation(
      summary = "비밀게시판 게시글 리스트 조회",
      description = "비밀게시판 게시글 리스트를 조회하는 API")
  @GetMapping("/secret")
  public ResponseEntity<BaseResponse<List<PostListResponse>>> getSecretPostList() {
    List<PostListResponse> postList = postService.getFreePostList();
    return ResponseEntity.ok(BaseResponse.success("게시글 리스트 조회 성공", postList));
  }

  // 게시글 단일 조회
  @Operation(
      summary = "자유게시판 게시글 단일 조회",
      description = "게시글 ID로 게시글을 조회하는 API")
  @GetMapping("/free/{postId}")
  public ResponseEntity<BaseResponse<PostDetailResponse>> getFreePostById(
      @Parameter(description = "게시글 ID")
      @PathVariable Long postId) {
    PostDetailResponse postDetailResponse = postService.getPostDetail(postId);
    return ResponseEntity.ok(BaseResponse.success("게시글 단일 조회 성공", postDetailResponse));
  }

  @Operation(
      summary = "비밀 게시판 게시글 단일 조회",
      description = "게시글 ID로 게시글을 조회하는 API")
  @GetMapping("/secret/{postId}")
  public ResponseEntity<BaseResponse<PostDetailResponse>> getSecretPostById(
      @Parameter(description = "게시글 ID")
      @PathVariable Long postId) {
    PostDetailResponse postDetailResponse = postService.getPostDetail(postId);
    return ResponseEntity.ok(BaseResponse.success("게시글 단일 조회 성공", postDetailResponse));
  }


}

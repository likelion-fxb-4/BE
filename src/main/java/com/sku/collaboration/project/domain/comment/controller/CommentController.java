package com.sku.collaboration.project.domain.comment.controller;

import com.sku.collaboration.project.domain.comment.dto.request.CommentRequest;
import com.sku.collaboration.project.domain.comment.dto.response.CommentResponse;
import com.sku.collaboration.project.domain.comment.service.CommentService;
import com.sku.collaboration.project.domain.comment_likes.dto.request.CommentLikeRequest;
import com.sku.collaboration.project.domain.comment_likes.dto.response.CommentLikeResponse;
import com.sku.collaboration.project.domain.comment_likes.service.CommentLikeService;
import com.sku.collaboration.project.domain.user.dto.request.SignUpRequest;
import com.sku.collaboration.project.domain.user.dto.response.SignUpResponse;
import com.sku.collaboration.project.global.response.BaseResponse;
import com.sku.collaboration.project.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/comments")
@Tag(name = "Comment", description = "Comment 관리 API")
public class CommentController {
    private final CommentService commentService;
    private final CommentLikeService commentLikeService;

    @Operation(summary = "댓글 작성 API", description = "사용자 댓글 작성을 위한 API")
    @PostMapping("/{postId}")
    public ResponseEntity<BaseResponse<Boolean>> addComment(
            @PathVariable Long postId,
            @RequestBody @Valid CommentRequest commentRequest,
    @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getUser().getId();
        Boolean response = commentService.addComment(postId, userId, commentRequest);
        return ResponseEntity.ok(BaseResponse.success("댓글 작성이이 완료되었습니다.", response));
    }

    @Operation(summary = "댓글 삭제 API", description = "사용자 댓글 삭제를 위한 API")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<BaseResponse<Boolean>> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getUser().getId();
        Boolean response = commentService.deleteComment(commentId, userId);
        return ResponseEntity.ok(BaseResponse.success("댓글 삭제가 완료되었습니다.", response));
    }

    @Operation(summary = "특정 게시글 댓글 조회 API", description = "특정 게시글 댓글 조회를 위한 API")
    @GetMapping("/{postId}")
    public ResponseEntity<BaseResponse<List<CommentResponse>>> Comment(@PathVariable Long postId) {
        List<CommentResponse> responses = commentService.getComments(postId);
        return ResponseEntity.ok(BaseResponse.success("댓글 조회가 완료되었습니다.", responses));
    }


    @Operation(summary = "댓글 좋아요 API", description = "댓글에 좋아요를 누르거나 취소하는 API")
    @PostMapping("/{commentId}/like")
    public ResponseEntity<BaseResponse<Void>> likeComment(
            @PathVariable Long commentId,
            @RequestBody @Valid CommentLikeRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getUser().getId();
        return ResponseEntity.ok(BaseResponse.success("댓글 좋아요가 완료되었습니다.", null));
    }
    @Operation(summary = "댓글 좋아요 수 조회 API", description = "특정 댓글의 좋아요 수를 조회하는 API")
    @GetMapping("/{commentId}/like/count")
    public ResponseEntity<BaseResponse<CommentLikeResponse>> getCommentLikeCount(
            @PathVariable Long commentId) {
        CommentLikeResponse response = commentLikeService.getCommentLikeCount(commentId);
        return ResponseEntity.ok(BaseResponse.success("댓글 좋아요 수 조회가 완료되었습니다.", response));
    }
}

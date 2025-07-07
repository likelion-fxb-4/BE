package com.sku.collaboration.project.domain.comment.controller;

import com.sku.collaboration.project.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
@Tag(name = "Comment", description = "Comment 관리 API")
public class CommentController {
    private final CommentService commentService;


}

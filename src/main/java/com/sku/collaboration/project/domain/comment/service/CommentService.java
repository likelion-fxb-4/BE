package com.sku.collaboration.project.domain.comment.service;

import com.sku.collaboration.project.domain.comment.dto.request.CommentRequest;
import com.sku.collaboration.project.domain.comment.dto.response.CommentResponse;
import com.sku.collaboration.project.domain.comment.entity.Comment;
import com.sku.collaboration.project.domain.comment.repository.CommentRepository;
import com.sku.collaboration.project.domain.post.dto.request.CreatePostRequest;
import com.sku.collaboration.project.domain.post.dto.response.PostDetailResponse;
import com.sku.collaboration.project.domain.post.entity.Post;
import com.sku.collaboration.project.domain.post.mapper.PostMapper;
import com.sku.collaboration.project.domain.post.repository.PostRepository;
import com.sku.collaboration.project.domain.user.entity.User;
import com.sku.collaboration.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Boolean addComment(Long postId, Long userId, CommentRequest commentRequest) {
        User user = userRepository.findById(userId).orElseThrow();

        Post post = postRepository.findById(postId).orElseThrow();

        Comment comment = Comment.builder()
                .post(post)
                .content(commentRequest.getContent())
                .is_anonymous(commentRequest.getIsAnonymous())
                .build();

        commentRepository.save(comment);

        return true;
    }

    @Transactional
    public Boolean deleteComment(Long postId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        Post post = postRepository.findById(postId).orElseThrow();

        commentRepository.deleteByPostId(postId);

        return true;
    }

    @Transactional
    public List<CommentResponse> getComments(Long postId) {
        List<Comment> commentList = commentRepository.findAllByPostId(postId);

        return commentList.stream().map(this::toCommentResponse).toList();
    }

    private CommentResponse toCommentResponse(Comment comment) {
        return CommentResponse.builder().postId(comment.getPost().getId()).userId(comment.getUser().getId())
                .content(comment.getContent()).isAnonymous(comment.getIs_anonymous()).build();
    }

}

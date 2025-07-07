package com.sku.collaboration.project.domain.post.service;

import com.sku.collaboration.project.domain.post.dto.request.CreatePostRequest;
import com.sku.collaboration.project.domain.post.dto.response.PostDetailResponse;
import com.sku.collaboration.project.domain.post.entity.Post;
import com.sku.collaboration.project.domain.post.exception.PostErrorCode;
import com.sku.collaboration.project.domain.post.mapper.PostMapper;
import com.sku.collaboration.project.domain.post.repository.PostRepository;
import com.sku.collaboration.project.domain.user.entity.User;
import com.sku.collaboration.project.global.exception.CustomException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

  private final PostRepository postRepository;

  @Transactional
  public PostDetailResponse createPost(User user, CreatePostRequest createPostRequest) {
    Post post = Post.builder()
        .user(user)
        .title(createPostRequest.getTitle())
        .content(createPostRequest.getContent())
        .isAnonymous(createPostRequest.getIsAnonymous())
        .build();
    Post savedPost = postRepository.save(post);

    return PostMapper.toPostDetail(savedPost);
  }

  @Transactional
  public PostDetailResponse updatePost(User user, Long postId, CreatePostRequest updatePostRequest) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

    if (!post.getUser().equals(user)) {
      throw new CustomException(PostErrorCode.NOT_POST_OWNER);
    }

    post.setTitle(updatePostRequest.getTitle());
    post.setContent(updatePostRequest.getContent());
    post.setIsAnonymous(updatePostRequest.getIsAnonymous());

    return PostMapper.toPostDetail(post);
  }

  @Transactional
  public void deletePost(User user, Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

    if (!post.getUser().equals(user)) {
      throw new CustomException(PostErrorCode.NOT_POST_OWNER);
    }

    postRepository.delete(post);
  }

  @Transactional
  public List<PostDetailResponse> get() {
    List<Post> posts = postRepository.findAll();
    return PostMapper.toPostDetails(posts);
  }

}





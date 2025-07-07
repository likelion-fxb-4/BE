package com.sku.collaboration.project.domain.post.service;

import com.sku.collaboration.project.domain.post.dto.request.CreatePostRequest;
import com.sku.collaboration.project.domain.post.dto.response.PostDetailResponse;
import com.sku.collaboration.project.domain.post.dto.response.PostListResponse;
import com.sku.collaboration.project.domain.post.dto.response.PostSummaryResponse;
import com.sku.collaboration.project.domain.post.entity.Post;
import com.sku.collaboration.project.domain.post.enums.PostType;
import com.sku.collaboration.project.domain.post.exception.PostErrorCode;
import com.sku.collaboration.project.domain.post.mapper.PostMapper;
import com.sku.collaboration.project.domain.post.repository.PostRepository;
import com.sku.collaboration.project.domain.user.entity.User;
import com.sku.collaboration.project.domain.user.exception.UserErrorCode;
import com.sku.collaboration.project.domain.user.repository.UserRepository;
import com.sku.collaboration.project.global.exception.CustomException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Transactional
  public void createPost(Long userId, CreatePostRequest createPostRequest) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
    Post post = Post.builder()
        .user(user)
        .title(createPostRequest.getTitle())
        .content(createPostRequest.getContent())
        .isAnonymous(createPostRequest.getIsAnonymous())
        .postType(createPostRequest.getPostType())
        .build();
    Post savedPost = postRepository.save(post);
  }

  @Transactional
  public void updatePost(Long userId, Long postId, CreatePostRequest updatePostRequest) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

    if (!post.getUser().equals(user)) {
      throw new CustomException(PostErrorCode.NOT_POST_OWNER);
    }

    post.setTitle(updatePostRequest.getTitle());
    post.setContent(updatePostRequest.getContent());
    post.setIsAnonymous(updatePostRequest.getIsAnonymous());
    post.setPostType(updatePostRequest.getPostType());
  }

  @Transactional
  public void deletePost(Long userId, Long postId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

    if (!post.getUser().equals(user)) {
      throw new CustomException(PostErrorCode.NOT_POST_OWNER);
    }

    postRepository.delete(post);
  }

  // Limit 3
  public List<PostSummaryResponse> getFreeSummaryPostList(){
    List<Post> postList = postRepository.findTop3ByPostTypeOrderByCreatedAtDesc(PostType.FREE);
    return postList.stream()
        .map(PostMapper::toPostSummary)
        .toList();
  }

  public List<PostSummaryResponse> getSecretSummaryPostList(){
    List<Post> postList = postRepository.findTop3ByPostTypeOrderByCreatedAtDesc(PostType.SECRET);
    return postList.stream()
        .map(PostMapper::toPostSummary)
        .toList();
  }

  public List<PostListResponse> getFreePostList() {
    List<Post> postList = postRepository.findAllByPostType(PostType.FREE);
    return postList.stream()
        .map(PostMapper::toPostList)
        .toList();
  }

  public List<PostListResponse> getSecretPostList() {
    List<Post> postList = postRepository.findAllByPostType(PostType.SECRET);
    return postList.stream()
        .map(PostMapper::toPostList)
        .toList();
  }

  public PostDetailResponse getPostDetail(Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));
    return PostMapper.toPostDetail(post);
  }

}





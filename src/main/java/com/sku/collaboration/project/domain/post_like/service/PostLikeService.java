package com.sku.collaboration.project.domain.post_like.service;

import com.sku.collaboration.project.domain.post_like.repository.PostLikeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

  private final PostLikeRepository postLikeRepository;



}





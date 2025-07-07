package com.sku.collaboration.project.domain.post.enums;

import lombok.Getter;

@Getter
public enum PostType {
  FREE("free"),
  SECRET("secret");
  private final String type;
  PostType(String type) {
    this.type = type;
  }
}

package com.sku.collaboration.project.domain.post.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum PostType {
  FREE("free"),
  SECRET("secret");

  private final String type;

  PostType(String type) {
    this.type = type;
  }

  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  public static PostType from(String value) {
    for (PostType type : PostType.values()) {
      if (type.type.equalsIgnoreCase(value)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Invalid PostType: " + value);
  }

  @JsonValue
  public String getType() {
    return type;
  }
}

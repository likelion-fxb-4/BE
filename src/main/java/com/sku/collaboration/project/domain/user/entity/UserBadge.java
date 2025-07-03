package com.likelion.springpractice.domain.user.entity;

import com.likelion.springpractice.global.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_badge")
public class UserBadge extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userBadgeId;

  @ManyToOne
  @JoinColumn(name = "badge_id", nullable = false)
  private Badge badge;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}

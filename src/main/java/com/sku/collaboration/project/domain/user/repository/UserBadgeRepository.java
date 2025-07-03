package com.likelion.springpractice.domain.user.repository;

import com.likelion.springpractice.domain.user.entity.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {

}

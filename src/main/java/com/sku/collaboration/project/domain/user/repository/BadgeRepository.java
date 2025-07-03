package com.likelion.springpractice.domain.user.repository;

import com.likelion.springpractice.domain.user.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BadgeRepository extends JpaRepository<Badge, Long> {

}
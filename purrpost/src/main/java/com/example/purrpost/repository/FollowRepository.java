package com.example.purrpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purrpost.model.Follow;
import com.example.purrpost.model.compositeid.FollowId;

//Spring Data JPA creates CRUD implementation at runtime automatically.
public interface FollowRepository extends JpaRepository<Follow, FollowId> {
	  void deleteByUserIdAndFollowerId(long userId, long followerId);
}

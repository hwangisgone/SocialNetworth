package com.example.purrpost.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purrpost.model.Follow;
import com.example.purrpost.model.Reaction;
import com.example.purrpost.model.compositeid.FollowId;

//Spring Data JPA creates CRUD implementation at runtime automatically.
public interface FollowRepository extends JpaRepository<Follow, FollowId> {
	Optional<Follow> findByUserIdAndFollowerId(long userId, long followerId);
	void deleteByUserIdAndFollowerId(long userId, long followerId);
}

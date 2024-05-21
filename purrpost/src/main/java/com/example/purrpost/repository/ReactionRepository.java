package com.example.purrpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purrpost.model.Reaction;
import com.example.purrpost.model.compositeid.ReactionId;

public interface ReactionRepository extends JpaRepository<Reaction, ReactionId> {
	  List<Reaction> findAllByPostId(long postId);
	  
	  void deleteByUserIdAndPostId(long postId, long userId);
}

package com.example.purrpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purrpost.model.Reaction;
import com.example.purrpost.model.compositeid.InteractionId;

public interface ReactionRepository extends JpaRepository<Reaction, InteractionId> {
	  List<Reaction> findByPostId(int postId);
	  
	  void deleteByUserIdAndPostId(int postId, int userId);
}

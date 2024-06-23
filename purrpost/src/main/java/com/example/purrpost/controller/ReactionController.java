package com.example.purrpost.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.model.Reaction;
import com.example.purrpost.repository.ReactionRepository;
import com.example.purrpost.service.UserRetrieval;

import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@Transactional
@RequestMapping("/api")
public class ReactionController {

	//  Input for create
	static class ReactionInput {
		@Schema(example = "L", requiredMode = Schema.RequiredMode.REQUIRED)
		private char reactionType;

		public char getReactionType() {
			return reactionType;
		}
	}


	@Autowired
	ReactionRepository reactionRepository;

//	!!! For testing only
	@GetMapping("/myreactions")
	public ResponseEntity<List<Reaction>> getAllMyReactions() {
		try {
			List<Reaction> reactions = reactionRepository.findAllByUserId(UserRetrieval.getCurrentUserId());

			return new ResponseEntity<>(reactions, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/post/{id}/reactions")
	public ResponseEntity<List<Reaction>> getAllPostReactions(@PathVariable("id") long postId) {
		try {
			List<Reaction> reactions = reactionRepository.findAllByPostId(postId);

			if (reactions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(reactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/post/{id}/react")
	public ResponseEntity<Reaction> createReaction(@PathVariable("id") long postId, @RequestBody ReactionInput new_react) {
		System.out.println("POST /post/" + postId +"/react");
		try {
			Reaction _reaction = reactionRepository.save(new Reaction(
					UserRetrieval.getCurrentUserId(),
					postId,
					new_react.getReactionType()
			));

			return new ResponseEntity<>(_reaction, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@DeleteMapping("/post/{id}/react")
	public ResponseEntity<HttpStatus> deleteReaction(@PathVariable("id") long postId) {
		System.out.println("DELETE /post/" + postId +"/react");
		try {
			reactionRepository.deleteByUserIdAndPostId(UserRetrieval.getCurrentUserId(), postId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package com.example.purrpost.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ReactionController {

	@Autowired
	ReactionRepository reactionRepository;

//	!!! For testing only
	@GetMapping("/post/{id}/reactions")
	public ResponseEntity<List<Reaction>> getAllReactions(@PathVariable("id") int postId) {
		try {
			List<Reaction> reactions = reactionRepository.findByPostId(postId);

			if (reactions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(reactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/post/{id}/react")
	public ResponseEntity<Reaction> createReaction(@RequestBody Reaction reaction) {
		try {
			Reaction _reaction = reactionRepository.save(reaction);
			
			return new ResponseEntity<>(_reaction, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@DeleteMapping("/post/{id}/react")
	public ResponseEntity<HttpStatus> deleteReaction(@RequestBody Reaction reaction) {
		try {
			reactionRepository.deleteByUserIdAndPostId(reaction.getUserId(), reaction.getPostId());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

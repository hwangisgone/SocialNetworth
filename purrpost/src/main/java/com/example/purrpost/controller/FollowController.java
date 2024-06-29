package com.example.purrpost.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.model.Follow;
import com.example.purrpost.repository.FollowRepository;
import com.example.purrpost.service.UserRetrieval;


@RestController
@RequestMapping("/api")
public class FollowController {

	@Autowired
	FollowRepository followRepository;

	
//	!!! For testing only
	@PostMapping("/user/follow/{target_id}")
	public ResponseEntity<String> addFollow(@PathVariable("target_id") long targetId) {
		try {
			// !!! IMPORTANT TO FIX
			Follow _follow = followRepository.save(new Follow(targetId, UserRetrieval.getCurrentUserId()));

			return new ResponseEntity<>(_follow.getFollowerId() + " followed " + _follow.getUserId(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping("/user/follow/{target_id}")
	public ResponseEntity<Boolean> getFollow(@PathVariable("target_id") long targetId) {
		Optional<Follow> thisfollow = followRepository.findByUserIdAndFollowerId(targetId, UserRetrieval.getCurrentUserId());
		if (thisfollow.isPresent()) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}

	@Transactional
	@DeleteMapping("/user/follow/{target_id}")
	public ResponseEntity<HttpStatus> deleteFollow(@PathVariable("target_id") long targetId) {
		try {
			// !!! IMPORTANT TO FIX
			followRepository.deleteByUserIdAndFollowerId(targetId, UserRetrieval.getCurrentUserId());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

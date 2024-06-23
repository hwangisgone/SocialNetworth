package com.example.purrpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	@PostMapping("/user/follow/{user_id}")
	public ResponseEntity<String> addFollow(@PathVariable("user_id") long userId) {
		try {
			// !!! IMPORTANT TO FIX
			Follow _follow = followRepository.save(new Follow(UserRetrieval.getCurrentUserId(), userId));

			return new ResponseEntity<>(_follow.getFollowerId() + " followed " + _follow.getUserId(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/user/follow/{user_id}")
	public ResponseEntity<HttpStatus> deleteFollow(@PathVariable("user_id") long userId) {
		try {
			// !!! IMPORTANT TO FIX
			followRepository.deleteByUserIdAndFollowerId(UserRetrieval.getCurrentUserId(), userId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

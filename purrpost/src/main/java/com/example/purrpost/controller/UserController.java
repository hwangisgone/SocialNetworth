package com.example.purrpost.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.model.SocialUser;
import com.example.purrpost.repository.UserRepository;
import com.example.purrpost.service.UserRetrieval;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;


	@GetMapping("/user/{id}")
	public ResponseEntity<SocialUser> getUser(@PathVariable("id") long id) {
		Optional<SocialUser> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/user/myself")
	public ResponseEntity<SocialUser> getUser() {
		Optional<SocialUser> userData = userRepository.findById(UserRetrieval.getCurrentUserId());

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

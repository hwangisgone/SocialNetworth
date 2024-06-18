package com.example.purrpost.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.model.Post;
import com.example.purrpost.model.SocialUser;
import com.example.purrpost.repository.PostRepository;
import com.example.purrpost.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping("/allusers")
	public ResponseEntity<List<SocialUser>> getAllUsers() {
		try {
			List<SocialUser> allUsers = userRepository.findAll();
			return new ResponseEntity<>(allUsers, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<SocialUser> getUser(@PathVariable("id") long id) {
		Optional<SocialUser> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

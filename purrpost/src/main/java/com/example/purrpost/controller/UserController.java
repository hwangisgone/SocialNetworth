package com.example.purrpost.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.purrpost.model.Post;
import com.example.purrpost.model.User;
import com.example.purrpost.repository.PostRepository;
import com.example.purrpost.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/allusers")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			List<User> allUsers = userRepository.findAll();
			
			return new ResponseEntity<>(allUsers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	@GetMapping("/post/{name_tag}/{password}")
	public long getTutorialByNameAndPassword(@PathVariable("name_tag") String name_tag, @PathVariable("password") String password){
		List<User> allUsers = userRepository.findAll();
		
		for (User user : allUsers) {
			if( user.getNametag().equals(name_tag)) {
				if( user.getPassword().equals(password)) {
					return user.getUserId();
				}
				else return -1;
			}
		}
		return -2;
 		
	}
}

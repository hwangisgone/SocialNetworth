package com.example.purrpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.repository.UserRepository;
import com.example.purrpost.service.UserRetrieval;

@RestController
@RequestMapping("/api")
public class AdminController {
	@Autowired
	UserRepository userRepository;

	@DeleteMapping("/user/delete/{user_id}")
	public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") long id) {
		try {
			if (!UserRetrieval.getCurrentUserRole().equals("admin")) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);	// 403 if not admin
			}
			
			if (userRepository.existsById(id)) {
				userRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

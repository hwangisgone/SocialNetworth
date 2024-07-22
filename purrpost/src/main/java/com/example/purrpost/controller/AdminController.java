package com.example.purrpost.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.model.SocialUser;
import com.example.purrpost.repository.UserRepository;
import com.example.purrpost.service.UserRetrieval;

@RestController
@RequestMapping("/api")
public class AdminController {
	@Autowired
	UserRepository userRepository;
	


	@GetMapping("/allusers")
	public ResponseEntity<List<SocialUser>> getAllUsers() {
		try {
			if (!UserRetrieval.getCurrentUserRole().equals("admin")) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);	// 403 if not admin
			}

			List<SocialUser> allUsers = userRepository.findAllByOrderByUserIdAsc();
			
			return new ResponseEntity<>(allUsers, HttpStatus.OK);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/user/delete/{user_id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("user_id") long id) {
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

package com.example.purrpost.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.model.SocialUser;
import com.example.purrpost.repository.UserRepository;
import com.example.purrpost.service.JwtTokenService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/allusers")
	public ResponseEntity<List<SocialUser>> getAllUsers() {
		try {
			List<SocialUser> allUsers = userRepository.findAll();
			
			return new ResponseEntity<>(allUsers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/register")
	public ResponseEntity<SocialUser> register(@RequestBody SocialUser user) {
		List<SocialUser> foundName = userRepository.findByNameTag(user.getNameTag());
		if (foundName.size() == 0) {
			try {
				SocialUser _user = userRepository.save(new SocialUser(user));
				return new ResponseEntity<>(_user, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@Autowired
	private JwtTokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<String> loginWithJWT(@RequestBody SocialUser user){
		List<SocialUser> foundUser = userRepository.findByNameTagAndPassword(user.getNameTag(), user.getPassword());

		HttpHeaders headers = new HttpHeaders();
		System.out.println("Logging in with nameTag=" + user.getNameTag() + " and password=" + user.getPassword());
		
		
		
		if (foundUser.size() == 1) {
			headers.setBearerAuth(tokenService.generateToken(foundUser.get(0)));
			
			return new ResponseEntity<>(foundUser.get(0).toString(), headers, HttpStatus.OK);
		} else if (foundUser.size() < 1) {
			return new ResponseEntity<>("Username/password incorrect", HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>("Server error, found more than 1 user???", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package com.example.purrpost.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.model.User;
import com.example.purrpost.repository.UserRepository;
import com.example.purrpost.service.JwtTokenService;

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

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		List<User> foundName = userRepository.findByNameTag(user.getNameTag());
		if (foundName.size() == 0) {
			try {
				User _user = userRepository.save(new User(
					user.getNameTag(),
					user.getPassword(),
					user.getName(),
					user.getEmail(),
					user.getBio(),
					user.getRole(),
					user.getPhone(),
					user.getRegistrationDate())
				);
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
	public ResponseEntity<User> loginWithJWT(@RequestBody User user){
		List<User> foundUser = userRepository.findByNameTagAndPassword(user.getNameTag(), user.getPassword());

		// Reference: https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html#servlet-authentication-unpwd
		Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(user.getNameTag(), user.getPassword());
		
		HttpHeaders headers = new HttpHeaders();
		
		if (foundUser.size() == 1) {
			headers.setBearerAuth(tokenService.generateToken(authRequest));
			return new ResponseEntity<>(foundUser.get(0), headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(user, HttpStatus.UNAUTHORIZED);
		}	
	}
}

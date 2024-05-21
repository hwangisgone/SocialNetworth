package com.example.purrpost.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.model.SocialUser;
import com.example.purrpost.repository.UserRepository;
import com.example.purrpost.service.JwtTokenService;

import io.swagger.v3.oas.annotations.media.Schema;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class LoginController {
	//  Input for register/login
	class SocialUserLoginInput {
		@Schema(example = "username", requiredMode = Schema.RequiredMode.REQUIRED)
		private String nameTag;

		@Schema(example = "password", requiredMode = Schema.RequiredMode.REQUIRED)
		private String password;

		public String getNameTag() {
			return nameTag;
		}

		public String getPassword() {
			return password;
		}
	}

	class SocialUserRegisterInput extends SocialUserLoginInput {
		@Schema(example = "Steve Joshua", requiredMode = Schema.RequiredMode.REQUIRED)
		private String name;

		@Schema(example = "example@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
		private String email;

		@Schema(example = "This bio contains nothing.", requiredMode = Schema.RequiredMode.REQUIRED)
		private String bio;


		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}

		public String getBio() {
			return bio;
		}
	}


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
	public ResponseEntity<SocialUser> register(@RequestBody SocialUserRegisterInput user) {
		List<SocialUser> foundName = userRepository.findByNameTag(user.getNameTag());
		if (foundName.size() == 0) {
			try {
				SocialUser _user = userRepository.save(new SocialUser(
						user.getNameTag(),
						user.getPassword(),
						user.getName(),
						user.getEmail(),
						user.getBio()
				));
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
	public ResponseEntity<String> loginWithJWT(@RequestBody SocialUserLoginInput user){
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

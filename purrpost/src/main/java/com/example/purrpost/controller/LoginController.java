package com.example.purrpost.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class LoginController {
	//  Input for register/login
	static class SocialUserLoginInput {
		@NonNull @Schema(example = "username", requiredMode = Schema.RequiredMode.REQUIRED)
		private String nameTag;

		@NonNull @Schema(example = "password", requiredMode = Schema.RequiredMode.REQUIRED)
		private String password;

		public String getNameTag() {
			return nameTag;
		}

		public String getPassword() {
			return password;
		}
		
	}

	// Spring DOES clear out static class before assigning them with RequestBody
	static class SocialUserRegisterInput extends SocialUserLoginInput {
		@NonNull @Schema(example = "Steve Joshua", requiredMode = Schema.RequiredMode.REQUIRED)
		private String name;

		@NonNull @Schema(example = "example@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
		private String email;

		@NonNull @Schema(example = "This bio contains nothing.", requiredMode = Schema.RequiredMode.REQUIRED)
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
				System.out.println(e.getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		System.out.println("User registered");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<Post> updateUser(@PathVariable("id") long id, @RequestBody SocialUserRegisterInput updated_user) {
		Optional<SocialUser> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			SocialUser selected_user = userData.get();
			selected_user.setNameTag(updated_post.getNameTag());
			selected_post.setPassword(updated_post.getPassword());
			return new ResponseEntity<>(userRepository.save(selected_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
			headers.setAccessControlExposeHeaders(List.of("Authorization"));

			return new ResponseEntity<>(foundUser.get(0).toString(), headers, HttpStatus.OK);
		} else if (foundUser.size() < 1) {
			return new ResponseEntity<>("Username/password incorrect", HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>("Server error, found more than 1 user???", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package com.example.purrpost.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.model.Post;
import com.example.purrpost.repository.PostRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/allposts")
	public ResponseEntity<List<Post>> getAllPosts() {
		try {
			List<Post> allPosts = postRepository.findAll();
			
			SecurityContext context = SecurityContextHolder.getContext();
			Authentication authentication = context.getAuthentication();
			System.out.println("\nLogged in with =" + authentication.getName() + "\n" );
			
			return new ResponseEntity<>(allPosts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/post/{id}")
	public ResponseEntity<Post> getTutorialById(@PathVariable("id") long id) {
		Optional<Post> postData = postRepository.findById(id);

		if (postData.isPresent()) {
			return new ResponseEntity<>(postData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	Send a request with post body:
//	{
//	    "content": "ABCDE",
//	    "timePosted": "2024-03-20T02:51:41.317"
//	}
	
	@PostMapping("/post")
	public ResponseEntity<Post> createPost(@RequestBody Post post) {
		try {
			post.setTimePosted(OffsetDateTime.now());		// !!! May need to reconsider timezome problems
			// https://stackoverflow.com/questions/3914404/how-to-get-current-moment-in-iso-8601-format-with-date-hour-and-minute
			Post _post = postRepository.save(post);
			
			return new ResponseEntity<>(_post, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/post/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable("id") long id, @RequestBody Post updated_post) {
		Optional<Post> postData = postRepository.findById(id);

		if (postData.isPresent()) {
			Post selected_post = postData.get();
			selected_post.setContent(updated_post.getContent());
			selected_post.setTimeEdited(OffsetDateTime.now());
			return new ResponseEntity<>(postRepository.save(selected_post), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/post/{id}")
	public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") long id) {
		try {
			postRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package com.example.purrpost.controller;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.model.Post;
import com.example.purrpost.repository.PostRepository;
import com.example.purrpost.service.UserRetrieval;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class PostController {

	//  Input for create/update
	static class PostInput {
		@Schema(example = "Something Something", requiredMode = Schema.RequiredMode.REQUIRED)
		private String content;

		public String getContent() {
			return content;
		}
	}


	@Autowired
	private PostRepository postRepository;

	@GetMapping("/allposts")
	public ResponseEntity<List<Post>> getAllPosts() {
		try {
			List<Post> allPosts = postRepository.findAll();
			return new ResponseEntity<>(allPosts, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/newsfeed")
	public ResponseEntity<List<Post>> getNewsfeed() {
		try {
			OffsetDateTime previousWeek = OffsetDateTime.now().minus(14, ChronoUnit.DAYS);
			List<Post> hotPosts = postRepository.findFirst10ByTimePostedGreaterThanOrderByLikeCountDesc(previousWeek);
					
			return new ResponseEntity<>(hotPosts, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user/{id}/allposts")
	public ResponseEntity<List<Post>> getUserPost(@PathVariable("id") long id) {
		try {
			List<Post> allPosts = postRepository.findAllByUserId(id);
			return new ResponseEntity<>(allPosts, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	// Search
	@GetMapping("/search")
	public ResponseEntity<List<Post>> searchPost(@RequestParam("term") String searchterm) {
		try {
			List<Post> searched = postRepository.searchContent(searchterm);

			return new ResponseEntity<>(searched, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

//	Send a request with post body:
//	{
//	    "content": "ABCDE",
//	}
	@ApiResponse(responseCode = "201", description = "Post created")
	@ApiResponse(responseCode = "500", description = "Error in creating post", content = @Content)

	@PostMapping("/post")
	public ResponseEntity<Post> createPost(@RequestBody PostInput new_post) {
		System.out.println("POST /post");
		try {
			Post _post = postRepository.save(new Post(
				UserRetrieval.getCurrentUserId(),
				new_post.getContent()
			));

			return new ResponseEntity<>(_post, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@ApiResponse(responseCode = "200", description = "Post updated")
	@ApiResponse(responseCode = "404", description = "Post not found", content = @Content)

	@GetMapping("/post/{id}")
	public ResponseEntity<Post> getPost(@PathVariable("id") long id) {
		System.out.println("GET /post/" + id);
		Optional<Post> postData = postRepository.findById(id);

		if (postData.isPresent()) {
			return new ResponseEntity<>(postData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@ApiResponse(responseCode = "200", description = "Post returned")
	@ApiResponse(responseCode = "404", description = "Post not found", content = @Content)

	@PutMapping("/post/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable("id") long id, @RequestBody PostInput updated_post) {
		System.out.println("PUT /post/" + id);
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



	@ApiResponse(responseCode = "204", description = "Post deleted")
	@ApiResponse(responseCode = "404", description = "Post not found", content = @Content)

	@DeleteMapping("/post/{id}")
	public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") long id) {
		System.out.println("DELETE /post/" + id);
		try {
			if (postRepository.existsById(id)) {
				postRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

package com.example.purrpost.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.model.Post;
import com.example.purrpost.model.Reply;
import com.example.purrpost.repository.PostRepository;
import com.example.purrpost.repository.ReplyRepository;
import com.example.purrpost.service.UserRetrieval;

import io.swagger.v3.oas.annotations.media.Schema;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ReplyController {

	//  Input for create/update
	static class ReplyInput {
		@Schema(example = "Something Something", requiredMode = Schema.RequiredMode.REQUIRED)
		private String content;


		private int parentId;

		public int getParentId() {
			return parentId;
		}

		public String getContent() {
			return content;
		}
	}

	@Autowired
	ReplyRepository replyRepository;
	@Autowired
	PostRepository postRepository;

	// @GetMapping("/replies_to/{id}")
	// public ResponseEntity<List<Reply>> getRepliesToId(@PathVariable("id") long id) {
	// 	try {
	// 		List<Reply> allRepliesTo = replyRepository.findAllByParentId(id);

	// 		return new ResponseEntity<>(allRepliesTo, HttpStatus.OK);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	// 	}
	// }

	@GetMapping("/replies_to/{id}")
	public ResponseEntity<List<Post>> getRepliesToId(@PathVariable("id") long id) {
		try {
			List<Reply> allRepliesTo = replyRepository.findAllByParentId(id);
			List<Post> allPosts = new ArrayList<>();

			// Fetch post details for each reply
			allRepliesTo.forEach(reply -> {
				Optional<Post> postOptional = postRepository.findById(reply.getParentId());
				postOptional.ifPresent(allPosts::add);
			});

			return new ResponseEntity<>(allPosts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// load reply
	// @GetMapping("/reply/{id}")
	// public ResponseEntity<Reply> getReplyById(@PathVariable("id") long id) {
	// Optional<Reply> replyData = replyRepository.findById(id);

	// if (replyData.isPresent()) {
	// return new ResponseEntity<>(replyData.get(), HttpStatus.OK);
	// } else {
	// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// }
	// }

	@PostMapping("/reply")
	public ResponseEntity<Reply> createReply(@RequestBody ReplyInput reply) {
		try {
			Post _post = postRepository.save(new Post(
				UserRetrieval.getCurrentUserId(),
				reply.getContent()
			));
			Reply _reply = replyRepository.save(new Reply(
				reply.getParentId(),
				_post.getUserId()
			));

			return new ResponseEntity<>(_reply, HttpStatus.CREATED);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

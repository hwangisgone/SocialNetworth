package com.example.purrpost.controller;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.purrpost.model.Post;
import com.example.purrpost.model.Reaction;
import com.example.purrpost.model.Reply;
import com.example.purrpost.model.SocialUser;
import com.example.purrpost.repository.PostRepository;
import com.example.purrpost.repository.ReactionRepository;
import com.example.purrpost.repository.ReplyRepository;
import com.example.purrpost.repository.UserRepository;
import com.example.purrpost.service.UserRetrieval;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class MegaPostController {

	//  Input for create/update
	static class PostOutput {
		private Post post;
		private boolean liked;
		private SocialUser user;
		
		public PostOutput(Post post, boolean liked, SocialUser user) {
			super();
			this.post = post;
			this.liked = liked;
			this.user = user;
		}
		
		public Post getPost() {
			return post;
		}
		public void setPost(Post post) {
			this.post = post;
		}
		public boolean isLiked() {
			return liked;
		}
		public void setLiked(boolean liked) {
			this.liked = liked;
		}
		public SocialUser getUser() {
			return user;
		}
		public void setUser(SocialUser user) {
			this.user = user;
		}
	}
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReactionRepository reactionRepository;
	
	
	private List<PostOutput> populatePostList(List<Post> postList) throws Exception {
		Map<Long, SocialUser> userMap = userRepository.findAll()
				.stream()
                .collect(Collectors.toMap(SocialUser::getUserId, user -> user));
		
		Map<Long, Reaction> reactionMap = reactionRepository.findAllByUserId(UserRetrieval.getCurrentUserId())
				.stream()
				.collect(Collectors.toMap(Reaction::getPostId, react -> react));
				
		
		List<PostOutput> newPostList = new ArrayList<>();
		
        for (Post post : postList) {
            SocialUser user = userMap.get(post.getUserId());
            Reaction reaction = reactionMap.get(post.getId());
            boolean liked = (reaction != null) ? true : false;

            newPostList.add(new PostOutput(post, liked, user));
        }
		
		return newPostList;
	}
	
	private PostOutput populatePost(Post post) throws Exception {
		Optional<SocialUser> user = userRepository.findById(post.getUserId());
		Optional<Reaction> reaction = reactionRepository.findByUserIdAndPostId(post.getUserId(), post.getId());
		
		boolean liked = !reaction.isEmpty();
		
		return new PostOutput(post, liked, user.get());
	}

	@ApiResponse(responseCode = "200", description = "Post returned")
	@ApiResponse(responseCode = "404", description = "Post not found", content = @Content)

	@GetMapping("/post/{id}")
	public ResponseEntity<PostOutput> getPost(@PathVariable("id") long id) {
		Optional<Post> postData = postRepository.findById(id);

		try {
			return new ResponseEntity<>(this.populatePost(postData.get()), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
//	@GetMapping("/allposts")
//	public ResponseEntity<List<Post>> getAllPosts() {
//		try {
//			List<Post> allPosts = postRepository.findAll();
//			return new ResponseEntity<>(allPosts, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@GetMapping("/newsfeed")
	public ResponseEntity<List<PostOutput>> getNewsfeed() {
		try {
			OffsetDateTime previousWeek = OffsetDateTime.now().minus(14, ChronoUnit.DAYS);
			List<Post> hotPosts = postRepository.findAllByTimePostedGreaterThanOrderByLikeCountDescIdDesc(previousWeek);
			
			OffsetDateTime recently = OffsetDateTime.now().minus(15, ChronoUnit.MINUTES);
			List<Post> myRecentPost = postRepository.findFirst10ByUserIdAndTimePostedGreaterThanOrderByIdDesc(UserRetrieval.getCurrentUserId(), recently);
			
	        Set<Long> seenIds = new HashSet<>();
	        List<Post> newsFeedPost = new ArrayList<>();
	        
	        for (Post post : myRecentPost) {
	        	seenIds.add(post.getId());
	            newsFeedPost.add(post);
	        }
	        
	        // To not repeat
	        for (Post post : hotPosts) {
	            if (seenIds.add(post.getId())) {
	            	newsFeedPost.add(post);
	            }
	        }
			
			return new ResponseEntity<>(this.populatePostList(newsFeedPost), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user/{id}/allposts")
	public ResponseEntity<List<PostOutput>> getUserPost(@PathVariable("id") long id) {
		try {
			List<Post> allPosts = postRepository.findAllByUserIdOrderByIdDesc(id);
			
			return new ResponseEntity<>(this.populatePostList(allPosts), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	// Search
	@GetMapping("/search")
	public ResponseEntity<List<PostOutput>> searchPost(@RequestParam("term") String searchterm) {
		try {
			List<Post> searched = postRepository.searchContent(searchterm);

			return new ResponseEntity<>(this.populatePostList(searched), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@Autowired
	ReplyRepository replyRepository;
	
	@GetMapping("/replies_to/{id}")
	public ResponseEntity<List<PostOutput>> getRepliesToId(@PathVariable("id") long id) {
		try {
			List<Reply> allRepliesTo = replyRepository.findAllByParentId(id);
			List<Post> allPosts = new ArrayList<>();

			// Fetch post details for each reply
			allRepliesTo.forEach(reply -> {
				Optional<Post> postOptional = postRepository.findById(reply.getChildId());
				postOptional.ifPresent(allPosts::add);
			});

			return new ResponseEntity<>(this.populatePostList(allPosts), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}

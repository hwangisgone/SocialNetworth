package com.example.purrpost.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.purrpost.model.Post;
import com.example.purrpost.repository.PostRepository;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@Component
public class TemplateSetup {
	private static boolean dataLoaded = false;
	private long testUserId;
	private long testPost1Id;
	private long testPost2Id;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserTestHelper userHelper; // Needed for auths

	
	
	public long getUserId() {
		return testUserId;
	}
	public long getPost1Id() {
		return testPost1Id;
	}
	public long getPost2Id() {
		return testPost2Id;
	}


	private void setUpOnce() {
		// Prepare test data
		testUserId = userHelper.initiateUser();
		userHelper.initiateToken();
		
		postRepository.deleteAll();
		
		testPost1Id = postRepository.save(new Post(testUserId, "First test")).getId();
		testPost2Id = postRepository.save(new Post(testUserId, "Second test")).getId();
	}

	public RequestSpecification buildAuthenticatedHeader() {
		// Load initial data (once only)
		if (!dataLoaded) {
			setUpOnce();
		}
		
		// Build request with header
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		builder.addHeader("Authorization", userHelper.getTestToken());
		return builder.build();
	}
}

package com.example.purrpost;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.startsWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.purrpost.model.SocialUser;
import com.example.purrpost.repository.UserRepository;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Component
public class UserTestHelper {
	
	private String testToken;

	UserRepository userRepository;
	
	@Autowired
	public UserTestHelper(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	long initiateUser() {
		userRepository.deleteAll();
		SocialUser testUser = new SocialUser();
		testUser.setNameTag("TEST 1");
		testUser.setPassword("TEST PASSWORD");
		testUser.setDefaultTestUser();
		testUser = userRepository.save(testUser);
		
		return testUser.getUserId();
	}
	
	void initiateToken() {
		Response res = 
				given()
				.contentType(ContentType.JSON)
					.body("{\"nameTag\":\"TEST 1\", \"password\": \"TEST PASSWORD\"}")
				.when()
					.post("/api/login")
				.then()
					.statusCode(200)
					.header("Authorization", startsWith("Bearer"))
					.extract()
					.response();
			
		this.setTestToken(res.getHeader("Authorization"));
	}

	public String getTestToken() {
		return testToken;
	}

	public void setTestToken(String testToken) {
		this.testToken = testToken;
	}
}

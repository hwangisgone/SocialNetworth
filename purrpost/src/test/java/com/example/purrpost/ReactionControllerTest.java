package com.example.purrpost;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

import com.example.purrpost.model.Post;
import com.example.purrpost.repository.PostRepository;
import com.example.purrpost.repository.ReactionRepository;

// https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {
	"file:../database/reset_all_table.sql",
	"file:../database/create_user.sql", 
	"file:../database/create_table_post.sql",
	"file:../database/function_post.sql"
}, executionPhase = BEFORE_TEST_CLASS)
// THIS SQL MAY MAKE TESTING CRASH

@TestPropertySource({"/test.properties"})
class ReactionControllerTest {

// \cd D:/,DOWNLOADS/SocialNetworth/database		// psql command saved here
// \i reset_all_table.sql
// \i load_all.sql

	@LocalServerPort
	private Integer port;

	@Autowired
	ReactionRepository reactionRepository;
	
	@Autowired
	PostRepository postRepository;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
	}

	// Get value from properties
	@Value("${spring.datasource.url}")
	private String dataSourceURL;
	
	@Test
	void correctClassPath() {
		// Check if using the correct database
		assertEquals(dataSourceURL, "jdbc:postgresql://localhost:5432/PURRPOST-TEST");
	}
	
	
	
	@Test
	void testGetAllPosts() {
		// Prepare test data
		postRepository.deleteAll();
		List<Post> posts = List.of(
			new Post(0, "First test"), 
			new Post(0, "Second test")
		);
		postRepository.saveAll(posts);
		
		
		// HTTP REQUEST & Assert returned object
		// https://www.baeldung.com/rest-assured-tutorial
		// https://github.com/rest-assured/rest-assured/wiki/Usage
		given().contentType(ContentType.JSON)
		.when().get("/api/allposts")
		.then()
			.statusCode(200)
			.body(".", hasSize(2));
		// .log().all();	if you want to log into the console
	}

	@Test
	void testReadPost() {
		Post newPost = new Post(0, "GET POST TEST");
		newPost = postRepository.save(newPost);
		postRepository.flush();
		
		// Found
		given().contentType(ContentType.JSON)
		.when()
			.get("/api/post/" + newPost.getId())
		.then()
			.statusCode(200)
			.body("content", equalTo("GET POST TEST"));
		
		// Not Found
		given().contentType(ContentType.JSON)
		.when()
			.get("/api/post/777")
		.then()
			.statusCode(404);
	}
	
	@Test
	void testCreatePost() {
		given().contentType(ContentType.JSON)
			.body("{\"content\":\"CREATE POST TEST\"}")
		.when()
			.post("/api/post")
		.then()
			.statusCode(201);	// 201 created
		
		
		Post latestPost = postRepository.findTopByOrderByIdDesc();
		assertEquals("CREATE POST TEST", latestPost.getContent());
	}
	
	@Test
	void testUpdatePost() {
		// Test data
		Post newPost = new Post(0, "GET POST TEST");
		newPost = postRepository.save(newPost);
		postRepository.flush();
		
		
		// HTTP REQUEST & Assert returned object
		given().contentType(ContentType.JSON)
			.body("{\"content\":\"UPDATED POST TEST\"}")
		.when()
			.put("/api/post/" + newPost.getId())
		.then()
			.statusCode(200)
			.body("content", equalTo("UPDATED POST TEST"));
		
		// Assert database
		Optional<Post> testPost = postRepository.findById(newPost.getId());
		if (testPost.isPresent()) {
			assertEquals("UPDATED POST TEST", testPost.get().getContent());
			assertTrue(testPost.get().getTimeEdited().until(OffsetDateTime.now(), ChronoUnit.SECONDS) < 1);		
		} else {
			fail("Cannot find post?");
		}
	}
	
	@Test
	void testDeletePost() {
		// Test data
		Post newPost = new Post(0, "GET POST TEST");
		newPost = postRepository.save(newPost);
		postRepository.flush();
		
		// HTTP REQUEST & Assert returned object
		given().contentType(ContentType.JSON)
		.when()
			.delete("/api/post/" + newPost.getId())
		.then()
			.statusCode(204);	// 204 no content
		
		// Assert database
		Optional<Post> testPost = postRepository.findById(newPost.getId());
		if (testPost.isPresent()) {
			fail("Post not deleted!");
		}
	}
	
	
//	@BeforeEach // For container based approach
//	void setUp() {
//	ScriptUtils.runInitScript(new JdbcDatabaseDelegate(CONTAINER, ""), "sql-files/Unittest-ddl.sql");
//	ScriptUtils.runInitScript(new JdbcDatabaseDelegate(CONTAINER, ""), "sql-files/Unittest-data.sql");
//	}
}

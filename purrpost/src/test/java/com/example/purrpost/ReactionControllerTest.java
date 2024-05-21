package com.example.purrpost;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.Optional;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

import com.example.purrpost.model.Reaction;
import com.example.purrpost.repository.ReactionRepository;
import com.example.purrpost.util.TemplateSetup;

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
	private TemplateSetup temp;
	private RequestSpecification requestSpec;
	
	@BeforeEach
	void setUp() {
		// Set up before each test: Make sure this is working with different ports
		RestAssured.baseURI = "http://localhost:" + port;

		requestSpec = temp.buildAuthenticatedHeader();
	}
	
	
	
//	@Test
//	void testGetAllReactions() {
//
//		// HTTP REQUEST & Assert returned object
//		// https://www.baeldung.com/rest-assured-tutorial
//		// https://github.com/rest-assured/rest-assured/wiki/Usage
//		given().contentType(ContentType.JSON)
//		.when().get("/api/allposts")
//		.then()
//			.statusCode(200)
//			.body(".", hasSize(2));
//		// .log().all();	if you want to log into the console
//	}
	
	@Test
	void testCreateReaction() {
		given().spec(requestSpec)
			.body("{\"reactionType\":\"H\"}")
		.when()
			.post("/api/post/" + temp.getPost1Id() + "/react")
		.then()
			.statusCode(201);	// 201 created
		
		
		Optional<Reaction>  latestReaction = reactionRepository.findByUserIdAndPostId(temp.getUserId(), temp.getPost1Id());
		if (latestReaction.isPresent()) {
			assertEquals("H", latestReaction.get().getReactionType());
		} else {
			fail("Reaction not created!");
		}
	}
	
	@Test
	void testDeleteReaction() {
		// Test data
		Reaction newReaction = new Reaction(temp.getUserId(), temp.getPost2Id(), 'L');
		reactionRepository.save(newReaction);
		reactionRepository.flush();
		
		// HTTP REQUEST & Assert returned object
		given().spec(requestSpec)
		.when()
			.delete("/api/post/" + temp.getPost2Id() + "/react")
		.then()
			.statusCode(204);	// 204 no content
		
		// Assert database
		Optional<Reaction> testReaction = reactionRepository.findByUserIdAndPostId(temp.getUserId(), temp.getPost2Id());
		if (testReaction.isPresent()) {
			fail("Reaction not deleted!");
		}
	}
	
	
//	@BeforeEach // For container based approach
//	void setUp() {
//	ScriptUtils.runInitScript(new JdbcDatabaseDelegate(CONTAINER, ""), "sql-files/Unittest-ddl.sql");
//	ScriptUtils.runInitScript(new JdbcDatabaseDelegate(CONTAINER, ""), "sql-files/Unittest-data.sql");
//	}
}

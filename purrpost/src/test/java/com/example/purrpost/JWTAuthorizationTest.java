package com.example.purrpost;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.startsWith;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

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

import com.example.purrpost.model.User;
import com.example.purrpost.repository.UserRepository;

// https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {
	"file:../database/reset_all_table.sql",
	"file:../database/create_user.sql", 
	"file:../database/create_table_post.sql",
	"file:../database/function_post.sql"
}, executionPhase = BEFORE_TEST_CLASS)

@TestPropertySource({"/test.properties"})
class JWTAuthorizationTest {

	@Autowired
	UserRepository userRepository;
	
	@LocalServerPort
	private Integer port;

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
	void testUnauthorizedApi() {
		given()
			.contentType(ContentType.JSON)
			.when()
				.get("/api/allposts")
			.then()
				.statusCode(401);
		// .log().all();	if you want to log into the console
	}
	
	String testToken;

	@Test
	void testGetToken() {
		userRepository.deleteAll();
		User testUser = new User("TEST 1", "TEST PASS");
		testUser.setDefaultUser();
		testUser.setName("TEST NAME");
		testUser.setEmail("test@email.com");
		userRepository.save(testUser);
		
		Response res = 
			given()
			.contentType(ContentType.JSON)
				.body("{\"nameTag\":\"TEST 1\", \"password\": \"TEST PASS\"}")
			.when()
				.post("/api/login")
			.then()
				.statusCode(200)
				.header("Authorization", startsWith("Bearer"))
				.extract()
				.response();
		
		testToken = res.getHeader("Authorization");
	}
	
	@Test
	void testAuthorizedApi() {
		testGetToken();
		
		given()
			.contentType(ContentType.JSON)
			.when()
				.header("Authorization", testToken)
				.get("/api/allposts")
			.then()
				.statusCode(200);
		// .log().all();	if you want to log into the console
	}
}

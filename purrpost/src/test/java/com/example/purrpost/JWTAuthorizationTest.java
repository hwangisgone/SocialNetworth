package com.example.purrpost;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.startsWith;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
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

import com.example.purrpost.model.SocialUser;
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
	
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
	}

	@BeforeAll
	static void checkDataSource(@Value("${spring.datasource.url}") String dataSourceURL) {
		// Check if using the correct database
		assertEquals("jdbc:postgresql://localhost:5432/PURRPOST-TEST", dataSourceURL);
	}
	
	@Autowired
	UserTestHelper userHelper;
	// Needed for auths
	
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

	@Test
	void testGetToken() {
		userHelper.initiateUser();
		userHelper.initiateToken();
	}
	
	@Test
	void testAuthorizedApi() {
		given()
			.contentType(ContentType.JSON)
			.when()
				.header("Authorization", userHelper.getTestToken())
				.get("/api/allposts")
			.then()
				.statusCode(200);
		// .log().all();	if you want to log into the console
	}
}

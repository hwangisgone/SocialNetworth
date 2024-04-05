package com.example.purrpost;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
//import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.transaction.annotation.Transactional;

import com.example.purrpost.model.Post;
import com.example.purrpost.repository.PostRepository;

// https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql({
//	"file:../database/reset_all_table.sql",
//	"file:../database/create_user.sql", 
//	"file:../database/create_table_post.sql",
//	"file:../database/function_post.sql"
//})
class PostControllerTest {

// \cd D:/,DOWNLOADS/SocialNetworth/database
// \i load_all.sql

	@LocalServerPort
	private Integer port;

	@Autowired
	PostRepository postRepository;

	@BeforeEach
	void setUp() {
//	ScriptUtils.runInitScript(new JdbcDatabaseDelegate(CONTAINER, ""), "sql-files/Unittest-ddl.sql");
//	ScriptUtils.runInitScript(new JdbcDatabaseDelegate(CONTAINER, ""), "sql-files/Unittest-data.sql");

		// Set up before each test: Make sure ports are working
		RestAssured.baseURI = "http://localhost:" + port;
	}

	@Test
	void testClassPath() {
		System.out.println(System.getProperty("java.class.path").replace(";", ";\n"));
	}
	
	@Test
	void testAllPosts() {
		List<Post> posts = List.of(
			new Post("First test", new Date()), 
			new Post("Second test", new Date())
		);
		postRepository.saveAll(posts);
		// https://www.baeldung.com/rest-assured-tutorial
		// https://github.com/rest-assured/rest-assured/wiki/Usage
		given().contentType(ContentType.JSON)
		.when().get("/api/allposts")
		.then()
		.statusCode(200)
		.body(".", hasSize(2));
	}

	@Test
	void testPost() {
		Post newPost = new Post("First test", new Date());
		newPost = postRepository.save(newPost);
		postRepository.flush();
		
		// Found
		given().contentType(ContentType.JSON)
		.when().get("/api/post/" + newPost.getId())
		.then()
		.statusCode(200)
		.body(".", hasSize(1));

		// Not Found
		given().contentType(ContentType.JSON)
		.when().get("/api/post/777")
		.then()
		.statusCode(404);
	}
}

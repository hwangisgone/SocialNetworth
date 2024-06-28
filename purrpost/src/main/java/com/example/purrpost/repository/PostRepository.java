package com.example.purrpost.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.purrpost.model.Post;

//Spring Data JPA creates CRUD implementation at runtime automatically.
public interface PostRepository extends JpaRepository<Post, Long> {

	// Get latest
	// https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html#repositories.limit-query-result
	Post findTopByOrderByIdDesc();

	List<Post> findAllByUserIdOrderByIdDesc(long userId);

	@Query(value ="SELECT * FROM post WHERE content_search @@ plainto_tsquery(:searchQuery)", nativeQuery = true)
	List<Post> searchContent(@Param(value="searchQuery") String searchQuery);
	
	List<Post> findFirst10ByTimePostedGreaterThanOrderByLikeCountDesc(OffsetDateTime timePosted);
//  List<Tutorial> findByPublished(boolean published);
//
//  List<Tutorial> findByTitleContaining(String title);
}

package com.example.purrpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purrpost.model.User;

//Spring Data JPA creates CRUD implementation at runtime automatically.
public interface UserRepository extends JpaRepository<User, Long> {
	// https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
	List<User> findByNameTagAndPassword(String name_tag, String password);
	
<<<<<<< HEAD
	List<User> findByNameTag(String name_tag);
	
=======
>>>>>>> 3bff5ee8c13e2728af7abbb779562b3bb7af8259
//  List<Tutorial> findByPublished(boolean published);
//
//  List<Tutorial> findByTitleContaining(String title);
}

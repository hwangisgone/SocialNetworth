package com.example.purrpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purrpost.model.SocialUser;

//Spring Data JPA creates CRUD implementation at runtime automatically.
public interface UserRepository extends JpaRepository<SocialUser, Long> {
	// https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
	List<SocialUser> findByNameTagAndPassword(String name_tag, String password);
	List<SocialUser> findByNameTag(String name_tag);
}

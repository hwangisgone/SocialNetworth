package com.example.purrpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purrpost.model.Post;

//Spring Data JPA creates CRUD implementation at runtime automatically.
public interface PostRepository extends JpaRepository<Post, Long> {
//  List<Tutorial> findByPublished(boolean published);
//
//  List<Tutorial> findByTitleContaining(String title);
}

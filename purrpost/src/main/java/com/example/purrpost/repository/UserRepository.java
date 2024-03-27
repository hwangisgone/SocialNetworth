package com.example.purrpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purrpost.model.User;

//Spring Data JPA creates CRUD implementation at runtime automatically.
public interface UserRepository extends JpaRepository<User, Long> {
//  List<Tutorial> findByPublished(boolean published);
//
//  List<Tutorial> findByTitleContaining(String title);
}

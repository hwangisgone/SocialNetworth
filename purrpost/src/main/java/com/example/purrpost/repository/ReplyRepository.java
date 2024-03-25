package com.example.purrpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purrpost.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
  //List<Tutorial> findById(long id_child);
  List<Reply> findAllById(Long id_parent);
  
}

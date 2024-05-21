package com.example.purrpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purrpost.model.Reply;
import com.example.purrpost.model.compositeid.ReplyId;

public interface ReplyRepository extends JpaRepository<Reply, ReplyId> {
  //List<Tutorial> findById(long id_child);
  List<Reply> findAllByParentId(long parentId);
  List<Reply> findAllByChildId(long childId);
  
}

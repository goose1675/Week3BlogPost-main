package com.agam.pcc.blogpost.repository;


import com.agam.pcc.blogpost.model.Blog;
// Import the Blog entity

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>{ 
 // Public: accessible from anywhere 
 // extends JpaRepository<Blog, Long>: 
 //the interface is a repository for the blog entity and the primary key(Id) has the type Long
  
    // Custom query method to find blogs by user ID
    List<Blog> findByUserId(Long userId);
  
}

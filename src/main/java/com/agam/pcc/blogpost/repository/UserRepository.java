package com.agam.pcc.blogpost.repository;

import com.agam.pcc.blogpost.model.User;
// Import the User entity so this repository knows what it's working with


import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{

 // Public: accessible from anywhere 
 // extends JpaRepository<user, Long>: 
 //the interface is a repository for the user entity and the primary key(Id) has the type Long
  
    
}



/**
 * This interface allows you to perform CRUD operations on the User table.
 * It extends JpaRepository, which provides built-in methods like:
 * - save()
 * - findById()
 * - findAll()
 * - deleteById()
 */


package com.agam.pcc.blogpost.service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agam.pcc.blogpost.model.User;
import com.agam.pcc.blogpost.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * This class contains business logic related to users.
 */
//service classes are different from models because they contain the business logic of the application.
// They are responsible for processing data, interacting with repositories, and implementing the application's core functionality.
// on the other hand, models represent the data structure and relationships in the application.
//for example, a UserService class would handle operations like adding, updating, or deleting users,
// while a User model would define the properties of a user, such as username and password.

 @Service
public class UserService {
    
    private final UserRepository userRepository;

    //@Autowired

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    // This constructor injects the UserRepository dependency into the UserService.
    // which means that when an instance of UserService is created, it will automatically receive an instance of UserRepository.
    // This allows UserService to perform operations on the User entity, such as saving, retrieving, and deleting users.

    
    public User addUser(User user){
        user.setCreatedAt(LocalDateTime.now()); 
        return userRepository.save(user);

    }
    // This method saves a new user to the database and returns the saved user object.

    public List<User> getAllUsers(){
        return userRepository.findAll();

    }
    // This method retrieves all users from the database and returns them as a list.

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    // This method retrieves a user by their ID. It returns an Optional<User> to handle cases where the user might not be found.
    
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    // This method deletes a user by their ID.

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
    // This method updates an existing user. It finds the user by ID, updates their username, and saves the changes.
}

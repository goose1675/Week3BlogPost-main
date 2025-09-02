package com.agam.pcc.blogpost.controller;

import com.agam.pcc.blogpost.model.User;
import com.agam.pcc.blogpost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
// This annotation indicates that this class is a REST controller, which handles HTTP requests and responses.
// It allows the class to handle requests at the specified URL path.
@RequestMapping("/api/users")
// This annotation specifies the base URL path for all methods in this controller.
// In this case, all user-related endpoints will start with "/users".
// @RestController and @RequestMapping work together to define the URL structure for user-related operations.
// For example, if the application is running on localhost:8080, the endpoint for adding a user would be "http://localhost:8080/users".
public class UserController {

    @Autowired
    // This annotation automatically injects an instance of UserService into this controller.
    // It allows the controller to use the methods defined in UserService to perform operations related to users.
    private UserService userService;

    // Add a new user   
    @PostMapping
    // This annotation maps HTTP POST requests to this method.
    // It indicates that this method will handle requests to create a new user.
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }   

    // Delete a user by ID  
    @DeleteMapping("/{id}")
    // This method handles DELETE requests to remove a user by their ID.
    // It uses the @PathVariable annotation to extract the user ID from the URL.    
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Modify a user
    @PutMapping("/{id}")
    // This method handles PUT requests to update an existing user.
    // It uses the @PathVariable annotation to extract the user ID from the URL and the @RequestBody annotation to get the updated user data from the request body.
    // The method calls the UserService to update the user and returns the updated user object.
    public User modifyUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // View all users
    @GetMapping
    // This method handles GET requests to retrieve all users.
    // It uses the UserService to fetch the list of users and returns it.
    public List<User> getAllUsers() {
        return userService.getAllUsers();

    }
    
}
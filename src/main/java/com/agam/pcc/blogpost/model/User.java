package com.agam.pcc.blogpost.model;

//import org.aspectj.apache.bcel.classfile.Module.Uses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity 
@Table(name = "\"user\"")
// @Table(name = "\"user\""): This specifies the name of the table in the database.

//@Entity: This tells Spring Boot (via JPA) that this class should be mapped to a table in the database.
//User: This is your model class representing a user in your system.

public class User {
    @Id //Marks this field as the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    //@GeneratedValue(...): Automatically generates a unique ID for each user.
    //GenerationType.IDENTITY: Uses the database’s auto-increment feature.
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private String password;
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    @JsonIgnore
    private List<Blog> blogs = new ArrayList<>(); 
    
    //@OneToMany: This means one user can have many blogs.
    //mappedBy = "user": This tells JPA that the user field in the Blog class is the owner of the relationship.
    //cascade = CascadeType.ALL: Any operation on the user (like delete) will also apply to their blogs.
    //List<Blog>: This is a collection of all blogs written by this user.
    
    // Getters and setters
    
    public Long getId() {
        return id;
    }
    // This method returns the ID of this user.

    public void setId(Long id) {
        this.id = id;
    }
    // This method sets the ID for this user.

    public String getUsername() {
        return username;
    }
    // This method returns the username of this user.

    public void setUsername(String username) {
        this.username = username;
    }
    // This method sets the username for this user.
    

    public List<Blog> getBlogs() {
        return blogs;
    }
    // This method returns the list of blogs written by this user.

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
    // This method sets the list of blogs for this user.

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
    

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;

    }

}

// the difference between set and get methods is that
// "get" methods retrieve the value of a field, while 
//"set" methods assign a value to that field.

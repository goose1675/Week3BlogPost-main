package com.agam.pcc.blogpost.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;

@Entity //@Entity marks this class as a table in the database.
public class Blog {
    @Id//Marks this field as the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    //@GeneratedValue(...): Automatically generates a unique ID for each user.

    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createdAt;
    private Long likes;
    

    @ManyToOne //Many blogs can belong to one user.

    //@com.fasterxml.jackson.annotation.JsonBackReference

    @JoinColumn(name = "user_id")
    //@JoinColumn(name = "user_id"): This creates a foreign key column in the Blog table that links to the User table.

    private User user;
    //private User user: This is the actual reference to the user who wrote the blog.
    
    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }
    // This method returns the ID of this blog.

    public void setId(Long id) {
        this.id = id;
    }
    // This method sets the ID for this blog.

    public String getTitle() {
        return title;
    }
    // This method returns the title of this blog.

    public void setTitle(String title) {
        this.title = title;
    }
    // This method sets the title for this blog.

    public String getContent() {
        return content;
    }
    // This method returns the content of this blog.

    public void setContent(String content) {
        this.content = content;
    }
    // This method sets the content for this blog.

    public User getUser() {
        return user;
    }
    // This method returns the user who wrote this blog.

    public void setUser(User user) {
        this.user = user;
    }
    // This method sets the user for this blog.    

    
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }


    public Long getLikes() {
        return likes;
    }
    public void setLikes(Long likes) {
        this.likes = likes;
    }

}

    


//When you create a User, you can also create and attach multiple Blog objects to them.
//When you fetch a User, you can access all their blogs via the blogs list.
//When you fetch a Blog, you can access the User who wrote it via the user field.

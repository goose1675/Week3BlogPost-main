package com.agam.pcc.blogpost.service;
import com.agam.pcc.blogpost.repository.UserRepository;
import com.agam.pcc.blogpost.model.User;

import com.agam.pcc.blogpost.model.Blog;
import com.agam.pcc.blogpost.repository.BlogRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * This class contains business logic related to blogs.
 */
@Service
public class BlogService {
    // Service classes are responsible for processing data, interacting with repositories, and implementing the application's core functionality.
    // They handle operations like adding, updating, or deleting blogs, while the Blog model defines the properties of a blog, such as title, content, and user.

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    //@Autowired
    public BlogService(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;                
        this.userRepository = userRepository;

        // This constructor injects the BlogRepository dependency into the BlogService.
        // When an instance of BlogService is created, it will automatically receive an instance of BlogRepository.
        // This allows BlogService to perform operations on the Blog entity, such as saving, retrieving, and deleting blogs.
    }
        // blog repository: This is the repository that interacts with the database to perform CRUD operations on the Blog entity.
        // it contains methods for saving, retrieving, updating, and deleting blogs and is found in the BlogRepository interface.
    public Blog addBlog(Blog blog) {
        blog.setCreatedAt(LocalDateTime.now());
        return blogRepository.save(blog);
    }


    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
    // This method retrieves all blogs from the database and returns them as a list.

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }
    // This method retrieves a blog by its ID. It returns an Optional<Blog> to handle cases where the blog might not be found.

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
    // This method deletes a blog by its ID.

    public Blog updateBlog(Long id, Blog updatedBlog) {
        return blogRepository.findById(id).map(blog -> {
            blog.setTitle(updatedBlog.getTitle());
            blog.setContent(updatedBlog.getContent());
            return blogRepository.save(blog);
        }).orElseThrow(() -> new RuntimeException("Blog not found"));
    }
    // This method updates an existing blog. It finds the blog by ID, updates its title and content, and saves the changes.

    public List<Blog> getBlogsByUserId(Long userId) {
        return blogRepository.findByUserId(userId);
    }
    // This method retrieves all blogs written by a specific user based on their user ID.

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Blog findBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }
    public Blog likeBlog(Long id) {
    Blog blog = findBlogById(id);
    if (blog != null) {
        blog.setLikes(blog.getLikes() == null ? 1 : blog.getLikes() + 1);
        return updateBlog(id, blog);
    }
    return null;
    }
}

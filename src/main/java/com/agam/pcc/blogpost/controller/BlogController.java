package com.agam.pcc.blogpost.controller;
import org.springframework.http.ResponseEntity;
import com.agam.pcc.blogpost.model.Blog;
import com.agam.pcc.blogpost.model.User;
import com.agam.pcc.blogpost.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")


@RestController
// This annotation indicates that this class is a REST controller, which handles HTTP requests and responses.
// It allows the class to handle requests at the specified URL path.
@RequestMapping("/api/blogs")
// This annotation specifies the base URL path for all methods in this controller.
// In this case, all blog-related endpoints will start with "/blogs".
public class BlogController {

    @Autowired
    // This annotation automatically injects an instance of BlogService into this controller.
    private BlogService blogService;

    @PostMapping
    public ResponseEntity<?> addBlog(@RequestBody Map<String, Object> body) {
        // Extract fields from the request body
        String title = (String) body.get("title");
        String content = (String) body.get("content");
        Map<String, Object> userMap = (Map<String, Object>) body.get("user");
        Long userId = Long.valueOf(userMap.get("id").toString());
        String password = (String) body.get("password");

        // Find user and check password
        User user = blogService.findUserById(userId);
        if (user == null) return ResponseEntity.badRequest().body("User not found");
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(403).body("Incorrect password");
        }

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUser(user);
        Blog savedBlog = blogService.addBlog(blog);

        return ResponseEntity.ok(savedBlog);
    }   

    // Delete a blog by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String password = body.get("password");
        Blog blog = blogService.findBlogById(id);
        if (blog == null) return ResponseEntity.notFound().build();

        User user = blog.getUser();
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(403).body("Incorrect password");
        }

        blogService.deleteBlog(id);
        return ResponseEntity.ok().build();
    }

    // Update a blog
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Blog blog = blogService.findBlogById(id);
        if (blog == null) return ResponseEntity.notFound().build();

        String title = (String) body.get("title");
        String content = (String) body.get("content");
        String password = (String) body.get("password");

        User user = blog.getUser();
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(403).body("Incorrect password");
        }

        blog.setTitle(title);
        blog.setContent(content);
        Blog updatedBlog = blogService.updateBlog(id, blog);

        return ResponseEntity.ok(updatedBlog);
    }
    // View all blogs
    @GetMapping
    // This method handles GET requests to retrieve all blogs.
    // It uses the BlogService to fetch the list of blogs and returns it.
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    // View blogs by user ID
    @GetMapping("/user/{userId}")
    // This method retrieves all blogs associated with a specific user ID.
    // It uses the @PathVariable annotation to extract the user ID from the URL.
    public List<Blog> getBlogsByUserId(@PathVariable Long userId) {
        return blogService.getBlogsByUserId(userId);
    }

    @PutMapping("/{id}/like")
    public ResponseEntity<Blog> likeBlog(@PathVariable Long id) {
        Blog updatedBlog = blogService.likeBlog(id);
        if (updatedBlog != null) {
            return ResponseEntity.ok(updatedBlog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


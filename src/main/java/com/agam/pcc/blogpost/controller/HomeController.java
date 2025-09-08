// src/main/java/com/agam/pcc/blogpost/controller/HomeController.java
package com.agam.pcc.blogpost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }
}
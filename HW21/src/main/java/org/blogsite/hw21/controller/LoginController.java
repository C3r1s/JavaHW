package org.blogsite.hw21.controller;

import org.blogsite.hw21.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final PostService postService;

    public LoginController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        return "index";
    }
}

package com.practice.scooterrentalspringapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index()
    {
        return "index.html";
    }

    @GetMapping("/admin")
    public String adminPage()
    {
        return "admin.html";
    }

    @GetMapping("/user")
    public String userPage()
    {
        return "user.html";
    }
}

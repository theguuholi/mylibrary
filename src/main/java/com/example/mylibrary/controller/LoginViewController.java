package com.example.mylibrary.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginViewController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    public String home(Authentication auth) {
        return auth.getName();
    }
}

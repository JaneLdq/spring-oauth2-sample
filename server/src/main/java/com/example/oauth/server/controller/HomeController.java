package com.example.oauth.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String login(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "home";
    }

}
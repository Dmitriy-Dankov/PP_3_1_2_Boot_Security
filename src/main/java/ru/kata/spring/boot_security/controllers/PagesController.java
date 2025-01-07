package ru.kata.spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.kata.spring.boot_security.models.ModelUser;
import ru.kata.spring.boot_security.services.UserServiceImpl;

@Controller
public class PagesController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/admin")
    public String adminEndpoint(@AuthenticationPrincipal ModelUser currentUser, Model model) {
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("users", userService.findAll());
        
        return "admin";
    }

    @GetMapping("/user")
    public String userEndpoint(@AuthenticationPrincipal ModelUser currentUser, Model model) {
        model.addAttribute("currentUser", currentUser);
        return "user";
    }
}

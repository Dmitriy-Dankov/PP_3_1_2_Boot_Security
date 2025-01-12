package ru.kata.spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;

@Controller
public class PagesController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String adminEndpoint(@AuthenticationPrincipal User currentUser, Model model) {
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("users", userService.findAll());      
        return "admin";
    }

    @GetMapping("/user")
    public String userEndpoint(@AuthenticationPrincipal User currentUser, Model model) {
        model.addAttribute("currentUser", currentUser);
        return "user";
    }
}

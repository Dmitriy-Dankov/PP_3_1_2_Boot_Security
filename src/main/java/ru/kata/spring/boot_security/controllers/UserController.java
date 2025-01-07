package ru.kata.spring.boot_security.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import ru.kata.spring.boot_security.forms.RegistrationForm;
import ru.kata.spring.boot_security.models.ModelUser;
import ru.kata.spring.boot_security.services.UserService;

@Controller
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/add_user")
    public String processAddUser(RegistrationForm form) {
        userService.save(form.toUser(passwordEncoder));
        return "redirect:/admin";
    }

    @PostMapping("/delete_user")
    public String processDeleteUser(Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/edit_user")
    public String processEditUser(ModelUser user) {
        userService.update(user, passwordEncoder);
        return "redirect:/admin";
    }
}

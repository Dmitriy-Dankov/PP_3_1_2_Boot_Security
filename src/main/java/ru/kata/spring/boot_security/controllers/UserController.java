package ru.kata.spring.boot_security.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.dto.UserDTO;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add_user")
    public String processAddUser(UserDTO form) {
        userService.save(form.toUser());
        return "redirect:/admin";
    }

    @PostMapping("/delete_user")
    public String processDeleteUser(Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/edit_user")
    public String processEditUser(User user) {
        userService.update(user);
        return "redirect:/admin";
    }
}

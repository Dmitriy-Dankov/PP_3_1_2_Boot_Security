package ru.kata.spring.boot_security.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.dto.UserDTO;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Iterable<User> getMethodName() {
        return userService.findAll();
    }

    @PostMapping("/add_user")
    public HttpStatus processAddUser(@RequestBody UserDTO form) {
        userService.save(form.toUser());
        return HttpStatus.OK;
    }

    @PostMapping("/delete_user")
    public HttpStatus processDeleteUser(@RequestBody Long id) {
        userService.delete(id);
        return HttpStatus.OK;
    }

    @PostMapping("/edit_user")
    public HttpStatus processEditUser(@RequestBody User user) {
        userService.update(user);
        return HttpStatus.OK;
    }
}

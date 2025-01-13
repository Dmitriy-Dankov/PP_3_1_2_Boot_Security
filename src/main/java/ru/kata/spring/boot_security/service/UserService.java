package ru.kata.spring.boot_security.service;

import ru.kata.spring.boot_security.model.User;

public interface UserService {
    
    Iterable<User> findAll();

    User findByEmail(String email);

    void save(User user);

    void delete(Long id);

    void update(User user);
}
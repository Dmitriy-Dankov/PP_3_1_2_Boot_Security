package ru.kata.spring.boot_security.services;

import org.springframework.security.crypto.password.PasswordEncoder;

import ru.kata.spring.boot_security.models.ModelUser;

public interface UserService {
    Iterable<ModelUser> findAll();
    ModelUser findByEmail(String email);
    void save(ModelUser user);
    void delete(Long id);
    void update(ModelUser user, PasswordEncoder passwordEncoder);
}
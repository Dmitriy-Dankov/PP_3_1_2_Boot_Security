package ru.kata.spring.boot_security.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.kata.spring.boot_security.models.ModelUser;
import ru.kata.spring.boot_security.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Iterable<ModelUser> findAll() {
        return userRepo.findAll();
    }

    @Override
    public ModelUser findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void save(ModelUser user) {
        if (!userRepo.existsByEmail(user.getEmail())) {
            userRepo.save(user);
        }
    }

    @Override
    public void delete(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        }
    }

    @Override
    public void update(ModelUser user, PasswordEncoder passwordEncoder) {
        if (userRepo.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        }
    }
}

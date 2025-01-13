package ru.kata.spring.boot_security.service;

import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.repositorie.UserRepo;
import ru.kata.spring.boot_security.security.UserDetailsImpl;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void save(User user) {
        if (!userRepo.existsByName(user.getName())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    @Transactional
    public void update(User user) {
        if (userRepo.existsByName(user.getName())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = findByName(name);

        if (user != null) {
            return new UserDetailsImpl(user);
        }

        throw new UsernameNotFoundException("User '" + name + "' not found");
    }

    @Transactional
    private User findByName(String name) {
        User usr = userRepo.findByName(name);
        Hibernate.initialize(usr.getRoles());
        return usr;
    }
}

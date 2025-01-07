package ru.kata.spring.boot_security.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.kata.spring.boot_security.models.ModelUser;

@Repository
public interface UserRepo extends CrudRepository<ModelUser, Long> {
    ModelUser findByEmail(String email);
    boolean existsByEmail(String email);
}
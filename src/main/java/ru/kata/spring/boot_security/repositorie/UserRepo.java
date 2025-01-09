package ru.kata.spring.boot_security.repositorie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findByName(String name);

    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
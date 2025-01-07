package ru.kata.spring.boot_security.forms;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import ru.kata.spring.boot_security.models.ModelUser;

@Data
public class RegistrationForm {
    private String password;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private List<SimpleGrantedAuthority> roles = List.of(new SimpleGrantedAuthority("USER"));

    public ModelUser toUser(PasswordEncoder passwordEncoder) {
        return new ModelUser(passwordEncoder.encode(password), name, surname, age, email, roles);
    }
}

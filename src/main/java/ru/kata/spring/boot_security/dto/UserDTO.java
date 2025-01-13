package ru.kata.spring.boot_security.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.kata.spring.boot_security.model.Role;
import ru.kata.spring.boot_security.model.User;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String password;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private List<Role> roles;

    public User toUser() {
        return new User(password, name, surname, age, email, roles);
    }

    @Override
    public String toString() {
        return "UserDTO [password=" + password + ", name=" + name + ", surname=" + surname + ", age=" + age + ", email="
                + email + ", roles=" + roles + "]";
    }
}

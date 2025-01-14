package ru.kata.spring.boot_security;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.kata.spring.boot_security.model.Role;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(UserService userService) {
		return args -> {
			userService.save(new User("admin", "admin", "admin", 35,
					"admin@mail.ru", List.of(new Role("ADMIN"))));
			userService.save(new User("user", "user", "user", 30,
					"user@mail.ru", List.of(new Role("USER"))));
		};
	}
}

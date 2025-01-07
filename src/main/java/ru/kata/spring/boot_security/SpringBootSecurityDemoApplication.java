package ru.kata.spring.boot_security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.kata.spring.boot_security.models.ModelUser;
import ru.kata.spring.boot_security.services.UserService;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);		
	}

	@Bean
    public CommandLineRunner dataLoader(UserService userService, PasswordEncoder encoder) {
        List<SimpleGrantedAuthority> ls = new ArrayList<>();
		ls.add(new SimpleGrantedAuthority("ADMIN"));
		ls.add(new SimpleGrantedAuthority("USER"));
		return args -> {
           userService.save(new ModelUser(encoder.encode("123"), "admin", "admin", 34,
                        "admin@mail.ru", ls));
        };
    }
}

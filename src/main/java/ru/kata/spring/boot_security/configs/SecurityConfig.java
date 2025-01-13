package ru.kata.spring.boot_security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@ComponentScan(basePackages = { "ru.kata.spring.boot_security.controllers",
        "ru.kata.spring.boot_security.service", "ru.kata.spring.boot_security.security" })
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SuccessUserHandler successUserHandler;

    public SecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    protected void authenticated(AuthenticationManagerBuilder auth) throws Exception {
        auth.getDefaultUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())).authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/admin", "/api/**").hasAuthority("ADMIN")
                                .requestMatchers("/user").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers("/", "/**").permitAll()
                                .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login").permitAll()
                        .usernameParameter("name")
                        .successHandler(successUserHandler).permitAll());
        return http.build();
    }
}
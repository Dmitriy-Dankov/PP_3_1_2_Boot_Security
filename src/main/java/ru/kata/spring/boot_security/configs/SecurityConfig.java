package ru.kata.spring.boot_security.configs;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;

@ComponentScan(basePackages = { "ru.kata.spring.boot_security.controllers",
        "ru.kata.spring.boot_security.service" })
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SuccessUserHandler successUserHandler;

    public SecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return name -> {
            User user = userService.findByName(name);
            if (user != null) {
                return user;
            }

            throw new UsernameNotFoundException("User '" + name + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())   
                .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())            
            ).authorizeHttpRequests(
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

final class SpaCsrfTokenRequestHandler implements CsrfTokenRequestHandler {
	private final CsrfTokenRequestHandler plain = new CsrfTokenRequestAttributeHandler();
	private final CsrfTokenRequestHandler xor = new XorCsrfTokenRequestAttributeHandler();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> csrfToken) {

		this.xor.handle(request, response, csrfToken);

		csrfToken.get();
	}

	@Override
	public String resolveCsrfTokenValue(HttpServletRequest request, CsrfToken csrfToken) {
		String headerValue = request.getHeader(csrfToken.getHeaderName());
		return (StringUtils.hasText(headerValue) ? this.plain : this.xor).resolveCsrfTokenValue(request, csrfToken);
	}
}
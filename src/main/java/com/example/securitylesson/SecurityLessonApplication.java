package com.example.securitylesson;

import com.example.securitylesson.config.jwt.JwtService;
import com.example.securitylesson.model.Authority;
import com.example.securitylesson.model.Role;
import com.example.securitylesson.model.user;
import com.example.securitylesson.repository.UserRepository;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
@RequiredArgsConstructor
@Slf4j
@SpringBootApplication
public class SecurityLessonApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    public static void main(String[] args) {
        SpringApplication.run(SecurityLessonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Try to add user");
        Authority authority=new Authority();
        authority.setRole(Role.ROLE_ADMIN );
        user use1r=user.builder().
                username("Taleh")
                .password(encoder.encode("elbrus11"))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .Authorities(List.of(authority))
                .build();
        userRepository.save(use1r);
        String token1=jwtService.issueToken(use1r, Duration.ofHours(1));
        log.info("JWT for user is{}",token1);

        log.info("Parse JWT {}",jwtService.parseToken(token1));
    }
}

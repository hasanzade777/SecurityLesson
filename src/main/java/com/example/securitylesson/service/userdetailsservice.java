package com.example.securitylesson.service;

import com.example.securitylesson.model.user;
import com.example.securitylesson.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class userdetailsservice implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("getting user info by username {}", username);
        user user= (com.example.securitylesson.model.user) userRepository.findByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException("User with" + username + "not found"));
        return user;
    }

    ;
}

package com.example.securitylesson.repository;

import com.example.securitylesson.model.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<user,Long> {

    @EntityGraph(attributePaths = "Authorities")
    Optional<user> findByUsername(String Username);
}

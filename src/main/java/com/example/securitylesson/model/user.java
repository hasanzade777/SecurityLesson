package com.example.securitylesson.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class user implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
     Long id;
    String username;
    String password;
    boolean isEnabled;
    boolean isCredentialsNonExpired;
    boolean isAccountNonLocked;
    boolean isAccountNonExpired;
    @OneToMany(cascade=CascadeType.ALL)
    List<Authority> Authorities;
}

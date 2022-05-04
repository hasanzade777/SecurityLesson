package com.example.securitylesson.controller;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/security")
public class Kontroller {
    @GetMapping("/test")
    public ResponseDto get() {
        return new ResponseDto("Hello World");
    }

    @GetMapping("/login")
    public ResponseDto login(Principal principal) {
        return new ResponseDto("Welcome to site " + principal.getName());
    }

    @GetMapping("/user")
    public ResponseDto user(Principal principal) {
        return new ResponseDto("Welcome to site " + principal.getName());
    }

    @GetMapping("/admin")
    public ResponseDto admin(Principal principal) {
        return new ResponseDto("Welcome to site " + principal.getName());
    }
    @PostMapping("/post")
    public ResponseDto post(@RequestBody ResponseDto dto) {
        return dto;
    }

}

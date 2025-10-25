package com.example.resumeportal.controller;

import com.example.resumeportal.entity.User;
import com.example.resumeportal.service.UserService;
import com.example.resumeportal.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

@RestController
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, Object> body) {
        User u = new User();
        u.setName((String)body.get("name"));
        u.setEmail((String)body.get("email"));
        u.setAddress((String)body.get("address"));
        u.setUserType(((String) body.get("userType")).trim());
        u.setPasswordHash((String)body.get("password"));
        u.setProfileHeadline((String)body.get("profileHeadline"));
        User saved = userService.createUser(u);
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email"); String pwd = body.get("password");
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pwd));
        String token = tokenProvider.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));
    }
}

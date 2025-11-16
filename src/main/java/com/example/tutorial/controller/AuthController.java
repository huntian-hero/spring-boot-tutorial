package com.example.tutorial.controller;

import com.example.tutorial.dto.LoginRequest;
import com.example.tutorial.dto.SignupRequest;
import com.example.tutorial.security.jwt.JwtUtils;
import com.example.tutorial.service.UserDetailsServiceImpl;
import com.example.tutorial.service.UserService;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private UserService userService;
  @Autowired private UserDetailsService userDetailsService;
  @Autowired private JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
    String token = jwtUtils.generateToken(userDetails);
    Map<String, Object> result = new HashMap<>();
    result.put("token", token);
    result.put("username", userDetails.getUsername());
    return ResponseEntity.ok(result);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
    if (userService.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity.badRequest().body("Error: Username is already taken!");
    }
    userService.registerUser(signupRequest);
    return ResponseEntity.ok("User registered successfully!");
  }
}

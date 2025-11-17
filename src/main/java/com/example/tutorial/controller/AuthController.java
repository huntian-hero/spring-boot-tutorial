package com.example.tutorial.controller;

import com.example.tutorial.dto.JwtResponse;
import com.example.tutorial.dto.LoginRequest;
import com.example.tutorial.dto.SignupRequest;
import com.example.tutorial.security.jwt.JwtUtils;
import com.example.tutorial.service.UserDetailsImpl;
import com.example.tutorial.service.UserDetailsServiceImpl;
import com.example.tutorial.service.UserService;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private UserService userService;
  @Autowired private UserDetailsService userDetailsService;
  @Autowired private JwtUtils jwtUtils;
  @Autowired private UserDetailsImpl userDetailsImpl;

  @PostMapping("/signin")
  public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    return ResponseEntity.ok(
        new JwtResponse(
            "Bearer " + jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
    if (userService.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity.badRequest().body("Error: Username is already taken!");
    }
    if (userService.existsByEmail(signupRequest.getEmail())) {

      return ResponseEntity.badRequest().body("Error: Email is already in use!");
    }
    userService.registerUser(signupRequest);
    return ResponseEntity.ok("User registered successfully!");
  }
}

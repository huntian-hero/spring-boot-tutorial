package com.example.tutorial.controller;

import com.example.tutorial.dto.SignupRequest;
import com.example.tutorial.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired private UserService userService;

  @PostMapping("signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
    if (userService.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity.badRequest().body("Error: Username is already taken!");
    }
    userService.registerUser(signupRequest);
    return ResponseEntity.ok("User registered successfully!");
  }
}

package com.example.tutorial.service;

import com.example.tutorial.dto.SignupRequest;

public interface UserService {
    boolean existsByUsername( String username);
    boolean existsByEmail(String email);
    void registerUser(SignupRequest signupRequest);
}

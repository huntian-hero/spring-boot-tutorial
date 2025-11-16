package com.example.tutorial.service;

import com.example.tutorial.dto.SignupRequest;

public interface UserService {
    boolean existsByUsername( String username);
    void registerUser(SignupRequest signupRequest);
}

package com.example.tutorial.service;

import com.example.tutorial.dto.SignupRequest;
import com.example.tutorial.entity.User;
import com.example.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired private UserRepository userRepository;

  @Autowired private PasswordEncoder encode;

  @Override
  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public void registerUser(SignupRequest signupRequest) {
    User user =
        User.builder()
            .username(signupRequest.getUsername())
            .password(encode.encode(signupRequest.getPassword()))
            .build();
    userRepository.save(user);
  }
}

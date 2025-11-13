package com.example.tutorial.controller;

import com.example.tutorial.dto.AuthRequest;
import com.example.tutorial.dto.UserResponse;
import com.example.tutorial.entity.User;
import com.example.tutorial.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "认证管理", description = "用户注册和登录API")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("用户名已存在");
        }
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("邮箱已存在");
        }

        User savedUser = userService.save(user);
        return ResponseEntity.ok(UserResponse.fromUser(savedUser));
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        Map<String, String> response = new HashMap<>();
        response.put("message", "登录成功");
        response.put("username", authRequest.getUsername());
        return ResponseEntity.ok(response);
    }
}
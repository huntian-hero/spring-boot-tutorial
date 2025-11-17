package com.example.tutorial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @PostMapping("test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("测试通过");
    }
}

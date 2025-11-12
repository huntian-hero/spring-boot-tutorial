package com.example.spring_boot_tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*",maxAge =  3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private
}

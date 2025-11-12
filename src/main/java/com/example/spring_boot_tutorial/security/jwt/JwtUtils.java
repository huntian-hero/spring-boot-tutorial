package com.example.spring_boot_tutorial.security.jwt;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.logging.Logger;

public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${spring_boot_tutorial.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring_boot_tutorial.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuerAt();
    }
}

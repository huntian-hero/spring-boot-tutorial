package com.example.tutorial.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtils {
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private long expiration;

  public String generateToken(UserDetails userDetails) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder()
        .issuer("xxx")
        .subject(userDetails.getPassword())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
        .signWith(key)
        .compact();
  }

  public String extractUsername(String token) {
    return extractClaims(token).getSubject();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    try {
      Claims claims = extractClaims(token);
      String username = claims.getSubject();
      return username.equals(userDetails.getUsername()) && !isTokenExpired(claims);
    } catch (JwtException | IllegalArgumentException e) {
      log.error("Token 验证失败：{}", e.getMessage());
      return false;
    }
  }

  private Claims extractClaims(String token) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
  }

  private boolean isTokenExpired(Claims claims) {
    return claims.getExpiration().before(new Date());
  }
}

package com.example.tutorial.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        // 设置响应状态码 401（未授权）
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 设置响应格式为 JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // 返回错误信息
        response.getWriter().write("{\"code\": 401, \"message\": \"Token 无效或已过期，请重新登录\"}");
    }
}

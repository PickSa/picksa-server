package com.picksa.picksaserver.global.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picksa.picksaserver.auth.exception.TokenEmptyException;
import com.picksa.picksaserver.global.response.AuthErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.picksa.picksaserver.auth.exception.AuthErrorCode.AUTHENTICATION_EMPTY;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        AuthErrorResponse errorResponse = AuthErrorResponse.from(AUTHENTICATION_EMPTY);
        String body = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(body);
    }

}

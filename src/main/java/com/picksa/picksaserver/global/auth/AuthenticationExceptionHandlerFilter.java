package com.picksa.picksaserver.global.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picksa.picksaserver.auth.exception.AuthenticationException;
import com.picksa.picksaserver.global.exception.ErrorCode;
import com.picksa.picksaserver.global.response.AuthErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationExceptionHandlerFilter extends OncePerRequestFilter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (AuthenticationException exception) {
            setResponse(response, exception);
        }
    }

    private void setResponse(HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        ErrorCode errorCode = exception.getErrorCode();
        AuthErrorResponse errorResponse = AuthErrorResponse.from(errorCode);
        String body = objectMapper.writeValueAsString(errorResponse);

        response.getWriter().write(body);
    }
}

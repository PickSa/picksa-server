package com.picksa.picksaserver.global.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picksa.picksaserver.global.response.AuthErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.picksa.picksaserver.auth.exception.AuthErrorCode.USER_NOT_PERMITTED;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        AuthErrorResponse errorResponse = AuthErrorResponse.from(USER_NOT_PERMITTED);
        String body = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(body);
    }

}

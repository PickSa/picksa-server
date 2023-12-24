package com.picksa.picksaserver.global.auth;

import com.picksa.picksaserver.auth.exception.AuthenticationUserNotRegisteredException;
import com.picksa.picksaserver.auth.exception.JwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.picksa.picksaserver.auth.exception.AuthErrorCode.USER_NOT_REGISTERED;
import static com.picksa.picksaserver.global.auth.JwtValidationType.VALID_JWT;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtManager jwtManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = getTokenFromJwt(request);
            JwtValidationType jwtValidationType = jwtManager.validateToken(token);
            if (jwtValidationType == VALID_JWT) {
                UserAuthentication authentication = jwtManager.getAuthenticationFromJwt(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new JwtAuthenticationException(jwtValidationType.getErrorCode());
            }
        } catch (UsernameNotFoundException exception) {
            throw new AuthenticationUserNotRegisteredException(USER_NOT_REGISTERED);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

}

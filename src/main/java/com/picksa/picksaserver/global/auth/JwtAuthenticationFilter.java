package com.picksa.picksaserver.global.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtManager jwtManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            final String token = getTokenFromJwt(request);
            if (jwtManager.validateToken(token) == JwtValidationType.VALID_JWT) {
                Long memberId = jwtManager.getUserFromJwt(token);
                UserAuthentication authentication = new UserAuthentication(memberId.toString(), null, null);       //사용자 객체 생성
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));  // request 정보로 사용자 객체 디테일 설정
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception exception) {
            throw new RuntimeException("Invalid Token");
        }
    }

    private String getTokenFromJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}

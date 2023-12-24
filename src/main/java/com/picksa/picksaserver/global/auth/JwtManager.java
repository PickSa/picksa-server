package com.picksa.picksaserver.global.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class JwtManager {

    @Value("${jwt.secret_key}")
    private String JWT_SECRET;
    private final UserDetailsService userDetailsService;

    public JwtValidationType validateToken(String token) {
        try {
            final Claims claims = getBody(token);
            return JwtValidationType.VALID_JWT;
        } catch (SignatureException exception) {
            return JwtValidationType.INVALID_JWT_SIGNATURE;
        } catch (MalformedJwtException exception) {
            return JwtValidationType.INVALID_JWT;
        } catch (ExpiredJwtException exception) {
            return JwtValidationType.EXPIRED_JWT;
        } catch (UnsupportedJwtException exception) {
            return JwtValidationType.UNSUPPORTED_JWT;
        } catch (IllegalArgumentException exception) {
            return JwtValidationType.EMPTY_JWT;
        }
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    }

    private Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public UserAuthentication getAuthenticationFromJwt(String token) {
        String userId = getBody(token).get("id").toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return UserAuthentication.from(userDetails);
    }

}

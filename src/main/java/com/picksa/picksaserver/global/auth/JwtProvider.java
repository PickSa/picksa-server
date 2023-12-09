package com.picksa.picksaserver.global.auth;

import com.picksa.picksaserver.user.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final SecretKey key;

    public JwtProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    public String provideAccessToken(UserEntity user) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + jwtProperties.getAccessTokenExpiration());

        return Jwts.builder()
                .claim("id", String.valueOf(user.getId()))
                .claim("position", user.getPosition())
                .claim("part", user.getPart().name())
                .setIssuedAt(now)
                .setExpiration(expiredAt)
                .signWith(key)
                .compact();
    }

}

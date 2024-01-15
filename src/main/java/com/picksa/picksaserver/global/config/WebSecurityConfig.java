package com.picksa.picksaserver.global.config;

import com.picksa.picksaserver.global.auth.AuthenticationExceptionHandlerFilter;
import com.picksa.picksaserver.global.auth.CustomAccessDeniedHandler;
import com.picksa.picksaserver.global.auth.CustomAuthenticationEntryPoint;
import com.picksa.picksaserver.global.auth.JwtAuthenticationFilter;
import com.picksa.picksaserver.user.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationExceptionHandlerFilter authenticationExceptionHandlerFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/auth/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/tags/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/evaluations/final/**", HttpMethod.PATCH.name())).hasRole(Position.PART_LEADER.name())
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/interview/schedules",HttpMethod.POST.name())).hasRole(Position.PRESIDENT.name())
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/interview/schedules",HttpMethod.PATCH.name())).hasRole(Position.PRESIDENT.name())
                        .anyRequest()
                        .authenticated()
                )
                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationExceptionHandlerFilter, JwtAuthenticationFilter.class)
                .exceptionHandling(handler -> handler
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers ->
                        headers.frameOptions(FrameOptionsConfig::sameOrigin)
                );

        return http.build();
    }

}

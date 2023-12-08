package com.picksa.picksaserver.global.config;

import com.picksa.picksaserver.auth.config.GoogleOAuthProperties;
import com.picksa.picksaserver.global.auth.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value= {GoogleOAuthProperties.class, JwtProperties.class})
public class PropertiesConfig {

}

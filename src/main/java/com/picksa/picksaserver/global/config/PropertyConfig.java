package com.picksa.picksaserver.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/.env")
public class PropertyConfig {
}

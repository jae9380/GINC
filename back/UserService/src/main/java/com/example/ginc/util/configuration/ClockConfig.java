package com.example.ginc.util.configuration;

import com.example.infrastructure.SystemClockHolder;
import com.example.service.port.ClockHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfig {

    @Bean
    public ClockHolder clockHolder() {
        return new SystemClockHolder();
    }
}
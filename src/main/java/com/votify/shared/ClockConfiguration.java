package com.votify.shared;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ClockConfiguration {
    @Bean
    public Clock clock() {
        return new Clock() {
        };
    }
}

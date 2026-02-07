package tn.enis.fooddelivery.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        // Set log level to FULL for detailed request/response logging
        return Logger.Level.FULL;
    }
}

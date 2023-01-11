package com.example.order_service.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Resilience4JConfig {

  @Bean
  public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
    return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
        .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(3)).build())
        .circuitBreakerConfig(CircuitBreakerConfig.custom()
            .slidingWindowSize(10) // window
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
            .minimumNumberOfCalls(5) // # of failing requests allowed
            .failureRateThreshold(25) // % of failing requests allowed
            .build())
        .build());
  }
}

/*
In service we can use:
   BigDecimal price = circuitBreakerFactory.create("getPrice").run(
                () -> priceService.getPrice(bookId, bookDto),
                t -> BigDecimal.ZERO); // fallback method
        bookDto.setPrice(price);
 */
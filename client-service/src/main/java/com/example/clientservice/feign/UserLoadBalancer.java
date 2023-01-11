package com.example.clientservice.feign;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

@LoadBalancerClient("ORDER-SERVICE")
public class UserLoadBalancer {
  @LoadBalanced
  @Bean
  public Feign.Builder feignBuilder() {
    return Feign.builder();
  }
}

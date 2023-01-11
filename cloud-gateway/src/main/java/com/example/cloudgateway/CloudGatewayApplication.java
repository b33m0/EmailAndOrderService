package com.example.cloudgateway;

import com.example.cloudgateway.config.RedisHashComponent;
import com.example.cloudgateway.dto.ApiKey;
import com.example.cloudgateway.util.AppConstants;
import jakarta.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@EnableHystrix
@SpringBootApplication
public class CloudGatewayApplication {

	@Autowired
	private RedisHashComponent redisHashComponent;

	@PostConstruct
	public void initKeysToRedis() {

		// list of APIKeys
		List<ApiKey> apiKeys = new ArrayList<>();

		apiKeys.add(new ApiKey("343C-ED0B-4137-B27E", Stream.of(AppConstants.ORDER_SERVICE_KEY,
				AppConstants.EMAIL_SERVICE_KEY).collect(Collectors.toList())));
		apiKeys.add(new ApiKey("FA48-EF0C-427E-8CCF", Stream.of(AppConstants.EMAIL_SERVICE_KEY)
				.collect(Collectors.toList())));

		List<Object> lists = redisHashComponent.hValues(AppConstants.RECORD_KEY);
		if (lists.isEmpty()) {
			apiKeys.forEach(k -> redisHashComponent.hSet(AppConstants.RECORD_KEY, k.getKey(), k));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

}

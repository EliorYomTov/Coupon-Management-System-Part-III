package com.elior.couponManagementSystem.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.elior.couponManagementSystem.security.Information;

@Configuration
public class TokenManagerConfig {
	@Bean
	public static Map<String, Information> createMap() {
		return new HashMap<>();
	}
}

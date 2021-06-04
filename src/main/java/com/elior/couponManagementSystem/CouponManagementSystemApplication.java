package com.elior.couponManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponManagementSystemApplication.class, args);
		System.out.println("\r\n" + "Ioc Container was loaded \r\n");
	}
}

package com.elior.couponManagementSystem.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	private String timestamp;
	private String Key;
	private String Value;
	private int code;
}

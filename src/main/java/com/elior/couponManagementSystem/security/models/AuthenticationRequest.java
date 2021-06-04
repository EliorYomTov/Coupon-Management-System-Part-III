package com.elior.couponManagementSystem.security.models;

import com.elior.couponManagementSystem.login.ClientType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
	 private String email;
	 private String password;
	 ClientType clientType;
}

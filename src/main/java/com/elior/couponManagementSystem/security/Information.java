package com.elior.couponManagementSystem.security;

import com.elior.couponManagementSystem.login.ClientType;
import com.elior.couponManagementSystem.services.ClientService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Information {
	private ClientService clientService;
	private ClientType clientType;
}

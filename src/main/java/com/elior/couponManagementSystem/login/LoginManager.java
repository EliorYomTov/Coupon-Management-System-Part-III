package com.elior.couponManagementSystem.login;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.elior.couponManagementSystem.exception.CouponSystemException;
import com.elior.couponManagementSystem.exception.LoginFailedException;
import com.elior.couponManagementSystem.services.AdminService;
import com.elior.couponManagementSystem.services.ClientService;
import com.elior.couponManagementSystem.services.CompanyService;
import com.elior.couponManagementSystem.services.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginManager {

	private final ApplicationContext ctx;
	private final AdminService adminService;

	public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException, LoginFailedException {
		ClientService clientService = null;
		switch (clientType) {
		case ADMINISTRATOR:
			clientService = (ClientService) adminService;
			break;
		case COMPANY:
			clientService = (ClientService) ctx.getBean(CompanyService.class);
			break;
		case CUSTOMER:
			clientService = (ClientService) ctx.getBean(CustomerService.class);
			break;
		}
		if (!clientService.login(email, password))
			throw new LoginFailedException(clientType.name().toString() + " Unauthorized");
		return clientService;
	}
}

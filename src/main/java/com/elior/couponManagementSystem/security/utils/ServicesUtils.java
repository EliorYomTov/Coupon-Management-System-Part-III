package com.elior.couponManagementSystem.security.utils;

import com.elior.couponManagementSystem.login.ClientType;
import com.elior.couponManagementSystem.services.ClientService;
import com.elior.couponManagementSystem.services.CompanyServiceImpl;
import com.elior.couponManagementSystem.services.CustomerServiceImpl;

public class ServicesUtils {
	public static int getClientServiceId(ClientService clientService, ClientType clientType) {
		int clientServiceId = 0;
		switch (clientType) {
		case COMPANY:
			clientServiceId = ((CompanyServiceImpl)clientService).getCompanyId();
			break;
		case CUSTOMER:
			clientServiceId = ((CustomerServiceImpl)clientService).getCustomerId();
			break;
		}
		return clientServiceId;
	}
}

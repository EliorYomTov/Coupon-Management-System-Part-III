package com.elior.couponManagementSystem.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.elior.couponManagementSystem.security.Information;
import com.elior.couponManagementSystem.services.ClientService;

@Component

public class LoginCacheManger {
	private Map<String, Information> map = new HashMap<>();

	public void insertInfo(String key, Information information) {
		map.put(key, information);
	}

	public ClientService getClientService(String key) {
		return map.get(key).getClientService();
	}

	public ClientType getcClientType(String key) {
		return map.get(key).getClientType();
	}

	public void removeElement(String key) {
		map.remove(key);
	}
}

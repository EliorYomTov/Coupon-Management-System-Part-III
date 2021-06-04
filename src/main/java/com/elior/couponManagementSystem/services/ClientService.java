package com.elior.couponManagementSystem.services;
import org.springframework.stereotype.Service;

import com.elior.couponManagementSystem.exception.LoginFailedException;
import com.elior.couponManagementSystem.repos.CompanyRepository;
import com.elior.couponManagementSystem.repos.CouponRepository;
import com.elior.couponManagementSystem.repos.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//@SuperBuilder
public abstract class ClientService {
	protected final CompanyRepository companyRepository;
	protected final CustomerRepository customerRepository;
	protected final CouponRepository couponRepository;

	public abstract boolean login(String email, String password) throws LoginFailedException;
}

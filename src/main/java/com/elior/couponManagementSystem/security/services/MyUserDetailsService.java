package com.elior.couponManagementSystem.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elior.couponManagementSystem.beans.Company;
import com.elior.couponManagementSystem.beans.Customer;
import com.elior.couponManagementSystem.services.AdminServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
	
	private final List<UserDetails> CompaniesDetails;
	private final List<UserDetails> CustomersDetails;
	private final AdminServiceImpl AdminServiceImpl;
	
	public void initUsersDetails() {
		List<Company> companies = AdminServiceImpl.getAllCompanies();
		List<Customer> customers = AdminServiceImpl.getAllCustomers();
		for (Customer customer : customers) {
			CustomersDetails.add(new User(customer.getEmail(),customer.getPassword(), new ArrayList<>()));
		}
		for (Company company : companies) {
			CompaniesDetails.add(new User(company.getEmail(),company.getPassword(), new ArrayList<>()));
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		for (UserDetails companyDetails : CompaniesDetails) {
			if(companyDetails.getUsername().equalsIgnoreCase(userName))
				return companyDetails;
		}
		for (UserDetails customerDetails : CustomersDetails) {
			if (customerDetails.getUsername().equalsIgnoreCase(userName))
				return customerDetails;
		}
		return new User("admin@admin.com", "admin", new ArrayList<>());
	}
}
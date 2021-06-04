package com.elior.couponManagementSystem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elior.couponManagementSystem.beans.Company;
import com.elior.couponManagementSystem.beans.Coupon;
import com.elior.couponManagementSystem.beans.Customer;
import com.elior.couponManagementSystem.exception.CouponSystemException;
import com.elior.couponManagementSystem.repos.CompanyRepository;
import com.elior.couponManagementSystem.repos.CouponRepository;
import com.elior.couponManagementSystem.repos.CustomerRepository;

@Service
//@SuperBuilder
public class AdminServiceImpl extends ClientService implements AdminService {

	public AdminServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		super(companyRepository, customerRepository, couponRepository);
	}

	@Override
	public boolean login(String email, String password) {
		return (email.equalsIgnoreCase("admin@admin.com") && password.equalsIgnoreCase("admin"));
	}

	@Override
	public void addCompany(Company company) throws CouponSystemException {
		if (companyRepository.findCompanyByEmail(company.getEmail()) != null) {
			throw new CouponSystemException("It is not possible to add a company with the same email");
		} else if (companyRepository.findCompanyByName(company.getName()) != null) {
			throw new CouponSystemException("It is not possible to add a company with the same name");
		}
		companyRepository.save(company);
	}

	@Override
	public void updateCompany(Company company) throws CouponSystemException {
		if (companyRepository.findCompanyByIdAndName(company.getId(), company.getName()) != null) {
			companyRepository.saveAndFlush(company);
			return;
		}
		throw new CouponSystemException("Cannot update Company by Id or Name");
	}

	@Override
	public void deleteCompany(int CompanyId) throws CouponSystemException {
		if (!companyRepository.existsById(CompanyId)) {
			throw new CouponSystemException("Id not found!");
		}
		couponRepository.deleteCouponsFromCustomersVsCouponsByCompanyId(CompanyId);
		companyRepository.deleteById(CompanyId);

	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Company getOneCompany(int companyId) throws CouponSystemException {
		return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException("Id not found!"));
	}

	@Override
	public void addCustomer(Customer customer) throws CouponSystemException {
		if (customerRepository.findCustomerByEmail(customer.getEmail()) != null) {
			throw new CouponSystemException("It is not possible to add a customer with the same email");
		}
		customerRepository.save(customer);
	}

	@Override
	public void updateCustomer(Customer customer) throws CouponSystemException {
		if (customerRepository.findCustomerById(customer.getId()) != null) {
			customerRepository.saveAndFlush(customer);
			return;
		}
		throw new CouponSystemException("Unable to update customer Id");
	}

	@Override
	public void deleteCustomer(int customerId) {
		customerRepository.deleteById(customerId);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getOneCustomer(int customerId) throws CouponSystemException {
		return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException("Id not found!"));
	}

	@Override
	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}
}

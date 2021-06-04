package com.elior.couponManagementSystem.services;

import java.util.List;

import com.elior.couponManagementSystem.beans.Company;
import com.elior.couponManagementSystem.beans.Coupon;
import com.elior.couponManagementSystem.beans.Customer;
import com.elior.couponManagementSystem.exception.CouponSystemException;

public interface AdminService {

	void addCompany(Company company) throws CouponSystemException;

	void updateCompany(Company company) throws CouponSystemException;

	void deleteCompany(int CompanyId) throws CouponSystemException;

	List<Company> getAllCompanies();

	Company getOneCompany(int companyId) throws CouponSystemException;

	void addCustomer(Customer customer) throws CouponSystemException;

	void updateCustomer(Customer customer) throws CouponSystemException;

	void deleteCustomer(int customerId);

	List<Customer> getAllCustomers();

	Customer getOneCustomer(int customerId) throws CouponSystemException;
	/*--------------------------------------------------------------------------*/
	List<Coupon> getAllCoupons();
}

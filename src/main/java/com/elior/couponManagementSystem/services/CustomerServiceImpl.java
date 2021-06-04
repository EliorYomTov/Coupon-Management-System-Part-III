package com.elior.couponManagementSystem.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.elior.couponManagementSystem.beans.Category;
import com.elior.couponManagementSystem.beans.Coupon;
import com.elior.couponManagementSystem.beans.Customer;
import com.elior.couponManagementSystem.exception.CouponSystemException;
import com.elior.couponManagementSystem.exception.LoginFailedException;
import com.elior.couponManagementSystem.repos.CompanyRepository;
import com.elior.couponManagementSystem.repos.CouponRepository;
import com.elior.couponManagementSystem.repos.CustomerRepository;

import lombok.Getter;

@Service
@Getter
@Scope("prototype")
public class CustomerServiceImpl extends ClientService implements CustomerService {

	private int customerId;

	public CustomerServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		super(companyRepository, customerRepository, couponRepository);
	}

	@Override
	public boolean login(String email, String password) throws LoginFailedException {
		Customer res = customerRepository.findCustomerByEmailAndPassword(email, password);
		if (res != null) {
			this.customerId = res.getId();
			return true;
		}
		return false;
	}

	@Override
	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CouponSystemException("Customer id not found!"));

		Coupon couponToPurchase = couponRepository.findById(coupon.getId())
				.orElseThrow(() -> new CouponSystemException("Coupun Id not found!"));

		if (customer.getCoupons().stream().filter(x -> x.getId() == coupon.getId()).count() != 0) {
			throw new CouponSystemException("cannot possible purchase the same coupon more than once");
		}
		if (couponToPurchase.getAmount() == 0) {
			throw new CouponSystemException("coupon cannot be purchased if is not in stock");

		}
		if (LocalDate.now().isAfter(couponToPurchase.getEndDate().toLocalDate())) {
			throw new CouponSystemException("coupon cannot be purchased if its expired");
		}

		List<Coupon> customerCoupons = customer.getCoupons();
		customerCoupons.add(coupon);
		customer.setCoupons(customerCoupons);
		customerRepository.saveAndFlush(customer);

		int newCouponAmount = couponToPurchase.getAmount() - 1;
		couponToPurchase.setAmount(newCouponAmount);
		couponRepository.saveAndFlush(couponToPurchase);
	}

	@Override
	public List<Coupon> getCustomerCoupons() {
		List<Coupon> result = new ArrayList<>();
		customerRepository.findAll().stream().filter(x -> x.getId() == customerId)
				.forEach(x -> result.addAll(x.getCoupons()));
		return result;
	}

	@Override
	public List<Coupon> getCustomerCoupons(Category category) {
		return getCustomerCoupons().stream().filter(x -> x.getCategory().compareTo(category) == 0)
				.collect(Collectors.toList());
	}

	@Override
	public List<Coupon> getCustomerCoupons(double maxPrice) {
		return getCustomerCoupons().stream().filter(x -> x.getPrice() <= maxPrice).collect(Collectors.toList());
	}

	@Override
	public Customer getCustomerDetails() throws CouponSystemException {
		return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException("Id not found!"));
	}
}

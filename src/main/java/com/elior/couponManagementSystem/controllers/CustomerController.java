package com.elior.couponManagementSystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elior.couponManagementSystem.beans.Category;
import com.elior.couponManagementSystem.beans.Coupon;
import com.elior.couponManagementSystem.exception.CouponSystemException;
import com.elior.couponManagementSystem.exception.SecuritySystemException;
import com.elior.couponManagementSystem.security.utils.TokenManager;
import com.elior.couponManagementSystem.services.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController // localHost:8080/customer
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

	@PostMapping("/purchase-coupon")
	public ResponseEntity<?> purchaseCoupon(@RequestAttribute(name = "service") CustomerService customerService,
			@RequestBody Coupon coupon) throws CouponSystemException, SecuritySystemException {
		customerService.purchaseCoupon(coupon);
		return new ResponseEntity<>(HttpStatus.CREATED); // 201
	}

	@GetMapping("/coupons")
	public ResponseEntity<?> getCustomerCoupons(@RequestAttribute(name = "service") CustomerService customerService)
			throws SecuritySystemException {
		return new ResponseEntity<>(customerService.getCustomerCoupons(), HttpStatus.OK); // 200
	}

	@GetMapping("/coupons/category/{category}")
	public ResponseEntity<?> getCustomerCoupons(@RequestAttribute(name = "service") CustomerService customerService,
			@PathVariable("category") Category category) throws SecuritySystemException {
		return new ResponseEntity<>(customerService.getCustomerCoupons(category), HttpStatus.OK); // 200
	}

	@GetMapping("/coupon{maxprice}")
	public ResponseEntity<?> getCustomerCoupons(@RequestAttribute(name = "service") CustomerService customerService,
			@RequestParam("maxprice") double maxPrice) throws SecuritySystemException {
		return new ResponseEntity<>(customerService.getCustomerCoupons(maxPrice), HttpStatus.OK); // 200
	}

	@GetMapping("/details")
	public ResponseEntity<?> getCustomerDetails(@RequestAttribute(name = "service") CustomerService customerService)
			throws CouponSystemException, SecuritySystemException {
		return new ResponseEntity<>(customerService.getCustomerDetails(), HttpStatus.OK); // 200
	}
}

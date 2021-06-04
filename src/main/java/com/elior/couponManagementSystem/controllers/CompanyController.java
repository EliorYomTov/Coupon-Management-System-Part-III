package com.elior.couponManagementSystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elior.couponManagementSystem.beans.Category;
import com.elior.couponManagementSystem.beans.Coupon;
import com.elior.couponManagementSystem.exception.CouponSystemException;
import com.elior.couponManagementSystem.exception.LoginFailedException;
import com.elior.couponManagementSystem.exception.SecuritySystemException;
import com.elior.couponManagementSystem.services.CompanyService;

import lombok.RequiredArgsConstructor;

@RestController // localHost:8080/company
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

	@PostMapping("/coupon")
	public ResponseEntity<?> addCoupon(@RequestAttribute(name="service") CompanyService companyService, @RequestBody Coupon coupon)
			throws CouponSystemException, SecuritySystemException {
		companyService.addCoupon(coupon);
		return new ResponseEntity<>(HttpStatus.CREATED); // 201
	}

	@PutMapping("/coupon")
	public ResponseEntity<?> updateCoupon(@RequestAttribute(name="service") CompanyService companyService,
			@RequestBody Coupon coupon) throws CouponSystemException, LoginFailedException, SecuritySystemException {
		companyService.updateCoupon(coupon);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/coupon/{id}")
	public ResponseEntity<?> deleteCoupon(@RequestAttribute(name="service") CompanyService companyService,
			@PathVariable("id") int couponId) throws CouponSystemException, SecuritySystemException {
		companyService.deleteCoupon(couponId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
	}

	@GetMapping("/coupons")
	public ResponseEntity<?> getCompanyCoupons(@RequestAttribute(name="service") CompanyService companyService)
			throws SecuritySystemException {
		return new ResponseEntity<>(companyService.getCompanyCoupons(),
				HttpStatus.OK); // 200
	}

	@GetMapping("/coupon/category/{category}")
	public ResponseEntity<?> getCompanyCoupons(@RequestAttribute(name="service") CompanyService companyService,
			@PathVariable("category") Category category) throws SecuritySystemException {
		return new ResponseEntity<>(companyService.getCompanyCoupons(category),
				HttpStatus.OK); // 200
	}

	@GetMapping("/coupon{maxprice}")
	public ResponseEntity<?> getCompanyCoupons(@RequestAttribute(name="service") CompanyService companyService,
			@RequestParam("maxprice") double maxPrice) throws LoginFailedException, SecuritySystemException {
		return new ResponseEntity<>(companyService.getCompanyCoupons(maxPrice),
				HttpStatus.OK); // 200
	}

	@GetMapping("/details")
	public ResponseEntity<?> getCompanyDetails(@RequestAttribute(name="service") CompanyService companyService)
			throws CouponSystemException, LoginFailedException, SecuritySystemException {
		return new ResponseEntity<>(companyService.getCompanyDetails(),
				HttpStatus.OK); // 200
	}
}

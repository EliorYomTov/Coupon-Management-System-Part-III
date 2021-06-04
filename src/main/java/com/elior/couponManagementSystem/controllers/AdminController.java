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
import org.springframework.web.bind.annotation.RestController;

import com.elior.couponManagementSystem.beans.Company;
import com.elior.couponManagementSystem.beans.Customer;
import com.elior.couponManagementSystem.exception.CouponSystemException;
import com.elior.couponManagementSystem.exception.SecuritySystemException;
import com.elior.couponManagementSystem.services.AdminService;

import lombok.RequiredArgsConstructor;

@RestController // localHost:8080/admin
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	

	@PostMapping("/company")
	public ResponseEntity<?> addCompany(@RequestAttribute(name="service") AdminService adminService,
			@RequestBody Company company) throws CouponSystemException {
		adminService.addCompany(company);
		return new ResponseEntity<>(HttpStatus.CREATED); // 201
	}

	@PutMapping("/company")
	public ResponseEntity<?> updateCompany(@RequestAttribute(name="service") AdminService adminService,
			@RequestBody Company company) throws CouponSystemException {
		adminService.updateCompany(company);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
	}

	@DeleteMapping("/company/{id}")
	public ResponseEntity<?> deleteCompany(@RequestAttribute(name="service") AdminService adminService, @PathVariable int id)
			throws CouponSystemException {
		adminService.deleteCompany(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
	}

	@GetMapping("/company/{id}")
	public ResponseEntity<?> getOneCompany(@RequestAttribute(name="service") AdminService adminService, @PathVariable int id)
			throws CouponSystemException {
		return new ResponseEntity<>(adminService.getOneCompany(id), HttpStatus.OK); // 200
	}

	@GetMapping("/companies")
	public ResponseEntity<?> getAllCompanies(@RequestAttribute(name="service") AdminService adminService)
			throws SecuritySystemException {
		return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK); // 200
	}

	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@RequestAttribute(name="service") AdminService adminService,
			@RequestBody Customer customer) throws CouponSystemException {
		adminService.addCustomer(customer);
		return new ResponseEntity<>(HttpStatus.CREATED); // 201
	}

	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomer(@RequestAttribute(name="service") AdminService adminService,
			@RequestBody Customer customer) throws CouponSystemException {
		adminService.updateCustomer(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@RequestAttribute(name="service") AdminService adminService, @PathVariable int id) {
		adminService.deleteCustomer(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getOneCustomer(@RequestAttribute(name="service") AdminService adminService, @PathVariable int id)
			throws CouponSystemException {
		return new ResponseEntity<>(adminService.getOneCustomer(id), HttpStatus.OK); // 200
	}

	@GetMapping("/customers")
	public ResponseEntity<?> getAllCustomers(@RequestAttribute(name="service") AdminService adminService) {
		return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.OK); // 200
	}

	@GetMapping("/coupons")
	public ResponseEntity<?> getAllCoupons(@RequestAttribute(name="service") AdminService adminService) {
		return new ResponseEntity<>(adminService.getAllCoupons(), HttpStatus.OK); // 200
	}

}

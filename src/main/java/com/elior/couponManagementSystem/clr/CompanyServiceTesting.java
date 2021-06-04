package com.elior.couponManagementSystem.clr;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.elior.couponManagementSystem.beans.Category;
import com.elior.couponManagementSystem.beans.Coupon;
import com.elior.couponManagementSystem.exception.CouponSystemException;
import com.elior.couponManagementSystem.exception.LoginFailedException;
import com.elior.couponManagementSystem.repos.CouponRepository;
import com.elior.couponManagementSystem.repos.CustomerRepository;
import com.elior.couponManagementSystem.services.AdminService;
import com.elior.couponManagementSystem.services.ClientService;
import com.elior.couponManagementSystem.services.CompanyService;
import com.elior.couponManagementSystem.utils.ArtUtils;
import com.elior.couponManagementSystem.utils.PrintUtils;

import lombok.RequiredArgsConstructor;

@Component
@Order(3)
@RequiredArgsConstructor
public class CompanyServiceTesting implements CommandLineRunner {

	private final CompanyService companyService;
	private final CouponRepository couponRepository;
	private final CustomerRepository customerRepository;
	
	private final AdminService adminService;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(ArtUtils.COMPANY_SERVICE);
		companyLoginTesting();
		addCouponTesting();
		updateCouponTesting();
		deleteCouponTesting();
		getAllCompanyCouponsTesting();
		getCompanyCouponsByCategoryTesting();
		getCompanyCouponsByMaxPriceTesting();
		getCompanyDetails();
	}
	
	public void companyLoginTesting() throws LoginFailedException {
		System.out.println(ArtUtils.COMPANY_LOGIN);
		PrintUtils.printTest("#Test 21: Login - bad credentials");
		System.out.println(
				"Login successfully: " + ((ClientService) companyService).login("Zara@gmail.com", "zara678") + "\r\n");
		/*---------------------------------------------------------------------------------------------------------------*/
		PrintUtils.printTest("# Test 22: Login - good credentials");
		System.out.println("Login successfully: "
				+ ((ClientService) companyService).login("Booking@Booking.com", "1234") + "\r\n");
	}
	
	public void addCouponTesting() throws CouponSystemException {
		addCouponExistsTitleTesting();
		/*-----------------------------------------------------------------*/
		PrintUtils.printTest("# Test 24: Add Coupon(13) - company(8)");
		Coupon toAdd = Coupon.builder()
				.company(companyService.getCompanyDetails())
				.category(Category.PC)
				.title("Test Coupon")
				.description("Test Coupon")
				.startDate(Date.valueOf(LocalDate.now()))
				.endDate(Date.valueOf(LocalDate.now().plusDays(4)))
				.amount(100)
				.price(129.99f)
				.image("https://Booking.jpg")
				.build();
		
		companyService.addCoupon(toAdd);
		PrintUtils.printResultList(couponRepository.findAll());
		PrintUtils.printResultList(adminService.getAllCompanies());
	}
	
	
	public void addCouponExistsTitleTesting() throws CouponSystemException {
		System.out.println(ArtUtils.ADD_COUPON);
		PrintUtils.printTest("# Test 23: Add Coupon - exists title");
		Coupon toAdd = Coupon.builder()
				.company(companyService.getCompanyDetails())
				.category(Category.PC)
				.title("Save up to 15%")
				.description("Test Coupon")
				.startDate(Date.valueOf(LocalDate.now()))
				.endDate(Date.valueOf(LocalDate.now().plusDays(4)))
				.amount(100)
				.price(129.99f)
				.image("https://Booking.jpg").build();
		try {
			companyService.addCoupon(toAdd);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
		System.out.println();
	}
	
	public void updateCouponTesting() throws CouponSystemException {
		updateCouponChangeCompanyIdTesting();
		updateCouponChangeCouponIdTesting();
		/*-----------------------------------------------------------------*/
		PrintUtils.printTest("# Test 27: Update Coupon(13) - change category");
		Coupon toUpdate = companyService.getCompanyCoupons().get(0);
		toUpdate.setCategory(Category.ELECTRONICS);
		companyService.updateCoupon(toUpdate);
		PrintUtils.printResultList(couponRepository.findAll());
	}
	
	public void updateCouponChangeCompanyIdTesting() throws CouponSystemException {
		System.out.println(ArtUtils.UPDATE_COUPON);
		PrintUtils.printTest("# Test 25: Update Coupon - change company Id");
		Coupon toUpdate = companyService.getCompanyCoupons().get(0);
		toUpdate.setCompany(adminService.getOneCompany(3));
		try {
			companyService.updateCoupon(toUpdate);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
		System.out.println();
	}
	
	public void updateCouponChangeCouponIdTesting() {
		PrintUtils.printTest("# Test 26: Update Coupon - change coupon id");
		Coupon toUpdate = companyService.getCompanyCoupons().get(0);
		toUpdate.setId(25);
		try {
			companyService.updateCoupon(toUpdate);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
		System.out.println();
	}
	public void deleteCouponTesting() throws CouponSystemException {
		deleteAnotherCompanyCouponTesting();
		/*-----------------------------------------------------------------*/
		PrintUtils.printTest("# Test 29: Delete Coupon Id(10) - good");
		companyService.deleteCoupon(10);
		PrintUtils.printResultList(couponRepository.findAll());
		PrintUtils.printResultList(customerRepository.findAll());
	}
	
	public void deleteAnotherCompanyCouponTesting() {
		System.out.println(ArtUtils.DELETE_COUPON);
		PrintUtils.printTest("# Test 28: Delete Coupon(7) - wrong");
		try {
			companyService.deleteCoupon(7);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
	}
	
	public void getAllCompanyCouponsTesting() {
		System.out.println(ArtUtils.COMPANY_COUPONS);
		PrintUtils.printTest("# Test 30: Get all Company copons");
		PrintUtils.printResultList(companyService.getCompanyCoupons());
	}
	
	public void getCompanyCouponsByCategoryTesting() { 
		System.out.println(ArtUtils.COMPANY_COUPONS_BY_CATEGORY);
		PrintUtils.printTest("# Test 31: all Company copons by category(VACTION)");
		PrintUtils.printResultList(companyService.getCompanyCoupons(Category.VACTION));
	}
	
	public void getCompanyCouponsByMaxPriceTesting() {
		System.out.println(ArtUtils.COMPANY_COUPONS_BY_PRICE);
		PrintUtils.printTest("# Test 32: all Company copons max price(150)");
		PrintUtils.printResultList(companyService.getCompanyCoupons(150.0));
	}
	
	public void getCompanyDetails() throws CouponSystemException {
		System.out.println(ArtUtils.COMPANY_DETAILS);
		PrintUtils.printTest("# Test 33: Get company details");
		PrintUtils.printResult(companyService.getCompanyDetails());
	}
}



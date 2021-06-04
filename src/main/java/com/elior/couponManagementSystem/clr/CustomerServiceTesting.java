package com.elior.couponManagementSystem.clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.elior.couponManagementSystem.beans.Category;
import com.elior.couponManagementSystem.beans.Coupon;
import com.elior.couponManagementSystem.exception.CouponSystemException;
import com.elior.couponManagementSystem.exception.LoginFailedException;
import com.elior.couponManagementSystem.repos.CouponRepository;
import com.elior.couponManagementSystem.repos.CustomerRepository;
import com.elior.couponManagementSystem.services.ClientService;
import com.elior.couponManagementSystem.services.CustomerService;
import com.elior.couponManagementSystem.utils.ArtUtils;
import com.elior.couponManagementSystem.utils.PrintUtils;

import lombok.RequiredArgsConstructor;

@Component
@Order(4)
@RequiredArgsConstructor
public class CustomerServiceTesting implements CommandLineRunner {

	private final CustomerService customerService;
	private final CouponRepository couponRepository;
	private final CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {

		System.out.println(ArtUtils.CUSTOMER_SERVICE);
		customerLoginTesting();
		purchaseCouponTesting();
		getAllCustomerCoponsTesting();
		getCustomerCoponsByCategoryTesting();
		getCustomerCoponsByMaxPriceTesting();
		getCustomerDetailsTesting();
	}

	public void customerLoginTesting() throws LoginFailedException {
		System.out.println(ArtUtils.CUSTOMER_LOGIN);
		PrintUtils.printTest("# Test 34: Login - bad credentials");
		System.out.println(
				"Login successfully: " + ((ClientService) customerService).login("Shoki@gmail.com", "1233") + "\r\n");
		/*---------------------------------------------------------------------------------------------------------------------------*/
		PrintUtils.printTest("# Test 35: Login - good credentials");
		System.out.println("Login successfully: "
				+ ((ClientService) customerService).login("SapirCohen2020@gmail.com", "1234") + "\r\n");
	}

	public void purchaseCouponTesting() throws CouponSystemException, InterruptedException {
		purchaseCouponMoreThanOnceTesting();
		purchaseCouponAmountIsZeroTesting();
		purchaseCouponDateExpiredTesting();
		/*---------------------------------------------------------------------------------*/
		PrintUtils.printTest("# Test 39: Purchase coupon(2) - successful purchas");
		Coupon toPurchase = couponRepository.findById(2).get();
		customerService.purchaseCoupon(toPurchase);
		PrintUtils.printResultList(customerRepository.findAll());
		PrintUtils.printResultList(couponRepository.findAll());

	}

	public void purchaseCouponMoreThanOnceTesting() throws InterruptedException {
		System.out.println(ArtUtils.PURCHASE_COUPON);
		PrintUtils.printTest("# Test 36: Purchase coupon(11) - more than once");
		Coupon toPurchase = couponRepository.findById(11).get();
		try {
			customerService.purchaseCoupon(toPurchase);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Thread.sleep(500);
		}
		System.err.flush();
		System.out.println();
	}

	public void purchaseCouponAmountIsZeroTesting() {
		PrintUtils.printTest("# Test 37: Purchase coupon(9) - amount is zero");
		Coupon toPurchase = couponRepository.findById(9).get();
		try {
			customerService.purchaseCoupon(toPurchase);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
		System.out.println();
	}

	public void purchaseCouponDateExpiredTesting() {
		PrintUtils.printTest("# Test 38: Purchase coupon(6) - date expired");
		Coupon toPurchase = couponRepository.findById(6).get();
		try {
			customerService.purchaseCoupon(toPurchase);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
		System.out.println();
	}

	public void getAllCustomerCoponsTesting() {
		System.out.println(ArtUtils.CUSTOMER_COUPON);
		PrintUtils.printTest("# Test 40: all Customer copons");
		PrintUtils.printResultList(customerService.getCustomerCoupons());
	}

	public void getCustomerCoponsByCategoryTesting() {
		System.out.println(ArtUtils.CUSTOMER_COUPONS_BY_CATEGORY);
		PrintUtils.printTest("# Test 41: all customer copons by category(FOOD)");
		PrintUtils.printResultList(customerService.getCustomerCoupons(Category.SPORT));
	}

	public void getCustomerCoponsByMaxPriceTesting() {
		System.out.println(ArtUtils.CUSTOMER_COUPONS_BY_PRICE);
		PrintUtils.printTest("# Test 42: all customer copons by price(60)");
		PrintUtils.printResultList(customerService.getCustomerCoupons(60));
	}

	public void getCustomerDetailsTesting() throws CouponSystemException {
		System.out.println(ArtUtils.CUSTOMER_DETAILS);
		PrintUtils.printTest("# Test 43: Customer Details");
		PrintUtils.printResult(customerService.getCustomerDetails());
	}
}

package com.elior.couponManagementSystem.clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.elior.couponManagementSystem.exception.CouponSystemException;
import com.elior.couponManagementSystem.exception.LoginFailedException;
import com.elior.couponManagementSystem.login.ClientType;
import com.elior.couponManagementSystem.login.LoginManager;
import com.elior.couponManagementSystem.services.AdminService;
import com.elior.couponManagementSystem.services.CompanyService;
import com.elior.couponManagementSystem.services.CustomerService;
import com.elior.couponManagementSystem.utils.ArtUtils;
import com.elior.couponManagementSystem.utils.PrintUtils;

import lombok.RequiredArgsConstructor;

@Component
@Order(5)
@RequiredArgsConstructor
public class LoginManagerTesting implements CommandLineRunner {

	private final LoginManager loginManager;

	@Override
	public void run(String... args) throws Exception {

		System.out.println(ArtUtils.LOGIN_MANAGER);
		loginManagerAdminTesting();
		loginManagerCompanyTesting();
		loginManagerCustomerTesting();
	}

	public void loginManagerAdminTesting() throws CouponSystemException, LoginFailedException {
		loginManagerAdminUnauthorizedTesting();
		/*----------------------------------------------------------------------------------------*/
		PrintUtils.printTest("# Test 45: Login manager - Admin correct");
		AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin",
				ClientType.ADMINISTRATOR);
		PrintUtils.printResultList(adminService.getAllCompanies());
		System.out.println();
	}

	public void loginManagerAdminUnauthorizedTesting() {
		PrintUtils.printTest("# Test 44: Login manager - Admin failed");
		try {
			loginManager.login("Moshe@gmail.com", "1234", ClientType.ADMINISTRATOR);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
		System.out.println();
	}

	public void loginManagerCompanyTesting() throws CouponSystemException, InterruptedException, LoginFailedException {
		loginManagerSamsungUnauthorizedTesting();
		loginManagerCompanyOneTesting();
		loginManagerCompanyTwoTesting();
	}

	public void loginManagerSamsungUnauthorizedTesting() {
		PrintUtils.printTest("# Test 46: Login manager - Company failed");
		try {
			loginManager.login("samsung@samsung.com", "1234", ClientType.COMPANY);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
		System.out.println();
	}

	public void loginManagerCompanyOneTesting() throws CouponSystemException, LoginFailedException {
		PrintUtils.printTest("# Test 47: Login manager - Company Booking");
		CompanyService companyService = (CompanyService) loginManager.login("Booking@Booking.com", "1234",
				ClientType.COMPANY);
		PrintUtils.printResult(companyService.getCompanyDetails());
		System.out.println();
	}

	public void loginManagerCompanyTwoTesting() throws CouponSystemException, InterruptedException, LoginFailedException {
		PrintUtils.printTest("# Test 48: Login manager - Company Adidas");
		CompanyService companyService = (CompanyService) loginManager.login("Adidas@Adidas.com", "1234",
				ClientType.COMPANY);
		PrintUtils.printResult(companyService.getCompanyDetails());
		System.out.println();
		Thread.sleep(500);
	}

	public void loginManagerCustomerTesting() throws CouponSystemException, LoginFailedException {
		loginManagerCustomerUnauthorizedTesting();
		loginManagerCustomerOneTesting();
		loginManagerCustomerTowTesting();
	}

	public void loginManagerCustomerUnauthorizedTesting() {
		PrintUtils.printTest("# Test 49: Login manager - Customer failed");
		try {
			loginManager.login("netanyahu@gmail.com", "9999", ClientType.CUSTOMER);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
		System.out.println();
	}

	public void loginManagerCustomerOneTesting() throws CouponSystemException, LoginFailedException {
		PrintUtils.printTest("# Test 50: Login manager - Customer Tzach");
		CustomerService customerService = (CustomerService) loginManager.login("TzachShar76@gmail.com", "1234",
				ClientType.CUSTOMER);
		PrintUtils.printResultList(customerService.getCustomerCoupons());
		System.out.println();
	}

	public void loginManagerCustomerTowTesting() throws CouponSystemException, LoginFailedException {
		PrintUtils.printTest("# Test 51: Login manager - Customer Ben");
		CustomerService customerService = (CustomerService) loginManager.login("BenLolo432@gmail.com", "1234",
				ClientType.CUSTOMER);
		PrintUtils.printResultList(customerService.getCustomerCoupons());
		System.out.println();
	}
}

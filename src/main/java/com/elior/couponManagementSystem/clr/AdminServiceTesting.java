package com.elior.couponManagementSystem.clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.elior.couponManagementSystem.beans.Company;
import com.elior.couponManagementSystem.beans.Customer;
import com.elior.couponManagementSystem.exception.CouponSystemException;
import com.elior.couponManagementSystem.exception.LoginFailedException;
import com.elior.couponManagementSystem.repos.CouponRepository;
import com.elior.couponManagementSystem.repos.CustomerRepository;
import com.elior.couponManagementSystem.services.AdminService;
import com.elior.couponManagementSystem.services.ClientService;
import com.elior.couponManagementSystem.utils.ArtUtils;
import com.elior.couponManagementSystem.utils.PrintUtils;

import lombok.RequiredArgsConstructor;

@Component
@Order(2)
@RequiredArgsConstructor
public class AdminServiceTesting implements CommandLineRunner {

	private final AdminService adminService;
	private final CouponRepository couponRepository;
	private final CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(ArtUtils.ADMIN_SERVICE);
		adminLoginTesting();
		addCompanyTesting();
		updateCompanyTesting();
		deleteCompanyTesting();
		getAllCompaniesTesting();
		getCompanyByIdTesting();
		addCustomerTesting();
		updateCustomerTesting();
		deleteCustomerTesting();
		getAllCustomersTesting();
		getCustomerTesting();
	}

	public void adminLoginTesting() throws LoginFailedException {
		System.out.println(ArtUtils.ADMIN_LOGIN);
		PrintUtils.printTest("# Test 1: Login - bad credentials");
		System.out.println(
				"Login successfully: " + ((ClientService) adminService).login("kobi@gmail.com", "admin") + "\r\n");
		/*-----------------------------------------------------------------------------------------------------------*/
		PrintUtils.printTest("# Test 2: Login - good credentials");
		System.out.println(
				"Login successfully: " + ((ClientService) adminService).login("admin@admin.com", "admin") + "\r\n");
	}

	public void addCompanyTesting() throws CouponSystemException, InterruptedException {
		addCompanyExistsEmailTesting();
		/*----------------------------------------------------*/
		addCompanyExistsNameTesting();
		/*----------------------------------------------------*/
		PrintUtils.printTest("# Test 5: Add Company - Ksp");
		Company toAdd = Company.builder().name("Ksp").email("Ksp@ksp.com").password("1234").build();
		adminService.addCompany(toAdd);
		PrintUtils.printResultList(adminService.getAllCompanies());
	}

	public void addCompanyExistsEmailTesting() throws InterruptedException {
		System.out.println(ArtUtils.ADD_COMPANY);
		PrintUtils.printTest("# Test 3: Add Company - exists email");
		Company toAdd = Company.builder().name("The New Cola").email("CocaCola@Coca Cola.com").password("1234").build();
		try {
			adminService.addCompany(toAdd);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Thread.sleep(500);
		}
	
		System.err.flush();
		System.out.println();
	}

	public void addCompanyExistsNameTesting() {
		PrintUtils.printTest("# Test 4: Add Company - exists name");
		Company toAdd = Company.builder().name("Coca Cola").email("pepsi@pepsi.com").password("1234").build();
		try {
			adminService.addCompany(toAdd);
		} catch (Exception e) {
			System.err.println(e.getMessage());		
		}
		System.err.flush();
		System.out.println();
	}

	public void updateCompanyTesting() throws CouponSystemException {
		updateCompanyExistsIdTesting();
		/*-------------------------------------------------------------*/
		updateCompanyExistsNameTesting();
		/*-------------------------------------------------------------*/
		PrintUtils.printTest("# Test 8: Update Company(2) - Pass 5555");
		Company toUpdate = adminService.getOneCompany(2);
		toUpdate.setPassword("5555");
		adminService.updateCompany(toUpdate);
		PrintUtils.printResultList(adminService.getAllCompanies());
	}

	public void updateCompanyExistsIdTesting() throws CouponSystemException {
		System.out.println(ArtUtils.UPDATE_COMPANY);
		PrintUtils.printTest("Test 6: Update Company - exists id");
		Company toUpdate = adminService.getOneCompany(1);
		toUpdate.setId(80);
		try {
			adminService.updateCompany(toUpdate);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
		System.out.println();
	}

	public void updateCompanyExistsNameTesting() throws CouponSystemException {
		PrintUtils.printTest("# Test 7: Update Company - exists name");
		Company toUpdate = adminService.getOneCompany(1);
		toUpdate.setName("Pepsi");
		try {
			adminService.updateCompany(toUpdate);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		
		}
		System.err.flush();
		System.out.println();
	}

	public void deleteCompanyTesting() throws CouponSystemException {
		System.out.println(ArtUtils.DELETE_COMPANY);
		PrintUtils.printTest("# Test 9: Delete company(2)");
		adminService.deleteCompany(2);
		PrintUtils.printResultList(adminService.getAllCompanies());
		PrintUtils.printResultList(couponRepository.findAll());
		System.out.println();
		PrintUtils.printTest("Delete couponId(3) from customers");
		PrintUtils.printResultList(customerRepository.findAll());
	}

	public void getAllCompaniesTesting() {
		System.out.println(ArtUtils.GET_ALL_COMPANIES);
		PrintUtils.printTest("# Test 10: Get All Companies");
		PrintUtils.printResultList(adminService.getAllCompanies());
	}

	public void getCompanyByIdTesting() throws CouponSystemException {
		getCompanyByIdWrongIdTesting();
		/*-------------------------------------------------------------*/
		PrintUtils.printTest("# Test 12: Get Company By Id (7)");
		PrintUtils.printResult(adminService.getOneCompany(7));
		System.err.flush();
	}

	public void getCompanyByIdWrongIdTesting() {
		System.out.println(ArtUtils.GET_COMPANY_BY_ID);
		PrintUtils.printTest("# Test 11: Get Company By Id - Wrong Id");
		try {
			PrintUtils.printResult(adminService.getOneCompany(50));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			
		}
		System.err.flush();
		System.out.println();
	}

	public void addCustomerTesting() throws CouponSystemException {
		addCustomerExistsEmailTesting();
		/*-------------------------------------------------------------*/
		PrintUtils.printTest("# Test 14: Add Customer - Tedi Golan");
		Customer ToAdd = Customer.builder().firstName("Tedi").lastName("Golan").email("Tedi@gmail.com").password("1234")
				.build();
		adminService.addCustomer(ToAdd);
		PrintUtils.printResultList(adminService.getAllCustomers());
	}

	public void addCustomerExistsEmailTesting() {
		System.out.println(ArtUtils.ADD_CUSTOMER);
		PrintUtils.printTest("# Test 13: Add Customer - exists email");
		Customer ToAdd = Customer.builder().firstName("Tedi").lastName("Golan").email("HerzelB@gmail.com")
				.password("1234").build();
		try {
			adminService.addCustomer(ToAdd);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
		System.out.println();
	}

	public void updateCustomerTesting() throws CouponSystemException {
		updateCustomerExistsIdTesting();
		/*-------------------------------------------------------------*/
		PrintUtils.printTest("# Test 16: Update Customer(9) - Pass 4321");
		Customer ToUpdate = adminService.getOneCustomer(9);
		ToUpdate.setPassword("4321");
		adminService.updateCustomer(ToUpdate);
		PrintUtils.printResultList(adminService.getAllCustomers());
	}

	public void updateCustomerExistsIdTesting() throws CouponSystemException {
		System.out.println(ArtUtils.UPDATE_CUSTOMER);
		PrintUtils.printTest("# Test 15: Update Customer - exists Id");
		Customer customerToUpdate = adminService.getOneCustomer(9);
		customerToUpdate.setId(80);
		try {
			adminService.updateCustomer(customerToUpdate);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
		System.out.println();
	}

	public void deleteCustomerTesting() {
		System.out.println(ArtUtils.DELETE_CUSTOMER);
		PrintUtils.printTest("# Test 17: Delete Customer(1)");
		customerRepository.deleteById(1);
		PrintUtils.printResultList(adminService.getAllCustomers());
	}

	public void getAllCustomersTesting() {
		System.out.println(ArtUtils.GET_ALL_CUSTOMERS);
		PrintUtils.printTest("# Test 18: Get All Customers");
		PrintUtils.printResultList(adminService.getAllCustomers());
	}

	public void getCustomerTesting() throws CouponSystemException {
		getCustomerExistsIdTesting();
		/*-------------------------------------------------------------*/
		PrintUtils.printTest("# Test 20: Get Customer By Id (5)");
		PrintUtils.printResult(adminService.getOneCustomer(5));
	}

	public void getCustomerExistsIdTesting() {
		System.out.println(ArtUtils.GET_CUSTOMER_BY_ID);
		PrintUtils.printTest("# Test 19: Get Customer By Id - Wrong Id");
		try {
			PrintUtils.printResult(adminService.getOneCustomer(100));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.flush();
		System.out.println();
	}
}

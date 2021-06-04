package com.elior.couponManagementSystem.clr;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.elior.couponManagementSystem.beans.Category;
import com.elior.couponManagementSystem.beans.Company;
import com.elior.couponManagementSystem.beans.Coupon;
import com.elior.couponManagementSystem.beans.Customer;
import com.elior.couponManagementSystem.repos.CompanyRepository;
import com.elior.couponManagementSystem.repos.CouponRepository;
import com.elior.couponManagementSystem.repos.CustomerRepository;
import com.elior.couponManagementSystem.utils.ArtUtils;
import com.elior.couponManagementSystem.utils.PrintUtils;

import lombok.RequiredArgsConstructor;

@Component
@Order(1)
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
	public final CompanyRepository companyRepository;
	public final CustomerRepository customerRepository;
	public final CouponRepository couponRepository;

	@Override
	public void run(String... args) throws Exception {
		/*---------------------------------------------------------------------------------------------------------------------------*/
		Company c1 = Company.builder()
				.name("Coca Cola")
				.email("CocaCola@Coca Cola.com")
				.password("1234")
				.build();
				
		Company c2 = Company.builder()
				.name("Bugaboo")
				.email("Bugaboo@Bugaboo.com")
				.password("1234")
				.build();
		
		Company c3 = Company.builder()
				.name("Dell")
				.email("Dell@Dell.com")
				.password("1234")
				.build();
		
		Company c4 = Company.builder()
				.name("Adidas")
				.email("Adidas@Adidas.com")
				.password("1234")
				.build();
		
		Company c5 = Company.builder()
				.name("Johnson & Johnson")
				.email("Johnson&Johnson@JohnsonAndJohnson.com")
				.password("1234")
				.build();
		
		Company c6 = Company.builder()
				.name("HappyPet")
				.email("HappyPet@Pet.com")
				.password("1234")
				.build();
		
		Company c7 = Company.builder()
				.name("McDonalds")
				.email("McDonalds@McDonalds.com")
				.password("1234")
				.build();
		
		Company c8 = Company.builder()
				.name("Booking")
				.email("Booking@Booking.com")
				.password("1234")
				.build();
		
		Company c9 = Company.builder()
				.name("HolmesPlace")
				.email("HolmesPlace@comp.com")
				.password("1234")
				.build();
		
		Company c10 = Company.builder()
				.name("LEGO")
				.email("lego@lego.com")
				.password("1234")
				.build();
		/*---------------------------------------------------------------------------------------------------------------------------*/
		Coupon cn1 = Coupon.builder()
				.company(c1)
				.category(Category.FOOD)
				.title("Free shipping")
				.description("Free Shipping on Orders Over $50")
				.startDate(Date.valueOf(LocalDate.now().minusWeeks(1)))
				.endDate(Date.valueOf(LocalDate.now().minusDays(2)))
				.amount(100)
				.price(29.99f)
				.image("https://coca_cola.jpg")
				.build();
		
		Coupon cn2 = Coupon.builder()
				.company(c2)
				.category(Category.BABY)
				.title("Up to 40% Off")
				.description("Up to 40% Off Accessories at Outlet")
				.startDate(Date.valueOf(LocalDate.now()))
				.endDate(Date.valueOf(LocalDate.now().plusWeeks(1)))
				.amount(100)
				.price(499.99f)
				.image("https://bugaboo.jpg")
				.build();
		
		Coupon cn3 = Coupon.builder()
				.company(c3)
				.category(Category.PC)
				.title("Save an $50")
				.description("Save an additional $50 on laptops & desktops")
				.startDate(Date.valueOf(LocalDate.now().minusWeeks(1)))
				.endDate(Date.valueOf(LocalDate.now().minusDays(1)))
				.amount(100)
				.price(1200)
				.image("https://Dell.jpg")
				.build();
		
		Coupon cn4 = Coupon.builder()
				.company(c4)
				.category(Category.FASHION)
				.title("15% discount")
				.description("Extra 15% off first purchase")
				.startDate(Date.valueOf(LocalDate.now().minusWeeks(1)))
				.endDate(Date.valueOf(LocalDate.now()))
				.amount(100)
				.price(79.99f)
				.image("https://Adidas.jpg")
				.build();
		
		Coupon cn5 = Coupon.builder()
				.company(c5)
				.category(Category.HEALTH)
				.title("$3 Off lotion")
				.description("$3 Off Johnson Baby Lotion")
				.startDate(Date.valueOf(LocalDate.now().minusWeeks(2)))
				.endDate(Date.valueOf(LocalDate.now().minusWeeks(1)))
				.amount(100)
				.price(29.99f)
				.image("https://Johnson&Johnson.jpg")
				.build();
		
		Coupon cn6 = Coupon.builder()
				.company(c6)
				.category(Category.PETS)
				.title("discount on diet products")
				.description("20% Off Select Veterinary Diet Products")
				.startDate(Date.valueOf(LocalDate.now()))
				.endDate(Date.valueOf(LocalDate.now().plusWeeks(1)))
				.amount(100)
				.price(29.99f)
				.image("https://HappyPet.jpg")
				.build();
		
		Coupon cn7 = Coupon.builder()
				.company(c7)
				.category(Category.RESTAURANTS)
				.title("Free delivery")
				.description("Free Delivery on Orders $15+ at McDonald's Through UberEats")
				.startDate(Date.valueOf(LocalDate.now()))
				.endDate(Date.valueOf(LocalDate.now().plusWeeks(1)))
				.amount(100)
				.price(12.49f)
				.image("https://McDonalds.jpg")
				.build();
		
		Coupon cn8 = Coupon.builder()
				.company(c8)
				.category(Category.VACTION)
				.title("Save up to 15%")
				.description("Book Ahead & save up to 15% at Hotels Worldwide")
				.startDate(Date.valueOf(LocalDate.now()))
				.endDate(Date.valueOf(LocalDate.now().plusWeeks(1)))
				.amount(100)
				.price(2799)
				.image("https://Booking.jpg")
				.build();
		
		Coupon cn9 = Coupon.builder()
				.company(c1)
				.category(Category.FOOD)
				.title("$5 discount")
				.description("$5 Off Every 6 Custom Bottles")
				.startDate(Date.valueOf(LocalDate.now().minusWeeks(1)))
				.endDate(Date.valueOf(LocalDate.now().plusWeeks(1)))
				.amount(100)
				.price(18.99f)
				.image("https://coca_cola.jpg")
				.build();
		
		Coupon cn10 = Coupon.builder()
				.company(c9)
				.category(Category.SPORT)
				.title("30% discount")
				.description("30% Off Your First Membership Order")
				.startDate(Date.valueOf(LocalDate.now()))
				.endDate(Date.valueOf(LocalDate.now().plusWeeks(1)))
				.amount(100)
				.price(79.99f)
				.image("https://HolmesPlace.jpg")
				.build();
		
		Coupon cn11 = Coupon.builder()
				.company(c10)
				.category(Category.TOYS)
				.title("Park Picnic Set")
				.description("Free Park Picnic Set With Sitewide Order of $75")
				.startDate(Date.valueOf(LocalDate.now()))
				.endDate(Date.valueOf(LocalDate.now().plusWeeks(1)))
				.amount(100)
				.price(199.99f)
				.image("https://LEGO.jpg")
				.build();
		
		Coupon cn12 = Coupon.builder()
				.company(c7)
				.category(Category.TOYS)
				.title("Special coupon")
				.description("1 + 1 on all products")
				.startDate(Date.valueOf(LocalDate.now()))
				.endDate(Date.valueOf(LocalDate.now().plusWeeks(1)))
				.amount(0)
				.price(10)
				.image("https://Kia.jpg")
				.build();
		/*---------------------------------------------------------------------------------------------------------------------------*/
		c1.setCoupons(Arrays.asList(cn1,cn9));
		c2.setCoupons(Arrays.asList(cn2));
		c3.setCoupons(Arrays.asList(cn3));
		c4.setCoupons(Arrays.asList(cn4));
		c5.setCoupons(Arrays.asList(cn5));
		c6.setCoupons(Arrays.asList(cn6));
		c7.setCoupons(Arrays.asList(cn7,cn12));
		c8.setCoupons(Arrays.asList(cn8));
		c9.setCoupons(Arrays.asList(cn10));
		c10.setCoupons(Arrays.asList(cn11));
		companyRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));
		/*---------------------------------------------------------------------------------------------------------------------------*/
		Customer cr1 = Customer.builder()
				.firstName("Kobi")
				.lastName("Perez")
				.email("kobi@gmail.com")
				.password("1234")
				.coupon(cn1)
				.coupon(cn9)
				.build();
		
		Customer cr2 = Customer.builder()
				.firstName("Lora")
				.lastName("Valenski")
				.email("Lora.Val@gmail.com")
				.password("1234")
				.coupon(cn2)
				.coupon(cn7)
				.build();
		
		Customer cr3 = Customer.builder()
				.firstName("Ronit")
				.lastName("Noiman")
				.email("RonitNoiman@gmail.com")
				.password("1234")
				.coupon(cn3)
				.build();
		
		Customer cr4 = Customer.builder()
				.firstName("Herzel")
				.lastName("Bashir")
				.email("HerzelB@gmail.com")
				.password("1234")
				.coupon(cn2)
				.coupon(cn4)
				.build();
		
		Customer cr5 = Customer.builder()
				.firstName("Paz")
				.lastName("Goldman")
				.email("Paz.Goldman@gmail.com")
				.password("1234")
				.coupon(cn5)
				.build();
		
		Customer cr6 = Customer.builder()
				.firstName("Joni")
				.lastName("Kalifa")
				.email("Joni-k@gmail.com")
				.password("1234")
				.coupon(cn4)
				.coupon(cn6)
				.build();
		
		Customer cr7 = Customer.builder()
				.firstName("Shira")
				.lastName("Ron")
				.email("ShiraR97@gmail.com")
				.password("1234")
				.coupon(cn7)
				.build();
		
		Customer cr8 = Customer.builder()
				.firstName("Tzach")
				.lastName("Sharabi")
				.email("TzachShar76@gmail.com")
				.password("1234")
				.coupon(cn1).coupon(cn7)
				.coupon(cn8)
				.build();

		Customer cr9 = Customer.builder()
				.firstName("Dani")
				.lastName("Shmit")
				.email("DaniS12@gmail.com")
				.password("1234")
				.coupon(cn9)
				.build();

		Customer cr10 = Customer.builder()
				.firstName("Sapir")
				.lastName("Cohen")
				.email("SapirCohen2020@gmail.com")
				.password("1234")
				.coupon(cn10)
				.build();

		Customer cr11 = Customer.builder()
				.firstName("Ben")
				.lastName("Lolo")
				.email("BenLolo432@gmail.com")
				.password("1234")
				.coupon(cn11)
				.build();

		customerRepository.saveAll(Arrays.asList(cr1, cr2, cr3, cr4, cr5, cr6, cr7, cr8, cr9, cr10, cr11));
		/*---------------------------------------------------------------------------------------------------------------------------*/
		System.out.println(ArtUtils.BOOTSTRAP);
		/*---------------------------------------------------------------------------------------------------------------------------*/
		PrintUtils.printTest("# Init Category");
		System.out.println();
		PrintUtils.printCategoriesList();
		/*---------------------------------------------------------------------------------------------------------------------------*/
		PrintUtils.printTest("# Init Company Table");
		PrintUtils.printResultList(companyRepository.findAll());
		System.out.println();
		/*---------------------------------------------------------------------------------------------------------------------------*/
		PrintUtils.printTest("# Init Customer Table");
		PrintUtils.printResultList(customerRepository.findAll());
		System.out.println();
		/*---------------------------------------------------------------------------------------------------------------------------*/
		PrintUtils.printTest("# Init Coupon Table");
		PrintUtils.printResultList(couponRepository.findAll());
		/*---------------------------------------------------------------------------------------------------------------------------*/
	}
	public void initCoupons () {
		
	}
}

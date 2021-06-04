package com.elior.couponManagementSystem.repos;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elior.couponManagementSystem.beans.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	
	@Query("from Coupon where title = ?1 and company_id = ?2")
	Coupon findCouponByTitle(String title, int companyId);
	
	@Query("from Coupon where company_id = ?1")
	List<Coupon> findAllCouponsByCompanyId(int companyId);
	
	@Query("from Coupon where end_date + 1 < current_date")
	List<Coupon> getExpiredCoupons();
	

	@Query("from Coupon where id = ?1 and company_id = ?2")
	Coupon findCouponByIdAndCompanyId(int Id, int companyId);
		
	@Transactional
	@Modifying
	@Query(value = "delete from customers_coupons where coupons_id in "
			+ "(select id from coupons where company_id = ?1)", nativeQuery = true)
	void deleteCouponsFromCustomersVsCouponsByCompanyId(int companyId);
	
	@Transactional
	@Modifying
	@Query(value = "delete from customers_coupons where coupons_id = ?1", nativeQuery = true)
	void deleteCouponsFromCustomersVsCouponsByCouponId(int couponId);
}
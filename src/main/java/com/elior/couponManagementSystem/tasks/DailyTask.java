package com.elior.couponManagementSystem.tasks;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.elior.couponManagementSystem.beans.Coupon;
import com.elior.couponManagementSystem.repos.CouponRepository;
import com.elior.couponManagementSystem.utils.PrintUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DailyTask {

	private final CouponRepository couponRepository;

	/* Delete coupns expired every 24 hours */
	// @Scheduled(1000 * 60 * 60 * 24);

	/* Delete coupns expired - Test */
	@Scheduled(fixedRate = 60000, initialDelay = 15000)
	public void removeExpiredCoupon()  {
		LocalTime localTime = LocalDateTime.now().toLocalTime();
		String currentTime = localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond();
		List<Coupon> expiredCoupons = couponRepository.getExpiredCoupons();
		System.out.print("Time is now: " + currentTime + " # Delete ");
		System.err.flush();
		System.err.println("expired coupons:");
		PrintUtils.printResultList(expiredCoupons);
		for (Coupon coupon : expiredCoupons) {
			couponRepository.deleteCouponsFromCustomersVsCouponsByCouponId(coupon.getId());
			couponRepository.deleteById(coupon.getId());
		}
	}
}

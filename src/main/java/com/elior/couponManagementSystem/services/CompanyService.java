package com.elior.couponManagementSystem.services;

import java.util.List;

import com.elior.couponManagementSystem.beans.Category;
import com.elior.couponManagementSystem.beans.Company;
import com.elior.couponManagementSystem.beans.Coupon;
import com.elior.couponManagementSystem.exception.CouponSystemException;

public interface CompanyService {
	
	public void addCoupon(Coupon coupon) throws CouponSystemException;

	public void updateCoupon(Coupon coupon) throws CouponSystemException;

	public void deleteCoupon(int couponId) throws CouponSystemException;

	public List<Coupon> getCompanyCoupons();

	public List<Coupon> getCompanyCoupons(Category category);

	public List<Coupon> getCompanyCoupons(Double maxPrice);

	public Company getCompanyDetails() throws CouponSystemException;

}

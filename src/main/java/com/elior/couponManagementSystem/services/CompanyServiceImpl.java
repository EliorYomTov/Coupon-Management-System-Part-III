package com.elior.couponManagementSystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.elior.couponManagementSystem.beans.Category;
import com.elior.couponManagementSystem.beans.Company;
import com.elior.couponManagementSystem.beans.Coupon;
import com.elior.couponManagementSystem.exception.CouponSystemException;
import com.elior.couponManagementSystem.repos.CompanyRepository;
import com.elior.couponManagementSystem.repos.CouponRepository;
import com.elior.couponManagementSystem.repos.CustomerRepository;

import lombok.Getter;

@Service
@Getter
@Scope("prototype")
public class CompanyServiceImpl extends ClientService implements CompanyService {

	private int companyId;

	public CompanyServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		super(companyRepository, customerRepository, couponRepository);
	}

	@Override
	public boolean login(String email, String password) {
		Company res = companyRepository.findCompanyByEmailAndPassword(email, password);
		if (res != null) {
			this.companyId = res.getId();
			return true;
		}
		return false;
	}

	@Override
	public void addCoupon(Coupon coupon) throws CouponSystemException {
		Coupon res = couponRepository.findCouponByTitle(coupon.getTitle(), this.companyId);
		if (res != null) {
			throw new CouponSystemException(
					"A coupon with the same title cannot be added to an existing coupon of the same company");
		}
		Company toUpdate = companyRepository.findById(companyId)
				.orElseThrow(() -> new CouponSystemException("Id not found!"));
		List<Coupon> compCoupons = toUpdate.getCoupons();
		compCoupons.add(coupon);
		toUpdate.setCoupons(compCoupons);
		companyRepository.saveAndFlush(toUpdate);
	}

	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		if (couponRepository.findCouponByIdAndCompanyId(coupon.getId(), coupon.getCompany().getId()) != null) {
			couponRepository.saveAndFlush(coupon);
			return;
		}
		throw new CouponSystemException("Coupon Id/Company code could not be updated!");
	}

	@Override
	public void deleteCoupon(int couponId) throws CouponSystemException {
		if (couponRepository.findCouponByIdAndCompanyId(couponId, companyId) == null) {
			throw new CouponSystemException(
					"No coupon belong to company " + companyId + " was found with coupon id " + couponId);
		}
		couponRepository.deleteCouponsFromCustomersVsCouponsByCouponId(couponId);
		couponRepository.deleteById(couponId);
	}

	@Override
	public List<Coupon> getCompanyCoupons() {
		return couponRepository.findAllCouponsByCompanyId(companyId);
	}

	@Override
	public List<Coupon> getCompanyCoupons(Category category) {
		return couponRepository.findAllCouponsByCompanyId(companyId).stream().filter(x -> x.getCategory() == category)
				.collect(Collectors.toList());
	}

	@Override
	public List<Coupon> getCompanyCoupons(Double maxPrice) {
		return couponRepository.findAllCouponsByCompanyId(companyId).stream().filter(x -> x.getPrice() <= maxPrice)
				.collect(Collectors.toList());
	}

	@Override
	public Company getCompanyDetails() throws CouponSystemException {
		return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException("Id not found!"));
	}
}

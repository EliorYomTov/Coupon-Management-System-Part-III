package com.elior.couponManagementSystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elior.couponManagementSystem.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	@Query("from Company where id = ?1 and name = ?2")
	Company findCompanyByIdAndName(int id, String name);
	
	@Query("from Company where email = ?1 and password = ?2")
	Company findCompanyByEmailAndPassword(String email, String password);
		
	@Query("from Company where email = ?1")
	Company findCompanyByEmail(String email);
	
	@Query("from Company where name = ?1")
	Company findCompanyByName(String name);
	
}

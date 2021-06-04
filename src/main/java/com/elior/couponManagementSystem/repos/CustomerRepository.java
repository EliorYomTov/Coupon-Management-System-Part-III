package com.elior.couponManagementSystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elior.couponManagementSystem.beans.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("from Customer where email = ?1")
	Customer findCustomerByEmail(String email);

	@Query("from Customer where id = ?1")
	Customer findCustomerById(int id);

	@Query("from Customer where email = ?1 and password = ?2")
	Customer findCustomerByEmailAndPassword(String email, String password);
}

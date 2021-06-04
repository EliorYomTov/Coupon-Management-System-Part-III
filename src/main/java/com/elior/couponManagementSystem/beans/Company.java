package com.elior.couponManagementSystem.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.elior.couponManagementSystem.utils.CommandLineTable.CommandLineTableIntf;
import com.elior.couponManagementSystem.utils.EntityIdResolver;
import com.elior.couponManagementSystem.utils.PrintUtils;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Entity
@Table(name = "companies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class, 
		property = "id", 
		resolver = EntityIdResolver.class, 
		scope = Company.class)

public class Company implements CommandLineTableIntf {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	@JsonBackReference
	@Singular
	private List<Coupon> coupons = new ArrayList<>();

	@Override
	public String[] asRow() {
		return new String[] { getId() + "", getName(), getEmail(), getPassword(),
				PrintUtils.getCouponsIdAsString(coupons) };
	}

	@Override
	public String[] asHeader() {
		return new String[] { "Id", "Name", "Email", "Password", "Coupons" };
	}
}

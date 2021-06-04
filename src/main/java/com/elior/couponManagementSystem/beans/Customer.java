package com.elior.couponManagementSystem.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.elior.couponManagementSystem.utils.CommandLineTable.CommandLineTableIntf;
import com.elior.couponManagementSystem.utils.PrintUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Entity
@Table(name = "customers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements CommandLineTableIntf {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;

	@ManyToMany(cascade = CascadeType.MERGE)
//	@JsonBackReference
	@Singular
	private List<Coupon> coupons = new ArrayList<>();

	@Override
	public String[] asRow() {
		return new String[] { getId() + "", getFirstName(), getLastName(), getEmail(), getPassword(),
				PrintUtils.getCouponsIdAsString(coupons) };
	}

	@Override
	public String[] asHeader() {
		return new String[] { "Id", "First Name", "Last Name", "Email", "Password", "Coupons" };
	}
}

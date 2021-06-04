package com.elior.couponManagementSystem.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.elior.couponManagementSystem.utils.CommandLineTable.CommandLineTableIntf;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupons")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coupon implements CommandLineTableIntf {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIdentityReference(alwaysAsId = true)
	private Company company;
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Category category;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private Date startDate;
	@Column(nullable = false)
	private Date endDate;
	@Column(nullable = false)
	private int amount;
	@Column(nullable = false)
	private float price;
	@Column(nullable = false)
	private String image;

	@Override
	public String[] asRow() {
		return new String[] { getId() + "", company.getId() + "", getCategory() + "", getTitle(), getDescription(),
				getStartDate() + "", getEndDate() + "", getAmount() + "", getPrice() + "", getImage() + "" };
	}

	@Override
	public String[] asHeader() {
		return new String[] { "Id", "CompanyId", "Category", "Title", "Description", "Start Date", "End Date", "Amount",
				"Price", "Image" };
	}
}

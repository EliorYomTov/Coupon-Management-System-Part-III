package com.elior.couponManagementSystem.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.elior.couponManagementSystem.beans.Category;


@Component
public class CategoryConverter implements Converter<String, Category> {
	
	@Override
	public Category convert(String value) {
		try {
			return Category.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException e ) {
			throw new IllegalArgumentException();
		}
	}
}
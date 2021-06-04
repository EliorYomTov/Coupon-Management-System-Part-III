package com.elior.couponManagementSystem.utils;

import java.util.List;

import com.elior.couponManagementSystem.beans.Category;
import com.elior.couponManagementSystem.beans.Coupon;
import com.elior.couponManagementSystem.utils.CommandLineTable.CommandLineTableIntf;

public class PrintUtils {
	private static CommandLineTable commandLineTable;

	public static void printResultList(List<? extends CommandLineTableIntf> list) {
		commandLineTable = new CommandLineTable();
		commandLineTable.setShowVerticalLines(true);
		if (list.isEmpty()) {
			return;
		}
		commandLineTable.setHeaders(list.get(0).asHeader());
		for (CommandLineTableIntf row : list) {
			commandLineTable.addRow(row);
		}
		System.out.println();
		commandLineTable.print();
	}

	public static void printCategoriesList() {
		commandLineTable = new CommandLineTable();
		commandLineTable.setShowVerticalLines(true);
		commandLineTable.setHeaders("Id", "Name");
		for (Category c : Category.values()) {
			commandLineTable.addRow(String.valueOf(c.ordinal()), c.name());
		}
		commandLineTable.print();
		System.out.println();
	}

	public static void printResult(CommandLineTableIntf c) {
		commandLineTable = new CommandLineTable();
		commandLineTable.setShowVerticalLines(true);
		commandLineTable.setHeaders(c.asHeader());
		commandLineTable.addRow(c);
		System.out.println();
		commandLineTable.print();
	}

	public static void printTest(String str) {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("           " + str + "                                             ");
		System.out.println("-------------------------------------------------------------------");
	}

	public static String getCouponsIdAsString(List<Coupon> coupons) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (int i = 0; i < coupons.size(); i++) {
			builder.append(coupons.get(i).getId() + "");
			if (i < coupons.size() - 1)
				builder.append(", ");
		}
		builder.append("]");
		return builder.toString();
	}
}
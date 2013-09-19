package com.jye.monthlyexpenses.model;

import java.util.Date;

public class Expense extends Outlay{

	public Expense() {
	}

	public Expense(String name, float price, Date date) {
		super(name, price);
		this.date = date;
	}

	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

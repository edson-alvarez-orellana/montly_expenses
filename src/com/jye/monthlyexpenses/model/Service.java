package com.jye.monthlyexpenses.model;

import java.util.Date;

public class Service extends Expense {

	public Service(String name, float price, Date date, String company, boolean reminder) {
		super(name, price, date);
		this.company = company;
		this.reminder = reminder;
	}
	private String company;
	private boolean reminder;
	private Date dueDate;

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public boolean isReminder() {
		return reminder;
	}
	public void setReminder(boolean reminder) {
		this.reminder = reminder;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}

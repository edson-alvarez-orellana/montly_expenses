package com.jye.monthlyexpenses.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Month {
	List<Service> services;
	List<Expense> expenses;
	String monthName; 

	public Month(String monthName) {
		services = new ArrayList<Service>();
		expenses = new ArrayList<Expense>();
		this.monthName = monthName;
	}

	public void addService(Service service) {
		Iterator<Service> servicesIterator = services.iterator();
		boolean isNewService = true;
		while ( servicesIterator.hasNext() ){
			if(servicesIterator.next().getName() == service.getName()) {
				isNewService = false;
				break;
			}
		}

		if (isNewService) {
			services.add(service);
		} else {
			// TODO: Log that this service already exist 
		}
	}

	public void removeService(Service service) {
		if(services.contains(service)) {
			services.remove(service);
		}
	}

	public void addExpense(Expense expense) {
		expenses.add(expense);
	}

	public void removeExpense(Expense expense) {
		if(expenses.contains(expense)) {
			expenses.remove(expense);
		}
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	
	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
}

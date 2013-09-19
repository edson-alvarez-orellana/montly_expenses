package com.jye.monthlyexpenses.model;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Schedule {
	List<Month> months;

	public Schedule(){
		String[] monthsArray = new DateFormatSymbols().getMonths();
		months = new ArrayList<Month>();
		
		for (int i = 0; i < monthsArray.length-1; i++) {
			months.add(new Month(monthsArray[i].toString()));
		}
	}

	public List<Month> getMonths() {
		return months;
	}

	public void setMonths(List<Month> months) {
		this.months = months;
	}
	
	public Month getCurrentMonth() {
		return months.get(Calendar.getInstance().get(Calendar.MONTH));
	}
	
	public Month getMonth(int monthIndex) {
		return months.get(monthIndex);
	}
}

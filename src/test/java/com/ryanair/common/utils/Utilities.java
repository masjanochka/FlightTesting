package com.ryanair.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ryanair.common.core.*;

public final class Utilities {
	
	private static Integer getPassengersNumber(List<Passenger> passengers, PassengerType type) {
		Integer number = 0;
		for(Iterator<Passenger> i = passengers.iterator(); i.hasNext(); ) {
			Passenger passenger = i.next();
			if(passenger.getType().equals(type)){
		    		++number;
		    	}
		}
		return number;
	}
	
	public static Integer getAdultsNumber(List<Passenger> passengers) {
		return getPassengersNumber(passengers, PassengerType.ADULT);
	}
	
	public static Integer getTeensNumber(List<Passenger> passengers) {
		return getPassengersNumber(passengers, PassengerType.TEEN);
	}
	
	public static Integer getChildrenNumber(List<Passenger> passengers) {
		return getPassengersNumber(passengers, PassengerType.CHILD);
	}
	
	public static Integer getInfantsNumber(List<Passenger> passengers) {
		return getPassengersNumber(passengers, PassengerType.INFANT);
	}

	public static String rearrangeDate(String date) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date calculatedDate = new Date();
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(calculatedDate); 
		if(date.equals("TOMMOROWS_DATE")) {
			calendar.add(Calendar.DATE, 1);
			calculatedDate = calendar.getTime();
			return format.format(calculatedDate);
		}
		if(date.equals("IN_A_WEEK")) {			
			calendar.add(Calendar.DATE, 8);
			calculatedDate = calendar.getTime();
			return format.format(calculatedDate);
		}
		return date;
	}
}

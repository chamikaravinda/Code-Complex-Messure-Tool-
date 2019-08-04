package com.spm.codecomplexity.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Generator {

	/*-------------------Generate Current Date -----------------*/
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String newDate = dateFormat.format(date);
		
		return newDate;
	}
	
	/*-------------------Generate Current Time -----------------*/
	public static String getCurrentTime() {
		
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    
	     return (sdf.format(cal.getTime()));
	
	}
}
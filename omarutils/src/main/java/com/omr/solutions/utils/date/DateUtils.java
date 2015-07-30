package com.omr.solutions.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.omr.solutions.utils.constants.Constantes;

public class DateUtils implements Constantes {
	
	
	
	
	public static String getStrDate(Date date, String format){
		String timeStamp = new SimpleDateFormat(format).format(date);
		return timeStamp;
	}
	
	public static String getStrDateActual(String format){
		return getStrDate(new Date(),format);
	}

}

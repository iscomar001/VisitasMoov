package com.omr.solutions.utils.strings;

import com.omr.solutions.utils.constants.Constantes;

public class StringUtils implements Constantes {
	
	public StringUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean isBlank(String cadena){

		if (cadena == null) {
			return true;
		} else {
			cadena = cadena.trim();
			return cadena.length() == 0;
		}
		
	}
	
	public static boolean isNoBlank(String cadena){
		return !isBlank(cadena);
	}
	
	public static String toString(int intValue){
		String strValue = "0";
		try {
			strValue = Integer.toString(intValue);
		} catch (Exception e) {
		}
		
		return strValue;
	}
	public static String getString(Integer intValue){
		if (intValue != null) {
			return Integer.toString(intValue);
		} else {
			return "";
		}
	}
	public static String getString(Double doubleValue){
		if (doubleValue != null) {
			return Double.toString(doubleValue);
		} else {
			return "";
		}
	}
	public static String getString(Float floatValue){
		if (floatValue != null) {
			return Double.toString(floatValue);
		} else {
			return "";
		}
	}
	public static String getString(Long longValue){
		if (longValue != null) {
			return Long.toString(longValue);
		} else {
			return "";
		}
	}
	

}

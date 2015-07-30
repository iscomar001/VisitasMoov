package com.omr.solutions.utils.sqllite.exception;

public class SQLUtilsException extends Exception{

	
	private static final long serialVersionUID = 1L;
	private String message = "";
	
	public SQLUtilsException(String message) {
		this.message = message;
	}
	
	public SQLUtilsException(Exception ex) {
		if (ex != null) {
			this.message = ex.getMessage();
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}

package com.omr.solutions.utils.sqllite;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface ColType {
	
	
	public static final String SQL_TYPE_TEXT = "TEXT";
	public static final String SQL_TYPE_INTEGER = "TEXT";
	
	String colName() default "NO_COLUMN_NAME";
	String descriptionName() default "Sin nombre";
	String colType() default SQL_TYPE_TEXT;
	boolean isPrimary() default false;
	boolean isAutoIncrement() default false;
	boolean acceptNull() default true;
    String dateFormat() default "dd/MM/yyyy HH:mm:ss";
    
}

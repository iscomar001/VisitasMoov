package com.omr.solutions.utils.sqllite;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface TableType {
	
	String tableName() default "NO_TABLE_NAME";
    
}

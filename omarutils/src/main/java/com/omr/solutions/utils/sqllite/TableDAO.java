package com.omr.solutions.utils.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.omr.solutions.utils.constants.Constantes;
import com.omr.solutions.utils.sqllite.exception.SQLUtilsException;
import com.omr.solutions.utils.strings.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableDAO<T> extends SQLiteOpenHelper implements Constantes{
	
	private Class<T> classType;
	private List<Field> listFields = new ArrayList<Field>();
	private Field fieldIdentifier;
	
	private String tableName = "";
	private String sqlCreate = "";
	private String sqlDelete = "";
	
	public TableDAO(Context context, int databaseVersion, String databaseName,Class<T> classType) {
        super(context, databaseName, null, databaseVersion);  
        this.classType = classType;
        init();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(getSqlCreate());
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		init();
		db.execSQL(getSqlDelete());
		onCreate(db);
	}
	
	private void init() {
		
		
		String createTable = "CREATE TABLE ";
		StringBuilder sb = new StringBuilder();
		sb.append(getTableName()).append(STR_PARENTESIS_OPEN);
		
		Field[] fields = classType.getDeclaredFields();
        for (Field field : fields) {
            ColType colType = field.getAnnotation(ColType.class);
            TableType tableType = field.getAnnotation(TableType.class);
            
            if (colType != null ) {
            	field.setAccessible(true);
            	
            	if (colType.isPrimary()) {
					fieldIdentifier = field;
				} else {
					listFields.add(field);
				}
            	sb.append(colType.colName()).append(STR_SPACE).append(colType.colType()).append(STR_SPACE);
            	
            	if (colType.isPrimary()) {
            		sb.append( " primary key " );
            	}
            	if (colType.isAutoIncrement()) {
            		sb.append( " autoincrement " );
            	}
            	
        		if(!colType.acceptNull()){
        			sb.append( " not null " );
        		}
            	
            	sb.append(STR_COMA);
            }
            
            
            if (tableType != null ) {
            	setTableName(tableType.tableName());
            }
        }
		
        removeLastComa(sb);
        sb.append(STR_PARENTESIS_CLOSE).append(STR_PUNTO_COMA);
        
        sqlCreate = createTable + getTableName() + sb.toString();
        sqlDelete = "DROP TABLE IF EXISTS " + getTableName() + ";";
	}

	
	public void insert(ContentValues values) throws SQLUtilsException{
		SQLiteDatabase db = null;
		try {
			db = this.getWritableDatabase();
			db.insert(getTableName(), null, values);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			throw new SQLUtilsException(e);
		}finally{
			if (db != null) {
				db.close();
			}
		}
	}
	
	public void insertBean(T t) throws SQLUtilsException{
		SQLiteDatabase db = null;
		try {
			ContentValues contentValues = getContentValue(t);
			db = this.getWritableDatabase();
			db.insert(getTableName(), null, contentValues);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			throw new SQLUtilsException(e);
		}finally{
			if (db != null) {
				db.close();
			}
		}
	}
	
	

	public List<T> select(Map<String,String> selectMap) throws SQLUtilsException{
		SQLiteDatabase db = null;
		List<T> listSelect = new ArrayList<T>();
		Cursor cursor = null;
		String strWhere = getWhereSelect(selectMap);
		String[] values = getValues(selectMap);
		try {
			db = this.getReadableDatabase();
			cursor = db.query(getTableName(), getColumnsNames(), strWhere, values, null, null, null, null);
			while (cursor.moveToNext()) {
				listSelect.add(loadObject(cursor));
			}
		}catch (Exception e) {
			throw new SQLUtilsException(e);
		}finally{
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
		
		return listSelect;
	}

	public T selectBean(Map<String,String> selectMap) throws SQLUtilsException{
		SQLiteDatabase db = null;
		T t = null;
		Cursor cursor = null;
		String strWhere = getWhereSelect(selectMap);
		String[] values = getValues(selectMap);
		try {
			db = this.getReadableDatabase();
			cursor = db.query(getTableName(), getColumnsNames(), strWhere, values, null, null, null, null);
			if (cursor.moveToNext()) {
				t = loadObject(cursor);
			}
		}catch (Exception e) {
			throw new SQLUtilsException(e);
		}finally{
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}		
		
		return t;
	}


	public List<T> selectAll() throws SQLUtilsException{
		String query = "SELECT  * FROM " + getTableName() + ";";
		return selectQuery(query);
	}
	
	public List<T> selectQuery(String query) throws SQLUtilsException{
		List<T> listSelect = new ArrayList<T>();
		Cursor cursor = null;
		SQLiteDatabase db = null;
		try {
			db = this.getReadableDatabase();
			cursor = db.rawQuery(query, null);
			while (cursor.moveToNext()) {
				listSelect.add(loadObject(cursor));
			}
		}catch (Exception e) {
			throw new SQLUtilsException(e);
		}finally{
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
		
		return listSelect;
	}

	public List<Object> selectQuery(String query,Class clazz) throws SQLUtilsException{
		List<Object> listSelect = new ArrayList<Object>();
		Cursor cursor = null;
		SQLiteDatabase db = null;
		try {
			db = this.getReadableDatabase();
			cursor = db.rawQuery(query, null);
			while (cursor.moveToNext()) {
				listSelect.add(loadObject(cursor, clazz));
			}
		}catch (Exception e) {
			throw new SQLUtilsException(e);
		}finally{
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

		return listSelect;
	}

	public List<T> selectQuery(String query, String... params) throws SQLUtilsException{
		List<T> listSelect = new ArrayList<T>();
		Cursor cursor = null; 
		SQLiteDatabase db = null;
		try {
			db = this.getReadableDatabase();
			cursor = db.rawQuery(query, params);
			
			while (cursor.moveToNext()) {
				listSelect.add(loadObject(cursor));
			}
		}catch (Exception e) {
			throw new SQLUtilsException(e);
		}finally{
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
		
		return listSelect;
	}

	public List<Object> selectQuery(String query, Class clazz, String... params) throws SQLUtilsException{
		List<Object> listSelect = new ArrayList<Object>();
		Cursor cursor = null;
		SQLiteDatabase db = null;
		try {
			db = this.getReadableDatabase();
			cursor = db.rawQuery(query, params);

			while (cursor.moveToNext()) {
				listSelect.add(loadObject(cursor,clazz));
			}
		}catch (Exception e) {
			throw new SQLUtilsException(e);
		}finally{
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

		return listSelect;
	}
	
	public void deleteBean(T t) throws SQLUtilsException{
		delete(getWhereMapByID(t));
	}

	public void delete(Map<String,String> whereMap) throws SQLUtilsException{
		String strWhere = getWhereSelect(whereMap);
		String[] values = getValues(whereMap);
		SQLiteDatabase db = null;
		try {
			db = this.getWritableDatabase();
			db.delete(tableName, strWhere, values);
		}catch (Exception e) {
			throw new SQLUtilsException(e);
		}finally{
			if (db != null) {
				db.close();
			}
		}
	}
	
	public int updateBean(T t) throws SQLUtilsException{
		Map<String,String> whereMap = new HashMap<String, String>();
		ContentValues contentValues = getContentValue(t);
		SQLiteDatabase db = null;
		String strWhere = getWhereSelect(whereMap);
		String[] valuesWhere = getValues(whereMap);
		try {
			db = this.getWritableDatabase();
			return db.update(tableName, contentValues, strWhere, valuesWhere);
		}catch (Exception e) {
			throw new SQLUtilsException(e);
		}finally{
			if (db != null) {
				db.close();
			}
		}
	}
	public int update(ContentValues values,Map<String,String> whereMap) throws SQLUtilsException{
		String strWhere = getWhereSelect(whereMap);
		String[] valuesWhere = getValues(whereMap);
		SQLiteDatabase db = null;
		try {
			db = this.getWritableDatabase();
			return db.update(tableName, values, strWhere, valuesWhere);
		}catch (Exception e) {
			throw new SQLUtilsException(e);
		}finally{
			if (db != null) {
				db.close();
			}
		}
	}
	
	// Getting contacts Count
    public int getCount() throws SQLUtilsException {
    	SQLiteDatabase db = null;
        String countQuery = "SELECT  * FROM " + tableName;
        Cursor cursor = null;
        try {
        	db = this.getWritableDatabase();
	        cursor = db.rawQuery(countQuery, null);
	        return cursor.getCount();
        }catch (Exception e) {
        	throw new SQLUtilsException(e);
        }finally{
        	if (cursor != null) {
				cursor.close();
			}
        	if (db != null) {
				db.close();
			}
        }
    }
	
    private String[] getValues(Map<String, String> map) {
		String[] values = new String[map.size()];
		int count = 0;
		for (String columna : map.keySet()) {
		    values[count]= map.get(columna);
		    count++;
		}
		return values;
	}

	private String getWhereSelect(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		for (String columna : map.keySet()) {
		    sb.append(columna).append(" = ? ");
		    sb.append(STR_COMA);
		}
		removeLastComa(sb);
		return sb.toString();
	}


	public String[] getColumnsNames(){
		String[] cols = new String[listFields.size()+1];
		cols[0] = fieldIdentifier.getName();
		for (int i = 0; i < listFields.size(); i++) {
			Field field = listFields.get(i);
			cols[i+1] = field.getName();
		}
		return cols;
		
	}
	
	private void removeLastComa(StringBuilder sb) {
		if (sb.length() > 2) {
			sb.replace(sb.length()-1, sb.length(), STR_EMPTY);
		}
	}

	private String getTableName() {
		return tableName;
	}

	private void setTableName(String tableName) {
		this.tableName = tableName;
	}

	private ContentValues getContentValue(T t) throws SQLUtilsException {
		ContentValues contentValues =  new ContentValues();
		try {
			for (Field field : listFields) {
				if (field.getType() == String.class) {
						contentValues.put(field.getName(), (String) field.get(t));
				}
				if (field.getType() == Integer.class) {
					contentValues.put(field.getName(), (Integer) field.get(t));
				}
				if (field.getType() == Double.class) {
					contentValues.put(field.getName(), (Double) field.get(t));
				}
				if (field.getType() == Float.class) {
					contentValues.put(field.getName(), (Float) field.get(t));
				}
				if (field.getType() == Long.class) {
					contentValues.put(field.getName(), (Long) field.get(t));
				}
			}
		} catch (IllegalArgumentException e) {
			throw new SQLUtilsException(e);
		} catch (IllegalAccessException e) {
			throw new SQLUtilsException(e);
		}
		return contentValues;
	}

	private T loadObject(Cursor cursor) throws SQLUtilsException {
		T t = null;
		try {
			t = classType.newInstance();
		} catch (InstantiationException e) {
			throw new SQLUtilsException(e);
		} catch (IllegalAccessException e) {
			throw new SQLUtilsException(e);
		}
		try {
			for (Field field : listFields) {
				Object value = null;
				
				if (field.getType() == String.class) {
					value = cursor.getString(cursor.getColumnIndex(field.getName()));
				}
				if (field.getType() == Integer.class) {
					value = cursor.getInt(cursor.getColumnIndex(field.getName()));
				}
				if (field.getType() == Double.class) {
					value = cursor.getDouble(cursor.getColumnIndex(field.getName()));
				}
				if (field.getType() == Float.class) {
					value = cursor.getFloat(cursor.getColumnIndex(field.getName()));
				}
				if (field.getType() == Long.class) {
					value = cursor.getLong(cursor.getColumnIndex(field.getName()));
				}
					field.set(t, value);
			}
		} catch (IllegalArgumentException e) {
			throw new SQLUtilsException(e);
		} catch (IllegalAccessException e) {
			throw new SQLUtilsException(e);
		}
		return t;
	}

	private Object loadObject(Cursor cursor,Class clazz) throws SQLUtilsException {
		Object t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e) {
			throw new SQLUtilsException(e);
		} catch (IllegalAccessException e) {
			throw new SQLUtilsException(e);
		}
		try {
			for (Field field : listFields) {
				Object value = null;

				if (field.getType() == String.class) {
					value = cursor.getString(cursor.getColumnIndex(field.getName()));
				}
				if (field.getType() == Integer.class) {
					value = cursor.getInt(cursor.getColumnIndex(field.getName()));
				}
				if (field.getType() == Double.class) {
					value = cursor.getDouble(cursor.getColumnIndex(field.getName()));
				}
				if (field.getType() == Float.class) {
					value = cursor.getFloat(cursor.getColumnIndex(field.getName()));
				}
				if (field.getType() == Long.class) {
					value = cursor.getLong(cursor.getColumnIndex(field.getName()));
				}
				field.set(t, value);
			}
		} catch (IllegalArgumentException e) {
			throw new SQLUtilsException(e);
		} catch (IllegalAccessException e) {
			throw new SQLUtilsException(e);
		}
		return t;
	}
	
	private Map<String, String> getWhereMapByID(T t) throws SQLUtilsException {
		Map<String,String> whereMap = new HashMap<String, String>();
		try {
			whereMap.put(fieldIdentifier.getName(), StringUtils.getString((Integer) fieldIdentifier.get(t)));
		} catch (IllegalArgumentException e) {
			throw new SQLUtilsException(e);
		} catch (IllegalAccessException e) {
			throw new SQLUtilsException(e);
		}
		return null;
	}

	public String getSqlCreate() {
		return sqlCreate;
	}

	public void setSqlCreate(String sqlCreate) {
		this.sqlCreate = sqlCreate;
	}

	public String getSqlDelete() {
		return sqlDelete;
	}

	public void setSqlDelete(String sqlDelete) {
		this.sqlDelete = sqlDelete;
	}

	
}

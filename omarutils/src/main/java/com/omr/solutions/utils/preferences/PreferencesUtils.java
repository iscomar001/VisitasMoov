package com.omr.solutions.utils.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class PreferencesUtils {
	
	public static final int MODE_PRIVATE = Context.MODE_PRIVATE;

	Context context;
	SharedPreferences preferences = null;

	
	public PreferencesUtils(Context context, String key) {
		this.context = context;
		this.preferences = context.getSharedPreferences(key, MODE_PRIVATE);
	}
	
	public void put(String key, Object value){
		SharedPreferences.Editor editor = preferences.edit();
		if (value.getClass() == String.class) {
			editor.putString(key, (String) value);
		} else if (value.getClass() == Integer.class) {
			editor.putInt(key, (Integer) value);
		}else if (value.getClass() == Boolean.class) {
			editor.putBoolean(key, (Boolean) value);
		}else if (value.getClass() == Long.class) {
			editor.putLong(key, (Long) value);
		}else if (value.getClass() == Float.class) {
			editor.putFloat(key, (Float) value);
		}
		editor.commit();
	}
	
	public void putAll(Map<String,?> map){
		SharedPreferences.Editor editor = preferences.edit();
		for (Map.Entry<String, ?> entry: map.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
		editor.commit();
	}
	
	public Object getValue(String key, Class<?> klass){
		Object value = null;
		if (klass == String.class) {
			value = preferences.getString(key, "");
		} else if (klass == Integer.class) {
			value = preferences.getInt(key, 0);
		}else if (klass == Boolean.class) {
			value = preferences.getBoolean(key, false);
		}else if (klass == Long.class) {
			value = preferences.getLong(key, 0);
		}else if (klass == Float.class) {
			value = preferences.getFloat(key, 0);
		}
		return value;
	}
	
	public Map<String,?> getAll(){
		return preferences.getAll();
	}
	
	public void remove(String key){
		SharedPreferences.Editor editor = preferences.edit();
		editor.remove(key);
		editor.commit();
	}
	
	public void clean(String key){
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear();
		editor.commit();
	}
}

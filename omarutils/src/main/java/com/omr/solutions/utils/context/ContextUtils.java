package com.omr.solutions.utils.context;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.omr.solutions.utils.constants.Constantes;


public class ContextUtils implements Constantes{
	
	public static View inflateLayout(Context context, int idLayout, String logTag){
		if (context == null) {
			Log.d(logTag, "inflateLayout context null");
			return null;
		}else{
			LayoutInflater inflater = LayoutInflater.from(context);
			if (inflater == null) {
				Log.d(logTag, "inflateLayout inflater null con id: " + idLayout);
			}
			return inflater.inflate(idLayout, null);
		}
	}
	
	
	public static String getString(Context context,int idComponent, String logTag){
		if (context == null) {
			Log.d(logTag, "getString context null");
			return null;
		}else{
			String texto = context.getString(idComponent);
			if (texto == null) {
				Log.d(logTag, "inflateLayout getString null con id: " + idComponent);
			}
			return texto;
		}
	}

}

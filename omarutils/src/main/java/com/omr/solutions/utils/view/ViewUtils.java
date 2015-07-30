package com.omr.solutions.utils.view;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.omr.solutions.utils.constants.Constantes;


public class ViewUtils implements Constantes {

	
	public static TextView getTextView(View view, int idComponent, String logTag){
		if (view == null || idComponent == 0) {
			Log.d(logTag, "getById parametros incorrectos " + view==null ?"View = Nulo":"idComponent = 0");
			return null;
		} else {
			TextView textView = (TextView) view.findViewById(idComponent);
			if (textView == null) {
				Log.d(logTag, "getById return null " + idComponent);
			}
			return textView; 
		}
	}
	
	public static ListView getListView(View view, int idComponent, String logTag){
		if (view == null || idComponent == 0) {
			Log.d(logTag, "getById parametros incorrectos " + view==null ?"View = Nulo":"idComponent = 0");
			return null;
		} else {
			ListView listView = (ListView) view.findViewById(idComponent);
			if (listView == null) {
				Log.d(logTag, "getById return null " + idComponent);
			}
			return listView; 
		}
	}
	
	public static EditText getEditText(View view, int idComponent, String logTag){
		if (view == null || idComponent == 0) {
			Log.d(logTag, "getById parametros incorrectos " + view==null ?"View = Nulo":"idComponent = 0");
			return null;
		} else {
			EditText editText = (EditText) view.findViewById(idComponent);
			if (editText == null) {
				Log.d(logTag, "getById return null " + idComponent);
			}
			return editText; 
		}
	}
	
	public static Button getButton(View view, int idComponent, String logTag){
		if (view == null || idComponent == 0) {
			Log.d(logTag, "getById parametros incorrectos " + view==null ?"View = Nulo":"idComponent = 0");
			return null;
		} else {
			Button button = (Button) view.findViewById(idComponent);
			if (button == null) {
				Log.d(logTag, "getById return null " + idComponent);
			}
			return button; 
		}
	}
	
	public static ImageView getImageView(View view, int idComponent, String logTag){
		if (view == null || idComponent == 0) {
			Log.d(logTag, "getById parametros incorrectos " + view==null ?"View = Nulo":"idComponent = 0");
			return null;
		} else {
			ImageView imageView = (ImageView) view.findViewById(idComponent);
			if (imageView == null) {
				Log.d(logTag, "getById return null " + idComponent);
			}
			return imageView; 
		}
	}
	
	
	public static LinearLayout getLinearLayout(View view, int idComponent, String logTag){
		if (view == null || idComponent == 0) {
			Log.d(logTag, "getById parametros incorrectos " + view==null ?"View = Nulo":"idComponent = 0");
			return null;
		} else {
			LinearLayout linearLayout = (LinearLayout) view.findViewById(idComponent);
			if (linearLayout == null) {
				Log.d(logTag, "getById return null " + idComponent);
			}
			return linearLayout; 
		}
	}
}

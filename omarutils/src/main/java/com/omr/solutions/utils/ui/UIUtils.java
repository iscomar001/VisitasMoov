package com.omr.solutions.utils.ui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.omr.solutions.utils.constants.Constantes;
import com.omr.solutions.utils.context.ContextUtils;
import com.omr.solutions.utils.view.ViewUtils;

public class UIUtils implements Constantes {

	private Context context;
	private View view;
	
	private String logTag;
	
	
	public UIUtils(Context context, String logTag) {
		this.setContext(context);
		this.logTag = logTag;
	}
	
	public View inflateLayout(int idComponent) {
		return ContextUtils.inflateLayout(context, idComponent, logTag);
	}
	
	public UIUtils(Context context, View view) {
		this.setContext(context);
		this.setView(view);
	}
	
	public TextView getTextView(int idComponent){
		return ViewUtils.getTextView(getView(), idComponent, logTag);
	}
	
	public Button getButton(int idComponent){
		return ViewUtils.getButton(getView(), idComponent, logTag);
	}

	public ImageView getImageView(int idComponent){
		return ViewUtils.getImageView(getView(), idComponent, logTag);
	}
	
	public String getString(int idComponent){
		return ContextUtils.getString(getContext(), idComponent, logTag);
	}

	/**
	 * @return the context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(View view) {
		this.view = view;
	}
	
}

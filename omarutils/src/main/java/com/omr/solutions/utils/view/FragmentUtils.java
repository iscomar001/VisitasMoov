package com.omr.solutions.utils.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.omr.solutions.utils.constants.Constantes;
import com.omr.solutions.utils.strings.StringUtils;

public class FragmentUtils extends Fragment implements Constantes {
	
	private View view;
	private String tagSource;
	
	public void inicialize(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, int idLayout, String tag) {
		setView(inflater.inflate(idLayout,container, false)); 
		this.setTagSource(tag);
	}

	public TextView getTextView(int idCommponent){
		return ViewUtils.getTextView(getView(), idCommponent, getTag());
	}
	
	public EditText getEditText(int idCommponent){
		return ViewUtils.getEditText(getView(), idCommponent, getTag());
	}
	
	public ListView getList(int idCommponent){
		return ViewUtils.getListView(getView(), idCommponent, getTag());
	}
	
	public LinearLayout getLinearLayout(int idCommponent){
		return ViewUtils.getLinearLayout(getView(), idCommponent, getTag());
	}
	
	public Button getButton(int idCommponent){
		return ViewUtils.getButton(getView(), idCommponent, getTag());
	}
	
	public String getBoundleString(String key){
		Bundle args= getArguments();
		String valor = args.getString(key);
		
		if (StringUtils.isBlank(valor)) {
			return "";
		}else{
			return valor;
		}
	}
	
	public String getBoundleStringDefault(String key, String defaultValue){
		Bundle args= getArguments();
		String valor = args.getString(key);
		
		if (StringUtils.isBlank(valor)) {
			return defaultValue;
		}else{
			return valor;
		}
	}
	
	

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public String getTagSource() {
		return tagSource;
	}

	public void setTagSource(String tagSource) {
		this.tagSource = tagSource;
	}
	
	
}

package com.omr.solutions.utils.adapters;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.omr.solutions.utils.constants.Constantes;

public abstract class ListAdapterAbstract extends BaseAdapter implements Constantes {
	
	private List<?> listItems;
	
	public ListAdapterAbstract(List<?> listItems, String logTag) {
		this.listItems = listItems;
	}
	
	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	abstract public View getView(int position, View convertView, ViewGroup parent);


}

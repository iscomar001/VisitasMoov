package com.omr.solutions.utils.adapters;


import java.util.List;

import com.omr.solutions.utils.constants.Constantes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public abstract class FragmentPagerAdapterAbstract extends FragmentPagerAdapter	implements Constantes {

	List<Fragment> listFragments;
	
	public FragmentPagerAdapterAbstract(FragmentManager fm, List<Fragment> listFragments, String logTag) {
		super(fm);
		if (listFragments == null) {
			Log.d(logTag, "Lista de fragments vacia");
		} 
		this.listFragments = listFragments;
	}
	
	public List<Fragment> getListFragments() {
		return listFragments;
	}

	public void setListFragments(List<Fragment> listFragments) {
		this.listFragments = listFragments;
	}

	@Override
	public abstract Fragment getItem(int index);

	@Override
	public int getCount() {
		return listFragments.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return listFragments.get(position).toString();
	}

}

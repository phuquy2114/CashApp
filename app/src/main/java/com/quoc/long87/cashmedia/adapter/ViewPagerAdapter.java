package com.quoc.long87.cashmedia.adapter;


import com.quoc.long87.cashmedia.fragment.OffersFragment;
import com.quoc.long87.cashmedia.fragment.RewardsFragment;
import com.quoc.long87.cashmedia.fragment.SettingFragment;
import com.quoc.long87.cashmedia.fragment.ShareFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter{
	private CharSequence Titles[]={"Offers","Rewards","Share","Settings"};
	private int numbTabs =4;

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment = null;
		switch (arg0){
		case 0:
			fragment = new OffersFragment();
			break;
		case 1:
			fragment = new RewardsFragment();
			break;
		case 2:
			fragment = new ShareFragment();
			break;
		case 3:
			fragment = new SettingFragment();
			break;
		}	
		return fragment;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return Titles[position];
	}

	@Override
	public int getCount() {
		return numbTabs;
	}

}

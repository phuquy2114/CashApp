package android.phuquy.dev.cashmedia.adapter;


import android.phuquy.dev.cashmedia.fragment.OffersFragment;
import android.phuquy.dev.cashmedia.fragment.RewardsFragment;
import android.phuquy.dev.cashmedia.fragment.SettingFragment;
import android.phuquy.dev.cashmedia.fragment.ShareFragment;
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

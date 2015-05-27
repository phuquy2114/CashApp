package android.phuquy.dev.cashmedia.adapter;

import android.phuquy.dev.cashmedia.fragment.OffersFragmentMain;
import android.phuquy.dev.cashmedia.fragment.RewardsFragment;
import android.phuquy.dev.cashmedia.fragment.SettingFragment;
import android.phuquy.dev.cashmedia.fragment.ShareFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by PC on 5/1/2015.
 */
public class MainFragmentAdapter extends FragmentStatePagerAdapter {

    public static int COUNT = 4;

    private OffersFragmentMain mOffersFragmentMain;
    private RewardsFragment mRewardsFragment;
    private ShareFragment mShareFragment;
    private SettingFragment mSettingFragment;

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
        mOffersFragmentMain = new OffersFragmentMain();
        mRewardsFragment = new RewardsFragment();
        mShareFragment = new ShareFragment();
        mSettingFragment = new SettingFragment();


    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return mOffersFragmentMain;
            case 1:
                return mRewardsFragment;
            case 2:
                return mShareFragment;
            case 3:
                return mSettingFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}

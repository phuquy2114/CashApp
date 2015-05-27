package android.phuquy.dev.cashmedia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.phuquy.dev.cashmedia.Helper.BaseFragment;
import android.phuquy.dev.cashmedia.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 5/27/15.
 */
public class OffersFragmentMain extends BaseFragment {


    private View mView;
    private Button mBtnTabOne;
    private Button mBtnTabTwo;
    private Fragment fragment = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragmentmain_offers, null);
        return mView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initialize();
        setValues();
        setEvents(mView);
    }

    public void initialize() {
        mBtnTabOne = (Button) mView.findViewById(R.id.btn_wall);
        mBtnTabTwo = (Button) mView.findViewById(R.id.btn_banners);
    }

    public void setValues() {
    }

    public void setEvents(View v) {

        if (v.getId() == R.id.btn_banners) {
            fragment = new OffersBannersFragment();
        } else {
            fragment = new OffersFragment();
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}

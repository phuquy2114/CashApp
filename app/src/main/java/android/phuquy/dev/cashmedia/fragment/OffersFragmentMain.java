package android.phuquy.dev.cashmedia.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.phuquy.dev.cashmedia.Helper.BaseFragment;
import android.phuquy.dev.cashmedia.MainActivity;
import android.phuquy.dev.cashmedia.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 5/27/15.
 */
public class OffersFragmentMain extends BaseFragment implements View.OnClickListener {


    private View mView;
    private Button mBtnTabOne;
    private Button mBtnTabTwo;
    private Fragment fragment = null;
    private MainActivity mainActivity;


    @Override
    public void onAttach(Activity activity) {
        mainActivity = (MainActivity) activity;
        super.onAttach(mainActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragmentmain_offers, null);
        initialize();
        setValues();
        setEvents();
        setFragmentLayout(new OffersFragment());
        return mView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void initialize() {
        mBtnTabOne = (Button) mView.findViewById(R.id.btn_wall);
        mBtnTabTwo = (Button) mView.findViewById(R.id.btn_banners);
    }

    public void setValues() {
    }

    public void setEvents() {
        mBtnTabTwo.setOnClickListener(this);
        mBtnTabOne.setOnClickListener(this);

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_banners:
                fragment = mainActivity.getSupportFragmentManager().findFragmentById(R.id.frame_layout);
                if (fragment instanceof OffersFragment) {
                    setFragmentLayout(new OffersBannersFragment());
                }
                break;
            case R.id.btn_wall:
                fragment = getFragmentManager().findFragmentById(R.id.frame_layout);
                setFragmentLayout(new OffersFragment());
                break;
            default:
                break;
        }
    }

    public void setFragmentLayout(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();
        } else {
            Log.e("TAG", "Error in creating fragment");
        }
    }
}

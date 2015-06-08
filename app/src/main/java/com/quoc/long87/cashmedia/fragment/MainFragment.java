package com.quoc.long87.cashmedia.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quoc.long87.R;
import com.quoc.long87.cashmedia.Helper.BaseFragment;
import com.quoc.long87.cashmedia.Helper.HackyViewPager;
import com.quoc.long87.cashmedia.Helper.TabHost;
import com.quoc.long87.cashmedia.MainActivity;
import com.quoc.long87.cashmedia.adapter.MainFragmentAdapter;

public class MainFragment extends BaseFragment implements TabHost.OnItemClickListtener {
    private TabHost mTab;
    private MainFragmentAdapter mainFragmentAdapter;
    private HackyViewPager mViewPager;
    private View view;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        setValue();
        setEvents();
    }

    public void initialize() {
        mTab = (TabHost) view.findViewById(R.id.tab_host);
        mViewPager = (HackyViewPager) view.findViewById(R.id.viewpager);
    }

    public void setValue() {
        mainFragmentAdapter = new MainFragmentAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mainFragmentAdapter);
    }

    public void setEvents() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTab.onItemClickEvent(position);
                ((MainActivity)getActivity()).setHeaderTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTab.setItemClickListten(this);
    }


    @Override
    public void ClickItemTabHost(int position) {
        mViewPager.setCurrentItem(position);
    }
}

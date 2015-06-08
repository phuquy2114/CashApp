package com.quoc.long87.cashmedia.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.quoc.long87.R;
import com.quoc.long87.cashmedia.Utils.Config;
import com.quoc.long87.cashmedia.Utils.FragmentViewClickListener;
import com.quoc.long87.cashmedia.adapter.RewardAdapter;

public class RewardsFragment extends Fragment {

    private static final String TAG = RewardsFragment.class.getSimpleName();
    private ListView listview;
    private TextView mTxtTitle;
    private FragmentViewClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewards, container,
                false);
        listview = (ListView) view.findViewById(R.id.lvPaypal);
        mTxtTitle = (TextView) view.findViewById(R.id.title_reward);


        RewardAdapter adapter = new RewardAdapter(getActivity(),
                R.layout.rewards_items, Config.REWARD_ICON, Config.PP_REWARDS);
        listview.setAdapter(adapter);

        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(TAG, "firstVisibleItem" + firstVisibleItem);
                if (firstVisibleItem < 4) {
                    mTxtTitle.setText("iTunes Card");
                } else if (firstVisibleItem > 4 && firstVisibleItem < 9) {
                    mTxtTitle.setText("Amazon Card");
                } else if (firstVisibleItem > 9 && firstVisibleItem < 13) {
                    mTxtTitle.setText("eBays Card");
                } else if (firstVisibleItem > 13 && firstVisibleItem < 17) {
                    mTxtTitle.setText("Google Play Card");
                } else if (firstVisibleItem > 17 && firstVisibleItem < 19) {
                    mTxtTitle.setText("Game Stop Card");
                } else if (firstVisibleItem > 19) {
                    mTxtTitle.setText("Other");
                }
//				Log.d(TAG,""+totalItemCount);
            }
        });

        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

                listener.onListRewardsClick(position);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (FragmentViewClickListener) activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("qqq", "Reward");
    }


}

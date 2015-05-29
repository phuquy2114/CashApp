package android.phuquy.dev.cashmedia.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.phuquy.dev.cashmedia.R;
import android.phuquy.dev.cashmedia.Utils.Config;
import android.phuquy.dev.cashmedia.Utils.FragmentViewClickListener;
import android.phuquy.dev.cashmedia.adapter.RewardAdapter;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class RewardsFragment extends Fragment {
	private ListView listview;
	private FragmentViewClickListener listener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_rewards, container,
				false);
		listview = (ListView) view.findViewById(R.id.lvPaypal);

		RewardAdapter adapter = new RewardAdapter(getActivity(),
				R.layout.rewards_items,Config.REWARD_ICON, Config.PP_REWARDS);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				listener.onListRewardsClick(arg2);
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

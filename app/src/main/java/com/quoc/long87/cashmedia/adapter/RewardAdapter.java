package com.quoc.long87.cashmedia.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quoc.long87.R;
import com.quoc.long87.cashmedia.Utils.Config;

public class RewardAdapter extends ArrayAdapter<String>{
	private String[] data;
	private int [] image;
	private Activity act;

	public RewardAdapter(Activity act, int resource,int [] image , String[] data) {
		super(act, resource);
		this.act = act;
		this.data = data;
		this.image = image;
	}
	
	private class ViewHolder {
		private TextView rewardType;
		private TextView rewardPoint;
		private ImageView mIngThumnai;
	}

	@Override
	public int getCount() {
		return data.length;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if(rowView == null){
			LayoutInflater inflater = act.getLayoutInflater();
			rowView = inflater.inflate(R.layout.rewards_items, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.mIngThumnai = (ImageView) rowView.findViewById(R.id.ivRewardRowIcon);
			viewHolder.rewardPoint = (TextView) rowView.findViewById(R.id.tvRewardRowPoints);
			viewHolder.rewardType = (TextView) rowView.findViewById(R.id.tvRewardRowType);
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.mIngThumnai.setImageResource(image[position]);
		holder.rewardPoint.setText(Config.POINTS[position]);
		holder.rewardType.setText(data[position]);
		
		return rowView;
	}
	

}

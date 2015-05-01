package android.phuquy.dev.cashmedia.adapter;


import android.app.Activity;
import android.phuquy.dev.cashmedia.R;
import android.phuquy.dev.cashmedia.Utils.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RewardAdapter extends ArrayAdapter<String>{
	private String[] data;
	private Activity act;

	public RewardAdapter(Activity act, int resource,String[] data) {
		super(act, resource);
		this.act = act;
		this.data = data;
	}
	
	private class ViewHolder {
		TextView rewardType;
		TextView rewardPoint;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if(rowView == null){
			LayoutInflater inflater = act.getLayoutInflater();
			rowView = inflater.inflate(R.layout.rewards_items, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.rewardPoint = (TextView) rowView.findViewById(R.id.tvRewardRowPoints);
			viewHolder.rewardType = (TextView) rowView.findViewById(R.id.tvRewardRowType);
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.rewardPoint.setText(Config.POINTS[position]);
		holder.rewardType.setText(data[position]);
		
		return rowView;
	}
	
	@Override
	public int getCount() {
		return data.length;
	}
}

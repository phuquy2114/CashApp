package android.phuquy.dev.cashmedia.adapter;


import android.app.Activity;
import android.phuquy.dev.cashmedia.R;
import android.phuquy.dev.cashmedia.Utils.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NetworkAdapter extends ArrayAdapter<String>{
	private Activity context;
	private String[] data;

	public NetworkAdapter(Activity context, int resource, String[] objects) {
		super(context, resource, objects);
		this.context = context;
		this.data = objects;
	}
	
	private class ViewHolder {
		TextView netName;
		ImageView icon;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if(rowView == null){
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.offers_item, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.icon = (ImageView) rowView.findViewById(R.id.netIcon);
			viewHolder.netName = (TextView) rowView.findViewById(R.id.netName);
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
			holder.icon.setImageResource(Config.NET_ICON[position]);
			holder.netName.setText(data[position]);
		return rowView;
	}

}

package android.phuquy.dev.cashmedia.adapter;

import android.content.Context;
import android.phuquy.dev.cashmedia.R;
import android.phuquy.dev.cashmedia.Utils.Config;
import android.phuquy.dev.cashmedia.Utils.CreditHistoryObj;
import android.phuquy.dev.cashmedia.fragment.CreditHistoryFragment;
import android.phuquy.dev.cashmedia.libraris.SecurePreferences;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CreditHistoryAdapter extends ArrayAdapter<CreditHistoryObj>{
	private Context context;
	private List<CreditHistoryObj> data;
	
	public CreditHistoryAdapter(Context context, int resource,
			List<CreditHistoryObj> objects) {
		super(context, resource, objects);
		this.context = context;
		data = objects;
	}
	
	private class ViewHolder{
		ImageView icon;
		TextView networkName;
		TextView points;
		TextView time;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if(convertView == null){
			rowView = LayoutInflater.from(context).inflate(R.layout.credit_history_item, parent, false);
			ViewHolder viewholder = new ViewHolder();
			viewholder.icon = (ImageView) rowView.findViewById(R.id.history_logo);
			viewholder.networkName = (TextView)rowView.findViewById(R.id.title);
			viewholder.points = (TextView)rowView.findViewById(R.id.price_num);
			viewholder.time = (TextView)rowView.findViewById(R.id.date);
			rowView.setTag(viewholder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		CreditHistoryObj co = data.get(position);
		SecurePreferences ref = new SecurePreferences(context, CreditHistoryFragment.SP_NET_NAME, CreditHistoryFragment.SP_NET_KEY, true);
		int num_net = Integer.parseInt(ref.getString("num_network"));
		
		if(num_net > Config.NET_ICON.length - 1){
			holder.icon.setImageResource(Config.NET_ICON[Integer.parseInt(co.getNetId()) -1]);
		}else{
			holder.icon.setImageResource(R.drawable.special_offer);
		}
		holder.networkName.setText(ref.getString(co.getNetId()));
		holder.points.setText(co.getPoints());
		String datetime = getDate(co.getTime()*1000);
		holder.time.setText(datetime);
		return rowView;
	}
	private String getDate(long time) {
	    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
	    cal.setTimeInMillis(time);
	    String date = DateFormat.format("yyyy/MM/dd", cal).toString();
	    return date;
	}

}

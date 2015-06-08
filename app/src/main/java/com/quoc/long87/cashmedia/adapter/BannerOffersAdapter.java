package com.quoc.long87.cashmedia.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quoc.long87.R;
import com.quoc.long87.cashmedia.model.Offer;
import com.squareup.picasso.Picasso;

import java.util.List;

;

public class BannerOffersAdapter extends ArrayAdapter<Offer>{
	private Activity context;
	private List<Offer> list;

	public BannerOffersAdapter(Activity context, int resource,
			List<Offer> objects) {
		super(context, resource, objects);
		this.context = context;
		this.list = objects;
	}
	class ViewHolder{
		ImageView photo;
		TextView name,des,points;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null){
			LayoutInflater inflater = context.getLayoutInflater();
			view = inflater.inflate(R.layout.banner_item, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.photo = (ImageView) view.findViewById(R.id.photo);
			viewHolder.name = (TextView) view.findViewById(R.id.bannerName);
			viewHolder.des = (TextView) view.findViewById(R.id.bannerDes);
			viewHolder.points = (TextView) view.findViewById(R.id.bannerPoints);
			view.setTag(viewHolder);
		}
		Offer banner = list.get(position);
		ViewHolder holder = (ViewHolder) view.getTag();
		Picasso.with(context).load(banner.getImageUrl()).placeholder(R.drawable.special_offers_1).into(holder.photo);
		holder.name.setText(banner.getName());
		holder.des.setText(banner.getDes());
		holder.points.setText(banner.getPoints());
		
		return view;
	}

}

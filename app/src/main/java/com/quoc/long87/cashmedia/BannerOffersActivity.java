package com.quoc.long87.cashmedia;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.quoc.long87.R;
import com.quoc.long87.cashmedia.Utils.Config;
import com.quoc.long87.cashmedia.Utils.JSONParser;
import com.quoc.long87.cashmedia.adapter.BannerOffersAdapter;
import com.quoc.long87.cashmedia.libraris.SecurePreferences;
import com.quoc.long87.cashmedia.model.BannerOffers;
import com.quoc.long87.cashmedia.model.Offer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BannerOffersActivity extends Activity implements OnItemClickListener{
	private TextView noOffer;
	private ListView lvOffer;
	private List<Offer> offers = new ArrayList<Offer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banner_offers);
		noOffer = (TextView) findViewById(R.id.nooffer);
		lvOffer = (ListView) findViewById(R.id.banners);
		new GetOffer().execute();
	}
	
	private class GetOffer extends AsyncTask<Void, Void, String>{
		ProgressDialog dialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(BannerOffersActivity.this);
			dialog.setMessage("Loading offers...");
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected String doInBackground(Void... params) {
			String result = null;
			try {
				JSONObject ipApi = new JSONObject(JSONParser.makeHttpRequest("http://ip-api.com/json", null));
				if (ipApi.getString("status").equalsIgnoreCase("success")) {
					String countryCode = ipApi.getString("countryCode");
					HashMap<String, String> data = new HashMap<String, String>();
					data.put("country", countryCode);
					data.put(Config.TAG_CONTROLLER, "GetOffers");
					result = JSONParser.makeHttpRequest(MainActivity.getLink(), data);
				}
			} catch (JSONException e) {
				e.printStackTrace();
				return result;
			}
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if(result == null){
				noOffer.setVisibility(View.VISIBLE);
				return;
			}
			try {
				BannerOffers bannerOff = new Gson().fromJson(result, BannerOffers.class);
				int success = bannerOff.getSuccess();
				if (success == 0){
					noOffer.setVisibility(View.VISIBLE);
				}else{
					offers = bannerOff.getOffers();
					if(offers.size() == 0){
						noOffer.setVisibility(View.VISIBLE);
					}else{
						BannerOffersAdapter adapter = new BannerOffersAdapter(BannerOffersActivity.this, R.layout.banner_item, offers);
						lvOffer.setAdapter(adapter);
						lvOffer.setOnItemClickListener(BannerOffersActivity.this);
					}
					}
			} catch (Exception e) {
				noOffer.setVisibility(View.VISIBLE);
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String id = offers.get(arg2).getOfferId();
		SecurePreferences ref = new SecurePreferences(BannerOffersActivity.this,Config.PREFERENCES_NAME,Config.PREFERENCES_KEY,true);
		String uid = ref.getString("uid");
		String link = new StringBuilder(MainActivity.getLink()).append("gooffer.php?oid=").append(id).append("&uid=").append(uid).toString();
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
		startActivity(i);
	}

}

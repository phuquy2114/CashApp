package android.phuquy.dev.cashmedia.fragment;

import android.os.Bundle;
import android.phuquy.dev.cashmedia.R;
import android.phuquy.dev.cashmedia.Utils.Config;
import android.phuquy.dev.cashmedia.Utils.CreditHistoryObj;
import android.phuquy.dev.cashmedia.adapter.CreditHistoryAdapter;
import android.phuquy.dev.cashmedia.libraris.SecurePreferences;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreditHistoryFragment extends ListFragment{
	private JSONObject data;
	public static final String SP_NET_NAME = "networks";
	public static final String SP_NET_KEY = "key";
	
	public CreditHistoryFragment(JSONObject result) {
		data = result;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_credits_his, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		try {
			int success = data.getInt(Config.TAG_SUCCESS);
			if(success == 0){
				String message = data.getString(Config.TAG_MESSAGE);
				Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
			}else{
				SecurePreferences ref = new SecurePreferences(getActivity(), SP_NET_NAME, SP_NET_KEY, true);
				List<CreditHistoryObj> creditObjs = new ArrayList<CreditHistoryObj>();
				JSONArray networkArray = data.getJSONArray("networks");
				
				ref.put("num_network", String.valueOf(networkArray.length()));
				for(int i = 0;i< networkArray.length();i++){
					JSONObject net = networkArray.getJSONObject(i);
					ref.put(net.getString("id"), net.getString("name"));
				}
				
				JSONArray creditsArray = data.getJSONArray("data");
				for(int i = 0; i< creditsArray.length();i++){
					JSONObject credit = creditsArray.getJSONObject(i);
					CreditHistoryObj obj = new CreditHistoryObj();
					obj.setNetId(credit.getString("networkId"));
					obj.setPoints("points");
					obj.setTime(Integer.parseInt(credit.getString("time")));
					creditObjs.add(obj);
				}
				CreditHistoryAdapter adapter = new CreditHistoryAdapter(getActivity(), R.layout.credit_history_item, creditObjs);
				setListAdapter(adapter);
				
			}
		} catch (JSONException e) {
			Toast.makeText(getActivity(), "Unknown Error", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}

}

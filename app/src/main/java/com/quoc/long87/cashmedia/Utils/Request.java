package com.quoc.long87.cashmedia.Utils;

import android.os.AsyncTask;
import com.quoc.long87.cashmedia.MainActivity;
import android.util.Log;

import java.util.HashMap;

public class Request extends AsyncTask<Void, Void, String>{
	private RequestCallback request;
	private int requestType;
	private HashMap<String, String> data;
	
	public Request(HashMap<String, String> data,RequestCallback listener,int requestType) {
		this.data = data;
		this.request = listener;
		this.requestType = requestType;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		request.onStartRequest();
	}

	@Override
	protected String doInBackground(Void... params) {
		String jObj = null;
		jObj = JSONParser.makeHttpRequest(MainActivity.getLink(),data);
		return jObj;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
        Log.d("qqq","result"+result);
		if(result != null){
			request.onComplete(result,requestType);
			}else{
				request.onFailed();
			}
	}
}

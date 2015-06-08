package com.quoc.long87.cashmedia.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


import android.util.Log;

public class JSONParser {

	public static String makeHttpRequest(String link,
			HashMap<String, String> postDataParams) {
		String responce = null;
		URL url;
		HttpURLConnection urlConnection = null;
		try {
			url = new URL(link);

			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(20000);
			urlConnection.setDoOutput(true);
			
			if (postDataParams != null) {
				OutputStream os = urlConnection.getOutputStream();
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(os, "UTF-8"));
				writer.write(getPostDataString(postDataParams));
				writer.flush();
				writer.close();
				os.close();
			}

			String line;
			String res = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			while ((line = br.readLine()) != null) {
				res += line;
			}
			Log.i("JSONParer", res);
			responce = res;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			urlConnection.disconnect();
		}

		return responce;
	}

	private static String getPostDataString(HashMap<String, String> params)
			throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
		}
		Log.i("JSONParer s", result.toString());
		return result.toString();
	}

}

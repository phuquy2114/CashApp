package android.phuquy.dev.cashmedia.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.phuquy.dev.cashmedia.R;
import android.phuquy.dev.cashmedia.Utils.Config;
import android.phuquy.dev.cashmedia.Utils.Request;
import android.phuquy.dev.cashmedia.Utils.RequestCallback;
import android.phuquy.dev.cashmedia.libraris.SecurePreferences;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedeemFragment extends Fragment implements OnClickListener{
	private EditText edtEmail;
	private Button btnRedeem;
	private TextView tvRewardType;
	private RequestCallback callback;
	private int position;
	
	public RedeemFragment(int position) {
		this.position = position;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_redeem, container, false);
		edtEmail = (EditText) view.findViewById(R.id.edtAddress);
		btnRedeem = (Button) view.findViewById(R.id.btnRewardRequest);
		tvRewardType = (TextView) view.findViewById(R.id.tvRewardType);
		
		tvRewardType.setText(Config.PP_REWARDS[position]);
		
		btnRedeem.setOnClickListener(this);
		return view;
	}
	@Override
	public void onClick(View v) {
		String email = edtEmail.getText().toString();
		if( !isEmailValid(email) || email.isEmpty()){
			Toast.makeText(getActivity(),
					"Email address is invalid", Toast.LENGTH_SHORT).show();
		}else{
			SecurePreferences ref = new SecurePreferences(getActivity(), Config.PREFERENCES_NAME, Config.PREFERENCES_KEY, true);
			HashMap<String, String> data = new HashMap<String, String>();
			data.put(Config.TAG_CONTROLLER, "Rewards");
			data.put("email", email);
			data.put("reward_type","Paypal");
			data.put("reward_value",Config.POINTS[position]);
			data.put("uid", ref.getString("uid"));
			Request request = new Request(data, callback, 2);
			request.execute();
		}
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		callback = (RequestCallback) activity;
	}
	
	private boolean isEmailValid(String email) {
		boolean isValid = false;

		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;

		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

}

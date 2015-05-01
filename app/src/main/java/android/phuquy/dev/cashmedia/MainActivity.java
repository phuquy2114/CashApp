package android.phuquy.dev.cashmedia;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.phuquy.dev.cashmedia.Utils.Config;
import android.phuquy.dev.cashmedia.Utils.FragmentViewClickListener;
import android.phuquy.dev.cashmedia.Utils.Request;
import android.phuquy.dev.cashmedia.Utils.RequestCallback;
import android.phuquy.dev.cashmedia.fragment.ChangePassFragment;
import android.phuquy.dev.cashmedia.fragment.MainFragment;
import android.phuquy.dev.cashmedia.fragment.RedeemFragment;
import android.phuquy.dev.cashmedia.libraris.SecurePreferences;
import android.phuquy.dev.cashmedia.model.ChangePass;
import android.phuquy.dev.cashmedia.model.Login;
import android.phuquy.dev.cashmedia.model.Redeem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

public class MainActivity extends FragmentActivity implements RequestCallback,
        FragmentViewClickListener, OnClickListener {
	private static final String TAG = "MainActivity";
	private SecurePreferences ref;
	private TextView tvpoints;
	private ImageView refresh;
	private ProgressDialog dialog;
	public static String link;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        initialize();
        setValues();
        setEvents();
	}

    private void initialize() {
        tvpoints = (TextView) findViewById(R.id.score_count);
        refresh = (ImageView) findViewById(R.id.score_logo);

    }

    private void setValues() {
        ref = new SecurePreferences(MainActivity.this, Config.PREFERENCES_NAME,
                Config.PREFERENCES_KEY, true);
        link = getResources().getString(R.string.web);
        dialog = new ProgressDialog(MainActivity.this);
        boolean isLogged = ref.containsKey("logged");
        Log.i(TAG, String.valueOf(isLogged));
        if (!isLogged) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
            return;
        }
        if (!ref.containsKey("direct")) {
            HashMap<String, String> data = new HashMap<String, String>();
            data.put(Config.TAG_CONTROLLER, "Login");
            data.put("email", ref.getString("email"));
            data.put("password", ref.getString("password"));
            Request request = new Request(data, this, 1);
            request.execute();
        } else {
            MainFragment frag = new MainFragment();
            showFragment(frag, true);
            ref.removeValue("direct");
            tvpoints.setText(ref.getString("points"));
        }
    }

    private void setEvents() {
        refresh.setOnClickListener(this);

    }

    @Override
	public void onBackPressed() {
		if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
			finish();
		} else {
			super.onBackPressed();
		}
	}

	private void showFragment(Fragment frag, boolean addtobackstack) {
		String backStateName = frag.getClass().getName();
		boolean fragmentPopped = getSupportFragmentManager()
				.popBackStackImmediate(backStateName, 0);

		if (!fragmentPopped) { // fragment not in back stack, create it.
			FragmentTransaction ft = getSupportFragmentManager()
					.beginTransaction();
			ft.replace(R.id.container, frag, backStateName);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			if (addtobackstack)
				ft.addToBackStack(backStateName);
			ft.commit();
		}

	}

	@Override
	public void onComplete(String result, int requestType) {
		hideDialog();
		switch (requestType) {
		case LOGIN_REQUEST:
			login(result);
			break;
		case REDEEM_REQUEST:
			redeem(result);
			break;
		case CREDIT_HISTORY:
//			creditHistory(result);
			break;
		case REFRESH_POINT_REQUEST:
			refreshPoint(result);
			break;
		case CHANGE_PASS_REQUEST:
			changePass(result);
			break;
		case INVITE_REQUEST:
			inviteCode(result);
			break;
		}

	}

//	private void creditHistory(String result) {
//		CreditHistoryFragment frag = new CreditHistoryFragment(result);
//		showFragment(frag, true);
//	}

	private void inviteCode(String result) {
		try {
			Redeem invitecode = new Gson().fromJson(result, Redeem.class);
			int success = invitecode.getSuccess();
			String message = invitecode.getMessage();
			if (success == 1) {
				String points = invitecode.getPoints();
				ref.put("points", points);
				showAlert(message);
				tvpoints.setText(points);

			} else {
				showAlert(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void changePass(String result) {
		try {
			ChangePass change = new Gson().fromJson(result, ChangePass.class);
			int success = change.getSuccess();
			String message = change.getMessage();
			if (success == 1) {
				String newpass = change.getPassword();
				ref.put("password", newpass);
				showAlert(message);
				getSupportFragmentManager().popBackStack();
			} else {
				showAlert(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void refreshPoint(String result) {
		try {
			Redeem refresh = new Gson().fromJson(result, Redeem.class);
			int success = refresh.getSuccess();
			if (success == 1) {
				String points = refresh.getPoints();
				ref.put("points", points);
				tvpoints.setText(points);
			} else {
				String message = refresh.getMessage();
				showAlert(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void redeem(String result) {
		try {
			Redeem redeem = new Gson().fromJson(result, Redeem.class);
			int success = redeem.getSuccess();
			if (success == 1) {
				ref.put("points", redeem.getPoints());
				tvpoints.setText(redeem.getPoints());
				showAlert(redeem.getMessage());

			} else {
				String message = redeem.getMessage();
				showAlert(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void login(String result) {
		try {
			Login login = new Gson().fromJson(result, Login.class);
			
			if (login.getSuccess() == 1) {
				tvpoints.setText(login.getPoints());
				ref.put("points", login.getPoints());
				ref.put("uid", login.getUid());
				ref.put("status", login.getStatus());
				MainFragment frag = new MainFragment();
				showFragment(frag, true);

				if (Integer.parseInt(login.getStatus()) == 2) {
					Toast.makeText(MainActivity.this,
							"Your account has been suspended",
							Toast.LENGTH_LONG).show();
					ref.clear();
					Intent intent = new Intent(MainActivity.this,
							LoginActivity.class);
					startActivity(intent);
					finish();
					return;
				}
			} else {
				showAlert(login.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFailed() {
		hideDialog();
		showAlert("Please check your network connection and try again");
	}

	@Override
	public void onListRewardsClick(int position) {
		RedeemFragment frag = new RedeemFragment(position);
		showFragment(frag, true);
	}

	@Override
	public void onSettingsFragmentClick(int position) {
		switch (position) {
		case 0:
			ChangePassFragment changeFrag = new ChangePassFragment();
			showFragment(changeFrag, true);
			break;
		case 1:
			Intent gmailIntent = new Intent();
			gmailIntent.setClassName("com.google.android.gm",
					"com.google.android.gm.ComposeActivityGmail");
			gmailIntent.putExtra(Intent.EXTRA_EMAIL,
					new String[] { Config.YOUR_EMAIL });
			gmailIntent.putExtra(Intent.EXTRA_SUBJECT,
					"Cash App Support");
			MainActivity.this.startActivity(gmailIntent);

			break;
		case 2:
			Intent intent = new Intent(MainActivity.this, LoginActivity.class);
			ref.clear();
			startActivity(intent);
			finish();
			break;
		}
	}

	private void showAlert(String message) {
		AlertDialog.Builder abuilder = new AlertDialog.Builder(
				MainActivity.this);
		abuilder.setMessage(message);
		abuilder.setTitle("Notice");
		abuilder.setNeutralButton("Ok", null);
		abuilder.create().show();
	}

	@Override
	public void onStartRequest() {
		dialog.setMessage("Loading..");
		dialog.setCancelable(false);
		dialog.show();
	}
	
	public static String getLink(){
		Log.i(TAG,link);
		return link;
	}

	private void hideDialog() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put(Config.TAG_CONTROLLER, "RefreshPoints");
		data.put("uid", ref.getString("uid"));
		Request request = new Request(data, this, 6);
		request.execute();
	}

}

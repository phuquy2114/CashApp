package android.phuquy.dev.cashmedia;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.phuquy.dev.cashmedia.Utils.Config;
import android.phuquy.dev.cashmedia.Utils.Request;
import android.phuquy.dev.cashmedia.Utils.RequestCallback;
import android.phuquy.dev.cashmedia.libraris.SecurePreferences;
import android.phuquy.dev.cashmedia.model.Login;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends Activity implements OnClickListener,
        RequestCallback {
	private TextView tvLogin;
	private EditText edtEmail, edtPass, edtConfirm;
	private Button btnRegister;
	private String email, password;
	private ProgressDialog pdialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		pdialog = new ProgressDialog(RegisterActivity.this);

		tvLogin = (TextView) findViewById(R.id.tvLogin);
		edtEmail = (EditText) findViewById(R.id.email);
		edtPass = (EditText) findViewById(R.id.pass);
		edtConfirm = (EditText) findViewById(R.id.passAgain);
		btnRegister = (Button) findViewById(R.id.btnRegister);

		tvLogin.setPaintFlags(tvLogin.getPaintFlags()
				| Paint.UNDERLINE_TEXT_FLAG);
		tvLogin.setText("Click here to login");

		tvLogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnRegister) {
			email = edtEmail.getText().toString();
			password = edtPass.getText().toString();
			String passConf = edtConfirm.getText().toString();
			if(email.isEmpty() || password.isEmpty()||passConf.isEmpty()){
				Toast.makeText(getApplicationContext(), "All fields is required !", Toast.LENGTH_SHORT).show();
				return;
			}
			if(!isEmailValid(email)){
				Toast.makeText(getApplicationContext(), "Email is invalid!",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if (!password.equals(passConf)) {
				Toast.makeText(getApplicationContext(), "Password not match!",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if(password.length() <6){
				Toast.makeText(getApplicationContext(), "Password must longer than 6 character!",
						Toast.LENGTH_SHORT).show();
				return;
			}

			HashMap<String, String> data = new HashMap<String, String>();
			data.put("email", email);
			data.put("password", password);
			data.put(Config.TAG_CONTROLLER, "register");
			Request request = new Request(data, this, 0);
			request.execute();
		}
		if (v.getId() == R.id.tvLogin) {
			Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
			startActivity(i);
		}
	}

	@Override
	public void onStartRequest() {
		pdialog.setMessage("Loading ...");
		pdialog.setCancelable(false);
		pdialog.show();
	}

	@Override
	public void onComplete(String result, int requestType) {
		hideDialog();
		try {
			Login register = new Gson().fromJson(result, Login.class);
			if(register.getSuccess() ==1){
				Toast.makeText(getApplicationContext(), "Register successful", Toast.LENGTH_SHORT).show();
				String points = register.getPoints();
				String uid = register.getUid();
				SecurePreferences ref = new SecurePreferences(getApplicationContext(), Config.PREFERENCES_NAME, Config.PREFERENCES_KEY, true);
				ref.put("logged", "true");
				ref.put("uid", uid);
				ref.put("points", points);
				ref.put("email", email);
				ref.put("password", password);
				ref.put("direct", "true");
				Intent i = new Intent(RegisterActivity.this,MainActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);
				finish();
			}else{
				String message = register.getMessage();
				AlertDialog.Builder abuilder = new AlertDialog.Builder(RegisterActivity.this);
				abuilder.setMessage(message);
				abuilder.setTitle("Error !");
				abuilder.setNeutralButton("Ok", null);
				abuilder.create().show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFailed() {
		hideDialog();
		AlertDialog.Builder abuilder = new AlertDialog.Builder(RegisterActivity.this);
		abuilder.setMessage("Please check your network connection and try again");
		abuilder.setTitle("Error !");
		abuilder.setNeutralButton("Ok", null);
		abuilder.create().show();
	}
	
	private void hideDialog(){
		if(pdialog != null && pdialog.isShowing()){
			pdialog.dismiss();
		}
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

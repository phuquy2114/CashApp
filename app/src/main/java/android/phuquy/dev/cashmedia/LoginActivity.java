package android.phuquy.dev.cashmedia;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.phuquy.dev.cashmedia.Helper.BaseActivity;
import android.phuquy.dev.cashmedia.Utils.Config;
import android.phuquy.dev.cashmedia.Utils.Request;
import android.phuquy.dev.cashmedia.Utils.RequestCallback;
import android.phuquy.dev.cashmedia.libraris.SecurePreferences;
import android.phuquy.dev.cashmedia.model.Login;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements OnClickListener,RequestCallback {

	private TextView tvRegister;
	private EditText edtEmail, edtPass;
	private Button btnLogin;
	private String email,password;
	private ProgressDialog pdialog;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
        initialize();
        setValue();
        setEvent();

	}

    @Override
    protected void initialize() {
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);

    }

    @Override
    protected void setValue() {

        tvRegister.setPaintFlags(tvRegister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvRegister.setText("Dont Have an Account");

        // Value  defauce
        edtEmail.setText("hoangtuthattinh2114@gmail.com");
        edtPass.setText("phuquy142011");


    }

    @Override
    protected void setEvent() {
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);

    }

    @Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnLogin) {
			email = edtEmail.getText().toString();
			password = edtPass.getText().toString();
			if(email.isEmpty() || password.isEmpty()){
				Toast.makeText(getApplicationContext(), "Please complete all fields !", Toast.LENGTH_SHORT).show();
				return;
			}
			HashMap<String,String> params = new HashMap<String, String>();
            params.put("email", email);
            params.put("password", password);
            params.put(Config.TAG_CONTROLLER,"login");
			Request request = new Request(params, this, 1);
			request.execute();
		}
		if (v.getId() == R.id.tvRegister) {
			Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
		}

	}

	@Override
	public void onStartRequest() {
		pdialog = new ProgressDialog(LoginActivity.this);
		pdialog.setMessage("Loading ...");
		pdialog.setCancelable(false);
		pdialog.show();
	}

	@Override
	public void onComplete(String result, int requestType) {
        Log.d("qqq", "result" + result);
		hideDialog();
		try {
			Login login = new Gson().fromJson(result, Login.class);
			if(login.getSuccess() != 1){
				String message = login.getMessage();
				AlertDialog.Builder abuilder = new AlertDialog.Builder(LoginActivity.this);
				abuilder.setMessage(message);
				abuilder.setNeutralButton("Ok", null);
				abuilder.setTitle("Error !");
				abuilder.create().show();
			}else{
				String points = login.getPoints();
				String uid = login.getUid();
				int status = Integer.parseInt(login.getStatus());
				if(status == 2 ){
					AlertDialog.Builder abuilder = new AlertDialog.Builder(LoginActivity.this);
					abuilder.setMessage("Your account has been suspended!");
					abuilder.setTitle("Error !");
					abuilder.setNeutralButton("Ok", null);
					abuilder.create().show();
				}else{
					SecurePreferences ref = new SecurePreferences(getApplicationContext(), Config.PREFERENCES_NAME, Config.PREFERENCES_KEY, true);
					ref.put("logged", "true");
					ref.put("uid", uid);
					ref.put("points", points);
					ref.put("email", email);
					ref.put("password", password);
					ref.put("direct", "true");
					Intent i = new Intent(LoginActivity.this,MainActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(i);
					finish();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFailed() {
		hideDialog();
		AlertDialog.Builder abuilder = new AlertDialog.Builder(LoginActivity.this);
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

}

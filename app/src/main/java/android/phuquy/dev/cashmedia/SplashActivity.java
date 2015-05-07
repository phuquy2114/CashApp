package android.phuquy.dev.cashmedia;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.phuquy.dev.cashmedia.Helper.BaseActivity;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by PC on 4/29/2015.
 */
public class SplashActivity extends BaseActivity {


    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "android.phuquy.dev.cashmedia",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        initialize();
        setValue();
        setEvent();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initialize() {
        handler = new Handler();
    }

    @Override
    protected void setValue() {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent startapp = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startapp);
                finish();
            }
        };
        handler.postDelayed(runnable, 5000);
    }

    @Override
    protected void setEvent() {

    }
}

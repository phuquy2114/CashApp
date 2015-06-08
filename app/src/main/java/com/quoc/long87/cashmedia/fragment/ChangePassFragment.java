package com.quoc.long87.cashmedia.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quoc.long87.R;
import com.quoc.long87.cashmedia.Helper.BaseFragment;
import com.quoc.long87.cashmedia.Utils.Config;
import com.quoc.long87.cashmedia.Utils.Request;
import com.quoc.long87.cashmedia.Utils.RequestCallback;
import com.quoc.long87.cashmedia.libraris.SecurePreferences;

import java.util.HashMap;


public class ChangePassFragment extends BaseFragment {
    private Button btnChange;
    private EditText curPass, newPass, conPass;
    private RequestCallback callback;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.change_pass, container, false);
        initialize();
        setValues();
        setEvents();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (RequestCallback) activity;
    }

    public void initialize() {
        curPass = (EditText) view.findViewById(R.id.curPass);
        newPass = (EditText) view.findViewById(R.id.newPass);
        conPass = (EditText) view.findViewById(R.id.conFPass);
        btnChange = (Button) view.findViewById(R.id.btnChangePAss);
    }

    public void setValues() {

    }

    public void setEvents() {
        btnChange.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String cp = curPass.getText().toString();
                String np = newPass.getText().toString();
                String cfp = conPass.getText().toString();

                if (cp.isEmpty() || np.isEmpty() || cfp.isEmpty()) {
                    Toast.makeText(getActivity(), "Please complete all fields !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!np.equals(cfp)) {
                    Toast.makeText(getActivity(), "New password do not match !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (np.length() < 6) {
                    Toast.makeText(getActivity(), "Password must longer than 6 character !", Toast.LENGTH_SHORT).show();
                    return;
                }
                SecurePreferences ref = new SecurePreferences(getActivity(), Config.PREFERENCES_NAME, Config.PREFERENCES_KEY, true);
                HashMap<String, String> data = new HashMap<String, String>();
                data.put(Config.TAG_CONTROLLER, "ChangePassword");
                data.put("newpass", cfp);
                data.put("uid", ref.getString("uid"));
                Request request = new Request(data, callback, 7);
                request.execute();
            }
        });
    }
}

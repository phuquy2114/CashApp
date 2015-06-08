package com.quoc.long87.cashmedia.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.quoc.long87.R;
import com.quoc.long87.cashmedia.Utils.Config;
import com.quoc.long87.cashmedia.Utils.FragmentViewClickListener;
import com.quoc.long87.cashmedia.Utils.Request;
import com.quoc.long87.cashmedia.Utils.RequestCallback;
import com.quoc.long87.cashmedia.libraris.SecurePreferences;

import java.util.HashMap;


public class SettingFragment extends Fragment implements OnClickListener {
    private EditText inviteCode;
    private Button btnEnter;
    private TextView tvcode, tvemail;
    private ListView listview;
    private String[] settings = {"Change Password", "Contact Us", "Logout"};
    private RequestCallback callback;
    private FragmentViewClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container,
                false);
        final SecurePreferences ref = new SecurePreferences(getActivity(), Config.PREFERENCES_NAME, Config.PREFERENCES_KEY, true);

        tvcode = (TextView) view.findViewById(R.id.tvCode);
        tvemail = (TextView) view.findViewById(R.id.tvEmails);
        tvcode.setText("Your invitation code: " + ref.getString("uid"));
        tvemail.setText("Your Email: " + ref.getString("email"));

        inviteCode = (EditText) view.findViewById(R.id.edtInput_inv_code);
        btnEnter = (Button) view.findViewById(R.id.btnEnter_code);
        listview = (ListView) view.findViewById(R.id.setting);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, settings);
        listview.setAdapter(adapter);
        btnEnter.setOnClickListener(this);
        listview.setOnItemClickListener(new OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                                                    long arg3) {
                                                listener.onSettingsFragmentClick(arg2);
                                            }
                                        }

        );

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("qqq", "Settings");
    }

    @Override
    public void onClick(View arg0) {
        String code = inviteCode.getText().toString();
        if (code.isEmpty() || code == null) {
            Toast.makeText(getActivity(), "Invitation code is invalid",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        SecurePreferences ref = new SecurePreferences(getActivity(),
                Config.PREFERENCES_NAME, Config.PREFERENCES_KEY,
                true);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put(Config.TAG_CONTROLLER, "Invitecode");
        data.put("code", code);
        data.put("uid", ref.getString("uid"));
        Request request = new Request(data, callback, 3);
        request.execute();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (RequestCallback) activity;
        listener = (FragmentViewClickListener) activity;
    }

}

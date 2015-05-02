package android.phuquy.dev.cashmedia.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.phuquy.dev.cashmedia.R;
import android.phuquy.dev.cashmedia.Utils.Config;
import android.phuquy.dev.cashmedia.adapter.ShareFragmentAdapter;
import android.phuquy.dev.cashmedia.libraris.SecurePreferences;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.List;

public class ShareFragment extends Fragment {
    private ListView lvshare;
    private View view;
    private int [] image = {R.drawable.ic_share_facebook,R.drawable.ic_share_twitter,R.drawable.ic_share_google_plus};
    private String[] names = {"Share via Facebook", "Share via Twitter",
            "Share via Email"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_share, container, false);
        initialize();
        setValues();
        setEvents();
        return view;
    }

    public void initialize() {
        lvshare = (ListView) view.findViewById(R.id.lvShare);
    }

    public void setValues() {
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, settings );
        ShareFragmentAdapter adapter = new ShareFragmentAdapter(getActivity() , names, image);
        lvshare.setAdapter(adapter);
    }

    public void setEvents() {
        lvshare.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                switch (arg2) {
                    case 0:
                        facebook();
                        break;
                    case 1:
                        twitter();
                        break;
                    case 2:
                        email();
                        break;
                }
            }
        });
    }

    private void email() {
        Intent gmailIntent = new Intent();
        SecurePreferences ref = new SecurePreferences(getActivity(), Config.PREFERENCES_NAME, Config.PREFERENCES_KEY, true);
        String text = "Get FreeApp on android " + Config.LINK_TO_SHARE + " to earn gift cards online.User code " + ref.getString("uid") + " to earn 200 credits.";
        gmailIntent.setClassName("com.google.android.gm",
                "com.google.android.gm.ComposeActivityGmail");
        gmailIntent.putExtra(Intent.EXTRA_SUBJECT,
                "Earn money with your phone");
        gmailIntent.putExtra(Intent.EXTRA_TEXT, text);
        getActivity().startActivity(gmailIntent);
    }

    private void twitter() {
        SecurePreferences ref = new SecurePreferences(getActivity(), Config.PREFERENCES_NAME, Config.PREFERENCES_KEY, true);
        String uri = Config.LINK_TO_SHARE + " Free app to earn amazing rewards for trying free app.Use code " + ref.getString("uid") + "for bonus 200 points.";
        uri = "https://twitter.com/intent/tweet?text=" + uri;
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(i);
    }

    @SuppressLint("DefaultLocale")
    private void facebook() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        // intent.putExtra(Intent.EXTRA_SUBJECT, "Foo bar"); // NB: has no
        // effect!
        intent.putExtra(Intent.EXTRA_TEXT, Config.LINK_TO_SHARE);

        // See if official Facebook app is found
        boolean facebookAppFound = false;
        List<ResolveInfo> matches = getActivity().getPackageManager()
                .queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith(
                    "com.facebook.katana")) {
                intent.setPackage(info.activityInfo.packageName);
                facebookAppFound = true;
                break;
            }
        }

        // As fallback, launch sharer.php in a browser
        if (!facebookAppFound) {
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u="
                    + Config.LINK_TO_SHARE;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }

        startActivity(intent);
    }

}

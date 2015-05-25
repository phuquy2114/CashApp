package android.phuquy.dev.cashmedia.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.phuquy.dev.cashmedia.BannerOffersActivity;
import android.phuquy.dev.cashmedia.MainActivity;
import android.phuquy.dev.cashmedia.R;
import android.phuquy.dev.cashmedia.Utils.Config;
import android.phuquy.dev.cashmedia.Utils.JSONParser;
import android.phuquy.dev.cashmedia.adapter.BannerOffersAdapter;
import android.phuquy.dev.cashmedia.adapter.NetworkAdapter;
import android.phuquy.dev.cashmedia.libraris.SecurePreferences;
import android.phuquy.dev.cashmedia.model.BannerOffers;
import android.phuquy.dev.cashmedia.model.Offer;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.supersonicads.sdk.SSAFactory;
import com.supersonicads.sdk.SSAPublisher;
import com.supersonicads.sdk.listeners.OnOfferWallListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OffersFragment extends ListFragment {

    private SecurePreferences ref;
    private String userid;
    private SSAPublisher ssaPub;
    private TextView noOffer;
    private ListView lvOffer;
    private List<Offer> offers = new ArrayList<Offer>();

    // private static final int OFFERWALL_REQUEST_CODE = 1243;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_offers, container, false);
        ref = new SecurePreferences(getActivity(), Config.PREFERENCES_NAME,
                Config.PREFERENCES_KEY, true);
        userid = ref.getString("uid");
        ssaPub = SSAFactory.getPublisherInstance(getActivity());

        noOffer = (TextView) view.findViewById(R.id.nooffer);
        lvOffer = (ListView) view.findViewById(R.id.banners);
        // Woobi.init(getActivity(),"14938", null);

        new GetOffer().execute();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NetworkAdapter adapter = new NetworkAdapter(getActivity(),
                R.layout.offers_item, Config.NET_NAME);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {
            case 0:
                sonic();
                break;
            case 1:
                matomy();
                break;
            case 2:
                radium();
                break;
            case 3:
                supperreward();
                break;
            case 4:
              //  banners();
                break;
        }
    }

    private void supperreward() {
//		sr.showOffers(getActivity(), "hufnlyoguhj.561747490284", userid);
        Intent intent = new Intent("android.intent.action.VIEW",
                Uri.parse(new StringBuilder("https://wall.superrewards.com/super/offers?h=hufnlyoguhj.561747490284&uid=")
                        .append(userid)
                        .toString()));
        startActivity(intent);
    }

    private void radium() {
//		Bundle adUnitIds = new Bundle();
//		adUnitIds.putInt(R1AdServer.ADAPTER_ENGAGE, 123); // can be any unique string to track if offerwall ad  completion served by Engage.
//
//		R1AdServer.getInstance(getActivity()).showOfferwall(adUnitIds); 

        Intent intent = new Intent("android.intent.action.VIEW",
                Uri.parse(new StringBuilder("http://panel.gwallet.com/network-node/impression?appId=3E97ABC9-1565-4FAF-864F-166868A9710A&userId=")
                        .append(userid)
                        .toString()));
        startActivity(intent);

    }

    private void matomy() {
        Intent i = new Intent(
                "android.intent.action.VIEW",
                Uri.parse(new StringBuilder(
                        "http://service.matomy.com/offer/?id=38e732f9-5ebe-48b5-bd01-842d3f051a45&user_id=")
                        .append(userid)
                        .append("&age=&gender=&offer=7&scroll_mode=true")
                        .toString()));
        startActivity(i);
    }

    private void banners() {
        Intent i = new Intent(getActivity(), BannerOffersActivity.class);
        startActivity(i);
    }

    private void sonic() {
        ssaPub.showOfferWall("38a2c625", userid, null,
                new OnOfferWallListener() {

                    @Override
                    public void onOWShowSuccess() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onOWShowFail(String arg0) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getActivity(), arg0, Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onOWGeneric(String arg0, String arg1) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public boolean onOWAdCredited(int arg0, int arg1,
                                                  boolean arg2) {
                        // TODO Auto-generated method stub
                        return false;
                    }

                    @Override
                    public void onOWAdClosed() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onGetOWCreditsFailed(String arg0) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    // private void woobi1() {
    // Woobi.showOffers (getActivity(), "14938", userid);
    // }
    //
    // private void fyber() {
    // Intent offerWallIntent =
    // SponsorPayPublisher.getIntentForOfferWallActivity(getActivity(),false);
    // startActivityForResult(offerWallIntent, OFFERWALL_REQUEST_CODE);
    // }
    //
    // private void aarki() {
    // Aarki.showAds(getActivity(), "87A445F39F6596C3AA", null);
    // }

    @Override
    public void onPause() {
        super.onPause();
        if (ssaPub != null)
            ssaPub.onPause(getActivity());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ssaPub != null)
            ssaPub.release(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
//        ((MainActivity) getActivity()).setHeaderTitle("Offers");
        if (ssaPub != null)
            ssaPub.onResume(getActivity());
        // try {
        // SponsorPay.start("24450", userid, "ab5ad496a8d4533a288d49d88f827e0",
        // getActivity());
        // } catch (RuntimeException e){
        // Log.d("Offers", e.getLocalizedMessage());
        // }
        // Aarki.registerApp(getActivity(), "fowJrgFQycrGqFfrAgl4OIBmE5Nf",
        // userid, new Aarki.AarkiListener() {
        //
        // @Override
        // public void onFinished(Aarki.Status status) {
        // if (status == Aarki.Status.AppNotRegistered) {
        // // This app was not registered in Aarki. Check if
        // // CLIENT_SECURITY_KEY is valid
        // }
        // }
        //
        // });
    }


    private class GetOffer extends AsyncTask<Void, Void, String> implements AdapterView.OnItemClickListener {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Loading offers...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = null;
            try {
                JSONObject ipApi = new JSONObject(JSONParser.makeHttpRequest("http://ip-api.com/json", null));
                if (ipApi.getString("status").equalsIgnoreCase("success")) {
                    String countryCode = ipApi.getString("countryCode");
                    HashMap<String, String> data = new HashMap<String, String>();
                    data.put("country", countryCode);
                    data.put(Config.TAG_CONTROLLER, "GetOffers");
                    result = JSONParser.makeHttpRequest(MainActivity.getLink(), data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return result;
            }
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if(result == null){
                noOffer.setVisibility(View.VISIBLE);
                return;
            }
            try {
                BannerOffers bannerOff = new Gson().fromJson(result, BannerOffers.class);
                int success = bannerOff.getSuccess();
                if (success == 0){
                    noOffer.setVisibility(View.VISIBLE);
                }else{
                    offers = bannerOff.getOffers();
                    if(offers.size() == 0){
                        noOffer.setVisibility(View.VISIBLE);
                    }else{
                        BannerOffersAdapter adapter = new BannerOffersAdapter(getActivity(), R.layout.banner_item, offers);
                        lvOffer.setAdapter(adapter);
                        lvOffer.setOnItemClickListener(this);
                    }
                }
            } catch (Exception e) {
                noOffer.setVisibility(View.VISIBLE);
                e.printStackTrace();
            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long ids) {
            String id = offers.get(position).getOfferId();
            SecurePreferences ref = new SecurePreferences(getActivity(),Config.PREFERENCES_NAME,Config.PREFERENCES_KEY,true);
            String uid = ref.getString("uid");
            String link = new StringBuilder(MainActivity.getLink()).append("gooffer.php?oid=").append(id).append("&uid=").append(uid).toString();
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(i);
        }
    }


}

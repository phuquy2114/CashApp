package com.quoc.long87.cashmedia.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.quoc.long87.R;
import com.quoc.long87.cashmedia.BannerOffersActivity;
import com.quoc.long87.cashmedia.Utils.Config;
import com.quoc.long87.cashmedia.adapter.NetworkAdapter;
import com.quoc.long87.cashmedia.libraris.SecurePreferences;
import com.supersonicads.sdk.SSAFactory;
import com.supersonicads.sdk.SSAPublisher;
import com.supersonicads.sdk.listeners.OnOfferWallListener;

public class OffersFragment extends ListFragment {

    private SecurePreferences ref;
    private String userid;
    private SSAPublisher ssaPub;

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

        // Woobi.init(getActivity(),"14938", null);


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





}

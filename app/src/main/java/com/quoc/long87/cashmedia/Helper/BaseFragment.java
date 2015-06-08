/*
 * Copyright (C) 2014 The Asian Tech. All rights reserved
 * WeightManagement Project
 */
package com.quoc.long87.cashmedia.Helper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

/**
 * @category BaseFragment
 * @author PhuQuy
 * @version 1.0.0
 */
public class BaseFragment extends Fragment {

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() 
					+ " must implement OnBaseFragmentListener");
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


    /**
     *
     * @param bitmap
     * @return
     */
    public static byte[] getByteFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
        return os.toByteArray();
    }

    /**
     *
     * @param bitmap
     * @return
     */
    public String getStringBase64FromBitmap(Bitmap bitmap) {
        String result = null;

        if (bitmap != null) {
            byte[] photoByte = getByteFromBitmap(bitmap);
            result = Base64.encodeToString(photoByte, Base64.DEFAULT);
        }
        return result;
    }

    /**
     *
     * @param path
     * @return
     */
    public String getStringBase64FromBitmap(String path) {
        String result = null;

        try {
            // get bitmap
            Bitmap bm = Picasso.with(getActivity())
                    .load(path)
                    .centerInside()
                    .resize(250,250)
                    .get();

            // check bitmap
            if (bm != null) {

                // get string file
                result = getStringBase64FromBitmap(bm);

                // recycle
                bm.recycle();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}

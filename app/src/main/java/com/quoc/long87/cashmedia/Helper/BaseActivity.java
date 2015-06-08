/*
 * Copyright (C) 2014 The Asian Tech. All rights reserved
 * WeightManagement Project
 */
package com.quoc.long87.cashmedia.Helper;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

/**
 * @category BaseActivity
 * @author PhuQuy
 * @version 1.0.0
 */
public abstract class BaseActivity extends FragmentActivity {

	// Fragment Manager
	protected FragmentManager fManager = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void setFragment(int id, Fragment f) {
		fManager.beginTransaction().replace(id, f).commit();
	}

	/**
	 * Function to make image (The girl) to show or hide
	 * @param _img1
	 */
	public void hideImage(ImageView _img1) {
		_img1.setVisibility(View.GONE);
	}
	
	public void showImage(ImageView _img1) {
		_img1.setVisibility(View.VISIBLE);
	}

	/*
	 * Abstract function
	 */
	protected abstract void initialize();

	protected abstract void setValue();

	protected abstract void setEvent();

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
            Bitmap bm = Picasso.with(getApplicationContext())
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

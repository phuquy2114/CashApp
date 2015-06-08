package com.quoc.long87.cashmedia.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PC on 5/2/2015.
 */
public class ShareNew implements Parcelable {

    private String name;
    private int image;



    public ShareNew (){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ShareNew (Parcel in) {

    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

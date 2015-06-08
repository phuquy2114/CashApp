package com.quoc.long87.cashmedia.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.quoc.long87.R;

/**
 * Created by PC on 4/30/2015.
 */
public class ImagePagerAdapter extends PagerAdapter{

    private Context mContext;
    public ImagePagerAdapter (Context mContext){
        this.mContext = mContext;
    }

    private int[] mImages = new int[]{

            R.drawable.ic_image_1,
            R.drawable.ic_image_2,
            R.drawable.ic_image_3,

    };

    @Override
    public int getCount() {
        return mImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(mImages[position]);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}

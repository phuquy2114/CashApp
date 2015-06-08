package com.quoc.long87.cashmedia.Helper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.quoc.long87.R;

/**
 * Created by PC on 4/30/2015.
 */
public class TabHost extends RelativeLayout implements View.OnClickListener {

    private View view;

    private LinearLayout mViewTabOne;
    private LinearLayout mViewTabTwo;
    private LinearLayout mViewTabThree;
    private LinearLayout mViewTabFour;
    private OnItemClickListtener mListten;
    private ImageView mImgTabOne;
    private ImageView mImgTabOTwo;
    private ImageView mImgTabThree;
    private ImageView mImgTabFour;


    public TabHost(Context context) {
        super(context);
        init(context);
    }

    public TabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabHost(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public TabHost(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    public void init(Context mContext) {
        view = LayoutInflater.from(mContext).inflate(R.layout.tabhost, null);
        addView(view);
        //initialize

        mViewTabOne = (LinearLayout) view.findViewById(R.id.view_tab_one);
        mViewTabTwo = (LinearLayout) view.findViewById(R.id.view_tab_two);
        mViewTabThree = (LinearLayout) view.findViewById(R.id.view_tab_three);
        mViewTabFour = (LinearLayout) view.findViewById(R.id.view_tab_four);

        mImgTabOne = (ImageView) view.findViewById(R.id.img_tab_one);
        mImgTabOTwo = (ImageView) view.findViewById(R.id.img_tab_two);
        mImgTabThree = (ImageView) view.findViewById(R.id.img_tab_three);
        mImgTabFour = (ImageView) view.findViewById(R.id.img_tab_four);

        setEvent();
        onItemClickEvent(0);
    }

    public void setDefault() {
        //Tab One
        mViewTabOne.setBackgroundColor(0xFFe78c28);
        mImgTabOne.setImageResource(R.drawable.icon_top_off);
        //mTxtTabOne.setTextColor(0xFF33691E);

        mViewTabTwo.setBackgroundColor(0xFFe78c28);
        mImgTabOTwo.setImageResource(R.drawable.ic_rewards);
      //  mTxtTabTwo.setTextColor(0xFF33691E);

        mViewTabThree.setBackgroundColor(0xFFe78c28);
        mImgTabThree.setImageResource(R.drawable.icon_share_off);
       // mTxtTabThree.setTextColor(0xFF33691E);

        mViewTabFour.setBackgroundColor(0xFFe78c28);
        mImgTabFour.setImageResource(R.drawable.icon_setting_off);
       // mTxtTabFour.setTextColor(0xFF33691E);
    }

    public void setEvent() {
        mViewTabOne.setOnClickListener(this);
        mViewTabTwo.setOnClickListener(this);
        mViewTabThree.setOnClickListener(this);
        mViewTabFour.setOnClickListener(this);
    }

    public void onItemClickEvent(int position) {
        setDefault();
        if (position == 0) {
            mViewTabOne.setBackgroundColor(0xFFFF0000);
            mImgTabOne.setImageResource(R.drawable.icon_top_on);
            //mTxtTabOne.setTextColor(0xFF000000);
        } else if (position == 1) {
            mViewTabTwo.setBackgroundColor(0xFFFFFF00);
            mImgTabOTwo.setImageResource(R.drawable.ic_rewards);
           // mTxtTabTwo.setTextColor(0xFF000000);
        } else if (position == 2) {
            mViewTabThree.setBackgroundColor(0xFF32CD32);
            mImgTabThree.setImageResource(R.drawable.icon_share_on);
           // mTxtTabThree.setTextColor(0xFF000000);
        } else if (position == 3) {
            mViewTabFour.setBackgroundColor(0xFF0000CD);
            mImgTabFour.setImageResource(R.drawable.icon_setting_on);
         //   mTxtTabFour.setTextColor(0xFF000000);
        } else {
            Toast.makeText(getContext(), "Tab Default ", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_tab_one:
                mListten.ClickItemTabHost(0);
                break;
            case R.id.view_tab_two:
                mListten.ClickItemTabHost(1);
                break;
            case R.id.view_tab_three:
                mListten.ClickItemTabHost(2);
                break;
            case R.id.view_tab_four:
                mListten.ClickItemTabHost(3);
                break;
            default:
                break;

        }
    }

    public void setItemClickListten(OnItemClickListtener l) {
        this.mListten = l;
    }

    public OnItemClickListtener getItemClickListten() {
        return mListten;
    }

    public interface OnItemClickListtener {
        public void ClickItemTabHost(int position);
    }
}

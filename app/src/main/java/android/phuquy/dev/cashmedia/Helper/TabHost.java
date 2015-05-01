package android.phuquy.dev.cashmedia.Helper;

import android.content.Context;
import android.phuquy.dev.cashmedia.R;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by PC on 4/30/2015.
 */
public class TabHost extends RelativeLayout implements View.OnClickListener {

    private View view;
    private TextView mTxtTabOne;
    private TextView mTxtTabTwo;
    private TextView mTxtTabThree;
    private TextView mTxtTabFour;
    private LinearLayout mViewTabOne;
    private LinearLayout mViewTabTwo;
    private LinearLayout mViewTabThree;
    private LinearLayout mViewTabFour;
    private OnItemClickListtener mListten;

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
        mTxtTabOne = (TextView) view.findViewById(R.id.txt_tab_one);
        mTxtTabTwo = (TextView) view.findViewById(R.id.txt_tab_two);
        mTxtTabThree = (TextView) view.findViewById(R.id.txt_tab_three);
        mTxtTabFour = (TextView) view.findViewById(R.id.txt_tab_four);

        mViewTabOne = (LinearLayout) view.findViewById(R.id.view_tab_one);
        mViewTabTwo = (LinearLayout) view.findViewById(R.id.view_tab_two);
        mViewTabThree = (LinearLayout) view.findViewById(R.id.view_tab_three);
        mViewTabFour = (LinearLayout) view.findViewById(R.id.view_tab_four);

        setEvent();
        onItemClickEvent(0);
    }

    public void setDefault() {
        //Tab One
        mViewTabOne.setBackgroundColor(0xFF438F77);
        mTxtTabOne.setTextColor(0xFF33691E);

        mViewTabTwo.setBackgroundColor(0xFF438F77);
        mTxtTabTwo.setTextColor(0xFF33691E);

        mViewTabThree.setBackgroundColor(0xFF438F77);
        mTxtTabThree.setTextColor(0xFF33691E);

        mViewTabFour.setBackgroundColor(0xFF438F77);
        mTxtTabFour.setTextColor(0xFF33691E);
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
            mViewTabOne.setBackgroundColor(0xFF32CD32);
            mTxtTabOne.setTextColor(0xFFFFFFFF);
        } else if (position == 1) {
            mViewTabTwo.setBackgroundColor(0xFF32CD32);
            mTxtTabTwo.setTextColor(0xFFFFFFFF);
        } else if (position == 2) {
            mViewTabThree.setBackgroundColor(0xFF32CD32);
            mTxtTabThree.setTextColor(0xFFFFFFFF);
        } else if (position == 3) {
            mViewTabFour.setBackgroundColor(0xFF32CD32);
            mTxtTabFour.setTextColor(0xFFFFFFFF);
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
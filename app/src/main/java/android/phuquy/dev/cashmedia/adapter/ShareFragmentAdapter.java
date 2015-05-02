package android.phuquy.dev.cashmedia.adapter;

import android.content.Context;
import android.phuquy.dev.cashmedia.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by PC on 5/2/2015.
 */
public class ShareFragmentAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private int[] image;
    private String[] name;


    public ShareFragmentAdapter(Context mContext, String[] _name, int[] _image) {
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mContext;
        name = _name;
        image = _image;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        private TextView mName;
        private ImageView mImageIcon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_share, null);
            viewHolder.mImageIcon = (ImageView) convertView.findViewById(R.id.img_icon_share);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.txt_name_share);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mImageIcon.setTag(viewHolder);
        viewHolder.mName.setTag(viewHolder);

        viewHolder.mImageIcon.setImageResource(image[position]);
        viewHolder.mName.setText(name[position]);

        return convertView;
    }

}

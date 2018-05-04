package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterClassificationLeftData;

import java.util.List;

/**
 * Created by cookie on 2016/8/4.
 */
public class ClassificationLeftFragmentAdapter extends BaseAdapter {

    private List<AdapterClassificationLeftData> mList;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public ClassificationLeftFragmentAdapter(Context context, List<AdapterClassificationLeftData> mList) {
        this.context = context;
        this.mList = mList;

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterClassificationLeftData data = mList.get(position);

        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_classification_left, null);

            viewHolder = new ViewHolder();
            viewHolder.mRelativeLayout = (RelativeLayout)convertView.findViewById(R.id.rl_adapter_classification_left);
            viewHolder.mImageViewLeft = (ImageView) convertView.findViewById(R.id.iv_adapter_classification_left);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_adapter_classification_left);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (data.isSelect()) {
            viewHolder.mImageViewLeft.setVisibility(View.VISIBLE);
            viewHolder.mTextView.setTextColor(context.getResources().getColor(R.color.colorGreenBefore));
            viewHolder.mRelativeLayout.setBackgroundResource(R.color.colorWrite);
        } else {
            viewHolder.mImageViewLeft.setVisibility(View.INVISIBLE);
            viewHolder.mRelativeLayout.setBackgroundResource(R.color.colorGray_2);
            viewHolder.mTextView.setTextColor(context.getResources().getColor(R.color.colorGray));
        }
        viewHolder.mTextView.setText(mList.get(position).getCateName());

        return convertView;
    }

    private final static class ViewHolder {
        RelativeLayout mRelativeLayout;
        ImageView mImageViewLeft;
        TextView mTextView;
    }
}

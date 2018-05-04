package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterNearbyAddressListData;

import java.util.List;

/**
 * Created by cookie on 2016/8/16.
 */
public class NearbyAddressListAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterNearbyAddressListData> mList;

    public NearbyAddressListAdapter(Context context, List<AdapterNearbyAddressListData> mList) {
        this.context = context;
        this.mList = mList;
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

        ViewHolder viewHolder = null;

        AdapterNearbyAddressListData data = mList.get(position);

        if (null == convertView) {

            viewHolder = new ViewHolder();

            LayoutInflater mInflater = LayoutInflater.from(context);

            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_nearbyaddresslist, null);

            viewHolder.mTextViewLocate = (TextView) convertView.findViewById(R.id.tv_adapter_nearbyaddresslist_locate);

            viewHolder.mTextViewDetails = (TextView) convertView.findViewById(R.id.tv_adapter_nearbyaddresslist_detail);

            viewHolder.mTextViewRecommend = (TextView) convertView.findViewById(R.id.tv_adapter_nearbyaddresslist_recommend);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextViewLocate.setText(data.getLocate());
        viewHolder.mTextViewDetails.setText(data.getDetails());

        if (data.isRecommend()){
            viewHolder.mTextViewRecommend.setVisibility(View.VISIBLE);
        }else {
            viewHolder.mTextViewRecommend.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }


    private static class ViewHolder {
        TextView mTextViewLocate, mTextViewDetails, mTextViewRecommend;
    }
}

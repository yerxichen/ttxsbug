package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterMyOrderDetailsCommodityListData;

import java.util.List;

/**
 * Created by cookie on 2016/8/12.
 */
public class MyOrderDetailsCommodityListAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterMyOrderDetailsCommodityListData> mList;

    public MyOrderDetailsCommodityListAdapter(Context context, List<AdapterMyOrderDetailsCommodityListData> mList) {
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

        AdapterMyOrderDetailsCommodityListData data = mList.get(position);

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_myorderdetails_commoditylist, null);

            viewHolder = new ViewHolder();

            viewHolder.mImageViewGoodds = (ImageView) convertView.findViewById(R.id.iv_adapter_activitymyorder_commoditylist_goods);
            viewHolder.mTextViewName = (TextView) convertView.findViewById(R.id.tv_adapter_activitymyorder_commoditylist_name);
            viewHolder.mTextViewPrice = (TextView) convertView.findViewById(R.id.tv_adapter_activitymyorder_commoditylist_price);
            viewHolder.mTextViewCount = (TextView) convertView.findViewById(R.id.tv_adapter_activitymyorder_commoditylist_count);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(data.getImageUrl()).into(viewHolder.mImageViewGoodds);
        viewHolder.mTextViewName.setText(data.getProductName());
        viewHolder.mTextViewPrice.setText(data.getProductPrice());
        viewHolder.mTextViewCount.setText(data.getProductCount());

        return convertView;
    }

    private static class ViewHolder {
        ImageView mImageViewGoodds;
        TextView mTextViewName, mTextViewPrice, mTextViewCount;
    }
}

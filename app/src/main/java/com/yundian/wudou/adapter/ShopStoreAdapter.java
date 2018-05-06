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
import com.yundian.wudou.data.AdapterHomepageShopData;

import java.util.List;

/**
 * Created by cookie on 2016/8/8.
 */
public class ShopStoreAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterHomepageShopData> mList;

    public ShopStoreAdapter(Context context, List<AdapterHomepageShopData> mList) {
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

        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_shop_store, null);
            viewHolder.mImageViewShop = (ImageView) convertView.findViewById(R.id.iv_fragmenthome_shop);
            viewHolder.mTextViewName = (TextView) convertView.findViewById(R.id.tv_fragmenthome_shop_name);
            viewHolder.mImageViewExpert = (ImageView) convertView.findViewById(R.id.iv_fragmenthome_shop_expert);
            viewHolder.mTextViewDistance = (TextView) convertView.findViewById(R.id.tv_fragmenthome_shop_distance);
            viewHolder.mTextViewCount = (TextView) convertView.findViewById(R.id.tv_fragmenthome_shop_count);
            viewHolder.mTextViewStartPrice = (TextView) convertView.findViewById(R.id.tv_fragmenthome_shop_startprice);
            viewHolder.mTextViewSendPrice = (TextView) convertView.findViewById(R.id.tv_fragmenthome_shop_sendprice);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AdapterHomepageShopData data = mList.get(position);

        if (data.isExpert()) {
            viewHolder.mImageViewExpert.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mImageViewExpert.setVisibility(View.GONE);
        }

        Glide.with(context).load(data.getImageAddress()).into(viewHolder.mImageViewShop);

        viewHolder.mTextViewName.setText(data.getName());
        viewHolder.mTextViewDistance.setText(data.getDistance());
        viewHolder.mTextViewCount.setText("月售" + data.getCount() + "份");
        viewHolder.mTextViewStartPrice.setText("¥ " + data.getStartprice());
        viewHolder.mTextViewSendPrice.setText("¥ " + data.getSendprice());

        return convertView;
    }

    private static class ViewHolder {
        ImageView mImageViewShop, mImageViewExpert;
        TextView mTextViewName, mTextViewDistance, mTextViewCount, mTextViewStartPrice, mTextViewSendPrice;
    }
}

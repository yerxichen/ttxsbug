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
import com.yundian.wudou.data.AdapterIntegralMallData;

import java.util.List;

public class IntegralMallAdapter extends BaseAdapter {

    private List<AdapterIntegralMallData> mList;
    private Context context;
    private LayoutInflater mLayoutInflater;


    public IntegralMallAdapter(Context context, List<AdapterIntegralMallData> mList) {
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

        ViewHolder viewHolder = null;
        AdapterIntegralMallData data = mList.get(position);

        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_integralmall, null);
            viewHolder = new ViewHolder();

            viewHolder.mImageViewProduct = (ImageView) convertView.findViewById(R.id.iv_adapter_integralmall_productimage);
            viewHolder.mTextViewProductName = (TextView) convertView.findViewById(R.id.tv_adapter_integralmall_productname);
            viewHolder.mTextViewProductCount = (TextView) convertView.findViewById(R.id.tv_adapter_integralmall_productcount_content);
            viewHolder.mTextViewProductExchange = (TextView) convertView.findViewById(R.id.tv_adapter_integralmall_productexchange_content);
            viewHolder.mTextViewProductPrice = (TextView) convertView.findViewById(R.id.tv_adapter_integralmall_productprice_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(data.getProductUrl()).into(viewHolder.mImageViewProduct);
        viewHolder.mTextViewProductName.setText(String.valueOf(data.getProductName()));
        viewHolder.mTextViewProductCount.setText(String.valueOf(data.getProductCount()));
        viewHolder.mTextViewProductExchange.setText(String.valueOf(data.getProductExchange()));
        viewHolder.mTextViewProductPrice.setText(String.valueOf(data.getProductPrice()));

        return convertView;
    }

    private final class ViewHolder {
        ImageView mImageViewProduct;
        TextView mTextViewProductName, mTextViewProductCount, mTextViewProductExchange, mTextViewProductPrice;
    }
}

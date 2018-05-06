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
import com.yundian.wudou.data.AdapterDailySaleData;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class DailySaleAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterDailySaleData> mDailySaleDatas;

    public DailySaleAdapter(Context context, List<AdapterDailySaleData> mList) {
        this.context = context;
        this.mDailySaleDatas = mList;
    }

    @Override
    public int getCount() {
        return mDailySaleDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDailySaleDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        final AdapterDailySaleData mDailySaleData = mDailySaleDatas.get(position);

        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_daily_sale, null);

            viewHolder = new ViewHolder();
            viewHolder.mImageViewGoods = (ImageView) convertView.findViewById(R.id.iv_adapter_daily_sale_goods);
            viewHolder.mTextViewName = (TextView) convertView.findViewById(R.id.tv_adapter_daily_sale_goods_name);
            viewHolder.mTextViewPrice = (TextView) convertView.findViewById(R.id.tv_adapter_daily_sale_goods_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(mDailySaleData.getImageid()).into(viewHolder.mImageViewGoods);
        viewHolder.mTextViewName.setText(mDailySaleData.getProductName());
        viewHolder.mTextViewPrice.setText(mDailySaleData.getProductPrice());

        return convertView;
    }

    private static class ViewHolder {
        ImageView mImageViewGoods;
        TextView mTextViewName, mTextViewPrice;
    }

}

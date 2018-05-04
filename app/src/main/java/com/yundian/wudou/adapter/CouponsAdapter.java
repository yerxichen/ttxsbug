package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterCouponsData;

import java.util.List;

/**
 * Created by cookie on 2016/8/29.
 */
public class CouponsAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterCouponsData> mList;

    public CouponsAdapter(Context context, List<AdapterCouponsData> mList) {
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

        AdapterCouponsData data = mList.get(position);
        ViewHolder viewHolder = null;

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_fragment_coupons, null);
            viewHolder = new ViewHolder();

            viewHolder.mTextViewCode = (TextView) convertView.findViewById(R.id.tv_adapter_coupons_code);
            viewHolder.mTextViewPrice = (TextView) convertView.findViewById(R.id.tv_adapter_coupons_price);
            viewHolder.mTextViewCondition = (TextView) convertView.findViewById(R.id.tv_adapter_coupons_condition);
            viewHolder.mTextViewDataStart = (TextView) convertView.findViewById(R.id.tv_adapter_coupons_datastart);
            viewHolder.mTextViewDataEnd = (TextView) convertView.findViewById(R.id.tv_adapter_coupons_dataend);
            viewHolder.mTextViewStatus = (TextView) convertView.findViewById(R.id.tv_adapter_coupons_status);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextViewCode.setText("兑换码：" + data.getCode());
        viewHolder.mTextViewPrice.setText("¥" + data.getPrice());
        viewHolder.mTextViewCondition.setText(data.getCondition());
        viewHolder.mTextViewDataStart.setText(data.getDataStart());
        viewHolder.mTextViewDataEnd.setText(data.getDataEnd());
        viewHolder.mTextViewStatus.setText(data.getStatus());

        return convertView;
    }

    private static class ViewHolder {
        private TextView mTextViewCode, mTextViewPrice, mTextViewCondition, mTextViewDataStart, mTextViewDataEnd, mTextViewStatus;
    }
}

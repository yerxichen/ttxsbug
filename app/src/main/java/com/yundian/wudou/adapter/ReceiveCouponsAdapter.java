package com.yundian.wudou.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterReceiveCouponsData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanStoreCoupons;
import com.yundian.wudou.network.NetWorkOperate;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class ReceiveCouponsAdapter extends BaseAdapter {

    private OnStatusClickListener onStatusClickListener;

    private Context context;
    private List<AdapterReceiveCouponsData> mList;

    public ReceiveCouponsAdapter(Context context, List<AdapterReceiveCouponsData> mList) {
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        AdapterReceiveCouponsData data = mList.get(position);
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

        viewHolder.mTextViewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onStatusClickListener!=null){
                    onStatusClickListener.onStatusClick(position);
                }
            }
        });

        viewHolder.mTextViewCode.setText("兑换码：" + data.getCode());
        viewHolder.mTextViewPrice.setText("¥" + data.getPrice());
        viewHolder.mTextViewCondition.setText(data.getCondition());
        viewHolder.mTextViewDataStart.setText(data.getDataStart());
        viewHolder.mTextViewDataEnd.setText(data.getDataEnd());
        if(data.getStatus().equals("0")){
            viewHolder.mTextViewStatus.setText("未领取");
        }else{
            viewHolder.mTextViewStatus.setText("已领取");
        }
        return convertView;
    }

    private static class ViewHolder {
         TextView mTextViewCode,
                mTextViewPrice, mTextViewCondition, mTextViewDataStart, mTextViewDataEnd, mTextViewStatus;
    }

    public interface OnStatusClickListener{
        void onStatusClick(int position);
    }

    public void setOnStatusClickListener(OnStatusClickListener listener){
        this.onStatusClickListener = listener;
    }
}

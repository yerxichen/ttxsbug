package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterPaymentData;

import java.util.List;

/**
 * Created by Administrator on 2016/9/27 0027.
 */
public class PaymentAdapter extends BaseAdapter {

      private List<AdapterPaymentData> mList;
      private Context context;

    public PaymentAdapter(Context context, List<AdapterPaymentData> mList) {
        super();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {



        ViewHolder viewHolder = null;

        AdapterPaymentData data = mList.get(position);

        if (null == convertView){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_payment, null);

            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_adapter_payment);
            viewHolder.mTextViewTitle = (TextView) convertView.findViewById(R.id.tv_adapter_payment_tltle);
            viewHolder.mTextViewName = (TextView) convertView.findViewById(R.id.tv_adapter_payment_name);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mImageView.setImageResource(data.getImageid());
        viewHolder.mTextViewTitle.setText(data.getTitle());
        viewHolder.mTextViewName.setText(data.getName());


        return convertView;


    }


    private static class ViewHolder{
        private TextView mTextViewTitle, mTextViewName;
        private ImageView mImageView;
    }

}


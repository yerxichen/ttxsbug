package com.yundian.wudou.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.activity.EvaluateActivity;
import com.yundian.wudou.activity.PaymentActivity;
import com.yundian.wudou.activity.VegetableShopActivity;
import com.yundian.wudou.data.AdapterMyAllOrderData;
import com.yundian.wudou.data.FlagData;

import java.util.List;

/**
 * Created by cookie on 2016/8/12.
 */
public class MyAllOrderAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterMyAllOrderData> mList;

    public MyAllOrderAdapter(Context context, List<AdapterMyAllOrderData> mList) {
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

        final AdapterMyAllOrderData data = mList.get(position);

        ViewHolder viewHolder = null;

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_myallorder, null);

            viewHolder = new ViewHolder();
            viewHolder.mLinearLayout = (LinearLayout) convertView.findViewById(R.id.ll_adapter_myallorder);

            viewHolder.mTextViewName = (TextView) convertView.findViewById(R.id.tv_adapter_myallorder_store);
            viewHolder.mTextViewStatus = (TextView) convertView.findViewById(R.id.tv_adapter_myallorder_status);
            viewHolder.mTextViewOrderTime = (TextView) convertView.findViewById(R.id.tv_adapter_myallorder_ordertime);
            viewHolder.mTextViewRight = (TextView) convertView.findViewById(R.id.tv_adapter_myallorder_right);
            viewHolder.mTextViewLeft = (TextView) convertView.findViewById(R.id.tv_adapter_myallorder_left);
            viewHolder.surplusTitle = (TextView) convertView.findViewById(R.id.tv_adapter_myallorder_surplusmoney_title);
            viewHolder.surplusMoney = (TextView) convertView.findViewById(R.id.tv_adapter_myallorder_surplusmoney);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mLinearLayout.removeAllViews();
        for (AdapterMyAllOrderData.ProductData productData : data.getmProductDataList()) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(195, 180));
            imageView.setPadding(0,0,15,0);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(productData.getImageUrl()).into(imageView);
            viewHolder.mLinearLayout.addView(imageView);
        }

        viewHolder.mTextViewName.setText(data.getStorename());
        viewHolder.mTextViewOrderTime.setText(data.getAddtime());
        viewHolder.mTextViewStatus.setText(data.getOrderstatus());

        if(data.getOrdertype().equals("1")){
            viewHolder.surplusTitle.setText("兑换积分 : ");
            viewHolder.surplusMoney.setText(data.getCreditsvalue());
        }else{
            viewHolder.surplusTitle.setText("应付金额 : ");
            viewHolder.surplusMoney.setText(data.getSurplusmoney());
        }

        if (data.isPaysate()) {
            viewHolder.mTextViewRight.setText(context.getString(R.string.pay_now));
            viewHolder.mTextViewRight.setBackgroundResource(R.color.colorGreenBefore);
        } else {
            viewHolder.mTextViewRight.setText("再次购买");
            viewHolder.mTextViewRight.setBackgroundResource(R.color.colorOrangeLight);
        }

        if (data.getIsreviews().equals("0")) {
            viewHolder.mTextViewLeft.setVisibility(View.VISIBLE);
            viewHolder.mTextViewLeft.setText(data.getReviewstext());
        } else {
            viewHolder.mTextViewLeft.setVisibility(View.INVISIBLE);
        }

        viewHolder.mTextViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EvaluateActivity.class);
                intent.putExtra(FlagData.FLAG_OID, data.getOid());
                context.startActivity(intent);
            }
        });
        viewHolder.mTextViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.isPaysate()) {
                    Intent intent = new Intent(context, PaymentActivity.class);
                    intent.putExtra(FlagData.FLAG_SNTYPE, "1");
                    intent.putExtra(FlagData.FLAG_SN, data.getOsn());
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, VegetableShopActivity.class);
                    intent.putExtra(FlagData.FLAG_SHOP_ID, data.getStoreid());
                    context.startActivity(intent);
                }
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        LinearLayout mLinearLayout;
        TextView mTextViewName, mTextViewStatus, surplusTitle,surplusMoney
                ,mTextViewOrderTime, mTextViewRight, mTextViewLeft;
    }
}

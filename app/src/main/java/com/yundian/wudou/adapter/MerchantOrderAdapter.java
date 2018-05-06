package com.yundian.wudou.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.yundian.wudou.data.AdapterMerchantOrderData;
import com.yundian.wudou.data.AdapterMyAllOrderData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.NetWorkOperate;

import java.util.List;

/**
 * Created by cookie on 2016/8/12.
 */
public class MerchantOrderAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterMerchantOrderData> mList;

    private OnMerchantEditClickListener onMerchantEditClickListener;

    public MerchantOrderAdapter(Context context, List<AdapterMerchantOrderData> mList) {
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

        final AdapterMerchantOrderData data = mList.get(position);

        ViewHolder viewHolder = null;

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_merchantorder, null);

            viewHolder = new ViewHolder();
            viewHolder.mLinearLayout = (LinearLayout) convertView.findViewById(R.id.ll_adapter_merchantorder);

            viewHolder.mTextViewName = (TextView) convertView.findViewById(R.id.tv_adapter_merchantorder_store);
            viewHolder.mTextViewStatus = (TextView) convertView.findViewById(R.id.tv_adapter_merchantorder_status);
            viewHolder.surplusTitle = (TextView) convertView.findViewById(R.id.tv_adapter_merchantorder_surplusmoney_title);
            viewHolder.surplusMoney = (TextView) convertView.findViewById(R.id.tv_adapter_merchantorder_surplusmoney);
            viewHolder.mTextViewOrderTime = (TextView) convertView.findViewById(R.id.tv_adapter_merchantorder_ordertime);
            viewHolder.mTextViewEdit = (TextView)convertView.findViewById(R.id.tv_adapter_merchantorder_edit);
            viewHolder.viewLineTwo = convertView.findViewById(R.id.view_adapter_merchantorder_linetwo);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mLinearLayout.removeAllViews();
        for (AdapterMerchantOrderData.ProductData productData : data.getmProductDataList()) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(195, 180));
            imageView.setPadding(0,0,15,0);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(productData.getImageUrl()).into(imageView);
            viewHolder.mLinearLayout.addView(imageView);
        }

        viewHolder.mTextViewName.setText(data.getStorename());
        viewHolder.surplusMoney.setText(data.getSurplusmoney());
        viewHolder.mTextViewOrderTime.setText(data.getAddtime());
        viewHolder.mTextViewStatus.setText(data.getOrderstatus());
        viewHolder.mTextViewEdit.setText(data.getEditshowText());

        if(data.getOrdertype().equals("1")){
            viewHolder.surplusTitle.setText("兑换积分 : ");
            viewHolder.surplusMoney.setText(data.getCreditsvalue());
        }else{
            viewHolder.surplusTitle.setText("应付金额 : ");
            viewHolder.surplusMoney.setText(data.getSurplusmoney());
        }

        if(data.getIsEditshow().equals("0")){
            viewHolder.mTextViewEdit.setVisibility(View.VISIBLE);
            viewHolder.viewLineTwo.setVisibility(View.VISIBLE);
        }else if(data.getIsEditshow().equals("1")){
            viewHolder.mTextViewEdit.setVisibility(View.GONE);
            viewHolder.viewLineTwo.setVisibility(View.GONE);
        }

        viewHolder.mTextViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onMerchantEditClickListener!=null){
                    onMerchantEditClickListener.onMerchantEditClick(data.getOid());
                }
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        LinearLayout mLinearLayout;
        TextView mTextViewName, mTextViewStatus,surplusTitle,surplusMoney,mTextViewOrderTime,mTextViewEdit;
        View viewLineTwo;
    }

    public interface OnMerchantEditClickListener{
        void onMerchantEditClick(String oid);
    }

    public void setOnMerchantEditClickListener(OnMerchantEditClickListener listener){
        this.onMerchantEditClickListener = listener;
    }

}

package com.yundian.wudou.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.activity.CommodityDetailsActivity;
import com.yundian.wudou.data.AdapterStoreDetailsBottomData;
import com.yundian.wudou.data.AdapterStoreDetailsRightData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.OrderManager;

import java.util.List;

/**
 * Created by cookie on 2016/8/11.
 */
public class StoreDetailsRightAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterStoreDetailsRightData> mList;

    private OrderManager mOrderManager;

    private String storeId,storeName,storeUrl,startValue,sendPrice;
    private int count;

    public StoreDetailsRightAdapter(Context context, List<AdapterStoreDetailsRightData> mList) {
        this.context = context;
        this.mList = mList;
        mOrderManager = new OrderManager(context);
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public void setSendPrice(String sendPrice) {
        this.sendPrice = sendPrice;
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

        final AdapterStoreDetailsRightData data = mList.get(position);
        data.setProductCount(mOrderManager.getProductCount(data.getProductId())+"");

        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_storeadetailsactivity_right, null);

            viewHolder = new ViewHolder();
            viewHolder.mImageViewGoods = (ImageView) convertView.findViewById(R.id.iv_adapter_activitystoredetils_productimg);
            viewHolder.mImageViewReduce = (ImageView) convertView.findViewById(R.id.iv_adapter_activitystoredetils_productreduce);
            viewHolder.mImageViewAdd = (ImageView) convertView.findViewById(R.id.iv_adapter_activitystoredetils_productadd);
            viewHolder.mTextViewName = (TextView) convertView.findViewById(R.id.tv_adapter_activitystoredetils_productname);
            viewHolder.mTextViewCount = (TextView) convertView.findViewById(R.id.tv_adapter_activitystoredetils_productcount);
            viewHolder.mTextViewPrice = (TextView) convertView.findViewById(R.id.tv_adapter_activitystoredetils_productprice);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mImageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数量加1
                count = Integer.parseInt(data.getProductCount());
                count++;
                data.setProductCount(count + "");

                mOrderManager.addProduct(storeId, storeName,storeUrl, data.getProductId(), data.getProductName(), data.getProductUrl(), String.valueOf(data.getProductPrice()),"",startValue,sendPrice);
                //回调
                if(mStoreItemAddListener!=null){
                    mStoreItemAddListener.storeItemAddListener();
                }
                notifyDataSetChanged();
            }
        });

        viewHolder.mImageViewReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数量减1
                count = Integer.parseInt(data.getProductCount());
                if (count > 0) {
                    count--;
                    data.setProductCount(count + "");
                }
                mOrderManager.reduceProduct(data.getProductId());
                //回调
                if(mStoreItemReduceListener!=null){
                    mStoreItemReduceListener.storeItemReduceListener();
                }
                notifyDataSetChanged();
            }
        });

        Picasso.with(context).load(data.getProductUrl()).into(viewHolder.mImageViewGoods);
        viewHolder.mTextViewName.setText(data.getProductName());
        viewHolder.mTextViewPrice.setText(data.getProductPrice());
        viewHolder.mTextViewCount.setText(data.getProductCount());

        if(data.getProductCount().equals("0")){
            viewHolder.mImageViewReduce.setVisibility(View.GONE);
            viewHolder.mTextViewCount.setVisibility(View.GONE);
        }else{
            viewHolder.mImageViewReduce.setVisibility(View.VISIBLE);
            viewHolder.mTextViewCount.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    private static class ViewHolder {
        ImageView mImageViewGoods, mImageViewAdd, mImageViewReduce;
        TextView mTextViewName, mTextViewPrice, mTextViewCount;
    }
    /**
     * 自定义监听的接口
     */
    private StoreItemAddListener mStoreItemAddListener;
    private StoreItemReduceListener mStoreItemReduceListener;

    public void setItemAddListener(StoreItemAddListener listener) {
        this.mStoreItemAddListener = listener;
    }

    public void setItemReduceListener(StoreItemReduceListener listener) {
        this.mStoreItemReduceListener = listener;
    }

    public interface StoreItemAddListener {
        void storeItemAddListener();
    }

    public interface StoreItemReduceListener {
        void storeItemReduceListener();
    }
}

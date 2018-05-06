package com.yundian.wudou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterStoreDetailsRightData;
import com.yundian.wudou.datawork.OrderManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cookie on 2016/8/11.
 */
public class TestStoreDetailsRightAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<AdapterStoreDetailsRightData> mList;
    private OrderManager mOrderManager;
    private String storeId, storeName, storeUrl, startValue, sendPrice;
    private int count;

    public TestStoreDetailsRightAdapter(Context context, List<AdapterStoreDetailsRightData> mList) {
        this.context = context;
        this.mList = mList;
        mOrderManager = new OrderManager(context);
    }

    /* * 自定义监听的接口
        */
    private StoreItemAddListener mStoreItemAddListener;
    private StoreItemReduceListener mStoreItemReduceListener;
    private SetOnItemClickLitener mOnItemClickLitener;

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

    /**
     * 继承点击事件
     */
    public interface SetOnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickLitener(SetOnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.adapter_storeadetailsactivity_right_test, parent, false);

        MyViewHolder holder = new MyViewHolder(convertView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final AdapterStoreDetailsRightData data = mList.get(position);
        data.setProductCount(mOrderManager.getProductCount(data.getProductId()) + "");
        ((MyViewHolder) holder).mImageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数量加1
                count = Integer.parseInt(data.getProductCount());
                count++;
                data.setProductCount(count + "");

                mOrderManager.addProduct(storeId, storeName, storeUrl, data.getProductId(), data.getProductName(), data.getProductUrl(), String.valueOf(data.getProductPrice()), "", startValue, sendPrice);
                //回调
                if (mStoreItemAddListener != null) {
                    mStoreItemAddListener.storeItemAddListener();
                }
                notifyDataSetChanged();
            }
        });

        ((MyViewHolder) holder).mImageViewReduce.setOnClickListener(new View.OnClickListener() {
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
                if (mStoreItemReduceListener != null) {
                    mStoreItemReduceListener.storeItemReduceListener();
                }
                notifyDataSetChanged();
            }
        });

        Glide.with(context).load(data.getProductUrl()).into(((MyViewHolder) holder).mImageViewGoods);
        ((MyViewHolder) holder).mTextViewName.setText(data.getProductName());
        ((MyViewHolder) holder).mTextViewPrice.setText(data.getProductPrice());
        ((MyViewHolder) holder).mTextViewCount.setText(data.getProductCount());

        if (data.getProductCount().equals("0")) {
            ((MyViewHolder) holder).mImageViewReduce.setVisibility(View.GONE);
            ((MyViewHolder) holder).mTextViewCount.setVisibility(View.GONE);
        } else {
            ((MyViewHolder) holder).mImageViewReduce.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).mTextViewCount.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() == 0 ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_adapter_activitystoredetils_productimg)
        ImageView mImageViewGoods;
        @Bind(R.id.tv_adapter_activitystoredetils_productname)
        TextView mTextViewName;
        @Bind(R.id.tv_adapter_activitystoredetils_productprice)
        TextView mTextViewPrice;
        @Bind(R.id.iv_adapter_activitystoredetils_productadd)
        ImageView mImageViewAdd;
        @Bind(R.id.tv_adapter_activitystoredetils_productcount)
        TextView mTextViewCount;
        @Bind(R.id.iv_adapter_activitystoredetils_productreduce)
        ImageView mImageViewReduce;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}

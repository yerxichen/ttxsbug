package com.yundian.wudou.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterStoreDetailsBottomData;
import com.yundian.wudou.datawork.OrderManager;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class StoreDetailsBottomAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterStoreDetailsBottomData> mList;
    private int count;
    private String storeName;
    private OrderManager mOrderManager;

    public StoreDetailsBottomAdapter(Context context, List<AdapterStoreDetailsBottomData> mList, String storeName) {
        this.context = context;
        this.mList = mList;
        this.storeName = storeName;
        mOrderManager = new OrderManager(context);
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

        final AdapterStoreDetailsBottomData data = mList.get(position);

        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_storedetailsactivity_bottom, null);

            viewHolder = new ViewHolder();
            viewHolder.mImageViewReduce = (ImageView) convertView.findViewById(R.id.iv_adapter_activitystoredetils_bottom_goods_reduce);
            viewHolder.mImageViewAdd = (ImageView) convertView.findViewById(R.id.iv_adapter_activitystoredetils_bottom_goods_add);
            viewHolder.mTextViewName = (TextView) convertView.findViewById(R.id.tv_adapter_activitystoredetils_bottom_goods);
            viewHolder.mTextViewCount = (TextView) convertView.findViewById(R.id.tv_adapter_activitystoredetils_bottom_goods_count);
            viewHolder.mTextViewPrice = (TextView) convertView.findViewById(R.id.tv_adapter_activitystoredetils_bottom_goods_price);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mImageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = Integer.parseInt(data.getProductCount());
                count++;
                data.setProductCount(count + "");

                mOrderManager.addProduct(data.getStoreId(), storeName, data.getStoreUrl(),data.getProductId(), data.getProductName(), data.getProductUrl(), data.getProductPrice(),
                        data.getProductWeight(),data.getStartValue(),data.getSendPrice());

                //回调
                if (onBottomAddListener != null) {
                    onBottomAddListener.onBottomAdd();
                }
                notifyDataSetChanged();
            }
        });

        viewHolder.mImageViewReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    count = Integer.parseInt(data.getProductCount());
                    count--;
                    data.setProductCount(count + "");
                }
                mOrderManager.reduceProduct(data.getProductId());
                if(count==0){
                    if (onBottomDeleteListener != null) {
                        onBottomDeleteListener.onBottomDelete();
                    }
                }else{
                    if (onBottomReduceListener != null) {
                        onBottomReduceListener.onBottomReduce();
                    }
                }
                notifyDataSetChanged();
            }
        });
        viewHolder.mTextViewName.setText(data.getProductName());
        viewHolder.mTextViewPrice.setText("" + data.getProductPrice());
        viewHolder.mTextViewCount.setText("" + data.getProductCount());

        return convertView;
    }

    private static class ViewHolder {
        ImageView mImageViewAdd, mImageViewReduce;
        TextView mTextViewName, mTextViewPrice, mTextViewCount;
    }

    /**
     * 自定义监听的接口
     */
    private OnBottomAddListener onBottomAddListener;
    private OnBottomReduceListener onBottomReduceListener;
    private OnBottomDeleteListener onBottomDeleteListener;

    public void setBottomAddListener(OnBottomAddListener listener) {
        this.onBottomAddListener = listener;
    }

    public void setBottomReduceListener(OnBottomReduceListener listener) {
        this.onBottomReduceListener = listener;
    }

    public void setBottomDeleteListener(OnBottomDeleteListener listener) {
        this.onBottomDeleteListener = listener;
    }

    public interface OnBottomAddListener {
        void onBottomAdd();
    }

    public interface OnBottomReduceListener {
        void onBottomReduce();
    }

    public interface OnBottomDeleteListener {
        void onBottomDelete();
    }
}

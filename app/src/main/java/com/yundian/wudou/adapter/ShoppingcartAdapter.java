package com.yundian.wudou.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.activity.LoginActivity;
import com.yundian.wudou.data.AdapterShoopingcartData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.OrderManager;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by cookie on 2016/8/9.
 */
public class ShoppingcartAdapter extends BaseAdapter {

    //监听器
    private OnTotalChangedListener mOnTotalChangedListener;
    private OnDeleteCommodityListener mOnDeleteCommodityListener;
    private OnSelectAllListener mOnSelectAllListener;
    private OnCancelSelectAllListener mOnCancelSelectAllListener;

    private Context context;
    private List<AdapterShoopingcartData> mList;

    private OrderManager mOrderManager;
    private DecimalFormat decimalFormat;

    private Boolean allSelect = true;

    public ShoppingcartAdapter(Context context, List<AdapterShoopingcartData> mList) {
        this.context = context;
        this.mList = mList;

        mOrderManager = new OrderManager(context);
        decimalFormat = new DecimalFormat("0.00");
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
    public int getItemViewType(int position) {
        switch (mList.get(position).getType()) {
            case 0: {
                return 0;
            }
            case 1: {
                return 1;
            }
            default: {
                return 0;
            }
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final AdapterShoopingcartData data = mList.get(position);

        switch (getItemViewType(position)) {
            case 0: {
                ViewHolderStore viewHolderStore = null;

                if (null == convertView) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.adapter_shoppingcart_store, null);

                    viewHolderStore = new ViewHolderStore();

                    viewHolderStore.mCheckBoxStore = (CheckBox) convertView.findViewById(R.id.cb_adapter_fragmentshoppingcart_store);
                    viewHolderStore.mTextViewStore = (TextView) convertView.findViewById(R.id.tv_adapter_fragmentshoppingcart_store);

                    convertView.setTag(viewHolderStore);
                } else {
                    viewHolderStore = (ViewHolderStore) convertView.getTag();
                }

                viewHolderStore.mCheckBoxStore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {

                        buttonView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                for (AdapterShoopingcartData dataAll : mList) {

                                    if (dataAll.getStoreId().equals(data.getStoreId())) {
                                        dataAll.setChecked(isChecked);
                                    }
                                }

                                allSelect = true;
                                for (AdapterShoopingcartData data : mList) {
                                    if (!data.isChecked()) {
                                        allSelect = false;
                                    }
                                }
                                if (allSelect) {
                                    mOnSelectAllListener.onSelectAll();
                                } else {
                                    mOnCancelSelectAllListener.onCancelSelectAll();
                                }
                            }
                        });

                        data.setChecked(isChecked);
                        notifyDataSetChanged();
                    }
                });

                viewHolderStore.mCheckBoxStore.setChecked(data.isChecked());
                viewHolderStore.mTextViewStore.setText(data.getStoreName());

                break;
            }
            case 1: {
                ViewHolderGoods viewHolderGoods = null;

                if (null == convertView) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.adapter_shoppingcart_goods, null);

                    viewHolderGoods = new ViewHolderGoods();

                    viewHolderGoods.mCheckBoxGoods = (CheckBox) convertView.findViewById(R.id.cb_adapter_fragmentshoppingcart_goods);
                    viewHolderGoods.mImageViewGoods = (ImageView) convertView.findViewById(R.id.iv_adapter_fragmentshoppingcart_goods);
                    viewHolderGoods.mTextViewGoods = (TextView) convertView.findViewById(R.id.tv_adapter_fragmentshoppingcart_goods);
                    viewHolderGoods.mTextViewPrice = (TextView) convertView.findViewById(R.id.tv_adapter_fragmentshoppingcart_goods_price);
                    viewHolderGoods.mTextViewCount = (TextView) convertView.findViewById(R.id.tv_adapter_fragmentshoppingcart_goods_count);
                    viewHolderGoods.mImageViewAdd = (ImageView) convertView.findViewById(R.id.iv_adapter_fragmentshoppingcart_goods_add);
                    viewHolderGoods.mImageViewReduce = (ImageView) convertView.findViewById(R.id.iv_adapter_fragmentshoppingcart_goods_reduce);

                    convertView.setTag(viewHolderGoods);
                } else {
                    viewHolderGoods = (ViewHolderGoods) convertView.getTag();
                }

                viewHolderGoods.mCheckBoxGoods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        data.setChecked(isChecked);

                        int chechkedCount = 0, storeidCount = 0;

                        for (AdapterShoopingcartData dataAll : mList) {
                            if (dataAll.getStoreId().equals(data.getStoreId()) && dataAll.isChecked() && !dataAll.isParent()) {
                                chechkedCount++;
                            }
                            if (dataAll.getStoreId().equals(data.getStoreId())) {
                                storeidCount++;
                            }
                        }
                        Log.e("Tag", "" + chechkedCount + "," + storeidCount);
                        if (chechkedCount < (storeidCount - 1)) {
                            for (AdapterShoopingcartData dataAll : mList) {
                                if (dataAll.getStoreId().equals(data.getStoreId()) && dataAll.isParent()) {
                                    dataAll.setChecked(false);
                                }
                            }
                        } else {
                            for (AdapterShoopingcartData dataAll : mList) {
                                if (dataAll.getStoreId().equals(data.getStoreId()) && dataAll.isParent()) {
                                    dataAll.setChecked(true);
                                }
                            }
                        }

                        allSelect = true;
                        for (AdapterShoopingcartData data : mList) {
                            if (!data.isChecked()) {
                                allSelect = false;
                            }
                        }

                        if (allSelect) {
                            mOnSelectAllListener.onSelectAll();
                        } else {
                            mOnCancelSelectAllListener.onCancelSelectAll();
                        }

                        notifyDataSetChanged();
                        mOnTotalChangedListener.onChanged(calculateTotal());
                    }
                });

                viewHolderGoods.mImageViewReduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //数据库里减少
                        mOrderManager.reduceProduct(data.getProductId());

                        //页面减少，只做显示
                        int count = data.getProductCount();
                        if (count > 0) {
                            if (count == 1) {
                                mOnDeleteCommodityListener.onDeleteCommodity();
                            }
                            count--;
                            data.setProductCount(count);
                            notifyDataSetChanged();
                            mOnTotalChangedListener.onChanged(calculateTotal());
                        }
                    }
                });

                viewHolderGoods.mImageViewAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //数据库添加
                        mOrderManager.addProduct(data.getStoreId(), data.getStoreName(), data.getStoreUrl(), data.getProductId(), data.getProductName()
                                , data.getProductUrl(), data.getProductPrice() + "", data.getProductWeight(), data.getStartValue(), data.getSendPrice());

                        //页面添加，只做显示
                        int count = data.getProductCount();
                        count++;
                        data.setProductCount(count);
                        notifyDataSetChanged();

                        mOnTotalChangedListener.onChanged(calculateTotal());
                    }
                });

                viewHolderGoods.mCheckBoxGoods.setChecked(data.isChecked());
                Log.d(">>>购物车图片地址>>>", data.getProductUrl());
                Glide.with(context).load(FlagData.FLAG_IMGADDRESS +data.getProductUrl()).into(viewHolderGoods.mImageViewGoods);
                viewHolderGoods.mTextViewGoods.setText(data.getProductName());
                viewHolderGoods.mTextViewPrice.setText(decimalFormat.format(data.getProductPrice()));
                viewHolderGoods.mTextViewCount.setText("" + data.getProductCount());
                break;
            }
            default: {
                Log.e("Tag", "shoppingcartadapter.default");
            }
        }
        return convertView;
    }

    private static class ViewHolderStore {
        CheckBox mCheckBoxStore;
        TextView mTextViewStore;
    }

    private static class ViewHolderGoods {
        CheckBox mCheckBoxGoods;
        ImageView mImageViewGoods, mImageViewReduce, mImageViewAdd;
        TextView mTextViewGoods, mTextViewPrice, mTextViewCount;
    }

    //计算总价格
    public String calculateTotal() {
        float total = 0;
        for (AdapterShoopingcartData data : mList) {
            if (data.isChecked()) {
                total += data.getProductCount() * data.getProductPrice();
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(total);
    }

    //价格变化监听器
    public interface OnTotalChangedListener {
        void onChanged(String total);
    }

    //删除商品监听器
    public interface OnDeleteCommodityListener {
        void onDeleteCommodity();
    }

    //全选监听器
    public interface OnSelectAllListener {
        void onSelectAll();
    }

    //取消全选监听器
    public interface OnCancelSelectAllListener {
        void onCancelSelectAll();
    }

    public void setOnTotalChangedListener(OnTotalChangedListener listener) {
        this.mOnTotalChangedListener = listener;
    }

    public void setOnDeleteCommodityListener(OnDeleteCommodityListener listener) {
        this.mOnDeleteCommodityListener = listener;
    }

    public void setOnSelectAllListener(OnSelectAllListener listener) {
        this.mOnSelectAllListener = listener;
    }

    public void setOnCancelSelectAllListener(OnCancelSelectAllListener listener) {
        this.mOnCancelSelectAllListener = listener;
    }
}

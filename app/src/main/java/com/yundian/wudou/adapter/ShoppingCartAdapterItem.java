package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterShoopingcartNewData;
import com.yundian.wudou.data.FlagData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taozipc on 2017/12/11.
 */

public class ShoppingCartAdapterItem extends BaseAdapter {
    private Context context;
    private List<AdapterShoopingcartNewData.ProductsBean.DataBean> mList;

    public ShoppingCartAdapterItem(Context context, List<AdapterShoopingcartNewData.ProductsBean.DataBean> mList) {
        this.context = context;
        this.mList = mList;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public List<AdapterShoopingcartNewData.ProductsBean.DataBean> getData() {
        return mList;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final AdapterShoopingcartNewData.ProductsBean.DataBean data = mList.get(position);
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_shoppingcart_store_new_item, null);
            viewHolder = new ViewHolder();
            viewHolder.ivToShop = (ImageView) convertView.findViewById(R.id.iv_adapter_shopping_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (data.getProductUrl().contains("http")){
            Picasso.with(context).load( data.getProductUrl()).into(viewHolder.ivToShop);
        }else {

            Picasso.with(context).load(FlagData.FLAG_IMGADDRESS + data.getProductUrl()).into(viewHolder.ivToShop);
        }
        return convertView;
    }

    //    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//
//
//        switch (getItemViewType(position)) {
//            case 0: {
//                ViewHolderStore viewHolderStore = null;
//
//                if (null == convertView) {
//                    convertView = LayoutInflater.from(context).inflate(R.layout.adapter_shoppingcart_store, null);
//
//                    viewHolderStore = new ViewHolderStore();
//
//                    viewHolderStore.mCheckBoxStore = (CheckBox) convertView.findViewById(R.id.cb_adapter_fragmentshoppingcart_store);
//                    viewHolderStore.mTextViewStore = (TextView) convertView.findViewById(R.id.tv_adapter_fragmentshoppingcart_store);
//
//                    convertView.setTag(viewHolderStore);
//                } else {
//                    viewHolderStore = (ViewHolderStore) convertView.getTag();
//                }
//
//                viewHolderStore.mCheckBoxStore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
//
//                        buttonView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                for (AdapterShoopingcartData dataAll : mList) {
//
//                                    if (dataAll.getStoreId().equals(data.getStoreId())) {
//                                        dataAll.setChecked(isChecked);
//                                    }
//                                }
//
//                                allSelect = true;
//                                for (AdapterShoopingcartData data : mList) {
//                                    if (!data.isChecked()) {
//                                        allSelect = false;
//                                    }
//                                }
//                                if (allSelect) {
//                                    mOnSelectAllListener.onSelectAll();
//                                } else {
//                                    mOnCancelSelectAllListener.onCancelSelectAll();
//                                }
//                            }
//                        });
//
//                        data.setChecked(isChecked);
//                        notifyDataSetChanged();
//                    }
//                });
//
//                viewHolderStore.mCheckBoxStore.setChecked(data.isChecked());
//                viewHolderStore.mTextViewStore.setText(data.getStoreName());
//
//                break;
//            }
//            case 1: {
//                ViewHolderGoods viewHolderGoods = null;
//
//                if (null == convertView) {
//                    convertView = LayoutInflater.from(context).inflate(R.layout.adapter_shoppingcart_goods, null);
//
//                    viewHolderGoods = new ViewHolderGoods();
//
//                    viewHolderGoods.mCheckBoxGoods = (CheckBox) convertView.findViewById(R.id.cb_adapter_fragmentshoppingcart_goods);
//                    viewHolderGoods.mImageViewGoods = (ImageView) convertView.findViewById(R.id.iv_adapter_fragmentshoppingcart_goods);
//                    viewHolderGoods.mTextViewGoods = (TextView) convertView.findViewById(R.id.tv_adapter_fragmentshoppingcart_goods);
//                    viewHolderGoods.mTextViewPrice = (TextView) convertView.findViewById(R.id.tv_adapter_fragmentshoppingcart_goods_price);
//                    viewHolderGoods.mTextViewCount = (TextView) convertView.findViewById(R.id.tv_adapter_fragmentshoppingcart_goods_count);
//                    viewHolderGoods.mImageViewAdd = (ImageView) convertView.findViewById(R.id.iv_adapter_fragmentshoppingcart_goods_add);
//                    viewHolderGoods.mImageViewReduce = (ImageView) convertView.findViewById(R.id.iv_adapter_fragmentshoppingcart_goods_reduce);
//
//                    convertView.setTag(viewHolderGoods);
//                } else {
//                    viewHolderGoods = (ViewHolderGoods) convertView.getTag();
//                }
//
//                viewHolderGoods.mCheckBoxGoods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        data.setChecked(isChecked);
//
//                        int chechkedCount = 0, storeidCount = 0;
//
//                        for (AdapterShoopingcartData dataAll : mList) {
//                            if (dataAll.getStoreId().equals(data.getStoreId()) && dataAll.isChecked() && !dataAll.isParent()) {
//                                chechkedCount++;
//                            }
//                            if (dataAll.getStoreId().equals(data.getStoreId())) {
//                                storeidCount++;
//                            }
//                        }
//                        Log.e("Tag", "" + chechkedCount + "," + storeidCount);
//                        if (chechkedCount < (storeidCount - 1)) {
//                            for (AdapterShoopingcartData dataAll : mList) {
//                                if (dataAll.getStoreId().equals(data.getStoreId()) && dataAll.isParent()) {
//                                    dataAll.setChecked(false);
//                                }
//                            }
//                        } else {
//                            for (AdapterShoopingcartData dataAll : mList) {
//                                if (dataAll.getStoreId().equals(data.getStoreId()) && dataAll.isParent()) {
//                                    dataAll.setChecked(true);
//                                }
//                            }
//                        }
//
//                        allSelect = true;
//                        for (AdapterShoopingcartData data : mList) {
//                            if (!data.isChecked()) {
//                                allSelect = false;
//                            }
//                        }
//
//                        if (allSelect) {
//                            mOnSelectAllListener.onSelectAll();
//                        } else {
//                            mOnCancelSelectAllListener.onCancelSelectAll();
//                        }
//
//                        notifyDataSetChanged();
//                        mOnTotalChangedListener.onChanged(calculateTotal());
//                    }
//                });
//
//                viewHolderGoods.mImageViewReduce.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //数据库里减少
//                        mOrderManager.reduceProduct(data.getProductId());
//
//                        //页面减少，只做显示
//                        int count = data.getProductCount();
//                        if (count > 0) {
//                            if (count == 1) {
//                                mOnDeleteCommodityListener.onDeleteCommodity();
//                            }
//                            count--;
//                            data.setProductCount(count);
//                            notifyDataSetChanged();
//                            mOnTotalChangedListener.onChanged(calculateTotal());
//                        }
//                    }
//                });
//
//                viewHolderGoods.mImageViewAdd.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //数据库添加
//                        mOrderManager.addProduct(data.getStoreId(), data.getStoreName(), data.getStoreUrl(), data.getProductId(), data.getProductName()
//                                , data.getProductUrl(), data.getProductPrice() + "", data.getProductWeight(), data.getStartValue(), data.getSendPrice());
//
//                        //页面添加，只做显示
//                        int count = data.getProductCount();
//                        count++;
//                        data.setProductCount(count);
//                        notifyDataSetChanged();
//
//                        mOnTotalChangedListener.onChanged(calculateTotal());
//                    }
//                });
//
//                viewHolderGoods.mCheckBoxGoods.setChecked(data.isChecked());
//                Log.d(">>>购物车图片地址>>>", data.getProductUrl());
//                Picasso.with(context).load(FlagData.FLAG_IMGADDRESS +data.getProductUrl()).into(viewHolderGoods.mImageViewGoods);
//                viewHolderGoods.mTextViewGoods.setText(data.getProductName());
//                viewHolderGoods.mTextViewPrice.setText(decimalFormat.format(data.getProductPrice()));
//                viewHolderGoods.mTextViewCount.setText("" + data.getProductCount());
//                break;
//            }
//            default: {
//                Log.e("Tag", "shoppingcartadapter.default");
//            }
//        }
//        return convertView;
//    }
//
    public static class ViewHolder {
        ImageView ivToShop;//去店铺

    }
}

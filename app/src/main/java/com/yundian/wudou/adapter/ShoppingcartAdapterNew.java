package com.yundian.wudou.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.activity.CommodityDetailsActivity;
import com.yundian.wudou.activity.LoginActivity;
import com.yundian.wudou.activity.SettlementActivity;
import com.yundian.wudou.activity.VegetableShopActivity;
import com.yundian.wudou.data.AdapterShoopingcartData;
import com.yundian.wudou.data.AdapterShoopingcartNewData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.data.StoresinfoData;
import com.yundian.wudou.datawork.OrderManager;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.utils.HorizontalListView;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static android.R.attr.data;
import static android.R.attr.handle;

/**
 * Created by cookie on 2016/8/9.
 */
public class ShoppingcartAdapterNew extends BaseAdapter {


    private Context context;
    private List<AdapterShoopingcartNewData> mList;
    private List<AdapterShoopingcartNewData.ProductsBean.DataBean> teamList = new ArrayList<>();
    private DecimalFormat decimalFormat;
    private SharedpreferencesManager sharedpreferencesManager;
    private List<AdapterShoopingcartData> mListSettlementData;
    private OrderManager orderManager;
    Handler handler;

    public ShoppingcartAdapterNew(Context context, List<AdapterShoopingcartNewData> mList, Handler handler) {
        this.context = context;
        this.mList = mList;
        this.handler = handler;
//        setCategoryList(mList);
        decimalFormat = new DecimalFormat("0.00");
        sharedpreferencesManager = new SharedpreferencesManager(context);
        orderManager = new OrderManager(context);
    }

//    public void setCategoryList(List<AdapterShoopingcartNewData> categoryList) {
//        this.mList = categoryList;
//        for (int i = 0; i < categoryList.size(); i++) {
//            for (int j = 0; j < categoryList.get(i).getProducts().getData().size(); j++) {
//
//                AdapterShoopingcartNewData.ProductsBean.DataBean data = categoryList.get(i).getProducts().getData().get(j);
//
//                teamList.add(data);
//            }
//        }
//        Log.d(">>>>>>>", teamList.size() + ">>>");
//        notifyDataSetChanged();
//    }

    @Override
    public int getCount() {
        return mList.size();
    }

    //
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
        final AdapterShoopingcartNewData data = mList.get(position);
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_shoppingcart_store_new, null);
            viewHolder = new ViewHolder();
            viewHolder.tvShopName = (TextView) convertView.findViewById(R.id.tv_adapter_shop_name);
            viewHolder.ivDele = (ImageView) convertView.findViewById(R.id.iv_dele);
            viewHolder.ivToShop = (ImageView) convertView.findViewById(R.id.iv_adapter_to_shop);
            viewHolder.tvNum = (TextView) convertView.findViewById(R.id.tv_adapter_num);
            viewHolder.tvWeight = (TextView) convertView.findViewById(R.id.tv_adapter_weight);
            viewHolder.tvCommodityAmount = (TextView) convertView.findViewById(R.id.tv_commodity_amount);
            viewHolder.tvCommodityFreight = (TextView) convertView.findViewById(R.id.tv_commodity_freight);
            viewHolder.tvSettlementTotle = (TextView) convertView.findViewById(R.id.tv_adapter_settlement_totle);
            viewHolder.tvSettleAccounts = (TextView) convertView.findViewById(R.id.tv_settle_accounts);
//            viewHolder.listView = (ListView) convertView.findViewById(R.id.lv_adapter_commodity);
            viewHolder.mLinearLayout = (LinearLayout) convertView.findViewById(R.id.ll_adapter_goods);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvShopName.setText(data.getStorename());
        viewHolder.tvNum.setText("共" + data.getProducts().getData().size() + "件");
        viewHolder.tvCommodityAmount.setText("¥" + calculateTotal(position));
        if (Float.parseFloat(calculateTotal(position)) >= Float.parseFloat(mList.get(position).getStartValue())) {
            viewHolder.tvCommodityFreight.setText("¥" + 0.00);
            viewHolder.tvSettlementTotle.setText(calculateTotal(position) + "");
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            viewHolder.tvCommodityFreight.setText("¥" + decimalFormat.format(Float.parseFloat(mList.get(position).getSendPrice())));
            viewHolder.tvSettlementTotle.setText(calculateFreightTotal(position) + "");
        }

        viewHolder.tvSettleAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //去结算
                mListSettlementData = new ArrayList<AdapterShoopingcartData>();
                if (sharedpreferencesManager.isLogin()) {

                    for (int i = 0; i < mList.get(position).getProducts().getData().size(); i++) {
                        AdapterShoopingcartNewData.ProductsBean.DataBean data = mList.get(position).getProducts().getData().get(i);
                        mListSettlementData.add(new AdapterShoopingcartData(true, false, mList.get(position).getStoreId()
                                , mList.get(position).getStorename(), "", data.getProductId(), data.getProductName(), data.getProductUrl(), data.getProductPrice(), data.getProductWeight()
                                , data.getProductCount(), mList.get(position).getStartValue(), mList.get(position).getSendPrice()));
                    }

                    Intent intent = new Intent(context, SettlementActivity.class);
                    intent.putExtra("list", (Serializable) mListSettlementData);
                    context.startActivity(intent);


                } else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
            }
        });
        viewHolder.tvShopName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//去商铺

                Intent intent = new Intent(context, VegetableShopActivity.class);
                Log.d("my", "shop_StoreId: "+data.getStoreId());
                intent.putExtra(FlagData.FLAG_SHOP_ID, data.getStoreId());
//                intent.putExtra(FlagData.FLAG_SHOP_ID, 30);
                context.startActivity(intent);
//
//
//                }
            }
        });
        viewHolder.ivToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//去商铺
                Intent intent = new Intent(context, VegetableShopActivity.class);
                intent.putExtra(FlagData.FLAG_SHOP_ID, data.getStoreId());
                context.startActivity(intent);
//
            }
        });
        viewHolder.ivDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message m = new Message();
                m.what = 0;
                m.arg1 = position;
                handler.sendMessage(m);
            }
        });
        //   final ShoppingCartAdapterItem adapterItem = new ShoppingCartAdapterItem(context, data.getProducts().getData());
        //   viewHolder.listView.setAdapter(adapterItem);
        //  viewHolder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //     @Override
        //   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //          Intent intent = new Intent(context, CommodityDetailsActivity.class);
        //           intent.putExtra(FlagData.FLAG_PRODUCT_ID, adapterItem.getData().get(i).getProductId());
        //           context.startActivity(intent);
        //       }
        //   });
        viewHolder.mLinearLayout.removeAllViews();
        for (int i = 0; i < data.getProducts().getData().size(); i++) {
            String url=data.getProducts().getData().get(i).getProductUrl();
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(3, 3, 3, 3);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            if (url.contains("http")) {

                Picasso.with(context).load(url).into(imageView);
            } else {
                Picasso.with(context).load(FlagData.FLAG_IMGADDRESS + url).into(imageView);

            }
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CommodityDetailsActivity.class);
                               intent.putExtra(FlagData.FLAG_PRODUCT_ID, data.getProducts().getData().get(finalI).getProductId());
                               context.startActivity(intent);
                }
            });
            viewHolder.mLinearLayout.addView(imageView);
        }
        return convertView;
    }


    public static class ViewHolder {
        ImageView ivToShop;//去店铺
        ImageView ivDele;//删除
        TextView tvShopName;//店铺名称
        TextView tvNum;// 几件商品
        TextView tvWeight;//重量
        TextView tvCommodityAmount;//商品价格
        TextView tvCommodityFreight;//运费价格
        TextView tvSettlementTotle;//共计
        TextView tvSettleAccounts;//去结算
        //   ListView listView;//商品列表
        LinearLayout mLinearLayout;
    }

    //
//    public String calculateFreight(int position) {
//
//        double total = 0;
//
//        for (int j = 0; j < mList.get(position).getProducts().getData().size(); j++) {
//
//            AdapterShoopingcartNewData.ProductsBean.DataBean data = mList.get(position).getProducts().getData().get(j);
//
//            total += Float.valueOf(data.getSendPrice());
//        }
//
//        DecimalFormat decimalFormat = new DecimalFormat("0.00");
//        return decimalFormat.format(total);
//
//    }

    //计算总价格
    public String calculateTotal(int position) {
        double total = 0;
        for (int j = 0; j < mList.get(position).getProducts().getData().size(); j++) {

            AdapterShoopingcartNewData.ProductsBean.DataBean data = mList.get(position).getProducts().getData().get(j);

            total += data.getProductCount() * data.getProductPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(total);
    }

    //计算总价格
    public String calculateFreightTotal(int position) {
        double total = 0;
        for (int j = 0; j < mList.get(position).getProducts().getData().size(); j++) {

            AdapterShoopingcartNewData.ProductsBean.DataBean data = mList.get(position).getProducts().getData().get(j);

            total += data.getProductCount() * data.getProductPrice();
        }
        total += Float.parseFloat(mList.get(position).getSendPrice());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(total);
    }


}

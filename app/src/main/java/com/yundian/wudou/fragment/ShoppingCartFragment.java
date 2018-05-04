package com.yundian.wudou.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.activity.CommodityDetailsActivity;
import com.yundian.wudou.activity.LoginActivity;
import com.yundian.wudou.activity.SettlementActivity;
import com.yundian.wudou.activity.VegetableShopActivity;
import com.yundian.wudou.adapter.ShoppingcartAdapter;
import com.yundian.wudou.adapter.ShoppingcartAdapterNew;
import com.yundian.wudou.data.AdapterShoopingcartData;
import com.yundian.wudou.data.AdapterShoopingcartNewData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.data.ShoppingCartData;
import com.yundian.wudou.datawork.OrderManager;
import com.yundian.wudou.datawork.SharedpreferencesManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cookie on 2016/7/6.
 */
public class ShoppingCartFragment extends Fragment {

    @Bind(R.id.rl_fragment_shoppingcart_nodata)
    RelativeLayout rlNodata;
    @Bind(R.id.tv_fragmentshoppingcart_delete_selected)
    TextView tvDeleteSelected;
    @Bind(R.id.lv_fragmentshoppingcart)
    ListView mListViewShoppingCart;

    private SharedpreferencesManager sharedpreferencesManager;
    private OrderManager orderManager;
    private ShoppingcartAdapterNew mShoppingCartAdapter;

    private List<AdapterShoopingcartData> mListAdapterShoopingcartData;
    private List<AdapterShoopingcartNewData> mList;
    private List<AdapterShoopingcartData> mListSettlementData;

    private String storeId, productId;
    private int state;
    private boolean isCallback;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    showMessageDialog("删除所选中的商品?", msg.arg1);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoppingcart, container, false);

        initialize(view);

        setEventListener();

        return view;
    }

    private void initialize(View view) {
        ButterKnife.bind(ShoppingCartFragment.this, view);
        sharedpreferencesManager = new SharedpreferencesManager(getContext());

        mListSettlementData = new ArrayList<>();
        mListAdapterShoopingcartData = new ArrayList<>();
        mList = new ArrayList<>();

        mShoppingCartAdapter = new ShoppingcartAdapterNew(getContext(), mList, handler);
        mListViewShoppingCart.setAdapter(mShoppingCartAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();


        mListAdapterShoopingcartData.clear();
        mList.clear();
        if (orderManager.getCartList().size() > 0) {

            AdapterShoopingcartNewData newData = null;
            AdapterShoopingcartNewData.ProductsBean productsBean = null;
            List<AdapterShoopingcartNewData.ProductsBean.DataBean> dataBeanList = null;
            for (AdapterShoopingcartData data : orderManager.getCartList()) {
                mListAdapterShoopingcartData.add(data);
                if (data.getType() == 0) {
                    newData = new AdapterShoopingcartNewData();
                    productsBean = new AdapterShoopingcartNewData.ProductsBean();
                    dataBeanList = new ArrayList<>();
                    newData.setStorename(data.getStoreName());
                    newData.setStoreId(data.getStoreId());
                    newData.setStartValue(data.getStartValue());
                    newData.setSendPrice(data.getSendPrice());
                } else {
                    AdapterShoopingcartNewData.ProductsBean.DataBean dataBean = new AdapterShoopingcartNewData.ProductsBean.DataBean();

                    dataBean.setProductWeight(data.getProductWeight());
                    dataBean.setProductUrl(data.getProductUrl());
                    dataBean.setProductPrice(data.getProductPrice());
                    dataBean.setProductId(data.getProductId());
                    dataBean.setProductName(data.getProductName());

                    dataBean.setProductCount(data.getProductCount());
                    dataBeanList.add(dataBean);
                    productsBean.setData(dataBeanList);
                    newData.setProducts(productsBean);
                    mList.add(newData);
                }
            }
            Set set = new HashSet();
            List newList = new ArrayList();
            for (Iterator iter = mList.iterator(); iter.hasNext(); ) {
                Object element = iter.next();
                if (set.add(element))
                    newList.add(element);
            }
            mList.clear();
            mList.addAll(newList);
            Log.d(">>>>>", "新列表" + mList.size());


        }

        mShoppingCartAdapter.notifyDataSetChanged();

        if (mListAdapterShoopingcartData.size() == 0) {
            rlNodata.setVisibility(View.VISIBLE);
        } else {
            rlNodata.setVisibility(View.GONE);
        }
    }

    private void setEventListener() {
//        mListViewShoppingCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                AdapterShoopingcartData data = mListAdapterShoopingcartData.get(position);
//                if (data.isParent()) {
//                    Intent intent = new Intent(getActivity(), VegetableShopActivity.class);
//                    intent.putExtra(FlagData.FLAG_SHOP_ID, data.getStoreId());
//                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
//                    intent.putExtra(FlagData.FLAG_PRODUCT_ID, data.getProductId());
//                    startActivity(intent);
//                }
//            }
//        });
//
//        mListViewShoppingCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                AdapterShoopingcartData data = mListAdapterShoopingcartData.get(position);
//                if (data.isParent()) {
//                    state = 1;
//                    storeId = data.getStoreId();
//                    showMessageDialog("确定移除此店铺的所有商品？");
//                } else {
//                    state = 2;
//                    productId = data.getProductId();
//                    showMessageDialog("确定移除此商品？");
//                }
//                return true;
//            }
//        });

//        tvDeleteSelected.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                state = 3;
//                showMessageDialog("删除选中的所有商品");
//            }
//        });

//        cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
//                if (checked) {
//                    for (AdapterShoopingcartData data : mListAdapterShoopingcartData) {
//                        data.setChecked(true);
//                    }
//                } else {
//                    if (!isCallback) {
//                        for (AdapterShoopingcartData data : mListAdapterShoopingcartData) {
//                            data.setChecked(false);
//                        }
//                    }
//                }
//                mShoppingCartAdapter.notifyDataSetChanged();
//            }
//        });
//

    }

    private void showMessageDialog(String msg, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                orderManager.reduceProductBySid(mList.get(position).getStoreId());
                for (int i = 0; i < mList.get(position).getProducts().getData().size(); i++) {
                    orderManager.removeProduct(mList.get(position).getProducts().getData().get(i).getProductId());
                }
                mList.remove(mList.get(position));
                if (mList.size() == 0) {
                    rlNodata.setVisibility(View.VISIBLE);
                } else {
                    rlNodata.setVisibility(View.GONE);
                }
                mShoppingCartAdapter.notifyDataSetChanged();
            }

        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        orderManager = new OrderManager(context);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mListAdapterShoopingcartData.clear();
            mList.clear();
            if (orderManager.getCartList().size() > 0) {
                AdapterShoopingcartNewData newData = null;
                AdapterShoopingcartNewData.ProductsBean productsBean = null;
                List<AdapterShoopingcartNewData.ProductsBean.DataBean> dataBeanList = null;
                for (AdapterShoopingcartData data : orderManager.getCartList()) {
                    mListAdapterShoopingcartData.add(data);
                    if (data.getType() == 0) {
                        newData = new AdapterShoopingcartNewData();
                        productsBean = new AdapterShoopingcartNewData.ProductsBean();
                        dataBeanList = new ArrayList<>();
                        newData.setStorename(data.getStoreName());
                        newData.setStoreId(data.getStoreId());
                        newData.setSendPrice(data.getSendPrice());
                    } else {
                        AdapterShoopingcartNewData.ProductsBean.DataBean dataBean = new AdapterShoopingcartNewData.ProductsBean.DataBean();
                        newData.setStartValue(data.getStartValue());
                        dataBean.setProductWeight(data.getProductWeight());
                        dataBean.setProductUrl(data.getProductUrl());
                        dataBean.setProductPrice(data.getProductPrice());
                        dataBean.setProductId(data.getProductId());
                        dataBean.setProductName(data.getProductName());

                        dataBean.setProductCount(data.getProductCount());
                        dataBeanList.add(dataBean);
                        productsBean.setData(dataBeanList);
                        newData.setProducts(productsBean);
                        mList.add(newData);
                    }
                }
                Set set = new HashSet();
                List newList = new ArrayList();
                for (Iterator iter = mList.iterator(); iter.hasNext(); ) {
                    Object element = iter.next();
                    if (set.add(element))
                        newList.add(element);
                }
                mList.clear();
                mList.addAll(newList);
                Log.d(">>>>>", "新列表" + mList.size());
            }
            for (int i = 0; i < mListAdapterShoopingcartData.size(); i++) {
                AdapterShoopingcartData data = mListAdapterShoopingcartData.get(i);
                Log.e("tag", "---------->>storename=" + data.getStoreName() + ",storeId=" + data.getStoreId() + ",productName=" + data.getProductName() +
                        ",productId=" + data.getProductId() + ",productUrl=" + data.getProductUrl() + ",productPrice=" + data.getProductPrice() +
                        ",productCount=" + data.getProductCount() + ",type" + data.getType() + ",isParent=" + data.isParent() + ",isChecked=" + data.isChecked() +
                        ",productWeight=" + data.getProductWeight() + ",startValue=" + data.getStartValue() + ",sendPrice=" + data.getSendPrice());
            }
            for (ShoppingCartData data : orderManager.getShoppingCartDataList()) {
                Log.e("tag", "----------原始数据>>storename=" + data.getStoreName() + ",storeId=" + data.getStoreId() + ",productName=" + data.getProductName() +
                        ",productId=" + data.getProductId() + ",productUrl=" + data.getProductUrl() + ",productPrice=" + data.getProductPrice() +
                        ",productCount=" + data.getProductCount() + ",productWeight=" + data.getProductWeight() + ",startValue=" + data.getStartValue() + ",sendPrice=" + data.getSendPrice());
            }
            mShoppingCartAdapter.notifyDataSetChanged();
        }

        if (mListAdapterShoopingcartData.size() == 0) {
            rlNodata.setVisibility(View.VISIBLE);
        } else {
            rlNodata.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        orderManager.close();
        super.onDestroy();
    }
}

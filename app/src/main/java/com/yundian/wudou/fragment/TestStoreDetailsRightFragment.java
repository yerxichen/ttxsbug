package com.yundian.wudou.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import com.yundian.wudou.R;

import com.yundian.wudou.activity.CommodityDetailsActivity;
import com.yundian.wudou.activity.VegetableShopActivity;

import com.yundian.wudou.adapter.TestStoreDetailsRightAdapter;
import com.yundian.wudou.customview.DividerItemDecoration;
import com.yundian.wudou.data.AdapterStoreDetailsRightData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanStoreDetailsCommodityData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by cookie on 2016/8/11.
 */
public class TestStoreDetailsRightFragment extends Fragment implements NetWorkInterface.OnGetStoreDetailsCommodityDataListener {

    private VegetableShopActivity storeDetailsActivity;
    private List<AdapterStoreDetailsRightData> mListAdapterStoreDetailsRightData;
    private RecyclerView mListViewStoreRight;
    private TestStoreDetailsRightAdapter mTestStoreDetailsRightAdapter;
    private TextView tvTitle;
    private SharedpreferencesManager sharedpreferencesManager;
    private NetWorkOperate netWorkOperate;
    private String sate, strToken, storeId, storeName, storeUrl, startValue, sendPrice, cateId, cateName;


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_store_right_test, container, false);

        initialize(viewRoot);

        setEventListener();

        return viewRoot;
    }

    private void initialize(View view) {
        storeDetailsActivity = (VegetableShopActivity) getActivity();
        tvTitle = (TextView) view.findViewById(R.id.tv_activity_store_right);

        mListAdapterStoreDetailsRightData = new ArrayList<>();
        mListViewStoreRight = (RecyclerView) view.findViewById(R.id.lv_activity_storedetails_right_test);

        mListViewStoreRight.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListViewStoreRight.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mTestStoreDetailsRightAdapter = new TestStoreDetailsRightAdapter(getContext(), mListAdapterStoreDetailsRightData);

        mListViewStoreRight.setAdapter(mTestStoreDetailsRightAdapter);

        sharedpreferencesManager = new SharedpreferencesManager(getContext());
        //  strToken = sharedpreferencesManager.getToken();
        strToken = "2B579CEC19B6184D9D04734041BE3132048EBAF5045D49A6A6F5EA1E4B47BDB3";
        netWorkOperate = new NetWorkOperate(TestStoreDetailsRightFragment.this);
    }

    private void setEventListener() {
        mTestStoreDetailsRightAdapter.setItemAddListener(new TestStoreDetailsRightAdapter.StoreItemAddListener() {
            @Override
            public void storeItemAddListener() {
                //改变店铺详情底部状态
                storeDetailsActivity.refreshBottom();
            }
        });
        mTestStoreDetailsRightAdapter.setItemReduceListener(new TestStoreDetailsRightAdapter.StoreItemReduceListener() {
            @Override
            public void storeItemReduceListener() {
                //改变店铺详情底部状态
                storeDetailsActivity.refreshBottom();
            }
        });
        mTestStoreDetailsRightAdapter.setOnItemClickLitener(new TestStoreDetailsRightAdapter.SetOnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), CommodityDetailsActivity.class);
                intent.putExtra(FlagData.FLAG_PRODUCT_ID, mListAdapterStoreDetailsRightData.get(position).getProductId());
                getContext().startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    /**
     * 对外的方法,传入cateid和catename
     */
    public void setCateId(String cateid, String catename, String sate) {
        this.cateId = cateid;
        this.cateName = catename;
        this.sate = sate;

        mTestStoreDetailsRightAdapter.setStoreId(storeId);
        mTestStoreDetailsRightAdapter.setStoreName(storeName);
        mTestStoreDetailsRightAdapter.setStoreUrl(storeUrl);
        mTestStoreDetailsRightAdapter.setStartValue(startValue);
        mTestStoreDetailsRightAdapter.setSendPrice(sendPrice);

        netWorkOperate.getStoreDetailsCommodityData(strToken, storeId, cateId, "", sate);
    }

    @Override
    public void onGetStoreDetailsCommodityData(JsonBeanStoreDetailsCommodityData jsonBeanStoreDetailsCommodityData) {
        mListAdapterStoreDetailsRightData.clear();
        // 得到店铺详情商品的数据
        for (JsonBeanStoreDetailsCommodityData.DataBean dataBean : jsonBeanStoreDetailsCommodityData.getData()) {
            mListAdapterStoreDetailsRightData.add(new AdapterStoreDetailsRightData(dataBean.getPid(), FlagData.FLAG_IMGADDRESS + dataBean.getImg(),
                    dataBean.getName(), dataBean.getShopprice(), "0"));
        }
        tvTitle.setText(cateName + "(" + mListAdapterStoreDetailsRightData.size() + ")");
        mTestStoreDetailsRightAdapter.notifyDataSetChanged();
        storeDetailsActivity.refreshBottom();
    }

    @Override
    public void onFailure(String msg) {
        tvTitle.setText("");
        mListAdapterStoreDetailsRightData.clear();
        mTestStoreDetailsRightAdapter.notifyDataSetChanged();
    }

    /**
     * 对外的方法,刷新adapter
     */
    public void refreshFragment() {
        netWorkOperate.getStoreDetailsCommodityData(strToken, storeId, cateId, "", sate);
    }
}


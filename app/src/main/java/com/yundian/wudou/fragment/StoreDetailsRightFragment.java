package com.yundian.wudou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.activity.CommodityDetailsActivity;
import com.yundian.wudou.activity.StoreDetailsActivity;
import com.yundian.wudou.activity.VegetableShopActivity;
import com.yundian.wudou.adapter.StoreDetailsRightAdapter;
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
public class StoreDetailsRightFragment extends Fragment implements NetWorkInterface.OnGetStoreDetailsCommodityDataListener {

    private StoreDetailsActivity storeDetailsActivity;

    private List<AdapterStoreDetailsRightData> mListAdapterStoreDetailsRightData;
    private ListView mListViewStoreRight;
    private StoreDetailsRightAdapter mStoreDetailsRightAdapter;

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

        View viewRoot = inflater.inflate(R.layout.fragment_store_right, container, false);

        initialize(viewRoot);

        setEventListener();

        return viewRoot;
    }

    private void initialize(View view) {
        storeDetailsActivity = (StoreDetailsActivity) getActivity();
        tvTitle = (TextView) view.findViewById(R.id.tv_activity_store_right);

        mListAdapterStoreDetailsRightData = new ArrayList<>();
        mListViewStoreRight = (ListView) view.findViewById(R.id.lv_activity_storedetails_right);
        mStoreDetailsRightAdapter = new StoreDetailsRightAdapter(getContext(), mListAdapterStoreDetailsRightData);
        mListViewStoreRight.setAdapter(mStoreDetailsRightAdapter);

        sharedpreferencesManager = new SharedpreferencesManager(getContext());
        strToken = sharedpreferencesManager.getToken();

        netWorkOperate = new NetWorkOperate(StoreDetailsRightFragment.this);
    }

    private void setEventListener() {
        mStoreDetailsRightAdapter.setItemAddListener(new StoreDetailsRightAdapter.StoreItemAddListener() {
            @Override
            public void storeItemAddListener() {
                //改变店铺详情底部状态
                  storeDetailsActivity.refreshBottom();
            }
        });
        mStoreDetailsRightAdapter.setItemReduceListener(new StoreDetailsRightAdapter.StoreItemReduceListener() {
            @Override
            public void storeItemReduceListener() {
                //改变店铺详情底部状态
                  storeDetailsActivity.refreshBottom();
            }
        });

        mListViewStoreRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), CommodityDetailsActivity.class);
                intent.putExtra(FlagData.FLAG_PRODUCT_ID, mListAdapterStoreDetailsRightData.get(position).getProductId());
                getContext().startActivity(intent);
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

        mStoreDetailsRightAdapter.setStoreId(storeId);
        mStoreDetailsRightAdapter.setStoreName(storeName);
        mStoreDetailsRightAdapter.setStoreUrl(storeUrl);
        mStoreDetailsRightAdapter.setStartValue(startValue);
        mStoreDetailsRightAdapter.setSendPrice(sendPrice);

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
        mStoreDetailsRightAdapter.notifyDataSetChanged();
        //  storeDetailsActivity.refreshBottom();
    }

    @Override
    public void onFailure(String msg) {
        tvTitle.setText("");
        mListAdapterStoreDetailsRightData.clear();
        mStoreDetailsRightAdapter.notifyDataSetChanged();
    }

    /**
     * 对外的方法,刷新adapter
     */
    public void refreshFragment() {
        netWorkOperate.getStoreDetailsCommodityData(strToken, storeId, cateId, "", sate);
    }
}


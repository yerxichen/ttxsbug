package com.yundian.wudou.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterClassificationLeftData;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {
    private TestStoreDetailsLeftFragment mStoreDetailsLeftFragment;
    private TestStoreDetailsRightFragment mStoreDetailsRightFragment;
    //左边的列表
    private List<AdapterClassificationLeftData> mListAdapterStoreDetailsLeftData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        {
            View view = inflater.inflate(R.layout.fragment_first, container, false);
            mStoreDetailsLeftFragment = new TestStoreDetailsLeftFragment();
            mStoreDetailsRightFragment = new TestStoreDetailsRightFragment();
            FragmentManager mFragmentManager = getChildFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.add(R.id.fragment_container_activitystoredetails_left, mStoreDetailsLeftFragment);
            mFragmentTransaction.add(R.id.fragment_container_activitystoredetails_right, mStoreDetailsRightFragment);
            mFragmentTransaction.commit();
            mListAdapterStoreDetailsLeftData = new ArrayList<>();
            return view;
        }
    }

    public void setLeftFragmentData(List<AdapterClassificationLeftData> LeftData) {
        mListAdapterStoreDetailsLeftData = LeftData;
        mStoreDetailsLeftFragment.update(mListAdapterStoreDetailsLeftData);
    }

    public void setStoreId(String storeId) {
        mStoreDetailsRightFragment.setStoreId(storeId);
    }

    public void setStoreName(String storeName) {
        mStoreDetailsRightFragment.setStoreName(storeName);
    }

    public void setStoreUrl(String storeUrl) {
        mStoreDetailsRightFragment.setStoreUrl(storeUrl);
    }

    public void setStartValue(String startValue) {
        mStoreDetailsRightFragment.setStartValue(startValue);
    }

    public void setSendPrice(String sendPrice) {
        mStoreDetailsRightFragment.setSendPrice(sendPrice);
    }

    /**
     * 对外的方法,传入cateid和catename
     */
    public void setCateId(String cateid, String catename, String sate) {
        mStoreDetailsRightFragment.setCateId(cateid, catename, sate);
    }
    /**
     * 对外的方法,刷新adapter
     */
    public void refreshFragment() {
        mStoreDetailsRightFragment.refreshFragment();

    }
}

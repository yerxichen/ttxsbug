package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.ReceiveCouponsAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterReceiveCouponsData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanReceiveCoupons;
import com.yundian.wudou.network.JsonBeanStoreCoupons;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceiveCouponsActivity extends BaseActivity implements NetWorkInterface.OnGetStoreCouponsListener, NetWorkInterface.OnGetReceiveCouponsListener{

    @Bind(R.id.lv_activity_receivecoupons)ListView listView;
    @Bind(R.id.prl_activity_receivecoupons)PullToRefreshLayout refreshLayout;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private ReceiveCouponsAdapter adapter;
    private List<AdapterReceiveCouponsData> list;

    private String token,storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recevie_coupon);

        initialize();

        setEventListener();
    }

    private void initialize(){
        ButterKnife.bind(ReceiveCouponsActivity.this);
        manager = new SharedpreferencesManager(ReceiveCouponsActivity.this);
        netWorkOperate = new NetWorkOperate(ReceiveCouponsActivity.this);

        setTitle("店铺优惠券");
        setBackVisibility(true);

        Intent intent = getIntent();
        storeId = intent.getStringExtra(FlagData.FLAG_SHOP_ID);

        list = new ArrayList<>();
        adapter = new ReceiveCouponsAdapter(ReceiveCouponsActivity.this, list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        token = manager.getToken();
        netWorkOperate.getStoreCoupons(token,storeId);
    }

    private  void setEventListener(){
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       refreshLayout.finishRefresh();
                    }
                },2000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                    }
                },2000);
            }
        });

        adapter.setOnStatusClickListener(new ReceiveCouponsAdapter.OnStatusClickListener() {
            @Override
            public void onStatusClick(int position) {
                netWorkOperate.getReceiveCoupons(token,list.get(position).getCouponid());
            }
        });
    }

    @Override
    public void onGetStoreCoupons(JsonBeanStoreCoupons jsonBeanStoreCoupons) {
        for(JsonBeanStoreCoupons.DataBean dataBean : jsonBeanStoreCoupons.getData()){
            list.add(new AdapterReceiveCouponsData(dataBean.getCouponid(),dataBean.getCouponsn(),
                    "满"+dataBean.getOrderamountlower()+"可以使用",dataBean.getUsestarttime(),dataBean.getUseendtime(),
                    Float.parseFloat(dataBean.getMoney()),dataBean.getSate()));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(ReceiveCouponsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetReceiveCoupons(JsonBeanReceiveCoupons jsonBeanReceiveCoupons) {
        Toast.makeText(ReceiveCouponsActivity.this, jsonBeanReceiveCoupons.getMsg(), Toast.LENGTH_SHORT).show();
        list.clear();
        netWorkOperate.getStoreCoupons(token,storeId);
    }
}

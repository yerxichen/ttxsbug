package com.yundian.wudou.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.yundian.wudou.R;
import com.yundian.wudou.adapter.RegionAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterRegionData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanRegionalData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegionActivity extends BaseActivity implements NetWorkInterface.OnGetRegionalDataListener{

    @Bind(R.id.rv_activity_region)RecyclerView recyclerView;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private List<AdapterRegionData> list;
    private RegionAdapter regionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        initialize();
    }

    private void initialize() {
        ButterKnife.bind(RegionActivity.this);
        manager = new SharedpreferencesManager(RegionActivity.this);
        netWorkOperate = new NetWorkOperate(RegionActivity.this);

        this.setBackVisibility(true);
        this.setTitle(getString(R.string.region));

        list = new ArrayList<>();
        regionAdapter = new RegionAdapter(list, RegionActivity.this);
        recyclerView.setLayoutManager(new GridLayoutManager(RegionActivity.this,3));
        recyclerView.setAdapter(regionAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        netWorkOperate.getRegionalData(manager.getToken());
    }

    @Override
    public void onGetRegionalData(JsonBeanRegionalData jsonBeanRegionalData) {
        list.clear();
        for(JsonBeanRegionalData.DataBean dataBean:jsonBeanRegionalData.getData()){
            list.add(new AdapterRegionData(dataBean.getCode(),dataBean.getName()));
        }
        regionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(RegionActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}

package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.DailySaleAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterDailySaleData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanDailySale;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeekSaleActivity extends BaseActivity implements NetWorkInterface.OnGetDailySaleListener {

    @Bind(R.id.prl_activity_weeksale)PullToRefreshLayout refreshLayout;
    @Bind(R.id.lv_activity_weeksale)ListView listView;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private List<AdapterDailySaleData> mDailySaleDatas;
    private DailySaleAdapter mDailySaleAdapter;

    private boolean hasMore = true;
    private int currentpage = 1;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeksale);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(WeekSaleActivity.this);
        manager = new SharedpreferencesManager(WeekSaleActivity.this);
        netWorkOperate = new NetWorkOperate(WeekSaleActivity.this);

        setTitle(R.string.week_sale);
        setBackVisibility(true);

        mDailySaleDatas = new ArrayList<>();
        mDailySaleAdapter = new DailySaleAdapter(WeekSaleActivity.this, mDailySaleDatas);
        listView.setAdapter(mDailySaleAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDailySaleDatas.clear();
        currentpage = 1;
        hasMore = true;
        token = manager.getToken();
        netWorkOperate.getDailySaleData(token, currentpage + "");
    }

    private void setEventListener() {
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        mDailySaleDatas.clear();
                        currentpage = 1;
                        hasMore = true;
                        netWorkOperate.getDailySaleData(token, currentpage + "");
                    }
                },2000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                        if(hasMore){
                            currentpage++;
                        }
                        netWorkOperate.getDailySaleData(token, currentpage + "");
                    }
                },2000);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(WeekSaleActivity.this, CommodityDetailsActivity.class);
                intent.putExtra(FlagData.FLAG_PRODUCT_ID, mDailySaleDatas.get(i).getProductId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onGetDailySale(JsonBeanDailySale jsonBeanDailySale) {
        for (JsonBeanDailySale.DataBean data : jsonBeanDailySale.getData()) {
            mDailySaleDatas.add(new AdapterDailySaleData(data.getStoreid(), data.getStorename(), data.getPid(), FlagData.FLAG_IMGADDRESS + data.getImg(), data.getName(), data.getShopprice()));
        }
        mDailySaleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        hasMore = false;
        Toast.makeText(WeekSaleActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}

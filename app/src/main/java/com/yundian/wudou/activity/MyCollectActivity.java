package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.CollectShopAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapteCollectShopData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanMyFavoriteData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyCollectActivity extends BaseActivity implements NetWorkInterface.OnGetMyFavoriteListener {

    @Bind(R.id.prl_activity_collect)
    PullToRefreshLayout refreshLayout;
    @Bind(R.id.rl_activity_collect_nodata)
    RelativeLayout rlNoData;
    @Bind(R.id.tv_activity_collect_nodata)
    TextView tvNoData;
    @Bind(R.id.lv_activity_collect)
    ListView listView;

    private List<AdapteCollectShopData> mListAdapterCollectShopData;
    private CollectShopAdapter mCollectShopAdapter;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private List<String> mListUrltype = new ArrayList<>();
    private List<String> mListurl = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        initialize();

        setEventListener();
    }

    protected void onResume() {
        super.onResume();
        mListAdapterCollectShopData.clear();
        mListUrltype.clear();
        mListurl.clear();
        netWorkOperate.getMyFavorite(manager.getToken());
    }

    private void initialize() {
        ButterKnife.bind(MyCollectActivity.this);
        refreshLayout.setCanLoadMore(false);
        manager = new SharedpreferencesManager(MyCollectActivity.this);
        netWorkOperate = new NetWorkOperate(MyCollectActivity.this);

        this.setTitle(R.string.my_collection);
        this.setBackVisibility(true);

        mListAdapterCollectShopData = new ArrayList<>();
        mCollectShopAdapter = new CollectShopAdapter(this, mListAdapterCollectShopData);
        listView.setAdapter(mCollectShopAdapter);
    }

    private void setEventListener() {
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        mListAdapterCollectShopData.clear();
                        mListUrltype.clear();
                        mListurl.clear();
                        netWorkOperate.getMyFavorite(manager.getToken());
                    }
                }, 2000);
            }

            @Override
            public void loadMore() {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (Integer.parseInt(mListUrltype.get(position)) == 2) {
                    String url = mListurl.get(position);
                    Intent intent = new Intent(MyCollectActivity.this, CommodityDetailsActivity.class);
                    intent.putExtra(FlagData.FLAG_PRODUCT_ID, url);
                    startActivity(intent);
                } else if (Integer.parseInt(mListUrltype.get(position)) == 1) {
                    String url = mListurl.get(position);
                    Intent intent = new Intent(MyCollectActivity.this, VegetableShopActivity.class);
                    intent.putExtra(FlagData.FLAG_SHOP_ID, url);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onGetMyFavoriteData(JsonBeanMyFavoriteData jsonBeanMyFavoriteData) {
        rlNoData.setVisibility(View.GONE);
        for (JsonBeanMyFavoriteData.DataBean dataBean : jsonBeanMyFavoriteData.getData()) {
            mListAdapterCollectShopData.add(new AdapteCollectShopData(FlagData.FLAG_IMGADDRESS + dataBean.getImg(), dataBean.getName(),
                    dataBean.getProductcount(), dataBean.getMonthlysales(), Float.parseFloat(dataBean.getStarcount())));
            mListurl.add(dataBean.getUrl());
            mListUrltype.add(dataBean.getUrltype());
        }
        mCollectShopAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        mListAdapterCollectShopData.clear();
        mListUrltype.clear();
        mListurl.clear();
        mCollectShopAdapter.notifyDataSetChanged();
        rlNoData.setVisibility(View.VISIBLE);
        tvNoData.setText(msg);
    }
}

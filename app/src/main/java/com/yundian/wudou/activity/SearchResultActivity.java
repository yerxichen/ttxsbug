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
import com.yundian.wudou.adapter.SearchResultListViewAdapter;
import com.yundian.wudou.adapter.SearchResultRecycleViewAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterSearchResultListViewData;
import com.yundian.wudou.data.AdapterSearchResultRecycleViewData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanSearhResultData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultActivity extends BaseActivity implements NetWorkInterface.OnGetSearchDataListener {

    @Bind(R.id.prl_activity_searchresult)
    PullToRefreshLayout refreshLayout;
    @Bind(R.id.rl_activity_searchresult_nodata)
    RelativeLayout rlNoData;
    @Bind(R.id.tv_activity_searchresult_nodata)
    TextView tvNoData;
    @Bind(R.id.tv_activity_searchresult_title)
    TextView tvTitle;
    @Bind(R.id.lv_activity_searchresult)
    ListView listView;

    private List<AdapterSearchResultListViewData> mAdapterSearchResultListViewDataList;
    private List<AdapterSearchResultRecycleViewData> mAdapterSearchResultRecycleViewDataList;
    private SearchResultListViewAdapter mSearchResultListViewAdapter;
    private SearchResultRecycleViewAdapter mSearchResultRecycleViewAdapter;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private String searchText, cateNumber, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(SearchResultActivity.this);
        refreshLayout.setCanLoadMore(false);
        manager = new SharedpreferencesManager(SearchResultActivity.this);
        netWorkOperate = new NetWorkOperate(SearchResultActivity.this);

        this.setBackVisibility(true);
        this.setTitle(R.string.search_result);

        Intent intent = getIntent();
        searchText = intent.getStringExtra(FlagData.FLAG_SEARCH_TEXT);
        cateNumber = intent.getStringExtra(FlagData.FLAG_SEARCH_CATENUMBER);
        state = intent.getStringExtra(FlagData.FLAG_STATE);

        mAdapterSearchResultListViewDataList = new ArrayList<>();
        mSearchResultListViewAdapter = new SearchResultListViewAdapter(SearchResultActivity.this, mAdapterSearchResultListViewDataList);
        listView.setAdapter(mSearchResultListViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (state.equals("1")) {
            netWorkOperate.getSearchData(manager.getToken(), searchText, state);
        } else if (state.equals("2")) {
            netWorkOperate.getSearchData(manager.getToken(), cateNumber, state);
        }
    }

    private void setEventListener() {
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        if (state.equals("1")) {
                            netWorkOperate.getSearchData(manager.getToken(), searchText, state);
                        } else if (state.equals("2")) {
                            netWorkOperate.getSearchData(manager.getToken(), cateNumber, state);
                        }
                    }
                }, 2000);

            }

            @Override
            public void loadMore() {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchResultActivity.this, VegetableShopActivity.class);
                intent.putExtra(FlagData.FLAG_SHOP_ID, mAdapterSearchResultListViewDataList.get(i).getStoreid());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onGetSearchData(JsonBeanSearhResultData jsonBeanSearhResultData) {

        rlNoData.setVisibility(View.GONE);
        tvTitle.setVisibility(View.VISIBLE);

        mAdapterSearchResultListViewDataList.clear();
        tvTitle.setText(jsonBeanSearhResultData.getProductscount() + "个商品" + jsonBeanSearhResultData.getStorescount() + "个门店");

        for (JsonBeanSearhResultData.DataBean dataBean : jsonBeanSearhResultData.getData()) {
            mAdapterSearchResultRecycleViewDataList = new ArrayList<>();
            for (AdapterSearchResultRecycleViewData data : dataBean.getStores_products().getSubdata()) {
                AdapterSearchResultRecycleViewData adapterSearchResultRecycleViewData = new AdapterSearchResultRecycleViewData(
                        data.getPid(), FlagData.FLAG_IMGADDRESS + data.getImg(), data.getName(), data.getShopprice());
                mAdapterSearchResultRecycleViewDataList.add(adapterSearchResultRecycleViewData);
            }
            mSearchResultRecycleViewAdapter = new SearchResultRecycleViewAdapter(SearchResultActivity.this, mAdapterSearchResultRecycleViewDataList);
            AdapterSearchResultListViewData mData = new AdapterSearchResultListViewData(dataBean.getStoreid(), FlagData.FLAG_IMGADDRESS + dataBean.getImg(),
                    dataBean.getName(), Float.parseFloat(dataBean.getStartfee()), Float.parseFloat(dataBean.getStartvalue()), mSearchResultRecycleViewAdapter);
            mAdapterSearchResultListViewDataList.add(mData);
        }
        mSearchResultListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        rlNoData.setVisibility(View.VISIBLE);
        tvNoData.setText(msg);
        tvTitle.setVisibility(View.GONE);
    }
}

package com.yundian.wudou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.SecondHandDivisionAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterSecondHandDivisionData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanSecondHandDivision;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SecondHandCommodityActivity extends BaseActivity implements NetWorkInterface.OnGetSecondHandDivisionListener {

    @Bind(R.id.rl_activity_secondhanddivision_nodata)
    RelativeLayout rlNoData;
    @Bind(R.id.tv_activity_secondhanddivision_nodata)
    TextView tvNoData;
    @Bind(R.id.prl_activity_secondhandcommodity)PullToRefreshLayout refreshLayout;
    @Bind(R.id.lv_activity_secondhandcommodity)ListView listView;

    @Bind(R.id.ll_secondhand_division_select_static)LinearLayout llStatic;
    @Bind(R.id.sp_secondhand_position_static)Spinner spPositionStatic;
    @Bind(R.id.sp_secondhand_price_static)Spinner spPriceStatic;
    @Bind(R.id.sp_secondhand_type_static)Spinner spTypeStatic;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private SecondHandDivisionAdapter secondHandDivisionAdapter;
    private ArrayAdapter<String> arr_adapter1, arr_adapter2, arr_adapter3;

    private EditText etSearch;
    private View headView;
    private LinearLayout llScroll;
    private Spinner spPosition,spPrice,spType;

    private List<String> mListRegionName,mListMoneyName,mListStateName;
    private List<RegionBean> mListRegionBean;
    private List<MoneyBean> mListMoneyBean;
    private List<StateBean> mListStateBean;
    private List<AdapterSecondHandDivisionData> secondHandDivisionList;

    private boolean hasMore = true,isRefresh;
    private int currentPage = 1;
    private String token,region,money,sate,keyvalue;

    private int[] locStatic = new int[2];
    private int[] locScroll = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_secondhandcommodity);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(SecondHandCommodityActivity.this);
        manager = new SharedpreferencesManager(SecondHandCommodityActivity.this);
        netWorkOperate = new NetWorkOperate(SecondHandCommodityActivity.this);

        this.setBackVisibility(true);
        this.setTitle("二手品专区");
        this.setRightViewVisibility(true);
        this.setRightViewText("发布信息");

        headView = LayoutInflater.from(SecondHandCommodityActivity.this).inflate(R.layout.layout_secondhandcommodity_headview, null);
        etSearch = (EditText)headView.findViewById(R.id.et_secondhand_division_search);
        llScroll = (LinearLayout)headView.findViewById(R.id.ll_secondhand_division_select);
        spPosition = (Spinner)headView.findViewById(R.id.sp_secondhand_position);
        spPrice = (Spinner)headView.findViewById(R.id.sp_secondhand_price);
        spType = (Spinner)headView.findViewById(R.id.sp_secondhand_type);

        mListRegionName = new ArrayList<>();
        mListMoneyName = new ArrayList<>();
        mListStateName = new ArrayList<>();
        mListRegionBean = new ArrayList<>();
        mListMoneyBean = new ArrayList<>();
        mListStateBean = new ArrayList<>();
        secondHandDivisionList = new ArrayList<>();

        arr_adapter1 = new ArrayAdapter<String>(this, R.layout.item_spinselect, mListRegionName);
        arr_adapter1.setDropDownViewResource(R.layout.item_dialogspinselect);
        spPosition.setAdapter(arr_adapter1);
        spPositionStatic.setAdapter(arr_adapter1);

        arr_adapter2 = new ArrayAdapter<String>(this, R.layout.item_spinselect, mListMoneyName);
        arr_adapter2.setDropDownViewResource(R.layout.item_dialogspinselect);
        spPrice.setAdapter(arr_adapter2);
        spPriceStatic.setAdapter(arr_adapter2);

        arr_adapter3 = new ArrayAdapter<String>(this, R.layout.item_spinselect, mListStateName);
        arr_adapter3.setDropDownViewResource(R.layout.item_dialogspinselect);
        spType.setAdapter(arr_adapter3);
        spTypeStatic.setAdapter(arr_adapter3);

        secondHandDivisionAdapter = new SecondHandDivisionAdapter(SecondHandCommodityActivity.this, secondHandDivisionList);
        listView.setAdapter(secondHandDivisionAdapter);
        listView.addHeaderView(headView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        token = manager.getToken();
        initRefreshVariables();
        netWorkOperate.getSecondHandDivision(token, region, money, sate, keyvalue, currentPage + "");
    }

    private void setEventListener() {
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        initRefreshVariables();
                        netWorkOperate.getSecondHandDivision(token, region, money, sate, keyvalue, currentPage + "");
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
                            currentPage++;
                        }
                        isRefresh = false;
                        netWorkOperate.getSecondHandDivision(token, region, money, sate, keyvalue, currentPage + "");
                    }
                },2000);
            }
        });

        this.setRightViewClickListener(new OnActionBarRightViewClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(SecondHandCommodityActivity.this, ReleaseGoodsActivity.class);
                intent.putExtra(FlagData.FLAG_TITLE, "发布二手品");
                intent.putExtra(FlagData.FLAG_RELEASE, 1);
                startActivity(intent);
                SecondHandCommodityActivity.this.finish();
            }
        });

        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    Intent intent = new Intent(SecondHandCommodityActivity.this, SearchResultActivity.class);
                    intent.putExtra(FlagData.FLAG_SEARCH_TEXT,etSearch.getText().toString());
                    intent.putExtra(FlagData.FLAG_STATE,"1");
                    startActivity(intent);
                }
                return false;
            }
        });

        // 下拉选择框点击事件
        spPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRefreshVariables();
                spPositionStatic.setSelection(position);
                region = mListRegionBean.get(position).code;
                netWorkOperate.getSecondHandDivision(token, region, money, sate, keyvalue, currentPage + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spPositionStatic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRefreshVariables();
                spPosition.setSelection(position);
                region = mListRegionBean.get(position).code;
                netWorkOperate.getSecondHandDivision(token, region, money, sate, keyvalue, currentPage + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spPrice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRefreshVariables();
                spPriceStatic.setSelection(position);
                money = mListMoneyBean.get(position).code;
                netWorkOperate.getSecondHandDivision(token, region, money, sate, keyvalue, currentPage + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spPriceStatic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRefreshVariables();
                spPrice.setSelection(position);
                money = mListMoneyBean.get(position).code;
                netWorkOperate.getSecondHandDivision(token, region, money, sate, keyvalue, currentPage + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRefreshVariables();
                spTypeStatic.setSelection(position);
                sate = mListStateBean.get(position).code;
                netWorkOperate.getSecondHandDivision(token, region, money, sate, keyvalue, currentPage + "");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spTypeStatic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRefreshVariables();
                spType.setSelection(position);
                sate = mListStateBean.get(position).code;
                netWorkOperate.getSecondHandDivision(token, region, money, sate, keyvalue, currentPage + "");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SecondHandCommodityActivity.this, SecondHandCommodityDetailsActivity.class);
                intent.putExtra(FlagData.FLAG_PRODUCT_ID, secondHandDivisionList.get(i-1).getPid());
                startActivity(intent);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                llScroll.getLocationOnScreen(locScroll);
                llStatic.getLocationOnScreen(locStatic);
                if (locScroll[1] <= locStatic[1]) {
                    llStatic.setVisibility(View.VISIBLE);
                } else {
                    llStatic.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });
    }

    @Override
    public void onGetSecondHandDivision(JsonBeanSecondHandDivision jsonBeanSecondHandDivision) {
        mListRegionName.clear();
        mListMoneyName.clear();
        mListStateName.clear();
        mListRegionBean.clear();
        mListMoneyBean.clear();
        mListStateBean.clear();
        for (JsonBeanSecondHandDivision.RegionBean.DataBean dataBean : jsonBeanSecondHandDivision.getRegion().getData()) {
            mListRegionBean.add(new RegionBean(dataBean.getCode(), dataBean.getName()));
            mListRegionName.add(dataBean.getName());
        }
        for (JsonBeanSecondHandDivision.MoneyBean.DataBean dataBean : jsonBeanSecondHandDivision.getMoney().getData()) {
            mListMoneyBean.add(new MoneyBean(dataBean.getCode(), dataBean.getName()));
            mListMoneyName.add(dataBean.getName());
        }
        for (JsonBeanSecondHandDivision.SateBean.DataBean dataBean : jsonBeanSecondHandDivision.getSate().getData()) {
            mListStateBean.add(new StateBean(dataBean.getCode(), dataBean.getName()));
            mListStateName.add(dataBean.getName());
        }
        if(isRefresh){
            secondHandDivisionList.clear();
            if(jsonBeanSecondHandDivision.getProducts().getRet().equals("0")){
                rlNoData.setVisibility(View.GONE);
            }else{
                rlNoData.setVisibility(View.VISIBLE);
                tvNoData.setText(jsonBeanSecondHandDivision.getProducts().getMsg());
            }
        }else{
            if(jsonBeanSecondHandDivision.getProducts().getRet().equals("1")){
                Toast.makeText(SecondHandCommodityActivity.this,jsonBeanSecondHandDivision.getProducts().getMsg(),Toast.LENGTH_SHORT).show();
            }
        }
        for (JsonBeanSecondHandDivision.ProductsBean.DataBean dataBean : jsonBeanSecondHandDivision.getProducts().getData()) {
            secondHandDivisionList.add(new AdapterSecondHandDivisionData(dataBean.getPid(), FlagData.FLAG_IMGADDRESS + dataBean.getImg(), dataBean.getName(), dataBean.getRegion(), dataBean.getTime()));
        }
        arr_adapter1.notifyDataSetChanged();
        arr_adapter2.notifyDataSetChanged();
        arr_adapter3.notifyDataSetChanged();
        secondHandDivisionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(SecondHandCommodityActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initRefreshVariables(){
        isRefresh = true;
        currentPage = 1;
        hasMore = true;
        region = "";
        money = "";
        sate = "";
        keyvalue = "";
        etSearch.setText("");
    }

    private class RegionBean {
        private String code, name;

        public RegionBean(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    private class MoneyBean {
        private String code, name;

        public MoneyBean(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    private class StateBean {
        private String code, name;

        public StateBean(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }
}

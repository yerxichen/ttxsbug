package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.HomepageShopAdapter;
import com.yundian.wudou.adapter.ShopStoreAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterHomepageShopData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanStoreData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreActivity extends BaseActivity implements NetWorkInterface.OnGetStoreDataListener {

    @Bind(R.id.prl_activity_store)PullToRefreshLayout refreshLayout;
    @Bind(R.id.btn_store_left)Button btnLeft;
    @Bind(R.id.btn_store_right)Button btnRight;
    @Bind(R.id.spinner_activity_store_class)Spinner spClassify;
    @Bind(R.id.spinner_activity_store_order)Spinner spOrder;
    @Bind(R.id.lv_activity_store)ListView listView;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private ShopStoreAdapter storeAdapter;
    private ArrayAdapter<String> arr_adapter1, arr_adapter2;

    private List<AdapterHomepageShopData> storeList = new ArrayList<>();
    private List<String> mListClassName = new ArrayList<>();
    private List<String> mListOrderName = new ArrayList<>();

    private String state,token,title,classCode,orderCode,sate;
    private boolean hasMore,flag1,flag2,flag3;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(StoreActivity.this);
        manager = new SharedpreferencesManager(StoreActivity.this);
        netWorkOperate = new NetWorkOperate(StoreActivity.this);

        this.setBackVisibility(true);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        state = intent.getStringExtra(FlagData.FLAG_STATE);

        this.setTitle(title);
        if(state.equals("0")){
            classCode = "";
        }else if(state.equals("1")){
            classCode = "01";
        }else if(state.equals("2")){
            classCode = "02";
        }else if(state.equals("3")){
            classCode = "03";
        }else if(state.equals("4")){
            classCode = "04";
        }else if(state.equals("5")){
            classCode = "05";
        }

        arr_adapter1 = new ArrayAdapter<>(this, R.layout.item_spinselect, mListClassName);
        arr_adapter1.setDropDownViewResource(R.layout.item_dialogspinselect);
        spClassify.setAdapter(arr_adapter1);

        arr_adapter2 = new ArrayAdapter<>(this, R.layout.item_spinselect, mListOrderName);
        arr_adapter2.setDropDownViewResource(R.layout.item_dialogspinselect);
        spOrder.setAdapter(arr_adapter2);

        storeAdapter = new ShopStoreAdapter(this, storeList);
        listView.setAdapter(storeAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        storeList.clear();
        hasMore = true;
        currentPage = 1;
        sate = "";
        orderCode = "";
        token = manager.getToken();
        netWorkOperate.getStoreData(token, sate, classCode, orderCode, currentPage + "");
    }

    private void setEventListener() {
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        storeList.clear();
                        hasMore = true;
                        currentPage = 1;
                        netWorkOperate.getStoreData(token, sate, classCode, orderCode, currentPage + "");
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
                        netWorkOperate.getStoreData(token, sate, classCode, orderCode, currentPage + "");
                    }
                },2000);
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnLeft.setEnabled(false);
                btnLeft.setTextColor(getResources().getColor(R.color.colorWrite));
                btnRight.setEnabled(true);
                btnRight.setTextColor(getResources().getColor(R.color.colorGreenBefore));

                storeList.clear();
                hasMore = true;
                currentPage = 1;
                sate = "";
                netWorkOperate.getStoreData(token, sate, classCode, orderCode, currentPage + "");
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLeft.setEnabled(true);
                btnLeft.setTextColor(getResources().getColor(R.color.colorGreenBefore));
                btnRight.setEnabled(false);
                btnRight.setTextColor(getResources().getColor(R.color.colorWrite));

                storeList.clear();
                hasMore = true;
                currentPage = 1;
                sate = "01";
                netWorkOperate.getStoreData(token, sate, classCode, orderCode, currentPage + "");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StoreActivity.this, VegetableShopActivity.class);
                Log.d("my", "store_StoreId: "+storeList.get(position).getStoreId());
                intent.putExtra(FlagData.FLAG_SHOP_ID, storeList.get(position).getStoreId());
                startActivity(intent);
            }
        });

        spClassify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(flag1){
                    if (0 == position) {
                        classCode = "";
                    } else {
                        classCode = "0" + position;
                    }
                storeList.clear();
                hasMore = true;
                currentPage = 1;
                netWorkOperate.getStoreData(token, sate, classCode, orderCode, currentPage + "");
                }
                flag1 = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(flag2){
                    if (0 == position) {
                        orderCode = "";
                    } else {
                        orderCode = "0" + position;
                    }
                storeList.clear();
                hasMore = true;
                currentPage = 1;
                netWorkOperate.getStoreData(token, sate, classCode, orderCode, currentPage + "");
                }
                flag2 = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onGetStoreData(JsonBeanStoreData jsonBeanStoreData) {
        for (JsonBeanStoreData.StoresateBean.DataBean dataBean : jsonBeanStoreData.getStoresate().getData()) {
            if (dataBean.getCode().equals("")) {
                btnLeft.setText(dataBean.getName());
            } else {
                btnRight.setText(dataBean.getName());
            }
        }
        mListClassName.clear();
        for (JsonBeanStoreData.StoreclassesBean.DataBean dataBean : jsonBeanStoreData.getStoreclasses().getData()) {
            mListClassName.add(dataBean.getName());
        }
        mListOrderName.clear();
        for (JsonBeanStoreData.StoreorderBean.DataBean dataBean : jsonBeanStoreData.getStoreorder().getData()) {
            mListOrderName.add(dataBean.getName());
        }
        if (jsonBeanStoreData.getStores().getRet().equals("1")) {
            hasMore = false;
            Toast.makeText(StoreActivity.this, jsonBeanStoreData.getStores().getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            for (JsonBeanStoreData.StoresBean.DataBean dataBean : jsonBeanStoreData.getStores().getData()) {
                boolean isExpert = false;
                if (dataBean.getIsown().equals("1")) {
                    isExpert = true;
                }
                storeList.add(new AdapterHomepageShopData(FlagData.FLAG_IMGADDRESS + dataBean.getImg()
                        , dataBean.getStoreid(), dataBean.getName(), dataBean.getMonthlysales(), dataBean.getStartvalue()
                        , dataBean.getStartfee(), dataBean.getDistance(), isExpert));
            }
        }

        arr_adapter1.notifyDataSetChanged();
        arr_adapter2.notifyDataSetChanged();
        storeAdapter.notifyDataSetChanged();

        if(!flag3){
            if(classCode.equals("")){
                spClassify.setSelection(0,true);
            }else{
                spClassify.setSelection(Integer.parseInt(classCode),true);
            }
            flag3 = true;
        }
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(StoreActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}

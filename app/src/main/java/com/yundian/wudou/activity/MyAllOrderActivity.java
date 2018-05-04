package com.yundian.wudou.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.MyAllOrderAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterMyAllOrderData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanDeleteMyOrder;
import com.yundian.wudou.network.JsonBeanMyAllOrder;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyAllOrderActivity extends BaseActivity implements NetWorkInterface.OnGetMyAllOrderDataListener,
        NetWorkInterface.OnDeleteMyOrderlistener {

    @Bind(R.id.prl_activity_myallorder)
    PullToRefreshLayout refreshLayout;
    @Bind(R.id.rl_activity_myallorder_nodata)
    RelativeLayout rlNoData;
    @Bind(R.id.tv_activity_myorderall_nodata)
    TextView tvNoData;
    @Bind(R.id.lv_activity_myallorder)
    ListView listView;


    private List<AdapterMyAllOrderData> list;
    private MyAllOrderAdapter adapter;

    private NetWorkOperate netWorkOperate;
    private SharedpreferencesManager manager;
    private String token;

    private int currentpage = 1;
    private Boolean hasMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_allorder);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(MyAllOrderActivity.this);
        manager = new SharedpreferencesManager(MyAllOrderActivity.this);
        netWorkOperate = new NetWorkOperate(MyAllOrderActivity.this);

        this.setBackVisibility(true);
        this.setTitle("我的订单");

        list = new ArrayList<>();
        adapter = new MyAllOrderAdapter(this, list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        list.clear();
        currentpage = 1;
        hasMore = true;
        token = manager.getToken();
        netWorkOperate.getMyAllOrderData(token, currentpage);
    }

    private void setEventListener() {
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();

                        list.clear();
                        currentpage = 1;
                        hasMore = true;
                        netWorkOperate.getMyAllOrderData(token, currentpage);
                    }
                }, 2000);
            }

            @Override
            public void loadMore() {
                refreshLayout.finishLoadMore();
                if (hasMore) {
                    currentpage++;
                }
                netWorkOperate.getMyAllOrderData(token, currentpage);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyAllOrderActivity.this, MyOrderDetailsActivity.class);
                intent.putExtra(FlagData.FLAG_OID, list.get(position).getOid());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showMessageDialog("确定删除此订单？", position);
                return true;
            }
        });
    }

    @Override
    public void onGetMyAllOrderData(JsonBeanMyAllOrder jsonBeanMyAllOrder) {
        rlNoData.setVisibility(View.GONE);

        for (JsonBeanMyAllOrder.DataBean dataBean : jsonBeanMyAllOrder.getData()) {
            boolean paystate = false;
            if (dataBean.getPaysate().equals("0")) {
                paystate = true;
            }
            List<AdapterMyAllOrderData.ProductData> mProductDataList = new ArrayList<>();
            for (JsonBeanMyAllOrder.DataBean.ProductsdataBean productsdataBean : dataBean.getProductsdata()) {
                mProductDataList.add(new AdapterMyAllOrderData.ProductData(productsdataBean.getPid(), FlagData.FLAG_IMGADDRESS + productsdataBean.getImg()));
            }
            list.add(new AdapterMyAllOrderData(dataBean.getStoreid(), dataBean.getStorename(), dataBean.getStoreimg(), dataBean.getOid(), dataBean.getOsn(),
                    dataBean.getOrderamount(), dataBean.getSurplusmoney(), dataBean.getAddtime(), dataBean.getOrderstatusdescription()
                    , dataBean.getIsreviews(), dataBean.getReviewstext(),dataBean.getOrdertype(),dataBean.getCreditsvalue(), paystate, mProductDataList));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteMyOrder(JsonBeanDeleteMyOrder jsonBeanDeleteMyOrder) {
        Toast.makeText(MyAllOrderActivity.this, jsonBeanDeleteMyOrder.getMsg(), Toast.LENGTH_SHORT).show();
        list.clear();
        currentpage = 1;
        netWorkOperate.getMyAllOrderData(token, currentpage);
    }

    @Override
    public void onFailure(String msg) {
        if (currentpage == 1) {
            adapter.notifyDataSetChanged();
            rlNoData.setVisibility(View.VISIBLE);
            tvNoData.setText(msg);
        } else {
            hasMore = false;
            Toast.makeText(MyAllOrderActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    private void showMessageDialog(String msg, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyAllOrderActivity.this);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                netWorkOperate.deleteMyOrder(token, list.get(position).getOid());
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyAllOrderActivity.this, FragmentContainerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("flag", 3);
        startActivity(intent);
        MyAllOrderActivity.this.finish();
    }
}

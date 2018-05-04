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
import com.yundian.wudou.adapter.MerchantOrderAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterMerchantOrderData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanDeleteMyOrder;
import com.yundian.wudou.network.JsonBeanMerchantOrder;
import com.yundian.wudou.network.JsonBeanMerchantOrderEdit;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MerchantOrderActivity extends BaseActivity implements NetWorkInterface.OnGetMerChantOrderDataListener
        , NetWorkInterface.OnGetMerchantOrderEditListener, NetWorkInterface.OnDeleteMyOrderlistener {

    @Bind(R.id.rl_activity_merchantorder_nodata)
    RelativeLayout rlNoData;
    @Bind(R.id.tv_activity_merchantorder_nodata)
    TextView tvNoData;
    @Bind(R.id.lv_activity_merchantorder)
    ListView listView;
    @Bind(R.id.prl_activity_merchantorder)
    PullToRefreshLayout refreshLayout;

    private MerchantOrderAdapter adapter;
    private List<AdapterMerchantOrderData> list;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private String title, state, token;
    private int currentpage = 1;
    private Boolean hasMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_order);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(MerchantOrderActivity.this);
        manager = new SharedpreferencesManager(MerchantOrderActivity.this);
        netWorkOperate = new NetWorkOperate(MerchantOrderActivity.this);

        Intent intent = getIntent();
        title = intent.getStringExtra(FlagData.FLAG_TITLE);
        state = intent.getStringExtra(FlagData.FLAG_STATE);

        this.setBackVisibility(true);
        if (title != null) {
            this.setTitle(title);
        }

        list = new ArrayList<>();
        adapter = new MerchantOrderAdapter(this, list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (state != null) {
            list.clear();
            currentpage = 1;
            hasMore = true;
            token = manager.getToken();
            netWorkOperate.getMerchantOrderData(token, state,currentpage);
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

                        list.clear();
                        currentpage = 1;
                        hasMore = true;
                        netWorkOperate.getMerchantOrderData(token, state,currentpage);
                    }
                }, 2000);
            }

            @Override
            public void loadMore() {
                refreshLayout.finishLoadMore();
                if (hasMore) {
                    currentpage++;
                }
                netWorkOperate.getMerchantOrderData(token, state,currentpage);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MerchantOrderActivity.this, MerchantOrderDetailsActivity.class);
                intent.putExtra(FlagData.FLAG_OID, list.get(position).getOid());
                startActivity(intent);
            }
        });

        adapter.setOnMerchantEditClickListener(new MerchantOrderAdapter.OnMerchantEditClickListener() {
            @Override
            public void onMerchantEditClick(String oid) {
                showEditClickMessage(oid);
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
    public void onGetMerChantOrderData(JsonBeanMerchantOrder jsonBeanMerchantOrder) {
        rlNoData.setVisibility(View.GONE);

        for (JsonBeanMerchantOrder.DataBean dataBean : jsonBeanMerchantOrder.getData()) {
            boolean paystate = false;
            if (dataBean.getPaysate().equals("0")) {
                paystate = true;
            }

            List<AdapterMerchantOrderData.ProductData> mProductDataList = new ArrayList<>();

            for (JsonBeanMerchantOrder.DataBean.ProductsdataBean productsdataBean : dataBean.getProductsdata()) {
                mProductDataList.add(new AdapterMerchantOrderData.ProductData(productsdataBean.getPid()
                        , FlagData.FLAG_IMGADDRESS + productsdataBean.getImg()));
            }
            list.add(new AdapterMerchantOrderData(dataBean.getStoreid(), dataBean.getStorename(), dataBean.getStoreimg(), dataBean.getOid(),
                    dataBean.getOsn(), dataBean.getOrderamount(),dataBean.getSurplusmoney(), dataBean.getAddtime(), dataBean.getOrderstatusdescription(), dataBean.getIsEditshow(),
                    dataBean.getEditshowText(),dataBean.getOrdertype(),dataBean.getCreditsvalue(), paystate, mProductDataList));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetMerchantOrderEdit(JsonBeanMerchantOrderEdit jsonBeanMerchantOrderEdit) {
        list.clear();
        currentpage = 1;
        hasMore = true;
        netWorkOperate.getMerchantOrderData(token, state,currentpage);
    }

    @Override
    public void onDeleteMyOrder(JsonBeanDeleteMyOrder jsonBeanDeleteMyOrder) {
        Toast.makeText(MerchantOrderActivity.this, jsonBeanDeleteMyOrder.getMsg(), Toast.LENGTH_SHORT).show();
        list.clear();
        currentpage = 1;
        netWorkOperate.getMerchantOrderData(manager.getToken(), state,currentpage);
    }

    @Override
    public void onFailure(String msg) {
        if (currentpage == 1) {
            adapter.notifyDataSetChanged();
            rlNoData.setVisibility(View.VISIBLE);
            tvNoData.setText(msg);
        } else {
            hasMore = false;
            Toast.makeText(MerchantOrderActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    private void showMessageDialog(String msg, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MerchantOrderActivity.this);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                netWorkOperate.deleteMyOrder(manager.getToken(), list.get(position).getOid());
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showEditClickMessage(final String oid){
        AlertDialog.Builder builder = new AlertDialog.Builder(MerchantOrderActivity.this);
        builder.setMessage("确认对此订单进行操作？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                netWorkOperate.getMerchantOrderEdit(token, oid);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

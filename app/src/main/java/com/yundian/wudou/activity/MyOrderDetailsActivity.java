package com.yundian.wudou.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.MyOrderDetailsCommodityListAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterMyOrderDetailsCommodityListData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanCancelOrder;
import com.yundian.wudou.network.JsonBeanOrderDetailsData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyOrderDetailsActivity extends BaseActivity implements NetWorkInterface.OnGetOrderDetailsDataListener,
        NetWorkInterface.OnGetCancelOrderListener {

    @Bind(R.id.prl_activity_myallorder_detail)
    PullToRefreshLayout refreshLayout;
    @Bind(R.id.tv_activity_myorderdetails_infomation)
    TextView tvInfomation;
    @Bind(R.id.tv_activity_myorderdetails_paynow)
    TextView tvPaynow;
    @Bind(R.id.tv_activity_myorderdetails_surplusmoney_title)
    TextView tvSurplusTitle;
    @Bind(R.id.tv_activity_myorderdetails_surplusmoney)
    TextView tvSurplusMoney;
    @Bind(R.id.tv_activity_myorderdetails_orderstatus)
    TextView tvOrderStatus;
    @Bind(R.id.tv_activity_myorderdetails_cancle)
    TextView tvCancel;
    @Bind(R.id.lv_activity_myorderdetails)
    ListView listView;

    private List<AdapterMyOrderDetailsCommodityListData> myOrderDetailsCommodityListDataList;
    private MyOrderDetailsCommodityListAdapter myOrderDetailsCommodityListAdapter;

    private SharedpreferencesManager manager;
    private NetWorkOperate mNetWorkOperate;
    private String strToken, strOid, strInfo, strOsn;
    private Boolean payState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orderdetails);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(MyOrderDetailsActivity.this);
        refreshLayout.setCanLoadMore(false);
        manager = new SharedpreferencesManager(MyOrderDetailsActivity.this);
        mNetWorkOperate = new NetWorkOperate(MyOrderDetailsActivity.this);

        this.setBackVisibility(true);
        this.setTitle(R.string.order_details);

        Intent intent = getIntent();
        strOid = intent.getStringExtra(FlagData.FLAG_OID);

        myOrderDetailsCommodityListDataList = new ArrayList<>();
        myOrderDetailsCommodityListAdapter = new MyOrderDetailsCommodityListAdapter(this, myOrderDetailsCommodityListDataList);
        listView.setAdapter(myOrderDetailsCommodityListAdapter);
        listView.setFocusable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        strToken = manager.getToken();
        mNetWorkOperate.getOrderDetailsData(strToken, strOid);
    }

    private void setEventListener() {
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        mNetWorkOperate.getOrderDetailsData(strToken, strOid);
                    }
                }, 2000);
            }

            @Override
            public void loadMore() {

            }
        });
        tvPaynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrderDetailsActivity.this, PaymentActivity.class);
                intent.putExtra(FlagData.FLAG_SNTYPE, "1");
                intent.putExtra(FlagData.FLAG_SN, strOsn);
                MyOrderDetailsActivity.this.startActivity(intent);
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (payState) {
                    Intent intent = new Intent(MyOrderDetailsActivity.this, RefundActivity.class);
                    intent.putExtra(FlagData.FLAG_OID, strOid);
                    startActivity(intent);
                } else {
                    showMessageDialog("确定取消订单？");
                }
            }
        });
    }

    @Override
    public void onGetOrderDetailsData(JsonBeanOrderDetailsData jsonBeanOrderDetailsData) {
        if (jsonBeanOrderDetailsData.getOrdertype().equals("0")) {
            tvSurplusTitle.setText("应付金额 : ");
            tvSurplusMoney.setText(jsonBeanOrderDetailsData.getSurplusmoney());
            strInfo = "订单编号：" + jsonBeanOrderDetailsData.getOsn() + "\n" +
                    "订单时间：" + jsonBeanOrderDetailsData.getAddtime() + "\n" +
                    "支付方式：" + jsonBeanOrderDetailsData.getPaysystemtype() + "\n" +
                    "收货姓名：" + jsonBeanOrderDetailsData.getConsignee() + "\n" +
                    "收货电话：" + jsonBeanOrderDetailsData.getMobile() + "\n" +
                    "收货地址：" + jsonBeanOrderDetailsData.getAddress() + "\n" +
                    "订单备注：" + jsonBeanOrderDetailsData.getBuyerremark() + "\n" +
                    "配送时间：" + jsonBeanOrderDetailsData.getDistributionDesn() + "\n" +
                    "商品金额：¥ " + jsonBeanOrderDetailsData.getProductamount() + "\n" +
                    "配送费：¥ " + jsonBeanOrderDetailsData.getShipfee() + "\n" +
                    "订单合计：¥ " + jsonBeanOrderDetailsData.getOrderamount() + "\n" +
                    "优惠券金额：¥ " + jsonBeanOrderDetailsData.getCouponmoney();
            if (jsonBeanOrderDetailsData.getPaysate().equals("0")) {
                payState = false;
            } else {
                payState = true;
                tvPaynow.setVisibility(View.INVISIBLE);
                tvCancel.setText("申请退款");
            }
        } else {
            tvPaynow.setVisibility(View.GONE);
            tvCancel.setVisibility(View.GONE);
            tvSurplusTitle.setText("兑换积分 : ");
            tvSurplusMoney.setText(jsonBeanOrderDetailsData.getCreditsvalue());
            strInfo = "订单编号：" + jsonBeanOrderDetailsData.getOsn() + "\n" +
                    "订单时间：" + jsonBeanOrderDetailsData.getAddtime() + "\n" +
                    "支付方式：" + jsonBeanOrderDetailsData.getPaysystemtype() + "\n" +
                    "收货姓名：" + jsonBeanOrderDetailsData.getConsignee() + "\n" +
                    "收货电话：" + jsonBeanOrderDetailsData.getMobile() + "\n" +
                    "收货地址：" + jsonBeanOrderDetailsData.getAddress();
        }
        tvInfomation.setText(strInfo);

        tvOrderStatus.setText(jsonBeanOrderDetailsData.getOrderstatusdescription());
        strOsn = jsonBeanOrderDetailsData.getOsn();

        myOrderDetailsCommodityListDataList.clear();
        for (JsonBeanOrderDetailsData.DataBean dataBean : jsonBeanOrderDetailsData.getData()) {
            myOrderDetailsCommodityListDataList.add(new AdapterMyOrderDetailsCommodityListData(
                    dataBean.getPid()
                    , dataBean.getName()
                    , dataBean.getShopprice()
                    , dataBean.getBuycount()
                    , FlagData.FLAG_IMGADDRESS + dataBean.getImg()));
        }
        myOrderDetailsCommodityListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetCancelOrder(JsonBeanCancelOrder jsonBeanCancelOrder) {
        Toast.makeText(MyOrderDetailsActivity.this, jsonBeanCancelOrder.getMsg(), Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onFailure(String msg) {
        showMessageDialog(msg);
    }

    private void showMessageDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyOrderDetailsActivity.this);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNetWorkOperate.getCancelOrder(strToken, strOid);
                MyOrderDetailsActivity.this.finish();
            }
        });
        builder.setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

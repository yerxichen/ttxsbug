package com.yundian.wudou.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;
import android.widget.TextView;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.MerchantOrderDetailsCommodityListAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterMerchantOrderDetailsCommodityListData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanMerchantOrderDetailsData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MerchantOrderDetailsActivity extends BaseActivity implements NetWorkInterface.OnGetMechantOrderDetailsDataListener {

    @Bind(R.id.prl_activity_merchantorder_detail)
    PullToRefreshLayout refreshLayout;
    @Bind(R.id.tv_activity_merchantorderdetails_infomation)
    TextView tvInfomation;
    @Bind(R.id.tv_activity_merchantorderdetails_surplusmoney_title)
    TextView tvSurplusTitle;
    @Bind(R.id.tv_activity_merchantorderdetails_surplusmoney)
    TextView tvSurplusMoney;
    @Bind(R.id.tv_activity_merchantorderdetails_orderstatus)
    TextView tvOrderStatus;
    @Bind(R.id.lv_activity_merchantorderdetails)
    ListView listView;

    private List<AdapterMerchantOrderDetailsCommodityListData> merchantOrderDetailsCommodityListDataList;
    private MerchantOrderDetailsCommodityListAdapter merchantOrderDetailsCommodityListAdapter;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;
    private String strToken, strOid, strInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_orderdetails);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(MerchantOrderDetailsActivity.this);
        refreshLayout.setCanLoadMore(false);
        netWorkOperate = new NetWorkOperate(MerchantOrderDetailsActivity.this);
        manager = new SharedpreferencesManager(MerchantOrderDetailsActivity.this);

        this.setBackVisibility(true);
        this.setTitle(R.string.order_details);

        Intent intent = getIntent();
        strOid = intent.getStringExtra(FlagData.FLAG_OID);

        merchantOrderDetailsCommodityListDataList = new ArrayList<>();
        merchantOrderDetailsCommodityListAdapter = new MerchantOrderDetailsCommodityListAdapter(this, merchantOrderDetailsCommodityListDataList);
        listView.setAdapter(merchantOrderDetailsCommodityListAdapter);
        listView.setFocusable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        strToken = manager.getToken();
        netWorkOperate.getMerchantOrderDetailsData(strToken, strOid);
    }

    private void setEventListener() {
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        netWorkOperate.getMerchantOrderDetailsData(strToken, strOid);
                    }
                }, 2000);
            }

            @Override
            public void loadMore() {

            }
        });
    }

    @Override
    public void onGetMechantOrderDetailsData(JsonBeanMerchantOrderDetailsData jsonBeanMerchantOrderDetailsData) {
        if(jsonBeanMerchantOrderDetailsData.getOrdertype().equals("0")){
            tvSurplusTitle.setText("应付金额 : ");
            tvSurplusMoney.setText(jsonBeanMerchantOrderDetailsData.getSurplusmoney());
            strInfo = "订单编号：" + jsonBeanMerchantOrderDetailsData.getOsn() + "\n" +
                    "订单时间：" + jsonBeanMerchantOrderDetailsData.getAddtime() + "\n" +
                    "支付方式：" + jsonBeanMerchantOrderDetailsData.getPaysystemtype() + "\n" +
                    "收货姓名：" + jsonBeanMerchantOrderDetailsData.getConsignee() + "\n" +
                    "收货电话：" + jsonBeanMerchantOrderDetailsData.getMobile() + "\n" +
                    "收货地址：" + jsonBeanMerchantOrderDetailsData.getAddress() + "\n" +
                    "订单备注：" + jsonBeanMerchantOrderDetailsData.getBuyerremark() + "\n" +
                    "配送时间：" + jsonBeanMerchantOrderDetailsData.getDistributionDesn() + "\n" +
                    "商品金额：¥ " + jsonBeanMerchantOrderDetailsData.getProductamount() + "\n" +
                    "配送费：¥ " + jsonBeanMerchantOrderDetailsData.getShipfee() + "\n" +
                    "订单合计：¥ " + jsonBeanMerchantOrderDetailsData.getOrderamount() + "\n" +
                    "优惠券金额：¥ " + jsonBeanMerchantOrderDetailsData.getCouponmoney();
        }else{
            tvSurplusTitle.setText("兑换积分 : ");
            tvSurplusMoney.setText(jsonBeanMerchantOrderDetailsData.getCreditsvalue());
            strInfo = "订单编号：" + jsonBeanMerchantOrderDetailsData.getOsn() + "\n" +
                    "订单时间：" + jsonBeanMerchantOrderDetailsData.getAddtime() + "\n" +
                    "支付方式：" + jsonBeanMerchantOrderDetailsData.getPaysystemtype() + "\n" +
                    "收货姓名：" + jsonBeanMerchantOrderDetailsData.getConsignee() + "\n" +
                    "收货电话：" + jsonBeanMerchantOrderDetailsData.getMobile() + "\n" +
                    "收货地址：" + jsonBeanMerchantOrderDetailsData.getAddress();
        }
        tvInfomation.setText(strInfo);

        tvSurplusMoney.setText(jsonBeanMerchantOrderDetailsData.getSurplusmoney());

        tvOrderStatus.setText(jsonBeanMerchantOrderDetailsData.getOrderstatusdescription());

        merchantOrderDetailsCommodityListDataList.clear();
        for (JsonBeanMerchantOrderDetailsData.DataBean dataBean : jsonBeanMerchantOrderDetailsData.getData()) {
            merchantOrderDetailsCommodityListDataList.add(new AdapterMerchantOrderDetailsCommodityListData(
                    dataBean.getPid()
                    , dataBean.getName()
                    , dataBean.getShopprice()
                    , dataBean.getBuycount()
                    , FlagData.FLAG_IMGADDRESS + dataBean.getImg()));
        }
        merchantOrderDetailsCommodityListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        showMessageDialog(msg);
    }

    private void showMessageDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MerchantOrderDetailsActivity.this);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                netWorkOperate.getCancelOrder(strToken, strOid);
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

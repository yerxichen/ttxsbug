package com.yundian.wudou.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.PaymentAdapter;
import com.yundian.wudou.data.AdapterPaymentData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanAliPayText;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 这个Activity需要传入Oid
 */

public class PaymentActivity extends Activity implements NetWorkInterface.OnGetAliPayTextListener {

    @Bind(R.id.tv_activitypayment_back)TextView tvBack;
    @Bind(R.id.lv_activity_payment)ListView listView;

    private PaymentAdapter mPaymentAdapter;
    private List<AdapterPaymentData> mAdapterPaymentDataList;

    private NetWorkOperate netWorkOperate;
    private SharedpreferencesManager manager;
    private String strToken, strSn, strSnType,strPayType = "";

    //微信支付
    private static final String APP_ID = "wxaf4ff32e6be3a395";
    private IWXAPI mIwxapi;

    //支付Hander
    private PaymentActivityHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(PaymentActivity.this);
        netWorkOperate = new NetWorkOperate(this);
        manager = new SharedpreferencesManager(PaymentActivity.this);
        strToken = manager.getToken();

        Intent intent = getIntent();
        strSnType = intent.getStringExtra(FlagData.FLAG_SNTYPE);
        strSn = intent.getStringExtra(FlagData.FLAG_SN);

        mAdapterPaymentDataList = new ArrayList<>();
        mAdapterPaymentDataList.add(new AdapterPaymentData(R.drawable.zhifubao,
                "支付宝支付",
                "支付宝支付安全有效"
        ));
        mAdapterPaymentDataList.add(new AdapterPaymentData(R.drawable.weixin,
                "微信支付",
                "微信支付快捷方便"
        ));
        mPaymentAdapter = new PaymentAdapter(PaymentActivity.this, mAdapterPaymentDataList);
        listView.setAdapter(mPaymentAdapter);

        //注册微信支付，并获取实例
        mIwxapi = WXAPIFactory.createWXAPI(this, APP_ID, true);
        mIwxapi.registerApp(APP_ID);

        handler = new PaymentActivityHandler();
    }

    private void setEventListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this, MyAllOrderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                PaymentActivity.this.finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        //获取支付宝支付字窜
                        strPayType = "1";
                        break;
                    }
                    case 1: {
                        //获取微信支付字窜
                        strPayType = "2";
                        break;
                    }
                    default: {
                        //Log("pay_default");
                        break;
                    }
                }
                netWorkOperate.getAliPayText(strToken, strPayType,strSnType, strSn);
            }
        });
    }

    //支付宝支付方法
    private void aliPay(final String payInfo) {
        final String str = "partner=\"2088221868910638\"&seller_id=\"2886855@qq" +
                ".com\"&out_trade_no=\"wudoll20161012115337750\"&subject=\"商品标题\"&body=\"商品描述\"&total_fee=\"0" +
                ".01\"&notify_url=\"www.wudoll.com\"&service=\"mobile.securitypay" +
                ".pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"10m\"&show_url=\"m.alipay" +
                ".com\"&sign=\"D%2btICfqkjA1mgpieIeYC3cjkhOoLW4uV7tMNq9Upmkzm2asLnm2c4h6Hx3rZalI17TljJEUpXT6vJycEWzhbZz8XzSWsZc6mEn5x12ZVgqpL469L%2fsj4ocmxt5jpfvURFx5qL6RhvRvFC1pAAZukFH76SJEkJvWeSnICCAd9siU%3d\"&sign_type=\"RSA\"";


        //Log("支付宝字窜-->" + payInfo);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(PaymentActivity.this);
                String result = payTask.pay(payInfo, true);

                Message msg = new Message();
                msg.arg1 = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    //微信支付方法
    private void wechatPay(String payInfo) {

        String str = payInfo;

        str = str.replace("appid=", "");
        str = str.replace("noncestr=", "");
        str = str.replace("package=", "");
        str = str.replace("partnerid=", "");
        str = str.replace("prepayid=", "");
        str = str.replace("sign=", "");
        str = str.replace("timestamp=", "");

        //Log(str);

        String[] strArray = str.split("&");

        PayReq mPayReq = new PayReq();

        mPayReq.appId = strArray[0];
        mPayReq.nonceStr = strArray[1];
        mPayReq.packageValue = strArray[2];
        mPayReq.partnerId = strArray[3];
        mPayReq.prepayId = strArray[4];
        mPayReq.sign = strArray[5];
        mPayReq.timeStamp = strArray[6];

        mIwxapi.sendReq(mPayReq);
    }

    private void showMessageDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onGetAliPayText(JsonBeanAliPayText jsonBeanAliPayText) {
        if (strPayType.equals("1")) {
            aliPay(jsonBeanAliPayText.getSignOrderStr());
        }else if (strPayType.equals("2")){
            wechatPay(jsonBeanAliPayText.getSignOrderStr());
        }else {
            showMessageDialog("支付错误！");
        }
    }

    @Override
    public void onFailure(String msg) {
        showMessageDialog(msg);
    }

    private class PaymentActivityHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("Tag", "msg-->" + msg);
            switch (msg.arg1) {
                case 1: {
                    Intent intent = new Intent(PaymentActivity.this, MyAllOrderActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    PaymentActivity.this.finish();
                }
            }
        }
    }
}

package com.yundian.wudou.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MerchantMessageActivity extends BaseActivity {

    @Bind(R.id.wv_activity_merchant_message)WebView webView;

    private SharedpreferencesManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_message);

        initalize();
    }

    private void initalize() {
        ButterKnife.bind(MerchantMessageActivity.this);
        manager = new SharedpreferencesManager(MerchantMessageActivity.this);

        setTitle("商户消息");
        setBackVisibility(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(FlagData.FLAG_WEB_MERCHANTMESSAGE + "access_token=" +  manager.getToken());
    }
}

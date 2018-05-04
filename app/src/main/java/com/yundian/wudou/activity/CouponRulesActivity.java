package com.yundian.wudou.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CouponRulesActivity extends BaseActivity {

    @Bind(R.id.wv_activity_coupon_rules)
    WebView webView;

    private SharedpreferencesManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_rules);

        initialize();
    }

    private void initialize() {
        ButterKnife.bind(CouponRulesActivity.this);
        manager = new SharedpreferencesManager(CouponRulesActivity.this);

        this.setBackVisibility(true);
        this.setTitle("使用规则");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(FlagData.FLAG_WEB_COUPONRULES + manager.getToken());
    }
}

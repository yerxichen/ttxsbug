package com.yundian.wudou.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistrationAgreementActivity extends BaseActivity {

    @Bind(R.id.wv_activity_registration_agreement) WebView webView;

    private SharedpreferencesManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_agreement);

        initialize();
    }

    private void initialize() {
        ButterKnife.bind(RegistrationAgreementActivity.this);
        manager = new SharedpreferencesManager(RegistrationAgreementActivity.this);

        this.setBackVisibility(true);
        this.setTitle(R.string.wudoll_regist_agreement);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(FlagData.FLAG_WEB_REGISTAGREEMENTACTIVITY + manager.getToken());
    }
}

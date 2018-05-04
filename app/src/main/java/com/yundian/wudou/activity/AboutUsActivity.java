package com.yundian.wudou.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;

public class AboutUsActivity extends BaseActivity {

    private SharedpreferencesManager manager;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        setBackVisibility(true);
        setTitle(getString(R.string.about_us));

        webView = (WebView) findViewById(R.id.wv_activity_aboutus);
        manager = new SharedpreferencesManager(AboutUsActivity.this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(FlagData.FLAG_WEB_ABOUTUS + manager.getToken());
    }
}

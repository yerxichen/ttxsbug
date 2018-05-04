package com.yundian.wudou.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HelpActivity extends BaseActivity {

    @Bind(R.id.wv_activity_help) WebView webView;

    private SharedpreferencesManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        initialize();
    }

    private void initialize(){
        ButterKnife.bind(HelpActivity.this);
        manager = new SharedpreferencesManager(HelpActivity.this);

        this.setBackVisibility(true);
        this.setTitle(R.string.help);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(FlagData.FLAG_WEB_HELP + manager.getToken());
    }
}

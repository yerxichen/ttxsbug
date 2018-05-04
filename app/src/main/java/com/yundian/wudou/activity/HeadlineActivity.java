package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class HeadlineActivity extends BaseActivity {

    @Bind(R.id.wv_activity_healine)WebView webView;

    private SharedpreferencesManager manager;
    private String newsid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headline);

        initialize();
    }

    private void initialize() {
        ButterKnife.bind(HeadlineActivity.this);
        manager = new SharedpreferencesManager(HeadlineActivity.this);

        this.setBackVisibility(true);
        this.setTitle(R.string.head_line);

        Intent intent = getIntent();
        newsid = intent.getStringExtra(FlagData.FLAG_STATE);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(FlagData.FLAG_WEB_HEADLINE+manager.getToken()+"&newsid="+newsid);
        Log.e("tag","---------------------------url="+ FlagData.FLAG_WEB_HEADLINE+manager.getToken()+"&newsid="+newsid);
    }
}

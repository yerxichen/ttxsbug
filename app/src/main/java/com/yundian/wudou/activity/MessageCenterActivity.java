package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageCenterActivity extends BaseActivity {

    @Bind(R.id.wv_activity_messagecenter)WebView webView;

    private SharedpreferencesManager manager;
    private String token,type,sid,pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(MessageCenterActivity.this);
        manager = new SharedpreferencesManager(MessageCenterActivity.this);

        this.setBackVisibility(true);
        this.setTitle(R.string.message_center);

        final Intent intent = getIntent();
        type = intent.getStringExtra(FlagData.FLAG_TYPE);
        sid = intent.getStringExtra(FlagData.FLAG_MESSAGE_SID);
        pid = intent.getStringExtra(FlagData.FLAG_MESSAGE_PID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        token = manager.getToken();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(FlagData.FLAG_WEB_MESSAGECENTER + token+"&type="+type+"&sid="+sid+"&pid="+pid);
    }

    private void setEventListener(){
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                Log.e("tag","---------------------------MESSAGECENTER-->>"+url);
                if(url.contains("nativesate")&&url.contains("storeid")){
                    String shopId = "";
                    shopId= url.substring(url.length()-2,url.length());
                    Intent intent =  new Intent(MessageCenterActivity.this,VegetableShopActivity.class);
                    intent.putExtra(FlagData.FLAG_SHOP_ID,shopId);
                    startActivity(intent);
                    return null;
                }else if(url.contains("login.html")){
                    Intent intent = new Intent(MessageCenterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                return super.shouldInterceptRequest(view, url);
            }
        });
    }
}

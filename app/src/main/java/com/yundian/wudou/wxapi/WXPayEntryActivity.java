package com.yundian.wudou.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yundian.wudou.activity.MyAllOrderActivity;

import cn.sharesdk.wechat.utils.WechatHandlerActivity;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class WXPayEntryActivity extends WechatHandlerActivity implements IWXAPIEventHandler {

    private static final String APP_ID = "wxecf915ab4f97f107";
    //    private static final String APP_ID = "wxaf4ff32e6be3a395";
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, APP_ID, false);
        api.handleIntent(getIntent(), WXPayEntryActivity.this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Intent intent = new Intent(WXPayEntryActivity.this, MyAllOrderActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}

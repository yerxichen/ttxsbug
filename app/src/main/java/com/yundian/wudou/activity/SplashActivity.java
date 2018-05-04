package com.yundian.wudou.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanCurrentVersion;
import com.yundian.wudou.network.JsonBeanGetToken;
import com.yundian.wudou.network.JsonBeanHomePage;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class SplashActivity extends Activity implements NetWorkInterface.OnGetInitialTokenListener,
        NetWorkInterface.OnGetHomaPageDataListener, NetWorkInterface.OnGetCurrentVersionListener {

    private static final int REQUEST_READ_PHONE_STATE = 1;

    private SharedpreferencesManager sharedpreferencesManager;
    private NetWorkOperate netWorkOperate;

    private String model, imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initialize();
    }

    private void initialize() {
        sharedpreferencesManager = new SharedpreferencesManager(SplashActivity.this);
        netWorkOperate = new NetWorkOperate(SplashActivity.this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        }else{
            model = Build.MODEL;
            TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            imei = telephonyManager.getDeviceId();
            netWorkOperate.getCurrentVersion();
        }
    }

    @Override
    public void onGetToken(JsonBeanGetToken jsonBeanGetToken) {
        sharedpreferencesManager.saveToken(jsonBeanGetToken.getAccess_token());
        netWorkOperate.getHomaPageData(sharedpreferencesManager.getToken());
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(SplashActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetData(JsonBeanHomePage jsonBeanHomePage) {
        sharedpreferencesManager.saveHomePageJson(jsonBeanHomePage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedpreferencesManager.getFirstShow()) {
                    Intent intent = new Intent(SplashActivity.this, FragmentContainerActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
            }
        }, 2000);
    }

    @Override
    public void onGetCurrentVersion(JsonBeanCurrentVersion jsonBeanCurrentVersion) {
        if (jsonBeanCurrentVersion.getVersion().equals("2.0")) {
            if (!sharedpreferencesManager.getFirstShow()) {
                netWorkOperate.getInitialToken(model, imei);
            } else {
                netWorkOperate.getHomaPageData(sharedpreferencesManager.getToken());
            }
        } else {
            Toast.makeText(this, "有新版本发布，请更新", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setData(Uri.parse(jsonBeanCurrentVersion.getUrl()));
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if ((requestCode == REQUEST_READ_PHONE_STATE) && (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                model = Build.MODEL;
                TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
                imei = telephonyManager.getDeviceId();
                netWorkOperate.getCurrentVersion();
        }
    }
}

package com.yundian.wudou.activity;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.util.Log;
import android.widget.Toast;

import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanCurrentVersion;
import com.yundian.wudou.network.JsonBeanGetToken;
import com.yundian.wudou.network.JsonBeanHomePage;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

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
        //判断权限，如果没有权限则弹出获取权限的提示

//        else {
//            model = Build.MODEL;
//            //获取TelephonyManager服务
//            TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
//            //获取设备id  即IEMI码
//            imei = telephonyManager.getDeviceId();
//            netWorkOperate.getCurrentVersion();
//        }

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
//        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
//        }


        PermissionGen.with(SplashActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .request();
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

    //如果是第一次打开app进入轮播欢迎页  否则直接进主页
    @Override
    public void onGetData(JsonBeanHomePage jsonBeanHomePage) {
        sharedpreferencesManager.saveHomePageJson(jsonBeanHomePage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedpreferencesManager.getFirstShow()) {
                    Intent intent = new Intent(SplashActivity.this, FragmentContainerActivity.class);
                    SplashActivity.this.startActivity(intent);
//                    Intent intent = new Intent(SplashActivity.this, VegetableShopActivity.class);
//                    intent.putExtra(FlagData.FLAG_SHOP_ID, "30");
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
        //获取当前版本号，如果等于2.2则不更新，其他情况提示更新
        if (jsonBeanCurrentVersion.getVersion().equals("2.2")) {
            if (!sharedpreferencesManager.getFirstShow()) {
                netWorkOperate.getInitialToken(model, imei);
            } else {
                netWorkOperate.getHomaPageData(sharedpreferencesManager.getToken());
            }
        } else {
            Toast.makeText(this, "有新版本发布，请更新", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setData(Uri.parse(jsonBeanCurrentVersion.getUrl()));
            intent.setAction(Intent.ACTION_VIEW);//根据情况打开相应的服务（这里就是打开浏览器）
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if ((requestCode == REQUEST_READ_PHONE_STATE) && (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//            model = Build.MODEL;
//            TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
//            imei = telephonyManager.getDeviceId();
//            netWorkOperate.getCurrentVersion();
//        }
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @SuppressLint("MissingPermission")
    @PermissionSuccess(requestCode = 100)
    public void doSomething() {
        model = Build.MODEL;
        //获取TelephonyManager服务
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        //获取设备id  即IEMI码
        imei = telephonyManager.getDeviceId();
        netWorkOperate.getCurrentVersion();
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {
        finish();
    }

}

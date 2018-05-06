package com.yundian.wudou.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
//        ImagePipelineConfig frescoConfig = ImagePipelineConfig.newBuilder(this).setDownsampleEnabled(true).build();
//        Fresco.initialize(this, frescoConfig);
        Fresco.initialize(this);
    }
}

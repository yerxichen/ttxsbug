package com.yundian.wudou.datawork;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.network.JsonBeanHomePage;

/**
 * Created by cookie on 2016/8/18.
 */
public class SharedpreferencesManager {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public SharedpreferencesManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(FlagData.FLAG_SHAREDPREFERENCES, Context.MODE_PRIVATE);
    }

    /**
     * 是否是第一次显示
     **/
    public void saveFirstShow(boolean first) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(FlagData.FLAG_FIRSTSHOW, first);
        mEditor.apply();
    }

    public boolean getFirstShow() {
        return mSharedPreferences.getBoolean(FlagData.FLAG_FIRSTSHOW, false);
    }

    /**
     * 存储Token
     **/
    public void saveToken(String token) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(FlagData.FLAG_TOKEN, token);
        mEditor.apply();
    }

    public String getToken() {
        return mSharedPreferences.getString(FlagData.FLAG_TOKEN, "");
    }

    /**
     * 存储json
     **/
    public void saveEvaluateJson(String json) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(FlagData.FLAG_EVALUATEJSON,json);
        mEditor.apply();
    }

    public String getEvaluateJson(){
        return mSharedPreferences.getString(FlagData.FLAG_EVALUATEJSON,"");
    }

    /**
     * 预加载首页数据
     * */
    public void saveHomePageJson(JsonBeanHomePage jsonBeanHomePage){
        Gson gson = new Gson();
        mEditor = mSharedPreferences.edit();
        mEditor.putString(FlagData.FLAG_HOMEPAGEJSON,gson.toJson(jsonBeanHomePage));
        mEditor.apply();
    }

    public <T> T getHomePageJson(Class<T> type) {
        String str = mSharedPreferences.getString(FlagData.FLAG_HOMEPAGEJSON,"");
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }


    /**
     *是否已登陆
     * */
    public void saveIsLogin(boolean isLogin){
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(FlagData.FLAG_ISLOGIN, isLogin);
        mEditor.apply();
    }

    public boolean isLogin(){
        return mSharedPreferences.getBoolean(FlagData.FLAG_ISLOGIN, false);
    }
}

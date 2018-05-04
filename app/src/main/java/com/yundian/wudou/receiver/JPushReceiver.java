package com.yundian.wudou.receiver;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.yundian.wudou.activity.FragmentContainerActivity;
import com.yundian.wudou.activity.HeadlineActivity;
import com.yundian.wudou.activity.MerchantMessageActivity;
import com.yundian.wudou.activity.MerchantOrderActivity;
import com.yundian.wudou.activity.MessageCenterActivity;
import com.yundian.wudou.activity.MyAllOrderActivity;
import com.yundian.wudou.data.FlagData;

import org.json.JSONObject;

import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class JPushReceiver extends BroadcastReceiver {

    private static final String TAG = "JPushReceiver";
    private NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (null == notificationManager) {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        Bundle bundle = intent.getExtras();

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.e(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.e(TAG, "接受到推送下来的自定义消息");

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.e(TAG, "接受到推送下来的通知");

            receivingNotification(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.e(TAG, "用户点击打开了通知");

            openNotification(context, bundle);

        } else {
            Log.e(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private void receivingNotification(Context context, Bundle bundle){
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Log.e(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        Log.e(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.e(TAG, "extras : " + extras);
    }

    private void openNotification(Context context, Bundle bundle){
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String myValue = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            myValue = extrasJson.optString("tag");
        } catch (Exception e) {
            Log.e(TAG, "Unexpected: extras is not a valid json", e);
            return;
        }
        if(!isAppForground(context)){
            Intent mIntent = new Intent(context, FragmentContainerActivity.class);
            mIntent.putExtras(bundle);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        }
        if ("1".equals(myValue)) {
            Intent mIntent = new Intent(context, MyAllOrderActivity.class);
            mIntent.putExtras(bundle);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        } else if ("2".equals(myValue)){
            Intent mIntent = new Intent(context, MessageCenterActivity.class);
            mIntent.putExtras(bundle);
            mIntent.putExtra(FlagData.FLAG_TYPE, "1");
            mIntent.putExtra(FlagData.FLAG_MESSAGE_SID, "0");
            mIntent.putExtra(FlagData.FLAG_MESSAGE_PID, "0");
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        }else if("3".equals(myValue)){
            Intent mIntent = new Intent(context, HeadlineActivity.class);
            mIntent.putExtras(bundle);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        }else if("4".equals(myValue)){
            Intent mIntent = new Intent(context, MerchantOrderActivity.class);
            mIntent.putExtras(bundle);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        }else if("5".equals(myValue)){
            Intent mIntent = new Intent(context, MessageCenterActivity.class);
            mIntent.putExtras(bundle);
            mIntent.putExtra(FlagData.FLAG_TYPE, "4");
            mIntent.putExtra(FlagData.FLAG_MESSAGE_SID, "0");
            mIntent.putExtra(FlagData.FLAG_MESSAGE_PID, "0");
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        }else if("6".equals(myValue)){
            Intent mIntent = new Intent(context, MerchantOrderActivity.class);
            mIntent.putExtras(bundle);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        }
    }

    private Boolean isAppForground(Context context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return false;
            }
        }
        return true;
    }
}

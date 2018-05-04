package com.yundian.wudou.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import jp.wasabeef.fresco.processors.BlurPostprocessor;

/**
 * Created by Administrator on 2016/11/18 0018.
 * 使用泛型，可以避免类型转换
 */

public final class ViewUtils {

    public static <T extends View> T findViewById(Activity activity, int resId) {
        return (T) activity.findViewById(resId);
    }

    public static <T extends View> T findViewById(View view, int resId) {
        return (T) view.findViewById(resId);
    }
    public static void getFrescoController(Context context, SimpleDraweeView imgIv, String uri, int width, int height) {
        if (uri != null) {
            ImageRequest request = ImageRequestBuilder
                    .newBuilderWithSource(Uri.parse(uri))
                    .setResizeOptions(new ResizeOptions(dip2px(context, width), dip2px(context, height)))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder().setOldController(imgIv.getController()).setImageRequest
                    (request)
                    .build();
            imgIv.setController(controller);
        }
    }
    // 获取状态栏高度
    public static int getStatusBarHeight(Context mContext) {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int dip2px(Context context, double d) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (d * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    public static void getBlurFresco(Context context, SimpleDraweeView simpleDraweeView, String uri) {
        if (uri != null) {
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                    .setPostprocessor(new BlurPostprocessor(context, 25))
                    .build();
            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(simpleDraweeView.getController())
                    .build();

            simpleDraweeView.setController(controller);
        }
    }

}

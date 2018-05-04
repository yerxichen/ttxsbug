package com.yundian.wudou.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jwenfeng.library.pulltorefresh.view.FooterView;
import com.yundian.wudou.R;

/**
 * Created by taozipc on 2017/10/30.
 */

public class MyFooterView extends FrameLayout implements FooterView {
    private ImageView tv;


    public MyFooterView(Context context) {
        this(context, null);
    }

    public MyFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_my_footer, null);
        addView(view);
        tv = (ImageView) view.findViewById(R.id.tv_my_footer);

    }

    @Override
    public void begin() {

    }

    @Override
    public void progress(float progress, float all) {
        float s = progress / all;
        if (s >= 0.9f) {
            tv.setVisibility(View.GONE);
        } else {
            tv.setVisibility(View.GONE);
        }
        if (progress >= all - 10) {
            tv.setVisibility(View.VISIBLE);
            //  tv.setText("——天天蔬心——");
        } else {
            tv.setVisibility(View.GONE);
            //  tv.setText("—天天疏心—");
        }
    }

    @Override
    public void finishing(float progress, float all) {

    }

    @Override
    public void loading() {
        //  arrow.setVisibility(GONE);
        // progressBar.setVisibility(VISIBLE);
        // tv.setText("—天天疏心—");
        tv.setVisibility(View.GONE);
    }

    @Override
    public void normal() {
        tv.setVisibility(View.VISIBLE);
        //  tv.setText("—天天蔬心—");
    }

    @Override
    public View getView() {
        return this;
    }
}

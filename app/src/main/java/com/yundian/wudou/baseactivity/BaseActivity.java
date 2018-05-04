package com.yundian.wudou.baseactivity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.utils.ViewUtils;

/**
 * Created by cookie on 2016/7/2.
 */
public class BaseActivity extends FragmentActivity {

    private OnActionBarRightViewClickListener onActionBarRightViewClickListener;

    private RelativeLayout mRelativeLayoutTitleBar;
    private FrameLayout mFrameLayoutContent;
    private TextView mTextViewBack, mTextViewTitle, mTextViewRight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(R.layout.activity_base);


        //透明状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        initialize();

        setEventListener();
    }

    private void initialize() {
        mTextViewBack = $(R.id.tv_baseactivity_back);
        mTextViewTitle = $(R.id.tv_baseactivity_title);
        mTextViewRight = $(R.id.tv_baseactivity_right);

        mRelativeLayoutTitleBar = $(R.id.rl_activitybase_titlebar);
        mFrameLayoutContent = $(R.id.layout_content);
    }

    private void setEventListener() {
        mTextViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mTextViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onActionBarRightViewClickListener) {
                    onActionBarRightViewClickListener.onClick();
                } else {
                    new Exception().printStackTrace();
                }
            }
        });
    }

    @Override
    public void setContentView(int layoutResID) {
        mFrameLayoutContent.removeAllViews();
        View.inflate(this, layoutResID, mFrameLayoutContent);
        onContentChanged();
    }

    @Override
    public void setContentView(View view) {
        mFrameLayoutContent.removeAllViews();
        mFrameLayoutContent.addView(view);
        onContentChanged();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mFrameLayoutContent.removeAllViews();
        mFrameLayoutContent.addView(view, params);
        onContentChanged();
    }

    /**
     * 设置TitleBar的可见性
     */
    public void setBackVisibility(boolean visibility) {
        if (visibility) {
            mTextViewBack.setVisibility(View.VISIBLE);
        } else {
            mTextViewBack.setVisibility(View.GONE);
        }
    }
    public void setTitleBarVisibility(boolean visibility) {
        if (visibility) {
            mRelativeLayoutTitleBar.setVisibility(View.VISIBLE);
        } else {
            mRelativeLayoutTitleBar.setVisibility(View.GONE);
        }
    }
    public void setRightViewVisibility(boolean visibility) {
        if (visibility) {
            mTextViewRight.setVisibility(View.VISIBLE);
        } else {
            mTextViewRight.setVisibility(View.GONE);
        }
    }

    /**
     * 设置TitleBar的内容
     */
    public void setTitle(String title) {mTextViewTitle.setText(title);}
    public void setTitle(int titleId) {
        mTextViewTitle.setText(getString(titleId));
    }
    public void setRightViewText(String title) {
        mTextViewRight.setText(title);
    }

    /**
     * 自定义的监听接口
     */
    protected interface OnActionBarRightViewClickListener {
            void onClick();
    }

    protected void setRightViewClickListener(OnActionBarRightViewClickListener listener) {
        this.onActionBarRightViewClickListener = listener;
    }

    /**
     * 打Log
     */
    protected void Log(String log) {
        Log.e("Tag", "" + this.getClass().getSimpleName() + "-->" + log);
    }

    /**
     * findviewbyid
     */
    public <T extends View> T $(int resId) {
        return ViewUtils.findViewById(this, resId);
    }
}

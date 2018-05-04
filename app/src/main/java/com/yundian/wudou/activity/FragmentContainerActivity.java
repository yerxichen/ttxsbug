package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.fragment.ClassificationFragment;
import com.yundian.wudou.fragment.HomePageFragment;
import com.yundian.wudou.fragment.MyFragment;
import com.yundian.wudou.fragment.ShoppingCartFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentContainerActivity extends BaseActivity {

    private static final String TAG = "FragmentContainerActivi";

    @Bind(R.id.btn_fragmentcontaineractivity_homepage)Button btnHomePage;
    @Bind(R.id.btn_fragmentcontaineractivity_classification)Button btnClassification;
    @Bind(R.id.btn_fragmentcontaineractivity_shoppingcart)Button btnShoppingCart;
    @Bind(R.id.btn_fragmentcontaineractivity_my)Button btnMy;
    @Bind(R.id.iv_fragmentcontaineractivity_store)ImageView ivStore;

    private SharedpreferencesManager manager;

    private FragmentManager mFragmentManager;
    private Fragment[] mFragments;

    private static Boolean isExit = false;
    private String regionName;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        //透明状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);



        initialize();

        setEventListener();

        Log.d(TAG, "onCreate: ");
    }

    //初始化
    private void initialize() {
        ButterKnife.bind(FragmentContainerActivity.this);
        manager = new SharedpreferencesManager(FragmentContainerActivity.this);

        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        regionName = intent.getStringExtra(FlagData.FLAG_REGION);

        mFragments = new Fragment[4];
        mFragments[0] = new HomePageFragment();
        mFragments[1] = new ClassificationFragment();
        mFragments[2] = new ShoppingCartFragment();
        mFragments[3] = new MyFragment();

        if(regionName!=null){
            Bundle bundle = new Bundle();
            bundle.putString(FlagData.FLAG_REGION,regionName);
            mFragments[0].setArguments(bundle);
        }

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        if (flag == 2) {
            mFragmentTransaction.add(R.id.fragment_container, mFragments[2]);
            mFragmentTransaction.commit();
            setDefaultButtonState(2);
        } else if (flag == 3) {
            mFragmentTransaction.add(R.id.fragment_container, mFragments[3]);
            mFragmentTransaction.commit();
            setDefaultButtonState(3);
        } else {
            mFragmentTransaction.add(R.id.fragment_container, mFragments[0]);
            mFragmentTransaction.commit();
            setDefaultButtonState(0);
        }
    }

    //设置事件监听
    private void setEventListener() {
        btnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(0);
            }
        });

        btnClassification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(1);
            }
        });

        ivStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentContainerActivity.this, StoreActivity.class);
                intent.putExtra("title", "商家");
                intent.putExtra(FlagData.FLAG_STATE,"0");
                startActivity(intent);
            }
        });

        btnShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showFragment(2);
            }
        });

        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (manager.isLogin()) {
                    showFragment(3);
                } else {
                    Intent intent = new Intent(FragmentContainerActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    //显示第几个fragment
    public void showFragment(int i) {
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        for (Fragment fragment : mFragmentManager.getFragments()) {
            mFragmentTransaction.hide(fragment);
        }
        if (mFragments[i].isAdded()) {
            mFragmentTransaction.show(mFragments[i]);
        } else {
            mFragmentTransaction.add(R.id.fragment_container, mFragments[i]);
        }
        mFragmentTransaction.commit();
        setDefaultButtonState(i);
    }

    //改变底部按钮状态
    private void setDefaultButtonState(int i) {
        btnHomePage.setEnabled(true);
        btnClassification.setEnabled(true);
        btnShoppingCart.setEnabled(true);
        btnMy.setEnabled(true);
        switch (i) {
            case 0: {
                btnHomePage.setEnabled(false);
                break;
            }
            case 1: {
                btnClassification.setEnabled(false);
                break;
            }
            case 2: {
                btnShoppingCart.setEnabled(false);
                setTitleBarVisibility(false);
                break;
            }
            case 3: {
                btnMy.setEnabled(false);
                break;
            }
            default: {
                Log.e("Tag", this.toString() + ".default");
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;
    }

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }
}

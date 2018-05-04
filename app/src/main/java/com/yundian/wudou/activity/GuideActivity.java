package com.yundian.wudou.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.yundian.wudou.R;
import com.yundian.wudou.adapter.GuideFragmentAdapter;
import com.yundian.wudou.fragment.GuideOneFragment;
import com.yundian.wudou.fragment.GuideTreeFragment;
import com.yundian.wudou.fragment.GuideTwoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GuideActivity extends FragmentActivity{

    @Bind(R.id.vp_guideactivity)ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        this.initialize();
    }

    private void initialize(){
        ButterKnife.bind(GuideActivity.this);

        List<Fragment> mFragmentList = new ArrayList<>();

        Fragment mFragmentOne = new GuideOneFragment();
        Fragment mFragmentTwo = new GuideTwoFragment();
        Fragment mFragmentTree = new GuideTreeFragment();

        mFragmentList.add(mFragmentOne);
        mFragmentList.add(mFragmentTwo);
        mFragmentList.add(mFragmentTree);

        viewPager.setAdapter(new GuideFragmentAdapter(getSupportFragmentManager(), mFragmentList));
    }
}

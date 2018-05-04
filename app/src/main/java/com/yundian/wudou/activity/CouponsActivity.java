package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.adapter.GuideFragmentAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.fragment.CouponsOneFragment;
import com.yundian.wudou.fragment.CouponsThreeFragment;
import com.yundian.wudou.fragment.CouponsTwoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CouponsActivity extends BaseActivity {

    @Bind(R.id.tv_activity_coupons_notused)
    TextView tvNotUsed;
    @Bind(R.id.tv_activity_coupons_used)
    TextView tvUsed;
    @Bind(R.id.tv_activity_coupons_overdue)
    TextView tvOverdue;
    @Bind(R.id.iv_activity_coupons_left)
    ImageView ivLeft;
    @Bind(R.id.iv_activity_coupons_right)
    ImageView ivRight;
    @Bind(R.id.vp_activity_coupons)
    ViewPager viewPager;

    CouponsOneFragment mFragmentOne = new CouponsOneFragment();
    CouponsTwoFragment mFragmentTwo = new CouponsTwoFragment();
    CouponsThreeFragment mFragmentTree = new CouponsThreeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(CouponsActivity.this);

        this.setBackVisibility(true);
        this.setTitle(getString(R.string.my_coupons));
        this.setRightViewVisibility(true);
        this.setRightViewText(getString(R.string.use_rule));

        List<Fragment> mFragmentList = new ArrayList<>();

        mFragmentList.add(mFragmentOne);
        mFragmentList.add(mFragmentTwo);
        mFragmentList.add(mFragmentTree);

        viewPager.setAdapter(new GuideFragmentAdapter(getSupportFragmentManager(), mFragmentList));
        viewPager.setOffscreenPageLimit(3);
    }

    private void setEventListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setTablePosition(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        tvNotUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {viewPager.setCurrentItem(0);
            }
        });

        tvUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);

            }
        });

        tvOverdue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });

        this.setRightViewClickListener(new OnActionBarRightViewClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(CouponsActivity.this, CouponRulesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setTablePosition(int position, float positionOffset) {
        ivLeft.setLayoutParams(new LinearLayout.LayoutParams(0, 0, position + positionOffset));
        ivRight.setLayoutParams(new LinearLayout.LayoutParams(0, 0, 2 - position - positionOffset));
    }
}

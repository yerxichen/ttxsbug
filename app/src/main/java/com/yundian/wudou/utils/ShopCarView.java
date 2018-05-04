package com.yundian.wudou.utils;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.yundian.wudou.R;
import com.yundian.wudou.activity.VegetableShopActivity;

import java.math.BigDecimal;

import static com.yundian.wudou.activity.VegetableShopActivity.mBottomAdapter;


public class ShopCarView extends FrameLayout {
    private TextView car_limit, tv_amount;
    public ImageView iv_shop_car;
    public TextView car_badge;
    private BottomSheetBehavior behavior;
    public boolean sheetScrolling;
    public int[] carLoc;

    public void setBehavior(final BottomSheetBehavior behavior) {
        this.behavior = behavior;
    }

    public void setBehavior(final BottomSheetBehavior behavior, final View blackView) {
        this.behavior = behavior;
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                sheetScrolling = false;
                if (newState == BottomSheetBehavior.STATE_COLLAPSED || newState == BottomSheetBehavior.STATE_HIDDEN) {
                    blackView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                sheetScrolling = true;
                blackView.setVisibility(View.VISIBLE);
                ViewCompat.setAlpha(blackView, slideOffset);
            }
        });
        blackView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                return true;
            }
        });
    }

    public ShopCarView(@NonNull Context context) {
        super(context);
    }

    public ShopCarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (iv_shop_car == null) {
            iv_shop_car = findViewById(R.id.iv_activitystoredetails_shoppingcart_tv);
            car_badge = findViewById(R.id.tv_activitystoredetails_total);
            car_limit = findViewById(R.id.tv_activitystoredetails_settlement);
            tv_amount = findViewById(R.id.tv_activitystoredetails_message);
        //    iv_shop_car.setOnClickListener(new toggleCar());
            carLoc = new int[2];
            iv_shop_car.getLocationInWindow(carLoc);
            carLoc[0] = carLoc[0] + iv_shop_car.getWidth() / 2 - ViewUtils.dip2px(getContext(), 10);
        }
    }




    public class toggleCar implements OnClickListener {

        @Override
        public void onClick(View view) {
            if (sheetScrolling) {
                return;
            }
			if (mBottomAdapter.getCount() == 0) {
				return;
			}
            if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            } else {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        }
    }

}

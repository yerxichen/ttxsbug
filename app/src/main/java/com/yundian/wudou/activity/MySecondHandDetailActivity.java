package com.yundian.wudou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanMySecondHandDivisionDetails;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MySecondHandDetailActivity extends BaseActivity implements NetWorkInterface.OnGetMySecondHandDivisionDetailsListener {

    @Bind(R.id.cb_activity_mysecondhandetail)ConvenientBanner banner;
    @Bind(R.id.wv_activity_mysecondhanddetail)WebView webView;
    @Bind(R.id.tv_activity_mysecondhanddetail_name)TextView commodityName;
    @Bind(R.id.tv_activity_mysecondhanddetail_shopprice)TextView commodityPrice;
    @Bind(R.id.tv_activity_mysecondhanddetail_time)TextView commodityTime;
    @Bind(R.id.tv_activity_mysecondhanddetail_sate)TextView commoditySate;
    @Bind(R.id.tv_activity_mysecondhanddetail_reviewedsate)TextView reviewedSate;

    private static final int DELAY_BANNER = 3000;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private CBViewHolderCreator cbViewHolderCreator;
    private List<String> imageAddressList;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysecondhand_detail);

        initialize();
    }

    private void initialize() {
        ButterKnife.bind(MySecondHandDetailActivity.this);
        manager = new SharedpreferencesManager(MySecondHandDetailActivity.this);
        netWorkOperate = new NetWorkOperate(MySecondHandDetailActivity.this);

        setTitle("我的二手品");
        setBackVisibility(true);

        Intent intent = getIntent();
        pid = intent.getStringExtra(FlagData.FLAG_PRODUCT_ID);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(FlagData.FLAG_WEB_MYSECONDHANDCOMMODITYDETAILS + "access_token=" + manager.getToken() + "&pid=" + pid);

        imageAddressList = new ArrayList<>();
        cbViewHolderCreator = new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new UrlImageHolderView();
            }
        };
        banner.setPages(cbViewHolderCreator, imageAddressList)
                .setPageIndicator(new int[]{R.drawable.banner_before, R.drawable.banner_after});
    }

    @Override
    protected void onResume() {
        super.onResume();
        banner.startTurning(DELAY_BANNER);
        netWorkOperate.getMySecondHandDivisionDetails(manager.getToken(), pid);
    }

    @Override
    public void onGetMySecondHandDivisionDetails(JsonBeanMySecondHandDivisionDetails jsonBeanMySecondHandDivision) {
        commodityName.setText(jsonBeanMySecondHandDivision.getProductsinfo().getName());
        commodityPrice.setText("¥ "+jsonBeanMySecondHandDivision.getProductsinfo().getShopprice());
        commodityTime.setText("发布时间 : "+jsonBeanMySecondHandDivision.getProductsinfo().getTime());
        commoditySate.setText(jsonBeanMySecondHandDivision.getProductsinfo().getSate());

        if (jsonBeanMySecondHandDivision.getProductsinfo().getReviewedsate().equals("0")) {
            reviewedSate.setText("已审核");
        } else {
            reviewedSate.setText("未审核");
        }

        imageAddressList.clear();
        for (JsonBeanMySecondHandDivisionDetails.ProductsinfoBean.ImagesBean.DataBean dataBean : jsonBeanMySecondHandDivision.getProductsinfo().getImages().getData()) {
            imageAddressList.add(FlagData.FLAG_IMGADDRESS + dataBean.getImg());
        }
        banner.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(MySecondHandDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        banner.stopTurning();
    }

    public class UrlImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(MySecondHandDetailActivity.this).load(data).into(imageView);
        }
    }
}

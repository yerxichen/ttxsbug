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
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanMyServicesDetails;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyServiceDetailActivity extends BaseActivity implements NetWorkInterface.OnGetMyServicesDetailsListener {

    @Bind(R.id.cb_activity_myservicedetail)ConvenientBanner banner;
    @Bind(R.id.wv_activity_myservicedetail)WebView webView;
    @Bind(R.id.tv_activity_myservicedetail_name)TextView serviceName;
    @Bind(R.id.tv_activity_myservicedetail_time)TextView serviceTime;
    @Bind(R.id.tv_activity_myservicedetail_sate)TextView serviceSate;
    @Bind(R.id.tv_activity_myservicedetail_reviewedsate)TextView reviewedSate;

    private static final int DELAY_BANNER = 3000;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private CBViewHolderCreator cbViewHolderCreator;
    private List<String> imageAddressList;
    private String newsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myservice_detail);

        initialize();
    }

    private void initialize() {
        ButterKnife.bind(MyServiceDetailActivity.this);
        manager = new SharedpreferencesManager(MyServiceDetailActivity.this);
        netWorkOperate = new NetWorkOperate(MyServiceDetailActivity.this);

        setTitle("我的便民服务");
        setBackVisibility(true);

        Intent intent = getIntent();
        newsId = intent.getStringExtra(FlagData.FLAG_NEWS_ID);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(FlagData.FLAG_WEB_MYCONVENIENCESERVICESDETAILS + "access_token=" + manager.getToken() + "&newsid=" + newsId);

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
        netWorkOperate.getMyServicesDetails(manager.getToken(), newsId);
    }

    @Override
    public void onGetMyServicesDetails(JsonBeanMyServicesDetails jsonBeanMyServicesDetails) {
        serviceName.setText(jsonBeanMyServicesDetails.getNewsinfo().getName());
        serviceSate.setText(jsonBeanMyServicesDetails.getNewsinfo().getSate());
        serviceTime.setText("发布时间 : "+jsonBeanMyServicesDetails.getNewsinfo().getTime());

        if (jsonBeanMyServicesDetails.getNewsinfo().getReviewedsate().equals("0")) {
            reviewedSate.setText("已审核");
        } else {
            reviewedSate.setText("未审核");
        }

        imageAddressList.clear();
        for (JsonBeanMyServicesDetails.NewsinfoBean.ImagesBean.DataBean dataBean : jsonBeanMyServicesDetails.getNewsinfo().getImages().getData()) {
            imageAddressList.add(FlagData.FLAG_IMGADDRESS + dataBean.getImg());
        }
        banner.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(MyServiceDetailActivity.this,msg,Toast.LENGTH_SHORT).show();
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
            Picasso.with(MyServiceDetailActivity.this).load(data).into(imageView);
        }
    }
}

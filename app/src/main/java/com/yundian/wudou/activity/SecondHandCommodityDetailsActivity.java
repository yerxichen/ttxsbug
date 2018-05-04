package com.yundian.wudou.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.WebSettings;
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
import com.yundian.wudou.network.JsonBeanSecondHandDivisionDetails;
import com.yundian.wudou.network.JsonBeanSecondHandMobile;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

public class SecondHandCommodityDetailsActivity extends BaseActivity implements NetWorkInterface.OnGetSecondHandDivisionDetailsListener,NetWorkInterface.OnGetSecondHandMobileListener {
    // 轮播图
    private static final int DELAY_BANNER = 3000;
    private CBViewHolderCreator mCbViewHolderCreator;
    private ConvenientBanner mConvenientBanner;
    private List<String> mListImageAddress;

    private WebView mWebView;
    private TextView mTextViewName, mTextViewPrice, mTextViewTime, mTextViewState,viewContact;

    private SharedpreferencesManager mSharedpreferencesManager;
    private NetWorkOperate mNetWorkOperate;
    private String strToken, strPid,creditsValue,contact,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_commodity_details);

        initialize();

        setEventListener();
    }

    private void initialize() {
        setTitle("二手品详情");
        setBackVisibility(true);

        Intent intent = getIntent();
        strPid = intent.getStringExtra(FlagData.FLAG_PRODUCT_ID);

        mTextViewName = $(R.id.tv_activity_secondcommoditydetails_goods_name);
        mTextViewPrice = $(R.id.tv_activity_secondcommoditydetails_goods_price);
        mTextViewTime = $(R.id.tv_activity_secondcommoditydetails_goods_time);
        mTextViewState = $(R.id.tv_activity_secondcommoditydetails_icon_state);

        viewContact = $(R.id.tv_layout_linkman_viewcontact);

        mWebView = $(R.id.wv_activity_secondcommoditydetails_goods_describe);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(FlagData.FLAG_WEB_SECONDHANDCOMMODITYDETAILS + "access_token=" + strToken + "&pid=" + strPid);

        mListImageAddress = new ArrayList<>();
        mConvenientBanner = $(R.id.cb_activity_secondcommoditydetails);
        mCbViewHolderCreator = new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new UrlImageHolderView();
            }
        };
        mConvenientBanner.setPages(mCbViewHolderCreator, mListImageAddress)
                .setPageIndicator(new int[]{R.drawable.banner_before, R.drawable.banner_after});

        mSharedpreferencesManager = new SharedpreferencesManager(SecondHandCommodityDetailsActivity.this);
        mNetWorkOperate = new NetWorkOperate(SecondHandCommodityDetailsActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mConvenientBanner.startTurning(DELAY_BANNER);
        strToken = mSharedpreferencesManager.getToken();
        mNetWorkOperate.getSecondHandDivisionDetails(strToken, strPid);
    }

    private void setEventListener() {
        //查看联系人
        viewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+mTextViewMobile.getText()));
                startActivity(intent);*/
                if(mobile.equals("")){
                    showReduceCreditsValueDialog("进行此操作需扣除"+creditsValue+"积分");
                }else{
                    showAlreadyChangedDialog("已兑换");
                }
            }
        });
    }

    @Override
    public void onGetSecondHandDivisionDetails(JsonBeanSecondHandDivisionDetails jsonBeanSecondHandDivisionDetails) {
        contact = jsonBeanSecondHandDivisionDetails.getProductsinfo().getContacts();
        mobile = jsonBeanSecondHandDivisionDetails.getProductsinfo().getMobile();
        creditsValue = jsonBeanSecondHandDivisionDetails.getProductsinfo().getCreditsvalue();

        if(mobile.equals("")){
            viewContact.setText("查看联系人");
        }else{
            viewContact.setText(contact+"     "+mobile);
        }

        mTextViewName.setText(jsonBeanSecondHandDivisionDetails.getProductsinfo().getName());
        mTextViewPrice.setText(jsonBeanSecondHandDivisionDetails.getProductsinfo().getShopprice());
        mTextViewTime.setText(jsonBeanSecondHandDivisionDetails.getProductsinfo().getTime());
        mTextViewState.setText(jsonBeanSecondHandDivisionDetails.getProductsinfo().getSate());

        mListImageAddress.clear();
        for (JsonBeanSecondHandDivisionDetails.ProductsinfoBean.ImagesBean.DataBean dataBean : jsonBeanSecondHandDivisionDetails.getProductsinfo().getImages().getData()) {
            mListImageAddress.add(FlagData.FLAG_IMGADDRESS + dataBean.getImg());
        }
        mConvenientBanner.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(SecondHandCommodityDetailsActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();
    }

    @Override
    public void onGetSecondHandMobile(JsonBeanSecondHandMobile jsonBeanSecondHandMobile) {
        viewContact.setText(contact+"     "+jsonBeanSecondHandMobile.getMobile());
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
            Picasso.with(SecondHandCommodityDetailsActivity.this).load(data).into(imageView);
        }
    }

    /**
     * 显示确认扣除积分的对话框
     * */
    private void showReduceCreditsValueDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(SecondHandCommodityDetailsActivity.this);
        builder.setMessage(msg);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNetWorkOperate.getSecondHandMobile(strToken,strPid);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    /**
     * 显示已兑换的对话框
     * */
    private void showAlreadyChangedDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(SecondHandCommodityDetailsActivity.this);
        builder.setMessage(msg);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

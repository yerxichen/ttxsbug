package com.yundian.wudou.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.yundian.wudou.network.JsonBeanConvenienceServicesDetails;
import com.yundian.wudou.network.JsonBeanServiceMobile;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

public class ConvenienceServicesDetailsActivity extends BaseActivity implements NetWorkInterface.OnGetConvenienceServicesDetailsListener, NetWorkInterface.OnGetServiceMobileListener {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 2;

    // 轮播图
    private static final int DELAY_BANNER = 3000;
    private CBViewHolderCreator mCbViewHolderCreator;
    private ConvenientBanner mConvenientBanner;
    private List<String> mListImageAddress;

    private WebView mWebView;
    private TextView mTextViewName, mTextViewTime, mTextViewState, viewContact;

    private SharedpreferencesManager mSharedpreferencesManager;
    private NetWorkOperate mNetWorkOperate;
    private String strToken, strNewsId, creditsValue, contact, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_details);

        initialize();
        setEventListener();
    }

    private void initialize() {
        setTitle("服务详情");
        setBackVisibility(true);

        Intent intent = getIntent();
        strNewsId = intent.getStringExtra("newsid");

        viewContact = $(R.id.tv_layout_linkman_viewcontact);
        mTextViewName = $(R.id.tv_activity_servicesdetails_goods_name);
        mTextViewTime = $(R.id.tv_activity_servicesdetails_goods_time);
        mTextViewState = $(R.id.tv_activity_servicesdetails_icon_state);

        mWebView = $(R.id.wv_activity_commodity_details_introduce);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(FlagData.FLAG_WEB_CONVENIENCESERVICESDETAILS + "access_token=" + strToken + "&newsid=" + strNewsId);

        mListImageAddress = new ArrayList<>();
        mConvenientBanner = $(R.id.cb_activity_servicesdetails);
        mCbViewHolderCreator = new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new UrlImageHolderView();
            }
        };
        mConvenientBanner.setPages(mCbViewHolderCreator, mListImageAddress)
                .setPageIndicator(new int[]{R.drawable.banner_before, R.drawable.banner_after});

        mSharedpreferencesManager = new SharedpreferencesManager(ConvenienceServicesDetailsActivity.this);
        mNetWorkOperate = new NetWorkOperate(ConvenienceServicesDetailsActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mConvenientBanner.startTurning(DELAY_BANNER);
        strToken = mSharedpreferencesManager.getToken();
        mNetWorkOperate.getConvenienceServicesDetails(strToken, strNewsId);
    }

    private void setEventListener() {
        //查看联系人
        viewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobile.equals("")) {
                    showReduceCreditsValueDialog("进行此操作需扣除" + creditsValue + "积分");
                } else {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onGetConvenienceServicesDetails(JsonBeanConvenienceServicesDetails jsonBeanConvenienceServicesDetails) {
        creditsValue = jsonBeanConvenienceServicesDetails.getNewsinfo().getCreditsvalue();
        contact = jsonBeanConvenienceServicesDetails.getNewsinfo().getContacts();
        mobile = jsonBeanConvenienceServicesDetails.getNewsinfo().getMobile();

        if (!mSharedpreferencesManager.isLogin()) {
            viewContact.setVisibility(View.GONE);
        } else {
            viewContact.setVisibility(View.VISIBLE);

            if (mobile.equals("")) {
                viewContact.setText("查看联系人");
            } else {
                viewContact.setText(contact + "     " + mobile);
            }
        }

        mTextViewName.setText(jsonBeanConvenienceServicesDetails.getNewsinfo().getName());
        mTextViewTime.setText(jsonBeanConvenienceServicesDetails.getNewsinfo().getTime());
        mTextViewState.setText(jsonBeanConvenienceServicesDetails.getNewsinfo().getSate());


        mListImageAddress.clear();
        for (JsonBeanConvenienceServicesDetails.NewsinfoBean.ImagesBean.DataBean dataBean : jsonBeanConvenienceServicesDetails.getNewsinfo().getImages().getData()) {
            mListImageAddress.add(FlagData.FLAG_IMGADDRESS + dataBean.getImg());
        }
        mConvenientBanner.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(ConvenienceServicesDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();
    }

    @Override
    public void onGetServiceMobile(JsonBeanServiceMobile jsonBeanServiceMobile) {
        viewContact.setText(contact + "     " + jsonBeanServiceMobile.getMobile());
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
            Picasso.with(ConvenienceServicesDetailsActivity.this).load(data).into(imageView);
        }

    }

    /**
     * 显示确认扣除积分的对话框
     */
    private void showReduceCreditsValueDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ConvenienceServicesDetailsActivity.this);
        builder.setMessage(msg);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNetWorkOperate.getServiceMobile(strToken, strNewsId);
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
     */
    private void showAlreadyChangedDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ConvenienceServicesDetailsActivity.this);
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

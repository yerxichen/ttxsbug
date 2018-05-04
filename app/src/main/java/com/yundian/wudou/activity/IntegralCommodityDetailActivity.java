package com.yundian.wudou.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanExchangeIntegralCommodity;
import com.yundian.wudou.network.JsonBeanIntergalCommodityInfo;
import com.yundian.wudou.network.JsonBeanLoadAddress;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

public class IntegralCommodityDetailActivity extends BaseActivity implements NetWorkInterface.OnGetIntergalCommodityInfoListener,
        NetWorkInterface.OnLoadAddressListener,NetWorkInterface.OnExchangeIntegralCommodityListener{
    // 轮播图
    private static final int DELAY_BANNER = 3000;
    private ConvenientBanner mConvenientBanner;
    private List<String> mListImageAddress;

    private BottomSheetLayout mBottomSheetLayout;
    private View bottomSheet;
    private RelativeLayout mRelativeLayout;

    private TextView mTextViewPoint,mTextViewBottomPoint,mTextViewName,mTextViewPhone,mTextViewAddress;
    private Button mButtonExchange,buttonPay;
    private WebView mWebView;

    private SharedpreferencesManager mSharedpreferencesManager;
    private NetWorkOperate mNetWorkOperate;
    private String strToken,strPid,strAddressId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intergalcommodity_detail);

        initialize();

        setEventListener();
    }

    private void initialize() {
        setTitle("商品详情");
        setBackVisibility(true);

        Intent intent = getIntent();
        strPid = intent.getStringExtra(FlagData.FLAG_PRODUCT_ID);

        mTextViewPoint = $(R.id.tv_activity_pointshop_commoditydetails_point);
        mButtonExchange =$(R.id.btn_activity_pointshop_commoditydetails);
        mWebView = $(R.id.tv_activity_pointshop_commoditydetails_goodsintroduce);
        mBottomSheetLayout = $(R.id.bsl_pointshop_commodity_details);

        if (bottomSheet == null) {
            bottomSheet = LayoutInflater.from(this).inflate(R.layout.layout_activity_integralcommoditydetail_bottom,
                    (ViewGroup) getWindow().getDecorView(), false);
            bottomSheet.setBackgroundColor(Color.WHITE);
        }
        mTextViewName = (TextView)bottomSheet.findViewById(R.id.tv_integralcommoditydetails_name);
        mTextViewPhone = (TextView)bottomSheet.findViewById(R.id.tv_integralcommoditydetails_phone);
        mTextViewAddress = (TextView)bottomSheet.findViewById(R.id.tv_integralcommoditydetails_address);
        mRelativeLayout = (RelativeLayout)bottomSheet.findViewById(R.id.rl_integralcommoditydetails);
        mTextViewBottomPoint = (TextView)bottomSheet.findViewById(R.id.tv_integralcommoditydetails_integral_content);
        buttonPay = (Button) bottomSheet.findViewById(R.id.btn_integralcommoditydetails_pay);

        //轮播图
        mConvenientBanner = $(R.id.cb_pointshop_commodity_details);

        mListImageAddress = new ArrayList<>();

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(FlagData.FLAG_WEB_INTERGALSHOPCOMMODITYDETAILS + "access_token=" + strToken + "&pid=" + strPid);

        mSharedpreferencesManager = new SharedpreferencesManager(getApplicationContext());
        mNetWorkOperate = new NetWorkOperate(IntegralCommodityDetailActivity.this);
        strToken = mSharedpreferencesManager.getToken();
        mNetWorkOperate.getAddress(strToken);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNetWorkOperate.getIntergalCommodityInfo(strToken,strPid);
    }

    private void setEventListener() {
        mButtonExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntegralCommodityDetailActivity.this, SelectAddressActivity.class);
                intent.putExtra("state", 9);
                startActivityForResult(intent, 9);
            }
        });
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNetWorkOperate.exchangeIntergalCommodity(strToken,strAddressId,strPid);
            }
        });
    }

    @Override
    public void onGetIntergalCommodityInfo(JsonBeanIntergalCommodityInfo jsonBeanIntergalCommodityInfo) {
        mListImageAddress.clear();
        for(JsonBeanIntergalCommodityInfo.ProductsinfoBean.ImagesBean.DataBean dataBean : jsonBeanIntergalCommodityInfo.getProductsinfo()
        .getImages().getData()){
            mListImageAddress.add(FlagData.FLAG_IMGADDRESS + dataBean.getImg() );
        }
        mConvenientBanner.setPages(new CBViewHolderCreator<UrlImageHolderView>() {
            @Override
            public UrlImageHolderView createHolder() {
                return new UrlImageHolderView();
            }
        }, mListImageAddress)
                .setPageIndicator(new int[]{R.drawable.banner_before, R.drawable.banner_after})
                .startTurning(DELAY_BANNER);

        mTextViewPoint.setText(jsonBeanIntergalCommodityInfo.getProductsinfo().getShopprice());
        mTextViewBottomPoint.setText(jsonBeanIntergalCommodityInfo.getProductsinfo().getShopprice());
        mConvenientBanner.notifyDataSetChanged();
    }

    @Override
    public void onLoadAddress(JsonBeanLoadAddress jsonBeanLoadAddress) {
        JsonBeanLoadAddress.DataBean data = jsonBeanLoadAddress.getData().get(0);
        mTextViewName.setText(data.getConsignee());
        mTextViewPhone.setText(data.getMobile());
        mTextViewAddress.setText(data.getAddress());

        strAddressId = data.getSaid();
    }

    @Override
    public void onExchangeIntegralCommodity(JsonBeanExchangeIntegralCommodity jsonBeanExchangeIntegralCommodity) {
        if (mBottomSheetLayout.isSheetShowing()) {
            mBottomSheetLayout.dismissSheet();
        }
        Intent intent = new Intent(IntegralCommodityDetailActivity.this,MyAllOrderActivity.class);
        startActivity(intent);
        IntegralCommodityDetailActivity.this.finish();
    }

    @Override
    public void onFailure(String msg) {
        mTextViewName.setText("请选择收获地址");
        mTextViewPhone.setVisibility(View.GONE);
        mTextViewAddress.setVisibility(View.GONE);
        Toast.makeText(IntegralCommodityDetailActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 9 && resultCode == 9) {
            mTextViewPhone.setVisibility(View.VISIBLE);
            mTextViewAddress.setVisibility(View.VISIBLE);
            mTextViewName.setText(data.getStringExtra("name"));
            mTextViewPhone.setText(data.getStringExtra("phone"));
            mTextViewAddress.setText(data.getStringExtra("locate"));
            strAddressId = data.getStringExtra("addressid");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showBottomSheet() {
        mBottomSheetLayout.showWithSheetView(bottomSheet);
        if (mBottomSheetLayout.isSheetShowing()) {
            mBottomSheetLayout.dismissSheet();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();
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
            Picasso.with(IntegralCommodityDetailActivity.this).load(data).into(imageView);
        }
    }
}
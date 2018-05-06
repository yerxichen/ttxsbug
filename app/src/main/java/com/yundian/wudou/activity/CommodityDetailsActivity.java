package com.yundian.wudou.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigButton;
import com.mylhyl.circledialog.callback.ConfigText;
import com.mylhyl.circledialog.callback.ConfigTitle;
import com.mylhyl.circledialog.params.ButtonParams;
import com.mylhyl.circledialog.params.TextParams;
import com.mylhyl.circledialog.params.TitleParams;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterShoopingcartData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.OrderManager;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanCommodityDetailsComment;
import com.yundian.wudou.network.JsonBeanCommodityDetailsData;
import com.yundian.wudou.network.JsonBeanConsumerUncheckMessage;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CommodityDetailsActivity extends AppCompatActivity implements NetWorkInterface.OnGetConsumerUncheckMessageListener,
        NetWorkInterface.OnGetCommodityDetailsCommentListener, NetWorkInterface.OnGetCommodityDetailsDataListener, View.OnClickListener {

    @Bind(R.id.tv_activity_commoditydetail_titleback)
    TextView titleBarBack;
    @Bind(R.id.iv_activity_commoditydetail_titlemessage)
    ImageView titleBarMessage;

    @Bind(R.id.cb_activity_commoditydetails)
    ConvenientBanner convenientBanner;
    @Bind(R.id.tv_activity_commoditydetails_productname)
    TextView tvProductName;
    @Bind(R.id.tv_activity_commoditydetails_productprice)
    TextView tvProductPrice;
    @Bind(R.id.tv_activity_commoditydetails_productmonthlysales)
    TextView tvMonthlySales;
    @Bind(R.id.iv_activity_commoditydetails_productadd)
    ImageView ivProductAdd;
    @Bind(R.id.iv_activity_commoditydetails_productreduce)
    ImageView ivProductReduce;
    @Bind(R.id.tv_activity_commoditydetails_productcount)
    TextView tvProductCount;
    @Bind(R.id.iv_activity_commoditydetails_storeicon)
    ImageView ivStoreIcon;
    @Bind(R.id.tv_activity_commoditydetails_storename)
    TextView tvStoreName;
    @Bind(R.id.tv_activity_commoditydetails_sendprice)
    TextView tvSendPrice;
    @Bind(R.id.tv_activity_commoditydetails_startprice)
    TextView tvStartPrice;
    @Bind(R.id.tv_activity_commoditydetails_storestate)
    TextView tvStoreState;
    @Bind(R.id.rb_activity_commoditydetails_storestar)
    RatingBar rbStoreStar;


    @Bind(R.id.rl_activity_commoditydetails_moredetails)
    RelativeLayout rlMoreDetails;

    @Bind(R.id.tv_activity_commoditydetails_totalcount)
    TextView tvTotalCount;
    @Bind(R.id.tv_activity_commoditydetails_totalprice)
    TextView tvTotalPrice;
    @Bind(R.id.btn_act_commoditydetail_toshopcart)
    TextView tvToShoppingCart;
    @Bind(R.id.btn_act_commoditydetail_settlement)
    TextView tvSettlement;
    @Bind(R.id.wv_activity_commodity_details_more)
    WebView webView;


    private SharedpreferencesManager sharedpreferencesManager;
    private NetWorkOperate netWorkOperate;
    private OrderManager orderManager;
    private DecimalFormat decimalFormat;


    private List<String> mListBannerUrls;
    private List<AdapterShoopingcartData> mListSettlementData;


    private String storeId, storeName, storeUrl, startPrice, sendPrice, starCount, isOpen,
            productId, productName, productUrl, productPrice, monthlySales;

    private float totalPrice, settlePrice;
    private int productCount;
    private boolean isOpens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_details);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        initialize();
    }


    private void initialize() {
        ButterKnife.bind(CommodityDetailsActivity.this);
        decimalFormat = new DecimalFormat("0.00");
        netWorkOperate = new NetWorkOperate(CommodityDetailsActivity.this);
        orderManager = new OrderManager(CommodityDetailsActivity.this);
        sharedpreferencesManager = new SharedpreferencesManager(CommodityDetailsActivity.this);

        Intent intent = getIntent();
        productId = intent.getStringExtra(FlagData.FLAG_PRODUCT_ID);
        isOpens = true;
        mListBannerUrls = new ArrayList<>();
        mListSettlementData = new ArrayList<>();


        rlMoreDetails.setOnClickListener(this);

        ivProductAdd.setOnClickListener(this);
        ivProductReduce.setOnClickListener(this);
        tvToShoppingCart.setOnClickListener(this);
        tvSettlement.setOnClickListener(this);
        setTitleBarClickListener();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setVerticalScrollBarEnabled(false);
        webView.setVerticalScrollbarOverlay(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setHorizontalScrollbarOverlay(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        Log.d("gao", ">>>>" + webView.getHeight());

        webView.loadUrl("http://admin.ttsxin.com/productsinfo/?" + "access_token=" + sharedpreferencesManager.getToken() + "&pid=" + productId);

        //   webView.loadUrl("https://www.baidu.com/s?wd=github&rsv_spt=1&rsv_iqid=0xc68ea8e000006cfa&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=1&rsv_t=c1c026yz4sjvm3WIeNdLUvRpLuYlh%2BLWDJMpv3b802ABv4a8ApX7L99o%2BLX2TN5MZePM&oq=scrollview%25E5%25B5%258C%25E5%25A5%2597webview%25E9%25AB%2598%25E5%25BA%25A6%25E6%2598%25BE%25E7%25A4%25BA%25E4%25B8%258D%25E6%25AD%25A3%25E7%25A1%25AE&rsv_pq=af7da3dd00013fcc&inputT=2223079&rsv_sug3=36&rsv_sug1=12&rsv_sug7=101&bs=scrollview%E5%B5%8C%E5%A5%97webview%E9%AB%98%E5%BA%A6%E6%98%BE%E7%A4%BA%E4%B8%8D%E6%AD%A3%E7%A1%AE");
        //webView.loadUrl(FlagData.FLAG_WEB_MORECOMMODITYDETAILS + "access_token=" + sharedpreferencesManager.getToken() + "&pid=" + productId);
        // Log.d(">>dd>>", FlagData.FLAG_WEB_MORECOMMODITYDETAILS + "access_token=" + sharedpreferencesManager.getToken() + "&pid=" + productId);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                //这个是一定要加上那个的,配合scrollView和WebView的height=wrap_content属性使用
                int w = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.AT_MOST);
                int h = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.AT_MOST);
                //重新测量

                webView.measure(w, h);
//                Log.d("重新测量gao", ">>>>" + webView.getHeight());
//                Log.d("getWidth", ">>>>" + webView.getWidth());
//                Log.d("重新测量getContentHeightgao", ">>>>" + webView.getContentHeight());
            }
        });

    }


    /**
     * 系统回调onResume
     */
    @Override
    protected void onResume() {
        super.onResume();

        productCount = orderManager.getProductCount(productId);
        if (productCount != 0) {
            showReduceAndCount();
        } else {
            hideReduceAndCount();
        }
        tvProductCount.setText(productCount + "");

        netWorkOperate.getConsumerUncheckMessage(sharedpreferencesManager.getToken());
        netWorkOperate.getCommodityDetailsData(sharedpreferencesManager.getToken(), productId);
        netWorkOperate.getCommodityDetailsComment(sharedpreferencesManager.getToken(), productId);
    }

    /**
     * 页面的点击事件
     */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rl_activity_commoditydetails_moredetails:
                intent = new Intent(CommodityDetailsActivity.this, MoreCommodityDetailsActivity.class);
                intent.putExtra("pid", productId);
                startActivity(intent);
                break;

            case R.id.iv_activity_commoditydetails_productadd:
                //   if (isOpens==true){

                if (productCount == 0) {
                    showReduceAndCount();
                }
                productCount++;
                tvProductCount.setText(productCount + "");
                orderManager.addProduct(storeId, storeName, storeUrl, productId, productName, productUrl, productPrice, "", startPrice, sendPrice);
                changeBottomState();
                //   }
                break;
            case R.id.iv_activity_commoditydetails_productreduce:
                if (isOpens == true) {

                    if (productCount >= 1) {
                        if (productCount == 1) {
                            hideReduceAndCount();
                        }
                        productCount--;
                        tvProductCount.setText(productCount + "");
                        orderManager.reduceProduct(productId);
                        changeBottomState();
                    }
                }
                break;
            case R.id.btn_act_commoditydetail_toshopcart:
                intent = new Intent(CommodityDetailsActivity.this, FragmentContainerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("flag", 2);
                startActivity(intent);
                CommodityDetailsActivity.this.finish();
                break;
            case R.id.btn_act_commoditydetail_settlement:
                float tempPrice = 0;
                if (sharedpreferencesManager.isLogin()) {
                    if (productCount == 0) {
                        Toast.makeText(CommodityDetailsActivity.this, "未选购商品", Toast.LENGTH_SHORT).show();
                    } else {
                        intent = new Intent(CommodityDetailsActivity.this, SettlementActivity.class);
                        mListSettlementData = orderManager.getCartDataByPid(productId);
                        for (AdapterShoopingcartData data : mListSettlementData) {
                            tempPrice += data.getProductPrice() * data.getProductCount();
                        }
//                        if (tempPrice < Float.parseFloat(startPrice)) {
//                            Toast.makeText(CommodityDetailsActivity.this, "未达到起送价", Toast.LENGTH_SHORT).show();
//                        } else {
                        intent.putExtra("list", (Serializable) mListSettlementData);
                        startActivity(intent);
//                        }
                    }
                } else {
                    intent = new Intent(CommodityDetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
        }
    }

    /**
     * 监听TitleBar的点击事件
     */
    private void setTitleBarClickListener() {
        titleBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleBarMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommodityDetailsActivity.this, MessageCenterActivity.class);
                intent.putExtra(FlagData.FLAG_TYPE, "3");
                intent.putExtra(FlagData.FLAG_MESSAGE_SID, storeId);
                intent.putExtra(FlagData.FLAG_MESSAGE_PID, productId);
                startActivity(intent);
            }
        });
    }

    /**
     * 获取商品详情数据
     **/
    @Override
    public void onGetCommodityDetailsData(JsonBeanCommodityDetailsData jsonBeanCommodityDetailsData) {
        mListBannerUrls.clear();
        for (JsonBeanCommodityDetailsData.ProductsinfoBean.ImagesBean.DataBean dataBean : jsonBeanCommodityDetailsData.getProductsinfo()
                .getImages().getData()) {
            mListBannerUrls.add(FlagData.FLAG_IMGADDRESS + dataBean.getImg());
        }
        convenientBanner.setPages(new CBViewHolderCreator<UrlImageHolderView>() {
            @Override
            public UrlImageHolderView createHolder() {
                return new UrlImageHolderView();
            }
        }, mListBannerUrls)
                .setPageIndicator(new int[]{R.drawable.banner_before, R.drawable.banner_after})
                .startTurning(FlagData.DELAY_BANNER);

        productName = jsonBeanCommodityDetailsData.getProductsinfo().getName();
        //productUrl = mListBannerUrls.get(0);
        productUrl =   FlagData.FLAG_IMGADDRESS + jsonBeanCommodityDetailsData.getProductsinfo().getPimg();
        productPrice = jsonBeanCommodityDetailsData.getProductsinfo().getShopprice();
        monthlySales = jsonBeanCommodityDetailsData.getProductsinfo().getMonthlysales();

        tvProductName.setText(productName);
        tvProductPrice.setText("¥ " + productPrice);
        tvMonthlySales.setText("月销售" + monthlySales + "笔");

        storeId = jsonBeanCommodityDetailsData.getStoresinfo().getStoreid();
        storeName = jsonBeanCommodityDetailsData.getStoresinfo().getName();
        storeUrl = FlagData.FLAG_IMGADDRESS + jsonBeanCommodityDetailsData.getStoresinfo().getImg();
        starCount = jsonBeanCommodityDetailsData.getStoresinfo().getStarcount();
        startPrice = jsonBeanCommodityDetailsData.getStoresinfo().getStartvalue();
        sendPrice = jsonBeanCommodityDetailsData.getStoresinfo().getStartfee();
        if (jsonBeanCommodityDetailsData.getStoresinfo().getIsopen().equals("1")) {
            isOpens = false;
        } else if (jsonBeanCommodityDetailsData.getStoresinfo().getIsDistributioning().equals("1")) {
            isOpens = false;
        } else {
            isOpens = true;
        }
        isOpen = jsonBeanCommodityDetailsData.getStoresinfo().getIsopen();

        tvStoreName.setText(storeName);
        Glide.with(CommodityDetailsActivity.this).load(storeUrl).into(ivStoreIcon);
        rbStoreStar.setRating(Float.parseFloat(starCount));
        tvSendPrice.setText("配送费￥" + sendPrice + "元 |");
        tvStartPrice.setText("起送价" + startPrice + "元");
        tvTotalPrice.setText("本店满" + startPrice + "元配送");
        if (isOpen.equals("0")) {
            tvStoreState.setVisibility(View.VISIBLE);
        } else {
            tvStoreState.setVisibility(View.INVISIBLE);
        }
        changeBottomState();
        if ("1".equals(jsonBeanCommodityDetailsData.getStoresinfo().getIsopen())) {
            new CircleDialog.Builder(this)
                    .setTitle("提示")
                    .setWidth(0.7f)
                    .setCanceledOnTouchOutside(false)
                    .configTitle(new ConfigTitle() {
                        @Override
                        public void onConfig(TitleParams params) {
                            params.height = 115;
                            params.textSize = 50;
                            params.textColor = CommodityDetailsActivity.this.getResources().getColor(R.color.colorBlack);
                            params.gravity = Gravity.BOTTOM | Gravity.CENTER;

                        }
                    })
                    .setWidth(Float.valueOf("0.7"))
                    .setText("本店已打烊")
                    .configText(new ConfigText() {
                        @Override
                        public void onConfig(TextParams params) {
                            params.height = 120;
                            params.textSize = 45;
                            params.textColor = CommodityDetailsActivity.this.getResources().getColor(R.color.colorBlack);
                            int[] a = {0, 15, 0, 0};
                            params.padding = a;
                            params.gravity = Gravity.TOP | Gravity.CENTER;
                        }
                    })
                    .setPositive("确定", null)
                    .configPositive(new ConfigButton() {
                        @Override
                        public void onConfig(ButtonParams params) {
                            params.height = 130;
                        }
                    })
                    .show();

        } else if ("1".equals(jsonBeanCommodityDetailsData.getStoresinfo().getIsDistributioning())) {
            new CircleDialog.Builder(CommodityDetailsActivity.this)
                    .setTitle("提示")
                    .setWidth(0.7f)
                    .setCanceledOnTouchOutside(false)
                    .configTitle(new ConfigTitle() {
                        @Override
                        public void onConfig(TitleParams params) {
                            params.height = 115;
                            params.textSize = 50;
                            params.textColor = CommodityDetailsActivity.this.getResources().getColor(R.color.colorBlack);
                            params.gravity = Gravity.BOTTOM | Gravity.CENTER;

                        }
                    })
                    .setWidth(Float.valueOf("0.7"))
                    .setText("超出配送范围")
                    .configText(new ConfigText() {
                        @Override
                        public void onConfig(TextParams params) {
                            params.height = 120;
                            params.textSize = 45;
                            params.textColor = CommodityDetailsActivity.this.getResources().getColor(R.color.colorBlack);
                            int[] a = {0, 15, 0, 0};
                            params.padding = a;
                            params.gravity = Gravity.TOP | Gravity.CENTER;
                        }
                    })
                    .setPositive("确定", null)
                    .configPositive(new ConfigButton() {
                        @Override
                        public void onConfig(ButtonParams params) {
                            params.height = 130;
                        }
                    })
                    .show();


        }
    }


    /**
     * 判断用户是否有未读消息
     */
    @Override
    public void onGetConsumerUncheckMessage(JsonBeanConsumerUncheckMessage jsonBeanConsumerUncheckMessage) {
        if (jsonBeanConsumerUncheckMessage.getIsreadcount().equals("0")) {
            titleBarMessage.setImageResource(R.drawable.message_rectangle_default);
        } else {
            titleBarMessage.setImageResource(R.drawable.message_rectangle_redpoint);
        }
    }

    /**
     * 网络请求失败
     **/
    @Override
    public void onFailure(String msg) {

    }

    /**
     * 显示加号和数量
     **/
    private void showReduceAndCount() {
        ivProductReduce.setVisibility(View.VISIBLE);
        tvProductCount.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏加号和数量
     **/
    private void hideReduceAndCount() {
        ivProductReduce.setVisibility(View.GONE);
        tvProductCount.setVisibility(View.GONE);
    }

    /**
     * 改变底部状态
     **/
    private void changeBottomState() {
        tvTotalCount.setText(productCount + "");
        totalPrice = Float.parseFloat(productPrice) * productCount;
        tvTotalPrice.setText("¥ " + decimalFormat.format(totalPrice));
        settlePrice = (Float.parseFloat(productPrice) * productCount);
        if (isOpens == true) {
            if ((settlePrice > 0)) {
                tvSettlement.setBackgroundResource(R.color.colorGreenBefore);
                tvToShoppingCart.setBackgroundResource(R.color.colorGreenBefore);
                tvSettlement.setClickable(true);
                tvToShoppingCart.setClickable(true);
                tvSettlement.setText("结算");
            } else {

                tvSettlement.setBackgroundResource(R.color.colorGray_divider);
                tvToShoppingCart.setBackgroundResource(R.color.colorGray_divider);
                tvSettlement.setClickable(false);
                tvToShoppingCart.setClickable(false);
//            tvSettlement.setText("差¥" + decimalFormat.format(settlePrice) + "起送");
                tvSettlement.setText("结算");
            }
        } else {
            tvSettlement.setBackgroundResource(R.color.colorGray_divider);
            tvToShoppingCart.setBackgroundResource(R.color.colorGray_divider);
            tvSettlement.setClickable(false);
            tvToShoppingCart.setClickable(false);
//            tvSettlement.setText("差¥" + decimalFormat.format(settlePrice) + "起送");
            tvSettlement.setText("结算");
        }
    }

    @Override
    public void onGetCommodityDetailsComment(JsonBeanCommodityDetailsComment jsonBeanCommodityDetailsComment) {

    }

    /**
     * 自定义ImageView的holder
     */
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
            Glide.with(CommodityDetailsActivity.this).load(data).into(imageView);
        }
    }

    /**
     * 系统回调onPause
     */
    @Override
    protected void onPause() {
        super.onPause();
        convenientBanner.stopTurning();
    }

    /**
     * 系统回调onResume
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        orderManager.close();
    }
}
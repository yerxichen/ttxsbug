package com.yundian.wudou.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.StoreDetailsBottomAdapter;
import com.yundian.wudou.customview.CircleImageView;
import com.yundian.wudou.data.AdapterClassificationLeftData;
import com.yundian.wudou.data.AdapterShoopingcartData;
import com.yundian.wudou.data.AdapterStoreDetailsBottomData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.data.ShoppingCartData;
import com.yundian.wudou.datawork.OrderManager;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.fragment.StoreDetailsLeftFragment;
import com.yundian.wudou.fragment.StoreDetailsRightFragment;
import com.yundian.wudou.network.JsonBeanConsumerUncheckMessage;
import com.yundian.wudou.network.JsonBeanEditCollect;
import com.yundian.wudou.network.JsonBeanStoreDetailsData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;
import com.yundian.wudou.publicinterface.StoreDetailsLeftSelectedListener;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreDetailsActivity extends FragmentActivity implements NetWorkInterface.OnGetConsumerUncheckMessageListener,
        NetWorkInterface.OnGetEditCollectListener, StoreDetailsLeftSelectedListener,NetWorkInterface.OnGetStoreDetailsDataListener{

    @Bind(R.id.tv_activitystoredetails_back)TextView tvTitleBack;
    @Bind(R.id.tv_activity_storedetails_left)TextView tvLeft;
    @Bind(R.id.tv_activity_storedetails_left_line)TextView tvLeftLine;
    @Bind(R.id.tv_activity_storedetails_right)TextView tvRight;
    @Bind(R.id.tv_activity_storedetails_right_line)TextView tvRightLine;
    @Bind(R.id.tv_activitystoredetails_shopname)TextView tvShopName;
    @Bind(R.id.tv_activitystoredetails_shopcontent)TextView tvShopContent;
    @Bind(R.id.tv_activitystoredetails_shopsale)TextView tvShopSale;
    @Bind(R.id.tv_activitystoredetails_message)TextView tvMessage;
    @Bind(R.id.tv_activitystoredetails_total)TextView tvTotal;
    @Bind(R.id.tv_activitystoredetails_to_shoppingcart)TextView tvToShoppingCart;
    @Bind(R.id.tv_activitystoredetails_settlement)TextView tvSettlement;
    @Bind(R.id.iv_activitystoredetails_shoppingcart)ImageView ivShoppingCart;
    @Bind(R.id.iv_activitystoredetails_shopicon)CircleImageView civShopIcon;
    @Bind(R.id.iv_activitystoredetails_collection)ImageView ivCollect;
    @Bind(R.id.iv_activitystoredetails_message)ImageView ivMessage;
    @Bind(R.id.iv_activity_storedetails_receviecoupon)ImageView ivReceiveCoupons;
    @Bind(R.id.et_activity_storedetails_search)EditText etSearch;
    @Bind(R.id.ll_activity_storedetails_select)LinearLayout llSelect;
    @Bind(R.id.ll_activity_storedetails_search)LinearLayout llSearch;
    @Bind(R.id.bottomSheetLayout_storedetails)BottomSheetLayout bottomSheetLayout;

    private SharedpreferencesManager sharedpreferencesManager;
    private NetWorkOperate netWorkOperate;
    private OrderManager orderManager;

    private StoreDetailsLeftFragment mStoreDetailsLeftFragment;
    private StoreDetailsRightFragment mStoreDetailsRightFragment;

    //左边的列表
    private List<AdapterClassificationLeftData> mListAdapterStoreDetailsLeftData;
    //底部购物车弹窗列表
    private View bottomSheet;
    private ListView mBottomListView;
    private StoreDetailsBottomAdapter mBottomAdapter;
    private List<ShoppingCartData> mListShoppingCartData;
    private List<AdapterStoreDetailsBottomData> mListAdapterStoreDetailsBottomData;
    //传递到结算页面的list
    private List<AdapterShoopingcartData> mListAdapterShoopingcartData;

    private String state,sate,strToken, storeId, storeName, storeUrl, startValue, sendPrice, monthlySales, productsCount, hasFavorite, hasCoupons;
    private float bottomPrice, settlePrice;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        initialize();

        setEventListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        etSearch.setText("");
        netWorkOperate.getConsumerUncheckMessage(strToken);
    }

    private void initialize() {
        ButterKnife.bind(StoreDetailsActivity.this);
        orderManager = new OrderManager(StoreDetailsActivity.this);
        netWorkOperate = new NetWorkOperate(StoreDetailsActivity.this);
        sharedpreferencesManager = new SharedpreferencesManager(StoreDetailsActivity.this);
        strToken = sharedpreferencesManager.getToken();
        decimalFormat = new DecimalFormat("0.00");

        Intent intent = getIntent();
        storeId = intent.getStringExtra(FlagData.FLAG_SHOP_ID);
        state = intent.getStringExtra(FlagData.FLAG_STATE);

        if(state!=null&&state.equals("1")){
            sate = "1";
            llSelect.setVisibility(View.VISIBLE);
            llSearch.setVisibility(View.GONE);
        }else{
            llSearch.setVisibility(View.VISIBLE);
            llSelect.setVisibility(View.GONE);
        }

        Drawable drawable = getResources().getDrawable(R.drawable.search_gray);
        drawable.setBounds(0, 0, 48, 48);
        etSearch.setCompoundDrawables(drawable, null, null, null);

        mStoreDetailsLeftFragment = new StoreDetailsLeftFragment();
        mStoreDetailsRightFragment = new StoreDetailsRightFragment();

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.fragment_container_activitystoredetails_left, mStoreDetailsLeftFragment);
        mFragmentTransaction.add(R.id.fragment_container_activitystoredetails_right, mStoreDetailsRightFragment);
        mFragmentTransaction.commit();

        mListAdapterShoopingcartData = new ArrayList<>();
        mListAdapterStoreDetailsLeftData = new ArrayList<>();

        mListShoppingCartData = new ArrayList<>();
        mListAdapterStoreDetailsBottomData = new ArrayList<>();
        mBottomAdapter = new StoreDetailsBottomAdapter(StoreDetailsActivity.this, mListAdapterStoreDetailsBottomData, storeName);

        netWorkOperate.getStoreDetailsData(strToken, storeId,sate);
    }

    private void setEventListener() {
        tvTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ivCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netWorkOperate.getEditCollect(storeId, strToken);
            }
        });
        ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreDetailsActivity.this, MessageCenterActivity.class);
                intent.putExtra(FlagData.FLAG_TYPE, "2");
                intent.putExtra(FlagData.FLAG_MESSAGE_SID, storeId);
                intent.putExtra(FlagData.FLAG_MESSAGE_PID, "0");
                startActivity(intent);
            }
        });

        etSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    Intent intent = new Intent(StoreDetailsActivity.this, SearchResultActivity.class);
                    intent.putExtra(FlagData.FLAG_SEARCH_TEXT,etSearch.getText().toString());
                    intent.putExtra(FlagData.FLAG_STATE,"1");
                    startActivity(intent);
                }
                return false;
            }
        });

        ivReceiveCoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreDetailsActivity.this, ReceiveCouponsActivity.class);
                intent.putExtra(FlagData.FLAG_SHOP_ID, storeId);
                startActivity(intent);
            }
        });

        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sate = "1";
                tvLeft.setTextColor(getResources().getColor(R.color.colorGreenBefore));
                tvLeftLine.setVisibility(View.VISIBLE);
                tvRight.setTextColor(getResources().getColor(R.color.colorBlack));
                tvRightLine.setVisibility(View.GONE);
                netWorkOperate.getStoreDetailsData(strToken, storeId,sate);
            }
        });
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sate = "2";
                tvLeft.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLeftLine.setVisibility(View.GONE);
                tvRight.setTextColor(getResources().getColor(R.color.colorGreenBefore));
                tvRightLine.setVisibility(View.VISIBLE);
                netWorkOperate.getStoreDetailsData(strToken, storeId,sate);
            }
        });

        ivShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheet();
            }
        });

        //ToShoppingCart
        tvToShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreDetailsActivity.this, FragmentContainerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("flag", 2);
                startActivity(intent);
                StoreDetailsActivity.this.finish();
            }
        });

        //ToSettlement
        tvSettlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (sharedpreferencesManager.isLogin()) {
                    intent = new Intent(StoreDetailsActivity.this, SettlementActivity.class);
                    mListAdapterShoopingcartData = orderManager.getCartDataBySid(storeId);
                    for (int i = 0; i < mListAdapterShoopingcartData.size(); i++) {
                        if (mListAdapterShoopingcartData.get(i).isParent()) {
                            mListAdapterShoopingcartData.remove(i);
                        }
                    }
                    intent.putExtra("list", (Serializable) mListAdapterShoopingcartData);
                } else {
                    intent = new Intent(StoreDetailsActivity.this, LoginActivity.class);
                }
                startActivity(intent);

            }
        });

        //Bottom Add Product
        mBottomAdapter.setBottomAddListener(new StoreDetailsBottomAdapter.OnBottomAddListener() {
            @Override
            public void onBottomAdd() {
                mStoreDetailsRightFragment.refreshFragment();
                refreshBottom();
            }
        });
        //Bottom Reduce Product
        mBottomAdapter.setBottomReduceListener(new StoreDetailsBottomAdapter.OnBottomReduceListener() {
            @Override
            public void onBottomReduce() {
                mStoreDetailsRightFragment.refreshFragment();
                refreshBottom();
            }
        });
        //Bottom Delete Product
        mBottomAdapter.setBottomDeleteListener(new StoreDetailsBottomAdapter.OnBottomDeleteListener() {
            @Override
            public void onBottomDelete() {
                mListShoppingCartData = orderManager.getShoppingCartDataBySid(storeId);
                mListAdapterStoreDetailsBottomData.clear();
                for (int i = 0; i < mListShoppingCartData.size(); i++) {
                    ShoppingCartData data = mListShoppingCartData.get(i);
                    mListAdapterStoreDetailsBottomData.add(new AdapterStoreDetailsBottomData(data.getStoreId(), data.getStoreName(),
                            data.getStoreUrl(), data.getProductId(), data.getProductName(), data.getProductUrl(), data.getProductPrice(),
                            data.getProductWeight(), data.getProductCount(), data.getStartValue(), data.getSendPrice()));
                }
                mBottomAdapter.notifyDataSetChanged();

                mStoreDetailsRightFragment.refreshFragment();
                refreshBottom();
            }
        });
    }

    //得到店铺标题数据
    @Override
    public void onGetStoreDetailsData(JsonBeanStoreDetailsData jsonBeanStoreDetailsData) {
        storeId = jsonBeanStoreDetailsData.getStoresinfo().getStoreid();
        storeName = jsonBeanStoreDetailsData.getStoresinfo().getName();
        storeUrl = FlagData.FLAG_IMGADDRESS + jsonBeanStoreDetailsData.getStoresinfo().getImg();
        startValue = jsonBeanStoreDetailsData.getStoresinfo().getStartvalue();
        sendPrice = jsonBeanStoreDetailsData.getStoresinfo().getStartfee();
        monthlySales = jsonBeanStoreDetailsData.getStoresinfo().getMonthlysales();
        productsCount = jsonBeanStoreDetailsData.getStoresinfo().getProductscount();
        hasFavorite = jsonBeanStoreDetailsData.getStoresinfo().getHasfavorite();
        hasCoupons = jsonBeanStoreDetailsData.getStoresinfo().getHascoupons();

        tvShopName.setText(storeName);
        tvShopContent.setText("配送费￥ " + sendPrice + " | 起送价￥ " + startValue);
        tvShopSale.setText("共" + productsCount + "件商品 | 月售" + monthlySales + "单");
        Glide.with(StoreDetailsActivity.this).load(storeUrl).into(civShopIcon);
        //是否收藏店铺
        if (sharedpreferencesManager.isLogin() && hasFavorite.equals("1")) {
            ivCollect.setImageResource(R.drawable.collect);
        } else {
            ivCollect.setImageResource(R.drawable.icon_collection_write);
        }
        // 是否有优惠券
        if (hasCoupons.equals("1")) {
            ivReceiveCoupons.setVisibility(View.VISIBLE);
        } else {
            ivReceiveCoupons.setVisibility(View.INVISIBLE);
        }

        refreshBottom();

        //Send List To LeftFragment
        mListAdapterStoreDetailsLeftData.clear();
        for (JsonBeanStoreDetailsData.StorescategoriesBean.DataBean dataBean : jsonBeanStoreDetailsData.getStorescategories().getData()) {
            mListAdapterStoreDetailsLeftData.add(new AdapterClassificationLeftData(false, dataBean.getCateid(), dataBean.getName()));
        }
        mStoreDetailsLeftFragment.update(mListAdapterStoreDetailsLeftData);
    }

    @Override
    public void onGetEditCollect(JsonBeanEditCollect jsonBeanEditCollect) {
        if (jsonBeanEditCollect.getHasfavorite().equals("1")) {
            ivCollect.setImageDrawable(getResources().getDrawable(R.drawable.collect));
            Toast.makeText(StoreDetailsActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
        } else if (jsonBeanEditCollect.getHasfavorite().equals("0")) {
            ivCollect.setImageDrawable(getResources().getDrawable(R.drawable.icon_collection_write));
            Toast.makeText(StoreDetailsActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetConsumerUncheckMessage(JsonBeanConsumerUncheckMessage jsonBeanConsumerUncheckMessage) {
        if (jsonBeanConsumerUncheckMessage.getIsreadcount().equals("0")) {
            ivMessage.setImageResource(R.drawable.message_rectangle_default);
        } else {
            ivMessage.setImageResource(R.drawable.message_rectangle_redpoint);
        }
    }

    @Override
    public void onFailure(String msg) {
    }

    private void showBottomSheet() {
        if (bottomSheet == null) {
            bottomSheet = LayoutInflater.from(this).inflate(R.layout.layout_activity_storedetails_bottomlist,
                    (ViewGroup) getWindow().getDecorView(), false);
        }
        bottomSheetLayout.showWithSheetView(bottomSheet);
        if (bottomSheetLayout.isSheetShowing()) {
            bottomSheetLayout.dismissSheet();
        }
        RelativeLayout relativeLayoutDelete = (RelativeLayout) bottomSheet.findViewById(R.id.rl_activity_storedetails_bottom_delete);
        mBottomListView = (ListView) bottomSheet.findViewById(R.id.lv_activity_storedetails_bottom);
        mBottomListView.setAdapter(mBottomAdapter);

        mListShoppingCartData = orderManager.getShoppingCartDataBySid(storeId);
        mListAdapterStoreDetailsBottomData.clear();
        for (int i = 0; i < mListShoppingCartData.size(); i++) {
            ShoppingCartData data = mListShoppingCartData.get(i);
            mListAdapterStoreDetailsBottomData.add(new AdapterStoreDetailsBottomData(data.getStoreId(), data.getStoreName(),
                    data.getStoreUrl(), data.getProductId(), data.getProductName(), data.getProductUrl(), data.getProductPrice(),
                    data.getProductWeight(), data.getProductCount(), data.getStartValue(), data.getSendPrice()));
        }
        mBottomAdapter.notifyDataSetChanged();

        relativeLayoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderManager.reduceProductBySid(storeId);
                mListAdapterStoreDetailsBottomData.clear();
                mBottomAdapter.notifyDataSetChanged();
                mStoreDetailsRightFragment.refreshFragment();
                refreshBottom();
            }
        });
    }

    public void refreshBottom() {
        tvTotal.setText(orderManager.getShoppingCartNumBySid(storeId) + "");
        settlePrice = Float.parseFloat(startValue) - orderManager.getStorePriceBySid(storeId);
        if (settlePrice > 0) {
            tvSettlement.setBackgroundResource(R.color.colorGray_divider);
            tvSettlement.setClickable(false);
            tvSettlement.setText("差¥" + decimalFormat.format(settlePrice));
            tvToShoppingCart.setBackgroundResource(R.color.colorGray_divider);
            tvToShoppingCart.setClickable(false);
        } else {
            tvSettlement.setBackgroundResource(R.color.colorGreenBefore);
            tvSettlement.setClickable(true);
            tvSettlement.setText("选好了");
            tvToShoppingCart.setBackgroundResource(R.color.colorGreenBefore);
            tvToShoppingCart.setClickable(true);
        }
        bottomPrice = orderManager.getStorePriceBySid(storeId);
        tvMessage.setText("¥" + decimalFormat.format(bottomPrice));
    }

    //获得分类选中的回调,将分类传递到RightFragment
    @Override
    public void onSelected(String cateid, String catename) {
        //把storeid和storename传到RightFragment
        mStoreDetailsRightFragment.setStoreId(storeId);
        mStoreDetailsRightFragment.setStoreName(storeName);
        mStoreDetailsRightFragment.setStoreUrl(storeUrl);
        mStoreDetailsRightFragment.setStartValue(startValue);
        mStoreDetailsRightFragment.setSendPrice(sendPrice);

        mStoreDetailsRightFragment.setCateId(cateid, catename,sate);
    }

    @Override
    protected void onDestroy() {
        orderManager.close();
        super.onDestroy();
    }
}

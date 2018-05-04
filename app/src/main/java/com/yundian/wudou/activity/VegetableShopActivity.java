package com.yundian.wudou.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigButton;
import com.mylhyl.circledialog.callback.ConfigText;
import com.mylhyl.circledialog.callback.ConfigTitle;
import com.mylhyl.circledialog.params.ButtonParams;
import com.mylhyl.circledialog.params.TextParams;
import com.mylhyl.circledialog.params.TitleParams;

import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.BaseFragmentAdapter;
import com.yundian.wudou.adapter.StoreDetailsBottomAdapter;
import com.yundian.wudou.customview.CircleImageView;
import com.yundian.wudou.data.AdapterShoopingcartData;
import com.yundian.wudou.data.AdapterStoreDetailsBottomData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.data.ShoppingCartData;
import com.yundian.wudou.data.StoresinfoData;
import com.yundian.wudou.datawork.OrderManager;
import com.yundian.wudou.datawork.SharedpreferencesManager;

import com.yundian.wudou.fragment.FreshAreaFragment;
import com.yundian.wudou.fragment.VegetableAreaFragment;
import com.yundian.wudou.network.JsonBeanConsumerUncheckMessage;
import com.yundian.wudou.network.JsonBeanEditCollect;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;
import com.yundian.wudou.utils.AppBarStateChangeListener;
import com.yundian.wudou.utils.NoScrollViewPager;
import com.yundian.wudou.utils.ShopCarView;
import com.yundian.wudou.utils.ViewUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by taozipc on 2017/11/6.
 */

public class VegetableShopActivity extends AppCompatActivity implements NetWorkInterface.OnGetConsumerUncheckMessageListener,
        NetWorkInterface.OnGetEditCollectListener, NetWorkInterface.OnGetStoreDetails2DataListener {

    @Bind(R.id.iv_activitystoredetails_shopicon)
    CircleImageView civShopIcon;//店铺图标
    @Bind(R.id.tv_activitystoredetails_shopname)
    TextView tvShopName;//店铺名称
    @Bind(R.id.tv_activitystoredetails_shopcontent)
    TextView tvShopContent;//显示配送
    @Bind(R.id.tv_activitystoredetails_shopsale)
    TextView tvShopSale;//显示商品销售情况
    @Bind(R.id.iv_activity_storedetails_receviecoupon)
    ImageView ivReceiveCoupons;//优惠券
    @Bind(R.id.tv_activitystoredetails_back)
    TextView tvTitleBack;//返回
    @Bind(R.id.tv_activitystoredetails_title)
    TextView tvActivitystoredetailsTitle;
    @Bind(R.id.iv_activitystoredetails_collection)
    ImageView ivCollect;//收藏
    @Bind(R.id.iv_activitystoredetails_message)
    ImageView ivMessage;//返回
    @Bind(R.id.rl_activitystoredetails_titlebar)
    RelativeLayout rlActivitystoredetailsTitlebar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;//标题栏
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.tabs)
    TabLayout mTabLayout;
    @Bind(R.id.appbar)//折叠view
            AppBarLayout mCollapsingToolbarLayout;
    @Bind(R.id.viewpagers)
    NoScrollViewPager viewpagers;
    @Bind(R.id.tv_activitystoredetails_settlement)
    TextView tvSettlement;//差多少可以下单
    @Bind(R.id.tv_activitystoredetails_to_shoppingcart)
    TextView tvToShoppingCart;//去购物车
    @Bind(R.id.tv_activitystoredetails_total)
    TextView tvTotal;
    @Bind(R.id.rl_act_storedetail_shopcart)
    RelativeLayout rlActStoredetailShopcart;
    @Bind(R.id.iv_activitystoredetails_line)
    View ivActivitystoredetailsLine;
    @Bind(R.id.tv_activitystoredetails_message_title)
    TextView tvActivitystoredetailsMessageTitle;
    @Bind(R.id.tv_activitystoredetails_message)
    TextView tvMessage;
    @Bind(R.id.et_activity_store_details_search)
    EditText etSearch;
    @Bind(R.id.ll_activity_storedetails_search)
    LinearLayout llSearch;
    //    @Bind(R.id.bottomSheetLayout_storedetails)BottomSheetLayout bottomSheetLayout;
    private FreshAreaFragment freshAreaFragment;
    private VegetableAreaFragment vegetableAreaFragment;
    List<Fragment> mFragments;
    String[] mTitles = new String[]{
            "生鲜区", "净菜区"
    };
    private int[] tabIcons = {
            R.drawable.ic_vegetables,
            R.drawable.ic_clean_vegetables,
    };
    private SharedpreferencesManager sharedpreferencesManager;
    private NetWorkOperate netWorkOperate;
    private OrderManager orderManager;
    private String state, sate, strToken, storeId, storeName, storeUrl, startValue, sendPrice, monthlySales, productsCount, hasFavorite, hasCoupons;
    private List<StoresinfoData.StorescategoriesBean.DataBeanX> categoryList;
    private List<StoresinfoData.StorescategoriesBean.DataBeanX> freshAreaCategoryList;
    private float bottomPrice, settlePrice;
    //底部购物车弹窗列表
    private ListView mBottomListView;
    public static StoreDetailsBottomAdapter mBottomAdapter;
    private List<ShoppingCartData> mListShoppingCartData;
    private List<AdapterStoreDetailsBottomData> mListAdapterStoreDetailsBottomData;
    //传递到结算页面的list
    private List<AdapterShoopingcartData> mListAdapterShoopingcartData;
    private DecimalFormat decimalFormat;
    public BottomSheetBehavior behavior;
    private ShopCarView shopCarView;
    private boolean isFreshArea, isOpen;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_coordinator_details);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initialize();


    }

    private void initialize() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mCollapsingToolbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {
                    //展开状态
                    tvActivitystoredetailsTitle.setText("店铺详情");
                } else if (state == State.COLLAPSED) {

                    //折叠状态
                    //  storeName = "肯德基";
                    tvActivitystoredetailsTitle.setText(storeName);
                } else {
                    //中间状态
                }
            }
        });
        categoryList = new ArrayList<>();
        freshAreaCategoryList = new ArrayList<>();
        orderManager = new OrderManager(VegetableShopActivity.this);
        netWorkOperate = new NetWorkOperate(VegetableShopActivity.this);
        sharedpreferencesManager = new SharedpreferencesManager(VegetableShopActivity.this);
        decimalFormat = new DecimalFormat("0.00");
        strToken = sharedpreferencesManager.getToken();
        Intent intent = getIntent();
        storeId = intent.getStringExtra(FlagData.FLAG_SHOP_ID);
        state = intent.getStringExtra(FlagData.FLAG_STATE);




        if (state != null && state.equals("1")) {
            sate = "1";
            mTabLayout.setVisibility(View.VISIBLE);
            llSearch.setVisibility(View.GONE);
        } else {
            llSearch.setVisibility(View.VISIBLE);
            mTabLayout.setVisibility(View.GONE);
        }

        Drawable drawable = getResources().getDrawable(R.drawable.search_gray);
        drawable.setBounds(0, 0, 48, 48);
        etSearch.setCompoundDrawables(drawable, null, null, null);
        mListAdapterShoopingcartData = new ArrayList<>();
        mListShoppingCartData = new ArrayList<>();
        mListAdapterStoreDetailsBottomData = new ArrayList<>();
        mBottomAdapter = new StoreDetailsBottomAdapter(VegetableShopActivity.this, mListAdapterStoreDetailsBottomData, storeName);
//        strToken = "2B579CEC19B6184D9D04734041BE3132048EBAF5045D49A6A6F5EA1E4B47BDB3";// sharedpreferencesManager.getToken();
//        storeId = "14";
//        sate = "1";

        if(storeId.equals("14"))
        {
            sate="1";
            mTabLayout.setVisibility(View.VISIBLE);
            llSearch.setVisibility(View.GONE);
        }
        netWorkOperate.getStoreDetails2Data(strToken, storeId, sate);
        isFreshArea = true;
        isOpen = true;
        initViewpager();

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
                Intent intent = new Intent(VegetableShopActivity.this, MessageCenterActivity.class);
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
                    Intent intent = new Intent(VegetableShopActivity.this, SearchResultActivity.class);
                    intent.putExtra(FlagData.FLAG_SEARCH_TEXT, etSearch.getText().toString());
                    intent.putExtra(FlagData.FLAG_STATE, "1");
                    startActivity(intent);
                }
                return false;
            }
        });

        ivReceiveCoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VegetableShopActivity.this, ReceiveCouponsActivity.class);
                intent.putExtra(FlagData.FLAG_SHOP_ID, storeId);
                startActivity(intent);
            }
        });

        shopCarView.findViewById(R.id.iv_activitystoredetails_shoppingcart_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(">>>>>", ">>>hah");
                if (isOpen == true) {

                    refreshBottom();
                    if (shopCarView.sheetScrolling) {
                        return;
                    }
//                if (mBottomAdapter.getCount() == 0) {
//                    return;
//                }
                    if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                    } else {
                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                    }
                    showBottomSheet();
                }

            }

        });
        //ToShoppingCart
        tvToShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen == true) {

                    Intent intent = new Intent(VegetableShopActivity.this, FragmentContainerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("flag", 2);
                    startActivity(intent);
                    VegetableShopActivity.this.finish();
                }
            }
        });

        //ToSettlement
        tvSettlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (sharedpreferencesManager.isLogin()) {
                    intent = new Intent(VegetableShopActivity.this, SettlementActivity.class);
                    mListAdapterShoopingcartData = orderManager.getCartDataBySid(storeId);
                    for (int i = 0; i < mListAdapterShoopingcartData.size(); i++) {
                        if (mListAdapterShoopingcartData.get(i).isParent()) {
                            mListAdapterShoopingcartData.remove(i);
                        }
                    }
                    intent.putExtra("list", (Serializable) mListAdapterShoopingcartData);
                } else {
                    intent = new Intent(VegetableShopActivity.this, LoginActivity.class);
                }
                startActivity(intent);

            }
        });

        //Bottom Add Product
        mBottomAdapter.setBottomAddListener(new StoreDetailsBottomAdapter.OnBottomAddListener() {
            @Override
            public void onBottomAdd() {
                freshAreaFragment.refreshFragment();
                vegetableAreaFragment.refreshFragment();

                refreshBottom();
            }
        });
        //Bottom Reduce Product
        mBottomAdapter.setBottomReduceListener(new StoreDetailsBottomAdapter.OnBottomReduceListener() {
            @Override
            public void onBottomReduce() {
                freshAreaFragment.refreshFragment();
                vegetableAreaFragment.refreshFragment();
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
                freshAreaFragment.refreshFragment();
                vegetableAreaFragment.refreshFragment();
                refreshBottom();
            }
        });
    }


    private void initViewpager() {


        mFragments = new ArrayList<>();
        freshAreaFragment = new FreshAreaFragment();
        mFragments.add(freshAreaFragment);
        vegetableAreaFragment = new VegetableAreaFragment();
        mFragments.add(vegetableAreaFragment);
//

        // 第二步：为ViewPager设置适配器
        BaseFragmentAdapter adapter =
                new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewpagers.setAdapter(adapter);
        //  第三步：将ViewPager与TableLayout 绑定在一起
        viewpagers.setNoScroll(false);
        mTabLayout.setupWithViewPager(viewpagers);


        setupTabIcons();
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabNormal(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        LinearLayout linearLayout = (LinearLayout) mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.tab_item_line));
        viewpagers.setCurrentItem(0);
        mTabLayout.getTabAt(0).select();
        mTabLayout.getTabAt(0).getCustomView().findViewById(R.id.tv_activity_storedetails_line).setVisibility(View.VISIBLE);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        reflex(mTabLayout);
        behavior = BottomSheetBehavior.from(findViewById(R.id.car_container));
        shopCarView = (ShopCarView) findViewById(R.id.car_mainfl);
        View blackView = findViewById(R.id.blackview);
        shopCarView.setBehavior(behavior, blackView);
        setEventListener();
        //showBottomSheet();
    }

    private void changeTabSelect(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        Log.d(">>>>>", tab.getText().toString());
        TextView txt_title = (TextView) view.findViewById(R.id.tab_text);
        TextView txt_lin = (TextView) view.findViewById(R.id.tv_activity_storedetails_line);
        txt_title.setTextColor(getResources().getColor(R.color.colorGreenBefore));
        txt_lin.setVisibility(View.VISIBLE);
        if (tab.getText().toString().equals("生鲜区")) {
            sate = "1";
            netWorkOperate.getStoreDetails2Data(strToken, storeId, sate);
            isFreshArea = true;

        } else if (tab.getText().toString().equals("净菜区")) {
            sate = "2";
            netWorkOperate.getStoreDetails2Data(strToken, storeId, sate);
            isFreshArea = false;
        }

    }

    private void changeTabNormal(TabLayout.Tab tab) {
        View view = tab.getCustomView();

        TextView txt_title = (TextView) view.findViewById(R.id.tab_text);
        TextView txt_lin = (TextView) view.findViewById(R.id.tv_activity_storedetails_line);
        txt_title.setTextColor(getResources().getColor(R.color.colorBlackLight));
        txt_lin.setVisibility(View.INVISIBLE);
    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout.getTabAt(1).setCustomView(getTabView(1));

    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        TextView txt_title = (TextView) view.findViewById(R.id.tab_text);
        txt_title.setText(mTitles[position]);
        ImageView img_title = (ImageView) view.findViewById(R.id.iv_activity_storedetails_left);
        img_title.setImageResource(tabIcons[position]);
        if (position == 0) {
            txt_title.setTextColor(getResources().getColor(R.color.colorGreenBefore));
            img_title.setImageResource(tabIcons[position]);
        } else {
            txt_title.setTextColor(getResources().getColor(R.color.colorBlackLight));
            img_title.setImageResource(tabIcons[position]);
        }
        return view;
    }

    public void reflex(final TabLayout tabLayout) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = ViewUtils.dip2px(tabLayout.getContext(), 25);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void onGetStoreDetails2Data(StoresinfoData jsonBeanStoreDetailsData) {
        storeId = jsonBeanStoreDetailsData.getStoresinfo().getStoreid();
        storeName = jsonBeanStoreDetailsData.getStoresinfo().getName();
        storeUrl = FlagData.FLAG_IMGADDRESS + jsonBeanStoreDetailsData.getStoresinfo().getImg();
        startValue = jsonBeanStoreDetailsData.getStoresinfo().getStartvalue();
        sendPrice = jsonBeanStoreDetailsData.getStoresinfo().getStartfee();
        monthlySales = jsonBeanStoreDetailsData.getStoresinfo().getMonthlysales();
        productsCount = jsonBeanStoreDetailsData.getStoresinfo().getProductscount();
        hasFavorite = jsonBeanStoreDetailsData.getStoresinfo().getHasfavorite();
        hasCoupons = jsonBeanStoreDetailsData.getStoresinfo().getHascoupons();
        if (jsonBeanStoreDetailsData.getStoresinfo().getIsopen().equals("1")) {
            isOpen = false;
        } else if (jsonBeanStoreDetailsData.getStoresinfo().getIsDistributioning().equals("1")) {
            isOpen = false;
        } else {
            isOpen = true;
        }
        tvShopName.setText(storeName);
        tvShopContent.setText("配送费￥ " + sendPrice + " | 起送价￥ " + startValue);
        tvShopSale.setText("共" + productsCount + "件商品 | 月售" + monthlySales + "单");
        Picasso.with(VegetableShopActivity.this).load(storeUrl).into(civShopIcon);
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

        if (isFreshArea == false) {
            categoryList.clear();
            for (StoresinfoData.StorescategoriesBean.DataBeanX dataBean : jsonBeanStoreDetailsData.getStorescategories().getData()) {
                categoryList.add(dataBean);
            }
            vegetableAreaFragment.update(categoryList);
            vegetableAreaFragment.setData(storeId, storeName, storeUrl, startValue, sendPrice, isOpen);

        } else if (isFreshArea == true) {

            freshAreaCategoryList.clear();
            for (StoresinfoData.StorescategoriesBean.DataBeanX dataBean : jsonBeanStoreDetailsData.getStorescategories().getData()) {

                freshAreaCategoryList.add(dataBean);
            }
            freshAreaFragment.update(freshAreaCategoryList);
            freshAreaFragment.setData(storeId, storeName, storeUrl, startValue, sendPrice, isOpen);
        }
        if ("1".equals(jsonBeanStoreDetailsData.getStoresinfo().getIsopen())) {
            new CircleDialog.Builder(this)
                    .setTitle("提示")
                    .setWidth(0.7f)
                    .setCanceledOnTouchOutside(false)
                    .configTitle(new ConfigTitle() {
                        @Override
                        public void onConfig(TitleParams params) {
                            params.height = 115;
                            params.textSize = 50;
                            params.textColor = VegetableShopActivity.this.getResources().getColor(R.color.colorBlack);
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
                            params.textColor = VegetableShopActivity.this.getResources().getColor(R.color.colorBlack);
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

        } else if ("1".equals(jsonBeanStoreDetailsData.getStoresinfo().getIsDistributioning())) {
            new CircleDialog.Builder(this)
                    .setTitle("提示")
                    .setWidth(0.7f)
                    .setCanceledOnTouchOutside(false)
                    .configTitle(new ConfigTitle() {
                        @Override
                        public void onConfig(TitleParams params) {
                            params.height = 115;
                            params.textSize = 50;
                            params.textColor = VegetableShopActivity.this.getResources().getColor(R.color.colorBlack);
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
                            params.textColor = VegetableShopActivity.this.getResources().getColor(R.color.colorBlack);
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

    @Override
    public void onGetEditCollect(JsonBeanEditCollect jsonBeanEditCollect) {
        if (jsonBeanEditCollect.getHasfavorite().equals("1")) {
            ivCollect.setImageDrawable(getResources().getDrawable(R.drawable.collect));
            Toast.makeText(VegetableShopActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
        } else if (jsonBeanEditCollect.getHasfavorite().equals("0")) {
            ivCollect.setImageDrawable(getResources().getDrawable(R.drawable.icon_collection_write));
            Toast.makeText(VegetableShopActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
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

    private void showBottomSheet() {


        RelativeLayout relativeLayoutDelete = (RelativeLayout) findViewById(R.id.rl_activity_storedetails_bottom_delete);

        mBottomListView = (ListView) findViewById(R.id.lv_activity_storedetails_bottom);
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
        refreshBottom();

        relativeLayoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderManager.reduceProductBySid(storeId);
                mListAdapterStoreDetailsBottomData.clear();
                mBottomAdapter.notifyDataSetChanged();
                freshAreaFragment.refreshFragment();
                vegetableAreaFragment.refreshFragment();
                refreshBottom();
            }
        });
    }

    public void refreshBottom() {
        tvTotal.setText(orderManager.getShoppingCartNumBySid(storeId) + "");
        settlePrice = orderManager.getStorePriceBySid(storeId);


            if (settlePrice > 0) {
                if (isOpen == true) {
                    tvSettlement.setBackgroundResource(R.color.colorGreenBefore);
                    tvSettlement.setClickable(true);
                    tvSettlement.setText("选好了");
                    tvToShoppingCart.setBackgroundResource(R.color.colorGreenBefore);
                    tvToShoppingCart.setClickable(true);
                }else{
                    tvSettlement.setBackgroundResource(R.color.colorGray_divider);
                    tvSettlement.setClickable(false);
//            tvSettlement.setText("差¥" + decimalFormat.format(settlePrice));
                    tvSettlement.setText("选好了");
                    tvToShoppingCart.setBackgroundResource(R.color.colorGray_divider);
                    tvToShoppingCart.setClickable(false);
                }
            } else {

                tvSettlement.setBackgroundResource(R.color.colorGray_divider);
                tvSettlement.setClickable(false);
//            tvSettlement.setText("差¥" + decimalFormat.format(settlePrice));
                tvSettlement.setText("选好了");
                tvToShoppingCart.setBackgroundResource(R.color.colorGray_divider);
                tvToShoppingCart.setClickable(false);
            }
            bottomPrice = orderManager.getStorePriceBySid(storeId);
            tvMessage.setText("¥" + decimalFormat.format(bottomPrice));
        }
//    }


    @Override
    protected void onDestroy() {
        orderManager.close();
        super.onDestroy();
    }


}

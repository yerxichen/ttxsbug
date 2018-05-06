package com.yundian.wudou.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.activity.CommodityDetailsActivity;
import com.yundian.wudou.activity.ConvenienceServicesActivity;
import com.yundian.wudou.activity.FragmentContainerActivity;
import com.yundian.wudou.activity.HeadlineActivity;
import com.yundian.wudou.activity.IntegralMallActivity;
import com.yundian.wudou.activity.MessageCenterActivity;
import com.yundian.wudou.activity.RegionActivity;
import com.yundian.wudou.activity.ScannerActivity;
import com.yundian.wudou.activity.SearchActivity;
import com.yundian.wudou.activity.SecondHandCommodityActivity;
import com.yundian.wudou.activity.StoreActivity;
import com.yundian.wudou.activity.VegetableShopActivity;
import com.yundian.wudou.activity.WeekSaleActivity;
import com.yundian.wudou.adapter.HomapagePreferedAdapter;
import com.yundian.wudou.adapter.HomapagePreferedAdapterOne;
import com.yundian.wudou.adapter.HomapagePreferedAdapterTwo;
import com.yundian.wudou.adapter.HomepageRecommendAdapter;
import com.yundian.wudou.adapter.HomepageSaleAdapter;
import com.yundian.wudou.adapter.HomepageShopAdapter;
import com.yundian.wudou.adapter.HorizontalListViewAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.customview.DividerItemDecoration;
import com.yundian.wudou.customview.GridLayoutManager;
import com.yundian.wudou.data.AdapterHomepageRecommendData;
import com.yundian.wudou.data.AdapterHomepageSaleData;
import com.yundian.wudou.data.AdvertData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.data.HomaLoadproductsData;
import com.yundian.wudou.data.HorizontaiListviewData;
import com.yundian.wudou.data.HorizontaiListviewListData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanAdverts;
import com.yundian.wudou.network.JsonBeanConsumerUncheckMessage;
import com.yundian.wudou.network.JsonBeanGetToken;
import com.yundian.wudou.network.JsonBeanHomePage;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;
import com.yundian.wudou.utils.HorizontalListView;
import com.yundian.wudou.utils.MyFooterView;
import com.yundian.wudou.utils.MyGridLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomePageFragment extends Fragment implements NetWorkInterface.OnGetHomaPageDataListener,
        NetWorkInterface.OnGetHomaPageAdvertsListOneListener,
        NetWorkInterface.OnGetHomaLoadTypeListListener,
        NetWorkInterface.OnGetHomaPageAdvertsListFourListener, NetWorkInterface.OnGetHomaLoadTypeListener,
        NetWorkInterface.OnGetLoadproductsListListener,
        NetWorkInterface.OnGetConsumerUncheckMessageListener,
        BDLocationListener, NetWorkInterface.OnGetNewTokenListener {

    private static final String TAG = "HomePageFragment";

    @Bind(R.id.prl_fragmenthome)
    PullToRefreshLayout refreshLayout;
    //TitleBar
    @Bind(R.id.iv_fragmenthome_titlebar_qrcode)
    ImageView titleBarQrcode;
    @Bind(R.id.tv_fragmenthome_titlebar_region)
    TextView titleBarRegion;
    @Bind(R.id.rl_fragmenthome_titlebar_search)
    RelativeLayout titleBarSearch;
    @Bind(R.id.iv_fragmenthome_titlebar_message)
    ImageView titleBarMessage;
    //Menu
    @Bind(R.id.iv_fragmenthome_menu_vegetables)
    ImageView menu_vegetables;
    @Bind(R.id.iv_fragmenthome_menu_fruits)
    ImageView menu_fruits;
    @Bind(R.id.iv_fragmenthome_menu_seasoning)
    ImageView menu_seasoning;
    @Bind(R.id.iv_fragmenthome_menu_market)
    ImageView menu_market;
    @Bind(R.id.iv_fragmenthome_menu_classify)
    ImageView menu_classify;
    @Bind(R.id.iv_fragmenthome_menu_snacks)
    ImageView menu_snacks;
    @Bind(R.id.iv_fragmenthome_menu_furniture)
    ImageView menu_furniture;
    @Bind(R.id.iv_fragmenthome_menu_integral)
    ImageView menu_integral;
    @Bind(R.id.iv_fragmenthome_menu_secondhand)
    ImageView menu_secondhand;
    @Bind(R.id.iv_fragmenthome_menu_service)
    ImageView menu_service;
    //HotPoint
    @Bind(R.id.rl_fragmenthome_hotpoint)
    RelativeLayout hotPointContent;
    @Bind(R.id.ts_fragmenthome_hotpoint_first)
    TextSwitcher hotPointFirst;
    @Bind(R.id.ts_fragmenthome_hotpoint_second)
    TextSwitcher hotPointSecond;
    //Banner
    @Bind(R.id.cb_fragmenthome_banner_first)
    ConvenientBanner bannerFirst;

    @Bind(R.id.home_banner_one)
    ImageView homeBannerOne;//第一张图

    @Bind(R.id.home_banner_one_gai_1)
    ImageView homeBannerOne_gai1;//第二张图

    @Bind(R.id.home_prefered_banner_one)
    ImageView homePreferedBannerOne;//第三张图

    @Bind(R.id.home_prefered_banner_second)
    ImageView homePreferedBannerSecond;//第四张图

    @Bind(R.id.home_shop_banner_one)
    ImageView homeShopBannerOne;//第五张图


    //List
    @Bind(R.id.rv_fragmenthome_recommend)
    RecyclerView recyclerViewRecommend;
    @Bind(R.id.rv_fragmenthome_prefered)
    RecyclerView recyclerViewPrefered;//精品推荐2
    @Bind(R.id.rv_fragmenthome_prefered_one)
    RecyclerView recyclerViewPreferedOne;//精品推荐1
    @Bind(R.id.rv_fragmenthome_prefered_two)//精品推荐3
            RecyclerView recyclerViewPreferedTwo;
    @Bind(R.id.lv_fragmenthome_shop)
    ListView listViewShop;
    //WeekSale
    @Bind(R.id.rv_fragmenthome_sale)
    RecyclerView recyclerViewSale;
    @Bind(R.id.tv_fragmenthome_sale_more)
    TextView tvSaleMore;
    @Bind(R.id.tv_fragmenthome_sale_days)
    TextView tvSaleDays;
    @Bind(R.id.tv_fragmenthome_sale_hours)
    TextView tvSaleHours;
    @Bind(R.id.tv_fragmenthome_sale_minutes)
    TextView tvSaleMinutes;

    @Bind(R.id.home_shop_horizontal_list_view)
    HorizontalListView homeShopHorizontalListView;

    @Bind(R.id.home_iv_tp)
    ImageView home_iv_tp;

    private HotPointHandler hotPointHandler;
    private BaseActivity baseActivity;
    private LocationClient locationClient;
    private NetWorkOperate netWorkOperate;
    private SharedpreferencesManager manager;
    private CBViewHolderCreator cbViewHolderCreator;

    private HomepageSaleAdapter saleAdapter;
    private HomepageRecommendAdapter recommendAdapter;
    private HomapagePreferedAdapter preferedAdapter;
    private HomapagePreferedAdapterOne preferedAdapterOne;
    private HomapagePreferedAdapterTwo preferedAdapterTwo;
    private HomepageShopAdapter shopAdapter;
    private List<AdapterHomepageSaleData> saleList;

    private HorizontalListViewAdapter horizontalListViewAdapter;
    private List<HorizontaiListviewData> horizontaiListviewData;
    private List<AdapterHomepageRecommendData> recommendList;
    private List<JsonBeanHomePage.ProductsCompetitiveBean.Data2Bean> preferedList;
    private List<JsonBeanHomePage.ProductsCompetitiveBean.DataBean> preferedListOne;
    private List<JsonBeanHomePage.ProductsCompetitiveBean.Data3Bean> preferedListTwo;

    private String homeBannerOne_type="";
    private String homeBannerOne_UrlAddress="";
    private String homeBannerSecond_type="";
    private String homeBannerSecond_UrlAddress="";
    private String homePreferedBannerOne_type="";
    private String homePreferedBannerOne_UrlAddress="";
    private String homePreferedBannerSecond_type="";
    private String homePreferedBannerSecond_UrlAddress="";
    private String homeShopBannerOne_type="";
    private String homeShopBannerOne_UrlAdddress="";


    private List<HorizontaiListviewListData.StoresBean.DataBean> shopList;
    private List<AdvertData> advertOneList;
    private AdvertData advert2  = new AdvertData();

    private List<HotPointBean> hotPointList;

    private  AdvertData  ad_url = new AdvertData();

    //定位权限的请求码
    private static final int REGION_REQUESTCODE = 100;
    //摄像头权限的请求码
    private static final int CAMERA_REQUESTCODE = 200;
    //TOKEN,经纬度,定位的区域
    private String token, latitude, longitude, regionName;
    //本周特价倒计时的天,小时,分钟,物兜热点的滚动位置
    private int mDisplayDays, mDisplayHours, mDisplayMinutes, position;
    //倒计时是否已启动
    private Boolean isTimerStart = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        ButterKnife.bind(HomePageFragment.this, view);
        manager = new SharedpreferencesManager(getContext());
        token = manager.getToken();
        netWorkOperate = new NetWorkOperate(HomePageFragment.this);
        refreshLayout.setCanLoadMore(true);
        refreshLayout.setFooterView(new MyFooterView(getActivity()));
        initLocation();
        initBannerAndHotPoint();

        initListView();
        initData(manager.getHomePageJson(JsonBeanHomePage.class));

        setTitleBarClickListener();
        setMenuClickListener();
        setHotPointClickListener();
        setBannerClickListener();
        setEventListener();
        setAdClickListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerViewSale.setFocusable(false);
        bannerFirst.startTurning(FlagData.DELAY_BANNER);

        netWorkOperate.getConsumerUncheckMessage(token);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseActivity = (BaseActivity) context;
        baseActivity.setTitleBarVisibility(false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            baseActivity.setTitleBarVisibility(false);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        bannerFirst.stopTurning();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        locationClient.stop();
        ButterKnife.unbind(this);
    }

    /**
     * 给regionName赋值,百度地图服务初始化,申请定位权限
     */
    private void initLocation() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            regionName = bundle.getString(FlagData.FLAG_REGION);
        }

        locationClient = new LocationClient(getContext());
        locationClient.registerLocationListener(HomePageFragment.this);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        locationClient.setLocOption(option);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            HomePageFragment.this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REGION_REQUESTCODE);
        } else {
            locationClient.start();
        }
    }

    /**
     * 初始化轮播图和物兜热点
     */
    private void initBannerAndHotPoint() {
        advertOneList = new ArrayList<>();
        hotPointList = new ArrayList<>();
        cbViewHolderCreator = new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new UrlImageHolderView();
            }
        };

        bannerFirst.setPages(cbViewHolderCreator, advertOneList)
                .setPageIndicator(new int[]{R.drawable.banner_before, R.drawable.banner_after})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

        hotPointFirst.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getContext());
                textView.setSingleLine();
                textView.setTextSize(13);
                textView.setTextColor(getResources().getColor(R.color.colorBlack));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return textView;
            }
        });
        hotPointSecond.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getContext());
                textView.setSingleLine();
                textView.setTextSize(13);
                textView.setTextColor(getResources().getColor(R.color.colorBlack));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return textView;
            }
        });
    }

    /**
     * 初始化首页的列表控件
     */
    private void initListView() {
        saleList = new ArrayList<>();
        recommendList = new ArrayList<>();
        preferedList = new ArrayList<>();
        preferedListOne = new ArrayList<>();
        preferedListTwo = new ArrayList<>();
        shopList = new ArrayList<>();
        shopAdapter = new HomepageShopAdapter(getContext(), shopList);
        recommendAdapter = new HomepageRecommendAdapter(getContext(), recommendList);
        preferedAdapter = new HomapagePreferedAdapter(getContext(), preferedList);

        preferedAdapterOne = new HomapagePreferedAdapterOne(getContext(), preferedListOne);
        preferedAdapterTwo = new HomapagePreferedAdapterTwo(getContext(), preferedListTwo);
        saleAdapter = new HomepageSaleAdapter(getContext(), saleList);
        recyclerViewSale.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewSale.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL_LIST));

        recyclerViewRecommend.setLayoutManager(new android.support.v7.widget.GridLayoutManager(getContext(), 4));



        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewPreferedOne.setLayoutManager(gridLayoutManager);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewPrefered.setLayoutManager(gridLayoutManager1);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewPreferedTwo.setLayoutManager(gridLayoutManager2);
        //   horizontalListViewAdapter;
        horizontaiListviewData = new ArrayList<>();
        horizontalListViewAdapter = new HorizontalListViewAdapter(getActivity(), horizontaiListviewData);
        homeShopHorizontalListView.setAdapter(horizontalListViewAdapter);
        homeShopHorizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i = 0; i < horizontalListViewAdapter.getDatas().size(); i++) {
                    if (i == position) {
                        horizontalListViewAdapter.getDatas().get(position).setSelected(true);
                    } else {
                        horizontalListViewAdapter.getDatas().get(i).setSelected(false);
                    }
                }
                netWorkOperate.getHomaLoadTypeList(token, horizontaiListviewData.get(position).getId());//附近店铺菜单
                horizontalListViewAdapter.notifyDataSetChanged();

            }
        });
        recyclerViewSale.setAdapter(saleAdapter);
        recyclerViewRecommend.setAdapter(recommendAdapter);
        recyclerViewPrefered.setAdapter(preferedAdapter);
        recyclerViewPreferedOne.setAdapter(preferedAdapterOne);
        recyclerViewPreferedTwo.setAdapter(preferedAdapterTwo);
        listViewShop.setAdapter(shopAdapter);
        listViewShop.setFocusable(false);
        bannerFirst.setFocusable(true);

    }

    /*
    private void initGirdView()
    {
        grid1 = (MyGridLayout)getView().findViewById(R.id.listgird);
        grid1.setGridAdapter(new MyGridLayout.GridAdatper() {

            @Override
            public View getView(int index) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_homepage_prefered,null);

                ImageView mImageViewGoods;
                TextView mTextViewName, mTextViewPrice;

                mImageViewGoods = (ImageView) view.findViewById(R.id.iv_adapter_fragmenthome_prefered_goods);
                mTextViewName = (TextView) view.findViewById(R.id.tv_adapter_fragmenthome_prefered_name);
                mTextViewPrice = (TextView) view.findViewById(R.id.tv_adapter_fragmenthome_prefered_price);


                JsonBeanHomePage.ProductsCompetitiveBean.DataBean data = preferedListOne.get(position);

                Glide.with(getContext()).load(FlagData.FLAG_IMGADDRESS + data.getImg()).into(mImageViewGoods);

                mTextViewPrice.setText("¥ " + data.getShopprice());
                mTextViewName.setText(data.getName());

                return view;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub;
                return preferedListOne.size();
            }
        });


    }

    */

    /**
     * 从SharePreference中读取首页数据，实现预加载
     */
    private void initData(JsonBeanHomePage jsonBeanHomePage) {
        advertOneList.clear();

        saleList.clear();
        recommendList.clear();

        shopList.clear();

        int count=0;
        for (JsonBeanHomePage.Adverts1Bean.DataBean dataBean : jsonBeanHomePage.getAdverts1().getData()) {
            advertOneList.add(new AdvertData(FlagData.FLAG_IMGADDRESS + dataBean.getImg(), dataBean.getUrltype(), dataBean.getUrl()));

        }
        advert2.setImageAddress(jsonBeanHomePage.getAdverts3().getData().get(0).getImg());
        advert2.setUrlAddress(jsonBeanHomePage.getAdverts3().getData().get(0).getUrl());
        advert2.setUrlType(jsonBeanHomePage.getAdverts3().getData().get(0).getUrltype());

        for (JsonBeanHomePage.ProductsLowerBean.DataBeanXXXX dataBean : jsonBeanHomePage.getProducts_lower().getData()) {
            saleList.add(new AdapterHomepageSaleData(FlagData.FLAG_IMGADDRESS + dataBean.getImg()
                    , dataBean.getName(), dataBean.getShopprice(), dataBean.getPid(), dataBean.getMarketprice()));
        }
        for (JsonBeanHomePage.Adverts4Bean.DataBeanXXX dataBean : jsonBeanHomePage.getAdverts4().getData()) {
            recommendList.add(new AdapterHomepageRecommendData(FlagData.FLAG_IMGADDRESS + dataBean.getImg()
                    , dataBean.getUrl(), dataBean.getUrltype()));
        }

        if (jsonBeanHomePage.getNews().getIsshow().equals("1")) {
            hotPointContent.setVisibility(View.GONE);
        } else {
            hotPointContent.setVisibility(View.VISIBLE);
        }
        for (JsonBeanHomePage.NewsBean.DataBeanXXXXXXX dataBeanXXXXXXX : jsonBeanHomePage.getNews().getData()) {
            hotPointList.add(new HotPointBean(dataBeanXXXXXXX.getNewsid(), dataBeanXXXXXXX.getTitle()));
        }
        if (hotPointHandler == null) {
            hotPointHandler = new HotPointHandler();
            new HotPointThread().start();
        }
        bannerFirst.notifyDataSetChanged();

        saleAdapter.notifyDataSetChanged();
        recommendAdapter.notifyDataSetChanged();
        preferedAdapter.notifyDataSetChanged();
    }

    /**
     * 监听TitleBar的点击事件
     */
    private void setTitleBarClickListener() {
        titleBarRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegionActivity.class);
                startActivity(intent);
            }
        });
        titleBarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        titleBarQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    HomePageFragment.this.requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_REQUESTCODE);
                } else {
                    startActivity(new Intent(getContext(), ScannerActivity.class));
                }
            }
        });
        titleBarMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MessageCenterActivity.class);
                intent.putExtra(FlagData.FLAG_TYPE, "1");
                intent.putExtra(FlagData.FLAG_MESSAGE_SID, "0");
                intent.putExtra(FlagData.FLAG_MESSAGE_PID, "0");
                startActivity(intent);
            }
        });
    }

    /**
     * 监听Menu的点击事件
     */
    private void setMenuClickListener() {
        menu_vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VegetableShopActivity.class);
                intent.putExtra(FlagData.FLAG_SHOP_ID, "14");
                intent.putExtra(FlagData.FLAG_STATE, "1");
                startActivity(intent);
            }
        });
        menu_fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                intent.putExtra(FlagData.FLAG_STATE, "1");
                intent.putExtra("title", "精品水果");
                startActivity(intent);
            }
        });
        menu_seasoning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                intent.putExtra("title", "粮油调味");
                intent.putExtra(FlagData.FLAG_STATE, "2");
                startActivity(intent);
            }
        });
        menu_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                intent.putExtra("title", "超市便利");
                intent.putExtra(FlagData.FLAG_STATE, "5");
                startActivity(intent);
                //  Intent intent = new Intent(getActivity(), StoreDetailsActivity.class);
                //  intent.putExtra(FlagData.FLAG_SHOP_ID, "27");
                // startActivity(intent);
            }
        });
        menu_classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentContainerActivity fragmentContainerActivity = (FragmentContainerActivity) getActivity();
                fragmentContainerActivity.showFragment(1);
            }
        });
        menu_snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                intent.putExtra("title", "吃货天地");
                intent.putExtra(FlagData.FLAG_STATE, "3");
                startActivity(intent);
            }
        });
        menu_furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                intent.putExtra("title", "家具建材");
                intent.putExtra(FlagData.FLAG_STATE, "4");
                startActivity(intent);
            }
        });
        menu_integral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntegralMallActivity.class);
                startActivity(intent);
            }
        });
        menu_secondhand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SecondHandCommodityActivity.class);
                startActivity(intent);
            }
        });
        menu_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ConvenienceServicesActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 监听HotPoint的点击事件
     */
    private void setHotPointClickListener() {
        hotPointFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HeadlineActivity.class);
                if (position == 0) {
                    intent.putExtra(FlagData.FLAG_STATE, hotPointList.get(hotPointList.size() - 1).newsid);
                } else {
                    intent.putExtra(FlagData.FLAG_STATE, hotPointList.get(position - 1).newsid);
                }
                startActivity(intent);
            }
        });
        hotPointSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HeadlineActivity.class);
                intent.putExtra(FlagData.FLAG_STATE, hotPointList.get(position).newsid);
                startActivity(intent);
            }
        });
    }

    /**
     * 监听HotPoint的点击事件
     */
    private void setBannerClickListener() {
        bannerFirst.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (advertOneList.get(position).getUrlType().equals("1")) {
                    Intent intent = new Intent(getActivity(), VegetableShopActivity.class);
                    intent.putExtra(FlagData.FLAG_SHOP_ID, advertOneList.get(position).getUrlAddress());
                    startActivity(intent);
                } else if (advertOneList.get(position).getUrlType().equals("2")) {
                    Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                    intent.putExtra(FlagData.FLAG_PRODUCT_ID, advertOneList.get(position).getUrlAddress());
                    startActivity(intent);
                } else if(advertOneList.get(position).getUrlType().equals("3")) {
                    Intent intent = new Intent(getActivity(), HeadlineActivity.class);
                    intent.putExtra(FlagData.FLAG_STATE,advertOneList.get(position).getUrlAddress());
                    startActivity(intent);
                }
            }
        });


    }

    /*
      监听点击图片的广告事件
     */
    private void setAdClickListener()
    {
        homeBannerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homeBannerOne_type.equals("1")) {
                    Intent intent = new Intent(getActivity(), VegetableShopActivity.class);
                    intent.putExtra(FlagData.FLAG_SHOP_ID, homeBannerOne_UrlAddress);
                    startActivity(intent);
                } else if (homeBannerOne_type.equals("2")) {
                    Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                    intent.putExtra(FlagData.FLAG_PRODUCT_ID, homeBannerOne_UrlAddress);
                    startActivity(intent);
                }else if(homeBannerOne_type.equals("3")) {
                    Intent intent = new Intent(getActivity(), HeadlineActivity.class);
                    intent.putExtra(FlagData.FLAG_STATE,homeBannerOne_UrlAddress);
                    startActivity(intent);
                }

            }
        });


        homeBannerOne_gai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homeBannerSecond_type.equals("1")) {
                    Intent intent = new Intent(getActivity(), VegetableShopActivity.class);
                    intent.putExtra(FlagData.FLAG_SHOP_ID, homeBannerSecond_UrlAddress);
                    startActivity(intent);
                } else if (homeBannerSecond_type.equals("2")) {
                    Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                    intent.putExtra(FlagData.FLAG_PRODUCT_ID, homeBannerSecond_UrlAddress);
                    startActivity(intent);
                } else if(homeBannerSecond_type.equals("3")) {
                    Intent intent = new Intent(getActivity(), HeadlineActivity.class);
                    intent.putExtra(FlagData.FLAG_STATE,homeBannerSecond_UrlAddress);
                    startActivity(intent);
                }
            }
        });


        homePreferedBannerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homePreferedBannerOne_type.equals("1")) {
                    Intent intent = new Intent(getActivity(), VegetableShopActivity.class);
                    intent.putExtra(FlagData.FLAG_SHOP_ID, homePreferedBannerOne_UrlAddress);
                    startActivity(intent);
                } else if (homePreferedBannerOne_type.equals("2")) {
                    Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                    intent.putExtra(FlagData.FLAG_PRODUCT_ID, homePreferedBannerOne_UrlAddress);
                    startActivity(intent);
                }else if(homePreferedBannerOne_type.equals("3")) {
                    Intent intent = new Intent(getActivity(), HeadlineActivity.class);
                    intent.putExtra(FlagData.FLAG_STATE,homePreferedBannerOne_UrlAddress);
                    startActivity(intent);
                }
            }
        });


        homePreferedBannerSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homePreferedBannerSecond_type.equals("1")) {
                    Intent intent = new Intent(getActivity(), VegetableShopActivity.class);
                    intent.putExtra(FlagData.FLAG_SHOP_ID, homePreferedBannerSecond_UrlAddress);
                    startActivity(intent);
                } else if (homePreferedBannerSecond_type.equals("2")) {
                    Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                    intent.putExtra(FlagData.FLAG_PRODUCT_ID, homePreferedBannerSecond_UrlAddress);
                    startActivity(intent);
                }else if(homePreferedBannerSecond_type.equals("3")) {
                    Intent intent = new Intent(getActivity(), HeadlineActivity.class);
                    intent.putExtra(FlagData.FLAG_STATE,homePreferedBannerSecond_UrlAddress);
                    startActivity(intent);
                }
            }
        });



        homeShopBannerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homeShopBannerOne_type.equals("1")) {
                    Intent intent = new Intent(getActivity(), VegetableShopActivity.class);
                    intent.putExtra(FlagData.FLAG_SHOP_ID, homeShopBannerOne_UrlAdddress);
                    startActivity(intent);
                } else if (homeShopBannerOne_type.equals("2")) {
                    Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                    intent.putExtra(FlagData.FLAG_PRODUCT_ID, homeShopBannerOne_UrlAdddress);
                    startActivity(intent);
                }else if(homeShopBannerOne_type.equals("3")) {
                    Intent intent = new Intent(getActivity(), HeadlineActivity.class);
                    intent.putExtra(FlagData.FLAG_STATE,homeShopBannerOne_UrlAdddress);
                    startActivity(intent);
                }
            }
        });


    }



    /**
     * 监听整个页面刷新,本周特价更多按钮,附近店铺的点击事件
     */
    private void setEventListener() {

        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        netWorkOperate.getHomaPageData(token);
                        netWorkOperate.getHomaPageAdvertsListOne(token);//轮播图下方图片
                        netWorkOperate.getHomaPageAdvertsListFour(token);//第4个轮播图

                    }
                }, 2000);
            }

            @Override
            public void loadMore() {

                refreshLayout.finishLoadMore();

            }
        });

        tvSaleMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WeekSaleActivity.class);
                startActivity(intent);
            }
        });
        listViewShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String storeId = shopList.get(position).getStoreid();
                Intent intent = new Intent(getActivity(), VegetableShopActivity.class);
                intent.putExtra(FlagData.FLAG_SHOP_ID, storeId);
                startActivity(intent);
            }
        });



    }

    /**
     * 系统请求定位和拍照权限的回调方法
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == CAMERA_REQUESTCODE) {
                startActivity(new Intent(getContext(), ScannerActivity.class));
            } else if (requestCode == REGION_REQUESTCODE) {
                locationClient.start();
            }
        }
    }

    /**
     * 百度地图定位的回调方法
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (regionName != null) {
            titleBarRegion.setText(regionName);
        } else {
            //addr : 中国江苏省南通市崇川区工农路243
            regionName = bdLocation.getAddrStr().substring(8, 11);
        }
        titleBarRegion.setText(regionName);
        Log.d(">>>>>>>>", regionName);
        latitude = bdLocation.getLatitude() + "";
        longitude = bdLocation.getLongitude() + "";
        if (latitude != null && longitude != null) {
            netWorkOperate.getCoordinateToken(manager.getToken(), latitude, longitude);
        }
    }

    /**
     * 网络请求首页数据的回调
     */
    @Override
    public void onGetData(JsonBeanHomePage jsonBeanHomePage) {
        advertOneList.clear();
        saleList.clear();
        recommendList.clear();
        preferedListOne.clear();
        preferedList.clear();
        preferedListTwo.clear();


        for (JsonBeanHomePage.Adverts1Bean.DataBean dataBean : jsonBeanHomePage.getAdverts1().getData()) {
            advertOneList.add(new AdvertData(FlagData.FLAG_IMGADDRESS + dataBean.getImg(), dataBean.getUrltype(), dataBean.getUrl()));
        }

        for (JsonBeanHomePage.ProductsLowerBean.DataBeanXXXX dataBean : jsonBeanHomePage.getProducts_lower().getData()) {
            saleList.add(new AdapterHomepageSaleData(FlagData.FLAG_IMGADDRESS + dataBean.getImg()
                    , dataBean.getName(), dataBean.getShopprice(), dataBean.getPid(), dataBean.getMarketprice()));
        }
        for (JsonBeanHomePage.Adverts4Bean.DataBeanXXX dataBean : jsonBeanHomePage.getAdverts4().getData()) {
            recommendList.add(new AdapterHomepageRecommendData(FlagData.FLAG_IMGADDRESS + dataBean.getImg()
                    , dataBean.getUrl(), dataBean.getUrltype()));
        }
        for (JsonBeanHomePage.ProductsCompetitiveBean.DataBean dataBean : jsonBeanHomePage.getProducts_competitive().getData()) {

            preferedListOne.add(dataBean);
        }
        for (JsonBeanHomePage.ProductsCompetitiveBean.Data2Bean data2Bean : jsonBeanHomePage.getProducts_competitive().getData2()) {

            preferedList.add(data2Bean);
        }
        for (JsonBeanHomePage.ProductsCompetitiveBean.Data3Bean data3Bean : jsonBeanHomePage.getProducts_competitive().getData3()) {
            preferedListTwo.add(data3Bean);
        }

//        Glide.with(getActivity()).load(FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getAdverts3().getData().get(0).getImg()).into(homeBannerOne);
//        Glide.with(getActivity()).load(FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getProducts_competitive().getAdverts().get(0).getImg()).into(homeBannerOne_gai1);
//        Log.d(">>精品推荐1>", FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getProducts_competitive().getAdverts().get(0).getImg());
//        Glide.with(getActivity()).load(FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getProducts_competitive().getAdverts2().get(0).getImg()).into(homePreferedBannerOne);
//        Glide.with(getActivity()).load(FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getProducts_competitive().getAdverts3().get(0).getImg()).into(homePreferedBannerSecond);
//        Glide.with(getActivity()).load(FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getAdverts_stores_nearby().getData().get(0).getImg()).into(homeShopBannerOne);

        Glide.with(getActivity()).load(FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getAdverts3().getData().get(0).getImg()).into(homeBannerOne);
        Glide.with(getActivity()).load(FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getProducts_competitive().getAdverts().get(0).getImg()).into(homeBannerOne_gai1);
        Log.d(">>精品推荐1>", FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getProducts_competitive().getAdverts().get(0).getImg());
        Glide.with(getActivity()).load(FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getProducts_competitive().getAdverts2().get(0).getImg()).into(homePreferedBannerOne);
        Glide.with(getActivity()).load(FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getProducts_competitive().getAdverts3().get(0).getImg()).into(homePreferedBannerSecond);
        Glide.with(getActivity()).load(FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getAdverts_stores_nearby().getData().get(0).getImg()).into(homeShopBannerOne);

        homeBannerOne_type = jsonBeanHomePage.getAdverts3().getData().get(0).getUrltype();
        homeBannerOne_UrlAddress = jsonBeanHomePage.getAdverts3().getData().get(0).getUrl();

        homeBannerSecond_type = jsonBeanHomePage.getProducts_competitive().getAdverts().get(0).getUrltype();
        homePreferedBannerOne_type = jsonBeanHomePage.getProducts_competitive().getAdverts2().get(0).getUrltype();
        homePreferedBannerSecond_type = jsonBeanHomePage.getProducts_competitive().getAdverts3().get(0).getUrltype();

        homeBannerSecond_UrlAddress = jsonBeanHomePage.getProducts_competitive().getAdverts().get(0).getUrl();
        homePreferedBannerOne_UrlAddress = jsonBeanHomePage.getProducts_competitive().getAdverts2().get(0).getUrl();
        homePreferedBannerSecond_UrlAddress = jsonBeanHomePage.getProducts_competitive().getAdverts3().get(0).getUrl();

        homeShopBannerOne_type = jsonBeanHomePage.getAdverts_stores_nearby().getData().get(0).getUrltype();
        homeShopBannerOne_UrlAdddress = jsonBeanHomePage.getAdverts_stores_nearby().getData().get(0).getUrl();


        if (jsonBeanHomePage.getNews().getIsshow().equals("1")) {
            hotPointContent.setVisibility(View.GONE);
        } else {
            hotPointContent.setVisibility(View.VISIBLE);
        }
        for (JsonBeanHomePage.NewsBean.DataBeanXXXXXXX dataBeanXXXXXXX : jsonBeanHomePage.getNews().getData()) {
            hotPointList.add(new HotPointBean(dataBeanXXXXXXX.getNewsid(), dataBeanXXXXXXX.getTitle()));
        }
        if (hotPointHandler == null) {
            hotPointHandler = new HotPointHandler();
            new HotPointThread().start();
        }

        if (!isTimerStart) {
            long milliDiff = Long.parseLong(jsonBeanHomePage.getProducts_lower().getCountdown());
            initCountDownTimer(milliDiff * 1000);
            isTimerStart = true;
        }

        bannerFirst.notifyDataSetChanged();
        saleAdapter.notifyDataSetChanged();
        recommendAdapter.notifyDataSetChanged();
        preferedAdapterOne.notifyDataSetChanged();
        preferedAdapter.notifyDataSetChanged();
        preferedAdapterTwo.notifyDataSetChanged();
        preferedAdapter.notifyDataSetChanged();
        shopAdapter.notifyDataSetChanged();

       // initGirdView();

    }

    /**
     * 判断用户是否有未读消息
     */
    @Override
    public void onGetConsumerUncheckMessage(JsonBeanConsumerUncheckMessage jsonBeanConsumerUncheckMessage) {
        if (jsonBeanConsumerUncheckMessage.getIsreadcount().equals("0")) {
            titleBarMessage.setImageResource(R.drawable.message_round_default);
        } else {
            titleBarMessage.setImageResource(R.drawable.message_round_redpoint);
        }
    }

    /**
     * 上传经纬度成功更新token和首页数据
     */
    @Override
    public void onGetNewToken(JsonBeanGetToken jsonBeanGetToken) {
        manager.saveToken(jsonBeanGetToken.getAccess_token());
        token = manager.getToken();
        netWorkOperate.getHomaPageData(token);
        netWorkOperate.getHomaPageAdvertsListOne(token);//轮播图下方图片
        netWorkOperate.getHomaPageAdvertsListFour(token);//第4个轮播图
        netWorkOperate.getHomaLoadType(token);//附近店铺菜单
        netWorkOperate.getConsumerUncheckMessage(token);
        netWorkOperate.getHomaLoadproducts(token);
    }

    /**
     * 网络请求失败的回调方法
     */
    @Override
    public void onFailure(String msg) {
    }

    private void initCountDownTimer(long milliDiff) {
        new CountDownTimer(milliDiff, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mDisplayDays = (int) ((millisUntilFinished / 1000) / 86400);
                mDisplayHours = (int) (((millisUntilFinished / 1000) - (mDisplayDays * 86400)) / 3600);
                mDisplayMinutes = (int) (((millisUntilFinished / 1000) - ((mDisplayDays * 86400) + (mDisplayHours * 3600))) / 60);

                try {
                    tvSaleDays.setText(mDisplayDays + "天");
                    tvSaleHours.setText(mDisplayHours + "时");
                    tvSaleMinutes.setText(mDisplayMinutes + "分");

                } catch (Exception e) {
                    //  Log.d(">>>e", e.toString());
                }
            }

            @Override
            public void onFinish() {
                try {
                    tvSaleDays.setText("0天");
                    tvSaleHours.setText("0时");
                    tvSaleMinutes.setText("0分");

                } catch (Exception e) {
                    //  Log.d(">>>e", e.toString());
                }

            }
        }.start();
    }

    //促销
    @Override
    public void onGetDataOne(JsonBeanAdverts jsonBeanHomePage) {
        Log.d(">>>>>>>", jsonBeanHomePage.toString());
//
        Log.d(">>tup>>>>>", FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getData().get(0).getImg());
//        Glide.with(getActivity()).load(FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getData().get(0).getImg()).into(home_iv_tp);
        Glide.with(getActivity()).load(FlagData.FLAG_IMGADDRESS + jsonBeanHomePage.getData().get(0).getImg()).into(home_iv_tp);
    }


    @Override
    public void onGetDataFour(JsonBeanAdverts jsonBeanHomePage) {


    }


    @Override
    public void onGetDataLoadType(List<HorizontaiListviewData> jsonBeanHomePage) {
        horizontaiListviewData.clear();
        horizontaiListviewData = jsonBeanHomePage;

        for (int i = 0; i < horizontaiListviewData.size(); i++) {
            if (i == 0) {
                horizontaiListviewData.get(i).setSelected(true);
            }
        }
        horizontalListViewAdapter.setDatas(horizontaiListviewData);
        horizontalListViewAdapter.notifyDataSetChanged();
        netWorkOperate.getHomaLoadTypeList(token, horizontaiListviewData.get(0).getId());//附近店铺数据

        Log.d(TAG, "onGetDataLoadType: horizontaiListviewData");
        
    }

    @Override
    public void onGetDataList(HorizontaiListviewListData jsonBeanHomePage) {
        shopList.clear();
        for (HorizontaiListviewListData.StoresBean.DataBean dataBean : jsonBeanHomePage.getStores().getData()) {
            shopList.add(dataBean);
        }

        shopAdapter.notifyDataSetChanged();
    }


    @Override
    public void onGetDataLoadproducts(HomaLoadproductsData jsonBeanHomePage) {

    }

    /**
     * 轮播图
     */
    private class UrlImageHolderView implements Holder<AdvertData> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, AdvertData data) {
//            Glide.with(getContext()).load(data.getImageAddress()).into(imageView);
            Glide.with(getContext()).load(data.getImageAddress()).into(imageView);
        }
    }

    /**
     * 物兜热点的Handler
     */
    private class HotPointHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {

                hotPointFirst.setText(hotPointList.get(position).title);
                position++;
                if (position == hotPointList.size()) {
                    position = 0;
                }
                hotPointSecond.setText(hotPointList.get(position).title);
            } catch (Exception e) {

            }
        }
    }

    /**
     * 物兜热点的Thread
     */
    private class HotPointThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (position < hotPointList.size()) {
                try {
                    synchronized (this) {
                        hotPointHandler.sendEmptyMessage(0);
                        this.sleep(3000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 物兜热点的基类
     */
    private class HotPointBean {
        private String newsid, title;

        public HotPointBean(String newsid, String title) {
            this.newsid = newsid;
            this.title = title;
        }
    }
}

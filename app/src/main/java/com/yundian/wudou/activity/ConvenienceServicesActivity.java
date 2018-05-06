package com.yundian.wudou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.ConvenienceServicesAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterConvenienceServicesData;
import com.yundian.wudou.data.AdvertData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanConvenienceServices;
import com.yundian.wudou.network.JsonBeanConvenienceServicesBanner;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConvenienceServicesActivity extends BaseActivity implements NetWorkInterface.OnGetConvenienceServicesBannerListener, NetWorkInterface.OnGetConvenienceServicesListener {

    @Bind(R.id.rl_activity_convenienceservices_nodata)
    RelativeLayout rlNoData;
    @Bind(R.id.tv_activity_convenienceservices_nodata)
    TextView tvNoData;
    @Bind(R.id.prl_activity_convenienceservices)
    PullToRefreshLayout refreshLayout;
    @Bind(R.id.lv_activity_convenienceservices)
    ListView listView;

    @Bind(R.id.ll_activity_convenienceservices_static)
    LinearLayout llStatic;
    @Bind(R.id.sp_activity_convenienceservices_static_region)
    Spinner spRegionStatic;
    @Bind(R.id.sp_activity_convenienceservices_static_category)
    Spinner spCategoryStatic;
    @Bind(R.id.sp_activity_convenienceservices_static_sate)
    Spinner spSateStatic;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private ConvenientBanner convenientBanner;

    private ConvenienceServicesAdapter convenienceServicesAdapter;
    private ArrayAdapter<String> arr_adapter1, arr_adapter2, arr_adapter3;

    private EditText etSearch;
    private View headView;
    private LinearLayout llScroll;
    private Spinner spRegion, spCategory, spSate;

    private List<String> mListurl, mListUrltype, imageAddressList;
    private List<String> mListRegionName, mListCategoryName, mListSateName;
    private List<AdvertData> advertDataList;
    private List<RegionBean> regionBeanList;
    private List<CategoryBean> categoryBeanList;
    private List<SateBean> sateBeanList;
    private List<AdapterConvenienceServicesData> convenienceServicesList;

    private boolean hasMore = true, isRefresh;
    private int currentPage = 1;
    private String token, region, sate, cateid, keyvalue;

    private int[] locStatic = new int[2];
    private int[] locScroll = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenienceservices);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(ConvenienceServicesActivity.this);
        manager = new SharedpreferencesManager(ConvenienceServicesActivity.this);
        netWorkOperate = new NetWorkOperate(ConvenienceServicesActivity.this);

        setTitle("便民服务");
        setBackVisibility(true);
        this.setRightViewVisibility(true);
        this.setRightViewText("发布信息");

        headView = LayoutInflater.from(ConvenienceServicesActivity.this).inflate(R.layout.layout_convenienceservices_headview, null);
        etSearch = (EditText) headView.findViewById(R.id.et_layout_convenienceservices_search);
        convenientBanner = (ConvenientBanner) headView.findViewById(R.id.cb_layout_convenienceservices);
        llScroll = (LinearLayout) headView.findViewById(R.id.ll_layout_convenienceservices);
        spRegion = (Spinner) headView.findViewById(R.id.sp_layout_convenienceservices_region);
        spCategory = (Spinner) headView.findViewById(R.id.sp_layout_convenienceservices_category);
        spSate = (Spinner) headView.findViewById(R.id.sp_layout_convenienceservices_sate);

        mListurl = new ArrayList<>();
        mListUrltype = new ArrayList<>();
        imageAddressList = new ArrayList<>();
        advertDataList = new ArrayList<>();

        mListRegionName = new ArrayList<>();
        mListCategoryName = new ArrayList<>();
        mListSateName = new ArrayList<>();
        regionBeanList = new ArrayList<>();
        categoryBeanList = new ArrayList<>();
        sateBeanList = new ArrayList<>();
        convenienceServicesList = new ArrayList<>();

        arr_adapter1 = new ArrayAdapter<String>(this, R.layout.item_spinselect, mListRegionName);
        arr_adapter1.setDropDownViewResource(R.layout.item_dialogspinselect);
        spRegion.setAdapter(arr_adapter1);
        spRegionStatic.setAdapter(arr_adapter1);

        arr_adapter2 = new ArrayAdapter<String>(this, R.layout.item_spinselect, mListCategoryName);
        arr_adapter2.setDropDownViewResource(R.layout.item_dialogspinselect);
        spCategory.setAdapter(arr_adapter2);
        spCategoryStatic.setAdapter(arr_adapter2);

        arr_adapter3 = new ArrayAdapter<String>(this, R.layout.item_spinselect, mListSateName);
        arr_adapter3.setDropDownViewResource(R.layout.item_dialogspinselect);
        spSate.setAdapter(arr_adapter3);
        spSateStatic.setAdapter(arr_adapter3);

        convenienceServicesAdapter = new ConvenienceServicesAdapter(ConvenienceServicesActivity.this, convenienceServicesList);
        listView.addHeaderView(headView);
        listView.setAdapter(convenienceServicesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        token = manager.getToken();
        initRefreshVariables();
        netWorkOperate.getConvenienceServicesBanner(token);
        netWorkOperate.getConvenienceServices(token, region, sate, cateid, keyvalue, currentPage + "");
    }

    private void setEventListener() {
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        initRefreshVariables();
                        netWorkOperate.getConvenienceServicesBanner(token);
                        netWorkOperate.getConvenienceServices(token, region, sate, cateid, keyvalue, currentPage + "");
                    }
                }, 2000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                        if (hasMore) {
                            currentPage++;
                        }
                        isRefresh = false;
                        netWorkOperate.getConvenienceServices(token, region, sate, cateid, keyvalue, currentPage + "");
                    }
                }, 2000);
            }
        });

        this.setRightViewClickListener(new OnActionBarRightViewClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(ConvenienceServicesActivity.this, ReleaseGoodsActivity.class);
                intent.putExtra(FlagData.FLAG_TITLE, "发布便民服务");
                intent.putExtra(FlagData.FLAG_RELEASE, 2);
                startActivity(intent);
                ConvenienceServicesActivity.this.finish();
            }
        });

        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    Intent intent = new Intent(ConvenienceServicesActivity.this, SearchResultActivity.class);
                    intent.putExtra(FlagData.FLAG_SEARCH_TEXT,etSearch.getText().toString());
                    intent.putExtra(FlagData.FLAG_STATE,"1");
                    startActivity(intent);
                }
                return false;
            }
        });

        spRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRefreshVariables();
                spRegionStatic.setSelection(position);
                region = regionBeanList.get(position).code;
                netWorkOperate.getConvenienceServices(token, region, sate, cateid, keyvalue, currentPage + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spRegionStatic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRefreshVariables();
                spRegion.setSelection(position);
                region = regionBeanList.get(position).code;
                netWorkOperate.getConvenienceServices(token, region, sate, cateid, keyvalue, currentPage + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRefreshVariables();
                spCategoryStatic.setSelection(position);
                cateid = categoryBeanList.get(position).code;
                netWorkOperate.getConvenienceServices(token, region, sate, cateid, keyvalue, currentPage + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spCategoryStatic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRefreshVariables();
                spCategoryStatic.setSelection(position);
                cateid = categoryBeanList.get(position).code;
                netWorkOperate.getConvenienceServices(token, region, sate, cateid, keyvalue, currentPage + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRefreshVariables();
                spSateStatic.setSelection(position);
                sate = sateBeanList.get(position).code;
                netWorkOperate.getConvenienceServices(token, region, sate, cateid, keyvalue, currentPage + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spSateStatic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRefreshVariables();
                spSate.setSelection(position);
                sate = sateBeanList.get(position).code;
                netWorkOperate.getConvenienceServices(token, region, sate, cateid, keyvalue, currentPage + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ConvenienceServicesActivity.this, ConvenienceServicesDetailsActivity.class);
                intent.putExtra("newsid", convenienceServicesList.get(i - 1).getNewsid());
                startActivity(intent);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                llScroll.getLocationOnScreen(locScroll);
                llStatic.getLocationOnScreen(locStatic);
                if (locScroll[1] <= locStatic[1]) {
                    llStatic.setVisibility(View.VISIBLE);
                } else {
                    llStatic.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });
    }

    @Override
    public void onGetConvenienceServicesBanner(JsonBeanConvenienceServicesBanner jsonBeanConvenienceServicesBanner) {
        mListurl.clear();
        mListUrltype.clear();
        advertDataList.clear();
        imageAddressList.clear();
        for (JsonBeanConvenienceServicesBanner.DataBean dataBean : jsonBeanConvenienceServicesBanner.getData()) {
            advertDataList.add(new AdvertData(FlagData.FLAG_IMGADDRESS + dataBean.getImg(), dataBean.getUrltype(), dataBean.getUrl()));
            imageAddressList.add(FlagData.FLAG_IMGADDRESS + dataBean.getImg());

            mListUrltype.add(dataBean.getUrltype());
            mListurl.add(dataBean.getUrl());
        }
        convenientBanner.setPages(new CBViewHolderCreator<UrlImageHolderView>() {
            @Override
            public UrlImageHolderView createHolder() {
                return new UrlImageHolderView();
            }
        }, imageAddressList).setPageIndicator(new int[]{R.drawable.banner_before, R.drawable.banner_after})
                .startTurning(FlagData.DELAY_BANNER);
    }


    @Override
    public void onGetConvenienceServices(JsonBeanConvenienceServices jsonBeanConvenienceServices) {
        regionBeanList.clear();
        mListRegionName.clear();
        sateBeanList.clear();
        mListSateName.clear();
        categoryBeanList.clear();
        mListCategoryName.clear();
        for (JsonBeanConvenienceServices.RegionBean.DataBean dataBean : jsonBeanConvenienceServices.getRegion().getData()) {
            regionBeanList.add(new RegionBean(dataBean.getCode(), dataBean.getName()));
            mListRegionName.add(dataBean.getName());
        }
        for (JsonBeanConvenienceServices.SateBean.DataBeanX dataBean : jsonBeanConvenienceServices.getSate().getData()) {
            sateBeanList.add(new SateBean(dataBean.getCode(), dataBean.getName()));
            mListSateName.add(dataBean.getName());
        }
        for (JsonBeanConvenienceServices.CategoriesBean.DataBeanXX dataBeanXX:jsonBeanConvenienceServices.getCategories().getData()){
            categoryBeanList.add(new CategoryBean(dataBeanXX.getCode(),dataBeanXX.getName()));
            mListCategoryName.add(dataBeanXX.getName());
        }
        if(isRefresh){
            convenienceServicesList.clear();
            if(jsonBeanConvenienceServices.getNews().getRet().equals("0")){
                rlNoData.setVisibility(View.INVISIBLE);
            }else {
                rlNoData.setVisibility(View.VISIBLE);
                tvNoData.setText(jsonBeanConvenienceServices.getNews().getMsg());
            }
        }else {
            if(jsonBeanConvenienceServices.getNews().getRet().equals("1")){
                Toast.makeText(ConvenienceServicesActivity.this,jsonBeanConvenienceServices.getNews().getMsg(),Toast.LENGTH_SHORT).show();
            }
        }
        for (JsonBeanConvenienceServices.NewsBean.DataBeanXXX dataBean : jsonBeanConvenienceServices.getNews().getData()) {
            convenienceServicesList.add(new AdapterConvenienceServicesData(dataBean.getNewsid(), FlagData.FLAG_IMGADDRESS + dataBean.getImg(), dataBean.getName(), dataBean.getRegion(), dataBean.getTime()));
        }
        arr_adapter1.notifyDataSetChanged();
        arr_adapter2.notifyDataSetChanged();
        arr_adapter3.notifyDataSetChanged();
        convenienceServicesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(ConvenienceServicesActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initRefreshVariables() {
        isRefresh = true;
        currentPage = 1;
        hasMore = true;
        region = "";
        cateid = "";
        sate = "";
        keyvalue = "";
        etSearch.setText("");
    }

    //轮播图
    @Override
    protected void onPause() {
        super.onPause();
        convenientBanner.stopTurning();
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
            Glide.with(ConvenienceServicesActivity.this).load(data).into(imageView);
        }
    }

    private class RegionBean {
        private String code, name;

        public RegionBean(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    private class CategoryBean {
        private String code, name;

        public CategoryBean(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    private class SateBean {
        private String code, name;

        public SateBean(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }
}

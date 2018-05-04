package com.yundian.wudou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.IntegralMallAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.customview.ListView;
import com.yundian.wudou.data.AdapterIntegralMallData;
import com.yundian.wudou.data.AdvertData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanIntergalShopCommodityData;
import com.yundian.wudou.network.JsonBeanLoadAdverts;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IntegralMallActivity extends BaseActivity implements NetWorkInterface.OnGetLoadAdvertsListener, NetWorkInterface.OnGetIntergalShopCommodityListener {

    @Bind(R.id.prl_activity_integralmall)PullToRefreshLayout refreshLayout;
    @Bind(R.id.lv_activity_integralmall)ListView listView;
    @Bind(R.id.cb_activity_integralmall)ConvenientBanner convenientBanner;

    private static final int DELAY_BANNER = 3000;

    private IntegralMallAdapter integralMallAdapter;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private List<AdapterIntegralMallData> integralMallList;
    private List<AdvertData> advertDataList;

    private boolean hasMore = true;
    private int currentPage = 1;
    private String token;
    private int width=0;
    private int height=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_integral_mall);

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();// 屏幕宽度
        height = wm.getDefaultDisplay().getHeight();// 屏幕高度

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(IntegralMallActivity.this);
        manager = new SharedpreferencesManager(IntegralMallActivity.this);
        netWorkOperate = new NetWorkOperate(IntegralMallActivity.this);

        setTitle("积分商城");
        setBackVisibility(true);

        integralMallList = new ArrayList<>();
        integralMallAdapter = new IntegralMallAdapter(IntegralMallActivity.this, integralMallList);
        listView.setAdapter(integralMallAdapter);

        advertDataList = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        integralMallList.clear();
        currentPage = 1;
        hasMore = true;
        token = manager.getToken();
        netWorkOperate.getLoadAdverts(token);
        netWorkOperate.getIntergalShopCpmmodityData(token, "" + currentPage);
    }

    private void setEventListener() {
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        integralMallList.clear();
                        currentPage = 1;
                        hasMore = true;
                        netWorkOperate.getLoadAdverts(token);
                        netWorkOperate.getIntergalShopCpmmodityData(token, "" + currentPage);
                    }
                },2000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                        if(hasMore){
                            currentPage++;
                        }
                        netWorkOperate.getIntergalShopCpmmodityData(token, "" + currentPage);
                    }
                },2000);
            }
        });

        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (advertDataList.get(position).getUrlType().equals("1")) {
                    Intent intent = new Intent(IntegralMallActivity.this, VegetableShopActivity.class);
                    intent.putExtra(FlagData.FLAG_SHOP_ID, advertDataList.get(position).getUrlAddress());
                    startActivity(intent);
                } else if (advertDataList.get(position).getUrlType().equals("2")) {
                    Intent intent = new Intent(IntegralMallActivity.this, CommodityDetailsActivity.class);
                    intent.putExtra(FlagData.FLAG_PRODUCT_ID, advertDataList.get(position).getUrlAddress());
                    startActivity(intent);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(IntegralMallActivity.this, IntegralCommodityDetailActivity.class);
                intent.putExtra(FlagData.FLAG_PRODUCT_ID, integralMallList.get(i).getPid());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onGetLoadAdverts(JsonBeanLoadAdverts jsonBeanLoadAdverts) {
        advertDataList.clear();
        for (JsonBeanLoadAdverts.DataBean dataBean : jsonBeanLoadAdverts.getData()) {
            advertDataList.add(new AdvertData(FlagData.FLAG_IMGADDRESS + dataBean.getImg(), dataBean.getUrltype(), dataBean.getUrl()));
        }
        convenientBanner.setPages(new CBViewHolderCreator<UrlImageHolderView>() {
            @Override
            public UrlImageHolderView createHolder() {
                return new UrlImageHolderView();
            }
        }, advertDataList)
                .setPageIndicator(new int[]{R.drawable.banner_before, R.drawable.banner_after})
                .startTurning(DELAY_BANNER);
    }

    @Override
    public void onGetIntergalShopCommodity(JsonBeanIntergalShopCommodityData jsonBeanIntergalShopCommodityData) {
        for (JsonBeanIntergalShopCommodityData.DataBean dataBean : jsonBeanIntergalShopCommodityData.getData()) {
            integralMallList.add(new AdapterIntegralMallData(dataBean.getPid(), FlagData.FLAG_IMGADDRESS + dataBean.getImg(),
                    dataBean.getName(), dataBean.getShopprice(), dataBean.getStock(), dataBean.getExchangecount()));
        }
        integralMallAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        hasMore = false;
        Toast.makeText(IntegralMallActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        convenientBanner.stopTurning();
    }

    public class UrlImageHolderView implements Holder<AdvertData> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, AdvertData data) {
            Picasso.with(IntegralMallActivity.this).load(data.getImageAddress()).into(imageView);
        }
    }
}


package com.yundian.wudou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.MyServiceAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterMyConvenienceServicesData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanDeleteMyConvenienceServices;
import com.yundian.wudou.network.JsonBeanMyConvenienceServices;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyServiceActivity extends BaseActivity implements NetWorkInterface.OnGetMyConvenienceServicesListener,
        NetWorkInterface.OnDeleteMyConvenienceServicesListener {

    @Bind(R.id.prl_activity_myservice)
    PullToRefreshLayout refreshLayout;
    @Bind(R.id.rl_activity_myservice_nodata)
    RelativeLayout rlNoData;
    @Bind(R.id.tv_activity_myservice_nodata)
    TextView tvNoData;
    @Bind(R.id.lv_activity_myservice)
    ListView listView;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private ViewGroup mViewGroupChild;
    private RelativeLayout.LayoutParams mLayoutParams;
    private int mScreenWidth, mDeleteBtnWidth, mDownX, mDownY;
    private boolean isDeleteShow, isMove;

    private MyServiceAdapter myServiceAdapter;
    private List<AdapterMyConvenienceServicesData> myServiceList;

    private boolean hasMore = true;
    private int currentpage = 1;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myservice);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(MyServiceActivity.this);
        manager = new SharedpreferencesManager(MyServiceActivity.this);
        netWorkOperate = new NetWorkOperate(MyServiceActivity.this);

        setTitle("我的便民服务");
        setBackVisibility(true);
        this.setRightViewVisibility(true);
        this.setRightViewText(getResources().getString(R.string.public_infomation));

        myServiceList = new ArrayList<>();
        myServiceAdapter = new MyServiceAdapter(MyServiceActivity.this, myServiceList);
        listView.setAdapter(myServiceAdapter);

        WindowManager windowManager = (WindowManager) MyServiceActivity.this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
    }

    @Override
    protected void onResume() {
        super.onResume();
        myServiceList.clear();
        currentpage = 1;
        hasMore = true;
        token = manager.getToken();
        netWorkOperate.getMyConvenienceServices(token, currentpage+"");
    }

    private void setEventListener() {
        this.setRightViewClickListener(new OnActionBarRightViewClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(MyServiceActivity.this, ReleaseGoodsActivity.class);
                intent.putExtra(FlagData.FLAG_TITLE, "发布便民服务");
                intent.putExtra(FlagData.FLAG_RELEASE, 2);
                startActivity(intent);
                MyServiceActivity.this.finish();
            }
        });

        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        myServiceList.clear();
                        currentpage = 1;
                        hasMore = true;
                        netWorkOperate.getMyConvenienceServices(token, currentpage+"");
                    }
                }, 2000);
            }

            @Override
            public void loadMore() {
                refreshLayout.finishLoadMore();
                if (hasMore) {
                    currentpage++;
                }
                netWorkOperate.getMyConvenienceServices(token, currentpage+"");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isDeleteShow) {
                    if (!isMove) {
                        Intent intent = new Intent(MyServiceActivity.this, MyServiceDetailActivity.class);
                        intent.putExtra(FlagData.FLAG_NEWS_ID, myServiceList.get(position).getNewsId());
                        startActivity(intent);
                    } else {
                        isMove = false;
                    }
                }
            }
        });

        myServiceAdapter.setItemReduceListener(new MyServiceAdapter.ItemReduceListener() {
            @Override
            public void itemReduceListener(String newsid) {
                netWorkOperate.deleteMyConvenienceServices(token, newsid);
            }
        });

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        performActionDown(motionEvent);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        return performActionMove(motionEvent);
                    case MotionEvent.ACTION_UP:
                        performActionUp();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        performActionUp();
                        break;
                }
                return MyServiceActivity.super.onTouchEvent(motionEvent);
            }
        });
    }

    @Override
    public void onGetMyConvenienceServices(JsonBeanMyConvenienceServices jsonBeanMyConvenienceServices) {
        if (jsonBeanMyConvenienceServices.getNews().getRet().equals("1")) {
            rlNoData.setVisibility(View.VISIBLE);
            tvNoData.setText(jsonBeanMyConvenienceServices.getNews().getMsg());
        } else if(jsonBeanMyConvenienceServices.getNews().getRet().equals("0")){
            rlNoData.setVisibility(View.GONE);
        }
        for (JsonBeanMyConvenienceServices.NewsBean.DataBean dataBean : jsonBeanMyConvenienceServices.getNews().getData()) {
            myServiceList.add(new AdapterMyConvenienceServicesData(dataBean.getNewsid(), FlagData.FLAG_IMGADDRESS + dataBean.getImg(), dataBean.getName(),
                    dataBean.getRegion(), dataBean.getTime(), dataBean.getReviewedsate()));
        }
        myServiceAdapter.notifyDataSetChanged();
    }

    /**
     * 删除item的操作
     * */
    @Override
    public void onDeleteMyConvenienceServices(JsonBeanDeleteMyConvenienceServices jsonBeanDeleteMyConvenienceServices) {
        turnToNormal();
        myServiceList.clear();
        netWorkOperate.getMyConvenienceServices(token, currentpage+"");
    }

    /**
     * 操作失败,Toast提示
     * */
    @Override
    public void onFailure(String msg) {
        Toast.makeText(MyServiceActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * ACTION_DOWN的操作
     * */
    private void performActionDown(MotionEvent ev) {
        if (isDeleteShow) {
            turnToNormal();
        }
        mDownX = (int) ev.getX();
        mDownY = (int) ev.getY();
        mViewGroupChild = (ViewGroup) listView.getChildAt(listView.pointToPosition(mDownX, mDownY) - listView.getFirstVisiblePosition());
        mDeleteBtnWidth = mViewGroupChild.getChildAt(1).getLayoutParams().width;

        mLayoutParams = (RelativeLayout.LayoutParams) mViewGroupChild.getChildAt(0).getLayoutParams();
        mLayoutParams.width = mScreenWidth;
        mViewGroupChild.getChildAt(0).setLayoutParams(mLayoutParams);
    }

    /**
     * ACTION_MOVE的操作
     * */
    private boolean performActionMove(MotionEvent ev) {
        int nowX = (int) ev.getX();
        int nowY = (int) ev.getY();
        if (Math.abs(nowX - mDownX) > Math.abs(nowY - mDownY)) {
            isMove = true;
            if (nowX < mDownX) {
                int scroll = (nowX - mDownX) / 2;
                if (-scroll >= mDeleteBtnWidth) {
                    scroll = -mDeleteBtnWidth;
                }
                mLayoutParams.leftMargin = scroll;
                mViewGroupChild.getChildAt(0).setLayoutParams(mLayoutParams);
            }
            return true;
        }
        return MyServiceActivity.super.onTouchEvent(ev);
    }

    /**
     * ACTION_UP和ACTION_CANCEL的操作
     * */
    private void performActionUp() {
        if (-mLayoutParams.leftMargin >= mDeleteBtnWidth / 2) {
            mLayoutParams.leftMargin = -mDeleteBtnWidth;
            mViewGroupChild.getChildAt(0).setLayoutParams(mLayoutParams);
            isDeleteShow = true;
        } else {
            turnToNormal();
        }
    }

    /**
     * 回归正常状态的操作
     * */
    public void turnToNormal() {
        mLayoutParams.leftMargin = 0;
        mViewGroupChild.getChildAt(0).setLayoutParams(mLayoutParams);
        isDeleteShow = false;
    }
}

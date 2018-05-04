package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.MoreCommentAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterMoreCommentData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanMoreComment;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoreCommentActivity extends BaseActivity implements NetWorkInterface.OnGetMoreCommentListener {

    @Bind(R.id.prl_activity_morecomment)PullToRefreshLayout refreshLayout;
    @Bind(R.id.lv_activity_morecomment)ListView listView;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private MoreCommentAdapter mAdapterMoreComment;

    private List<String> imgUrls;
    private List<AdapterMoreCommentData> mListMoreCommentData;

    private String strToken, strPid;
    private int currentPage;
    private boolean hasMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_comment);

        initalize();

        setEventListener();
    }

    private void initalize() {
        ButterKnife.bind(MoreCommentActivity.this);
        manager = new SharedpreferencesManager(MoreCommentActivity.this);
        netWorkOperate = new NetWorkOperate(MoreCommentActivity.this);

        setTitle(R.string.consumer_comment);
        setBackVisibility(true);

        Intent intent = getIntent();
        strPid = intent.getStringExtra(FlagData.FLAG_PRODUCT_ID);

        imgUrls = new ArrayList<>();
        mListMoreCommentData = new ArrayList<>();

        mAdapterMoreComment = new MoreCommentAdapter(MoreCommentActivity.this, mListMoreCommentData);
        listView.setAdapter(mAdapterMoreComment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListMoreCommentData.clear();
        currentPage = 1;
        hasMore = true;
        strToken = manager.getToken();
        netWorkOperate.getMoreComment(strToken, strPid, currentPage+"");
    }

    private void setEventListener() {
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        mListMoreCommentData.clear();
                        currentPage = 1;
                        hasMore = true;
                        netWorkOperate.getMoreComment(strToken, strPid, currentPage+"");
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
                        netWorkOperate.getMoreComment(strToken, strPid, currentPage+"");
                    }
                },2000);
            }
        });
        /*swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        mListMoreCommentData.clear();
                        currentPage = 1;
                        hasMore = true;
                        netWorkOperate.getMoreComment(strToken, strPid, currentPage+"");
                    }
                },0);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isBottom = ((firstVisibleItem + visibleItemCount) == totalItemCount);
            }
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isBottom && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    footerView.setVisibility(View.VISIBLE);
                    if(hasMore){
                        currentPage++;
                    }
                    netWorkOperate.getMoreComment(strToken, strPid, currentPage+"");
                }
            }
        });*/
    }

    @Override
    public void onGetMoreComment(JsonBeanMoreComment jsonBeanMoreComment) {
        for (JsonBeanMoreComment.DataBean dataBean : jsonBeanMoreComment.getData()) {
            imgUrls = new ArrayList<>();
            for (JsonBeanMoreComment.DataBean.ImgsBean imgsBean : dataBean.getImgs()) {
                imgUrls.add(FlagData.FLAG_IMGADDRESS + imgsBean.getImg());
            }
            mListMoreCommentData.add(new AdapterMoreCommentData(dataBean.getUsername(),
                    dataBean.getTime(), dataBean.getPercentage(), dataBean.getMessage(), imgUrls));
        }
        mAdapterMoreComment.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        hasMore = false;
        Toast.makeText(MoreCommentActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}

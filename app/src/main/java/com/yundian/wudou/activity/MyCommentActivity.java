package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.MyCommentAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterMyCommentData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanMyComment;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyCommentActivity extends BaseActivity implements NetWorkInterface.OnGetMyCommentListener{

    @Bind(R.id.prl_activity_mycomment)PullToRefreshLayout refreshLayout;
    @Bind(R.id.lv_activity_mycomment)ListView listView;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private List<String> imgUrls;
    private List<AdapterMyCommentData> mListMyCommentData;
    private MyCommentAdapter myCommentAdapter;

    private boolean hasMore = true;
    private int currentpage = 1;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        initalize();

        setEventListener();
    }

    public void initalize() {
        ButterKnife.bind(MyCommentActivity.this);
        manager = new SharedpreferencesManager(MyCommentActivity.this);
        netWorkOperate = new NetWorkOperate(MyCommentActivity.this);

        setTitle(R.string.my_comment);
        setBackVisibility(true);

        imgUrls = new ArrayList<>();
        mListMyCommentData = new ArrayList<>();
        myCommentAdapter = new MyCommentAdapter(MyCommentActivity.this,mListMyCommentData);
        listView.setAdapter(myCommentAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListMyCommentData.clear();
        currentpage = 1;
        hasMore = true;
        token = manager.getToken();
        netWorkOperate.getMyComment(token,currentpage+"");
    }

    private void setEventListener() {
        refreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        mListMyCommentData.clear();
                        currentpage = 1;
                        hasMore = true;
                        netWorkOperate.getMyComment(token,currentpage+"");
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
                            currentpage++;
                        }
                        netWorkOperate.getMyComment(token,currentpage+"");
                    }
                },2000);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyCommentActivity.this,CommodityDetailsActivity.class);
                intent.putExtra(FlagData.FLAG_PRODUCT_ID,mListMyCommentData.get(position).getProductId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onGetMyComment(JsonBeanMyComment jsonBeanMyComment) {
        for(JsonBeanMyComment.DataBean dataBean:jsonBeanMyComment.getData()){
            imgUrls = new ArrayList<>();
            for(JsonBeanMyComment.DataBean.ImgsBean imgsBean:dataBean.getImgs()){
                imgUrls.add(FlagData.FLAG_IMGADDRESS+imgsBean.getImg());
            }
            mListMyCommentData.add(new AdapterMyCommentData(dataBean.getPid(),dataBean.getPname(),
                    dataBean.getTime(),dataBean.getPercentage(),dataBean.getMessage(),imgUrls));
        }
        myCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        hasMore = false;
        Toast.makeText(MyCommentActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}

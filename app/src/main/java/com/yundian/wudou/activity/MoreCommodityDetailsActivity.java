package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.adapter.ConsumerCommentAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.customview.ListView;
import com.yundian.wudou.data.AdapterConsumerCommentData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanCommodityDetailsComment;
import com.yundian.wudou.network.JsonBeanCommodityDetailsData;
import com.yundian.wudou.network.JsonBeanConsumerUncheckMessage;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class MoreCommodityDetailsActivity extends BaseActivity implements NetWorkInterface.OnGetConsumerUncheckMessageListener,
        NetWorkInterface.OnGetCommodityDetailsCommentListener, NetWorkInterface.OnGetCommodityDetailsDataListener, View.OnClickListener {

    @Bind(R.id.tv_activity_commoditydetails_commentcount)
    TextView tvCommentCount;
    @Bind(R.id.tv_activity_commoditydetails_commentrate)
    TextView tvCommentRate;
    @Bind(R.id.lv_activity_commoditydetails_comment)
    ListView mListViewComment;
    @Bind(R.id.tv_activity_commoditydetails_morecomment)
    TextView tvMoreComment;
    private List<AdapterConsumerCommentData> mListAdapterConsumerCommentData;
    private ConsumerCommentAdapter consumerCommentAdapter;
    private SharedpreferencesManager manager;
    private String strPid;
    private NetWorkOperate netWorkOperate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_details_more);

        initialize();
    }

    private void initialize() {
        ButterKnife.bind(MoreCommodityDetailsActivity.this);

        manager = new SharedpreferencesManager(MoreCommodityDetailsActivity.this);
        netWorkOperate = new NetWorkOperate(MoreCommodityDetailsActivity.this);
        this.setTitle(R.string.more_consumer_comment);
        this.setBackVisibility(true);

        Intent intent = getIntent();
        strPid = intent.getStringExtra("pid");
        mListAdapterConsumerCommentData = new ArrayList<>();

        consumerCommentAdapter = new ConsumerCommentAdapter(MoreCommodityDetailsActivity.this, mListAdapterConsumerCommentData);
        mListViewComment.setAdapter(consumerCommentAdapter);
        tvMoreComment.setOnClickListener(this);
    }

    /**
     * 系统回调onResume
     */
    @Override
    protected void onResume() {
        super.onResume();

        netWorkOperate.getCommodityDetailsComment(manager.getToken(), strPid);
    }



    /**
     * 获取商品评论数据
     **/
    @Override
    public void onGetCommodityDetailsComment(JsonBeanCommodityDetailsComment jsonBeanCommodityDetailsComment) {
        tvCommentCount.setText("(" + jsonBeanCommodityDetailsComment.getTreviewscount() + ")");
        tvCommentRate.setText("好评率" + jsonBeanCommodityDetailsComment.getPercentage());

        mListAdapterConsumerCommentData.clear();
        for (JsonBeanCommodityDetailsComment.DataBean dataBean : jsonBeanCommodityDetailsComment.getData()) {
            mListAdapterConsumerCommentData.add(new AdapterConsumerCommentData(dataBean.getStar(), dataBean.getUsername(),
                    dataBean.getMessage(), dataBean.getTime()));
        }
        consumerCommentAdapter.notifyDataSetChanged();
    }


    @Override
    public void onFailure(String msg) {
        tvMoreComment.setText("暂无评价");
        tvMoreComment.setClickable(false);
    }

    @Override
    public void onGetCommodityDetailsData(JsonBeanCommodityDetailsData jsonBeanCommodityDetailsData) {

    }

    @Override
    public void onGetConsumerUncheckMessage(JsonBeanConsumerUncheckMessage jsonBeanConsumerUncheckMessage) {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
switch (v.getId()){
    case R.id.tv_activity_commoditydetails_morecomment:
        intent = new Intent(MoreCommodityDetailsActivity.this, MoreCommentActivity.class);
        intent.putExtra(FlagData.FLAG_PRODUCT_ID, strPid);
        startActivity(intent);
        break;
}
    }
}

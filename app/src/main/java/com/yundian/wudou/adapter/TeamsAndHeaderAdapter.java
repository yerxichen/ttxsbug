package com.yundian.wudou.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterStoreDetailsRightData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.data.StoresinfoData;
import com.yundian.wudou.datawork.OrderManager;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenlijin on 2016/3/18.
 */
public class TeamsAndHeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    private List<StoresinfoData.StorescategoriesBean.DataBeanX> categoryList;
    private List<StoresinfoData.StorescategoriesBean.DataBeanX.ProductsBean.DataBean> teamList = new ArrayList<>();
    private Context mContext;
    private int count;
    private OrderManager mOrderManager;
    private StoreItemAddListener mStoreItemAddListener;
    private StoreItemReduceListener mStoreItemReduceListener;
    private String storeId, storeName, storeUrl, startValue, sendPrice;
    private boolean isOpen;

    public TeamsAndHeaderAdapter(Context context, List<StoresinfoData.StorescategoriesBean.DataBeanX> categoryList) {
        mContext = context;
        setCategoryList(categoryList);
        mOrderManager = new OrderManager(context);
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public void setSendPrice(String sendPrice) {
        this.sendPrice = sendPrice;
    }

    public void setisOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setCategoryList(List<StoresinfoData.StorescategoriesBean.DataBeanX> categoryList) {
        this.categoryList = categoryList;
        for (int i = 0; i < categoryList.size(); i++) {
            for (int j = 0; j < categoryList.get(i).getProducts().getData().size(); j++) {

                StoresinfoData.StorescategoriesBean.DataBeanX.ProductsBean.DataBean data = categoryList.get(i).getProducts().getData().get(j);
                data.setProductCount("0");
                teamList.add(data);
            }
//            if (teamList != null) {
//                teamList.addAll(categoryList.get(i).getProducts().getData());
//            }
        }
        Log.d(">>>>>>>", teamList.size() + ">>>");
        notifyDataSetChanged();
    }

    public List<StoresinfoData.StorescategoriesBean.DataBeanX> getCategoryList() {
        return categoryList;
    }

    public List<StoresinfoData.StorescategoriesBean.DataBeanX.ProductsBean.DataBean> getTeamList() {
        return teamList;
    }


    public void setItemAddListener(StoreItemAddListener listener) {
        this.mStoreItemAddListener = listener;
    }

    public void setItemReduceListener(StoreItemReduceListener listener) {
        this.mStoreItemReduceListener = listener;
    }

    public interface StoreItemAddListener {
        void storeItemAddListener();
    }

    public interface StoreItemReduceListener {
        void storeItemReduceListener();
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    /**
     * 返回值相同会被默认为同一项
     *
     * @param position
     * @return
     */
    @Override
    public long getHeaderId(int position) {
        return getSortType(position);
    }

    //获取当前球队的类型
    public int getSortType(int position) {
        int sort = -1;
        int sum = 0;
        for (int i = 0; i < categoryList.size(); i++) {
            if (position >= sum) {
                sort++;
            } else {
                return sort;
            }
            sum += categoryList.get(i).getProducts().getData().size();
        }
        return sort;
    }

    /**
     * ===================================================================================================
     * header的ViewHolder
     * ===================================================================================================
     */
    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.header_team_list, viewGroup, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        TextView textView = (TextView) viewHolder.itemView;
        textView.setText(categoryList.get(getSortType(position)).getName() + "(" + categoryList.get(getSortType(position)).getProducts().getCount() + ")");
        //  textView.setBackgroundColor(mContext.getResources().getColor(R.color.color_d2));
    }


    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }


    /**
     * ==================================================================================================
     * 以下为contentViewHolder
     * ==================================================================================================
     */
    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_storeadetailsactivity_right_test, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ContentViewHolder viewHolder = (ContentViewHolder) holder;


        final StoresinfoData.StorescategoriesBean.DataBeanX.ProductsBean.DataBean data = teamList.get(position);
        data.setProductCount(mOrderManager.getProductCount(data.getPid()) + "");
        (viewHolder).mImageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数量加1
                //    if (isOpen == true) {

                count = Integer.parseInt(data.getProductCount());

                count++;
                data.setProductCount(count + "");
                mOrderManager.addProduct(storeId, storeName, storeUrl, data.getPid(), data.getName(), data.getImg(), String.valueOf(data.getShopprice()), "", startValue, sendPrice);
                //回调
                if (mStoreItemAddListener != null) {
                    mStoreItemAddListener.storeItemAddListener();
                }

                notifyDataSetChanged();
                //    }
            }
        });

        (viewHolder).mImageViewReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数量减1
                //   if (isOpen == true) {

                count = Integer.parseInt(data.getProductCount());
                if (count > 0) {
                    count--;
                    data.setProductCount(count + "");
                }
                mOrderManager.reduceProduct(data.getPid());
                //回调
                if (mStoreItemReduceListener != null) {
                    mStoreItemReduceListener.storeItemReduceListener();
                }
                notifyDataSetChanged();
                //  }
            }
        });
//        Glide.with(mContext).load(FlagData.FLAG_IMGADDRESS + data.getImg()).into(viewHolder.mImageViewGoods);
        Glide.with(mContext)
                .load(FlagData.FLAG_IMGADDRESS + data.getImg())
                .into(viewHolder.mImageViewGoods);

        viewHolder.mTextViewName.setText(data.getName());
        viewHolder.mTextViewPrice.setText(data.getShopprice());
        viewHolder.mTextViewCount.setText(data.getProductCount());

        if (data.getProductCount().equals("0")) {
            viewHolder.mImageViewReduce.setVisibility(View.GONE);
            viewHolder.mTextViewCount.setVisibility(View.GONE);
        } else {
            viewHolder.mImageViewReduce.setVisibility(View.VISIBLE);
            viewHolder.mTextViewCount.setVisibility(View.VISIBLE);
        }
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_adapter_activitystoredetils_productimg)
        ImageView mImageViewGoods;
        @Bind(R.id.tv_adapter_activitystoredetils_productname)
        TextView mTextViewName;
        @Bind(R.id.tv_adapter_activitystoredetils_productprice)
        TextView mTextViewPrice;
        @Bind(R.id.iv_adapter_activitystoredetils_productadd)
        ImageView mImageViewAdd;
        @Bind(R.id.tv_adapter_activitystoredetils_productcount)
        TextView mTextViewCount;
        @Bind(R.id.iv_adapter_activitystoredetils_productreduce)
        ImageView mImageViewReduce;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

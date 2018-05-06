package com.yundian.wudou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.activity.CommodityDetailsActivity;
import com.yundian.wudou.activity.HeadlineActivity;
import com.yundian.wudou.activity.VegetableShopActivity;
import com.yundian.wudou.data.AdapterHomepageRecommendData;
import com.yundian.wudou.data.FlagData;

import java.util.List;

/**
 * Created by cookie on 2016/8/8.
 */
public class HomepageRecommendAdapter extends RecyclerView.Adapter<HomepageRecommendAdapter.RecommendViewHolder> {

    private Context context;
    private List<AdapterHomepageRecommendData> mList;

    public HomepageRecommendAdapter(Context context, List<AdapterHomepageRecommendData> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_homepage_recommend, parent, false));
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {
        Glide.with(context).load(mList.get(position).getImgUrl()).into(holder.mImageViewRecommend);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class RecommendViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageViewRecommend;

        public RecommendViewHolder(final View itemView) {
            super(itemView);
            mImageViewRecommend = (ImageView) itemView.findViewById(R.id.iv_fragmenthome_recommend);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2016/8/23 品牌推荐点击事件
                    int position = getAdapterPosition();
                    if(mList.get(position).getUrlType().equals("1")){
                        Intent intent = new Intent(context, VegetableShopActivity.class);
                        intent.putExtra(FlagData.FLAG_SHOP_ID,mList.get(position).getUrl());
                        context.startActivity(intent);
                    }else if(mList.get(position).getUrlType().equals("3")) {
                        Intent intent = new Intent(context, HeadlineActivity.class);
                        intent.putExtra(FlagData.FLAG_STATE,mList.get(position).getUrl());
                        context.startActivity(intent);
                    } else{
                        Intent intent = new Intent(context, CommodityDetailsActivity.class);
                        intent.putExtra(FlagData.FLAG_PRODUCT_ID,mList.get(position).getUrl());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}

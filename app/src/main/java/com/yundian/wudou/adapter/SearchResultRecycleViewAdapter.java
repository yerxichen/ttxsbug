package com.yundian.wudou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.activity.CommodityDetailsActivity;
import com.yundian.wudou.data.AdapterSearchResultRecycleViewData;
import com.yundian.wudou.data.FlagData;

import java.util.List;

/**
 * Created by cookie on 2016/8/15.
 */
public class SearchResultRecycleViewAdapter extends RecyclerView.Adapter<SearchResultRecycleViewAdapter.SearchResultViewHolder> {

    private Context context;
    private List<AdapterSearchResultRecycleViewData> mList;

    public SearchResultRecycleViewAdapter(Context context, List<AdapterSearchResultRecycleViewData> mList) {
        this.context = context;
        this.mList = mList;
    }

    public List<AdapterSearchResultRecycleViewData> getmList() {
        return mList;
    }

    public void setmList(List<AdapterSearchResultRecycleViewData> mList) {
        this.mList = mList;
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchResultViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_searchresult_recycleview, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        AdapterSearchResultRecycleViewData data = mList.get(position);

        Glide.with(context).load(data.getImg()).into(holder.mImageView);
        holder.mTextViewTitle.setText(data.getName());
        holder.mTextViewPrice.setText("" + data.getShopprice());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class SearchResultViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextViewTitle, mTextViewPrice;

        public SearchResultViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.iv_adapter_searchresult_recycleview);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.tv_adapter_searchresult_recycleview_title);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.tv_adapter_searchresult_recycleview_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CommodityDetailsActivity.class);
                    int position = getAdapterPosition();
                    intent.putExtra(FlagData.FLAG_PRODUCT_ID, mList.get(position).getPid());
                    context.startActivity(intent);
                }
            });
        }
    }
}

package com.yundian.wudou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.activity.CommodityDetailsActivity;
import com.yundian.wudou.activity.SearchResultActivity;
import com.yundian.wudou.data.AdapterClassificationRightRecycleData;
import com.yundian.wudou.data.AdapterSearchResultRecycleViewData;
import com.yundian.wudou.data.FlagData;

import java.util.List;

/**
 * Created by cookie on 2016/8/15.
 */
public class ClassificationRightRecycleViewAdapter extends RecyclerView.Adapter<ClassificationRightRecycleViewAdapter.SearchResultViewHolder> {

    private Context context;
    private List<AdapterClassificationRightRecycleData> mList;

    public ClassificationRightRecycleViewAdapter(Context context, List<AdapterClassificationRightRecycleData> mList) {
        this.context = context;
        this.mList = mList;
    }

    public List<AdapterClassificationRightRecycleData> getmList() {
        return mList;
    }

    public void setmList(List<AdapterClassificationRightRecycleData> mList) {
        this.mList = mList;
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchResultViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_classificationright_recycleview, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        AdapterClassificationRightRecycleData data = mList.get(position);

        holder.mTextViewName.setText(data.getName());
        Picasso.with(context).load(data.getImgUrl()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class SearchResultViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextViewName;

        public SearchResultViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.iv_adapter_classificationright_recycleview);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_adapter_classificationleft_recycleview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SearchResultActivity.class);
                    intent.putExtra(FlagData.FLAG_SEARCH_CATENUMBER,mList.get(getAdapterPosition()).getCateNumber());
                    intent.putExtra(FlagData.FLAG_STATE,"2");
                    context.startActivity(intent);
                }
            });
        }
    }
}

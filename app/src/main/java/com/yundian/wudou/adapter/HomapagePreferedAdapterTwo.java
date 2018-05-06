package com.yundian.wudou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.activity.CommodityDetailsActivity;
import com.yundian.wudou.data.AdapterHomepagePreferedData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.data.HomaLoadproductsData;
import com.yundian.wudou.network.JsonBeanHomePage;

import java.util.List;

/**
 * Created by cookie on 2016/8/8.
 */
public class HomapagePreferedAdapterTwo extends RecyclerView.Adapter<HomapagePreferedAdapterTwo.PreferedViewHolder> {

    private Context context;
    private List<JsonBeanHomePage.ProductsCompetitiveBean.Data3Bean> mList;

    public HomapagePreferedAdapterTwo(Context context, List<JsonBeanHomePage.ProductsCompetitiveBean.Data3Bean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public PreferedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PreferedViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_homepage_prefered, parent, false));
    }

    @Override
    public void onBindViewHolder(PreferedViewHolder holder, int position) {
        JsonBeanHomePage.ProductsCompetitiveBean.Data3Bean data = mList.get(position);

        Glide.with(context).load(FlagData.FLAG_IMGADDRESS + data.getImg()).into(holder.mImageViewGoods);

        holder.mTextViewPrice.setText("¥ " + data.getShopprice());
        holder.mTextViewName.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class PreferedViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageViewGoods;
        TextView mTextViewName, mTextViewPrice;

        public PreferedViewHolder(View itemView) {
            super(itemView);
            mImageViewGoods = (ImageView) itemView.findViewById(R.id.iv_adapter_fragmenthome_prefered_goods);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_adapter_fragmenthome_prefered_name);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.tv_adapter_fragmenthome_prefered_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, CommodityDetailsActivity.class);
                    intent.putExtra(FlagData.FLAG_PRODUCT_ID, mList.get(position).getPid());
                    context.startActivity(intent);
                }
            });
        }
    }
}

package com.yundian.wudou.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.yundian.wudou.data.AdapterHomepageSaleData;
import com.yundian.wudou.data.FlagData;

import java.util.List;

/**
 * Created by cookie on 2016/8/8.
 */
public class HomepageSaleAdapter extends RecyclerView.Adapter<HomepageSaleAdapter.SaleViewHolder> {

    private Context context;
    private List<AdapterHomepageSaleData> mList;

    public HomepageSaleAdapter(Context context, List<AdapterHomepageSaleData> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public SaleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SaleViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_homepage_sale, parent, false));
    }

    @Override
    public void onBindViewHolder(SaleViewHolder holder, int position) {
        AdapterHomepageSaleData data = mList.get(position);
        Log.d(">>>首页图片地址>>>", data.getImageAddress());
        Glide.with(context).load(data.getImageAddress()).into(holder.mImageViewGoods);
        holder.mTextViewName.setText(data.getName());
        holder.mTextViewPrice.setText("¥ " + data.getPrice());
        holder.tv_line_price.setText("¥ "+data.getMarketprice());
        holder.tv_line_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        holder.tv_line_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class SaleViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageViewGoods;
        TextView mTextViewName, mTextViewPrice, tv_line_price;

        public SaleViewHolder(View itemView) {
            super(itemView);
            mImageViewGoods = (ImageView) itemView.findViewById(R.id.iv_adapter_fragmenthome_sale_goods);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_adapter_fragmenthome_sale_name);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.iv_adapter_fragmenthome_sale_price);
            tv_line_price = (TextView) itemView.findViewById(R.id.tv_line_price);

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

package com.yundian.wudou.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.data.HorizontaiListviewListData;
import com.yundian.wudou.utils.DashedLineView;


import java.util.List;

/**
 * Created by cookie on 2016/8/8.
 */
public class HomepageShopAdapterNew extends RecyclerView.Adapter<HomepageShopAdapterNew.ViewHolder> {

    private Context context;
    private List<HorizontaiListviewListData.StoresBean.DataBean> mList;

    public HomepageShopAdapterNew(Context context, List<HorizontaiListviewListData.StoresBean.DataBean> mList) {
        this.context = context;
        this.mList = mList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_homepage_shop, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(FlagData.FLAG_IMGADDRESS + mList.get(position).getImg()).into(holder.mImageViewShop);
        holder.mTextViewName.setText(mList.get(position).getName());
        holder.mTextViewDistance.setText(mList.get(position).getDistance());
        holder.mTextViewCount.setText("月售" + mList.get(position).getMonthlysales() + "份");
        holder.mTextViewStartPrice.setText("¥ " + mList.get(position).getStartvalue());
        holder.mTextViewSendPrice.setText("¥ " + mList.get(position).getStartfee());
        if (mList.get(position).getIsdiscount().equals("1")) {
            String[] yh = mList.get(position).getDiscounttitle().split(",");
            holder.lineView.setVisibility(View.VISIBLE);
            holder.ly_shop_rx.setVisibility(View.VISIBLE);
            holder.promotionIcon.setVisibility(View.VISIBLE);
            holder.rl_shop_view.setVisibility(View.VISIBLE);
            LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.restaurant_promotion_with_icon_text_view, null);

            for (int i = 0; i < yh.length; i++) {
                TextView promotionTv = new TextView(context);
                promotionTv.setText(yh[i] + "");
                view.addView(promotionTv);
                Log.d(">>>>调用次数>>", i + ">>>>");
            }
            holder.promotion.addView(view);
        } else {
            holder.lineView.setVisibility(View.GONE);
            holder.ly_shop_rx.setVisibility(View.GONE);
            holder.promotionIcon.setVisibility(View.GONE);
            holder.rl_shop_view.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


//
//    @Override
//    public int getCount() {
//
//        return mList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder viewHolder = null;
//
//        if (null == convertView) {
//            viewHolder = new ViewHolder();
//            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_homepage_shop, null);
//
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//
//        }
//
//
//
//        return convertView;
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageViewShop, mImageViewExpert, promotionIcon;
        TextView mTextViewName, mTextViewDistance, mTextViewCount, mTextViewStartPrice, mTextViewSendPrice;
        public LinearLayout promotion, ly_shop_rx;//�ƹ���Ϣ
        DashedLineView lineView;
        RelativeLayout rl_shop_view;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewShop = (ImageView) itemView.findViewById(R.id.iv_fragmenthome_shop);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_fragmenthome_shop_name);
            mImageViewExpert = (ImageView) itemView.findViewById(R.id.iv_fragmenthome_shop_expert);
            mTextViewDistance = (TextView) itemView.findViewById(R.id.tv_fragmenthome_shop_distance);
            mTextViewCount = (TextView) itemView.findViewById(R.id.tv_fragmenthome_shop_count);
            mTextViewStartPrice = (TextView) itemView.findViewById(R.id.tv_fragmenthome_shop_startprice);
            mTextViewSendPrice = (TextView) itemView.findViewById(R.id.tv_fragmenthome_shop_sendprice);
            promotion = (LinearLayout) itemView
                    .findViewById(R.id.restaurant_list_item_present_promotion_container);
            ly_shop_rx = (LinearLayout) itemView
                    .findViewById(R.id.ly_shop_rx);
            lineView = (DashedLineView) itemView
                    .findViewById(R.id.das_line);
            promotionIcon = (ImageView) itemView.findViewById(R.id.restaurant_promotion_icon);
            rl_shop_view = (RelativeLayout) itemView.findViewById(R.id.rl_shop_view);
        }
    }
}

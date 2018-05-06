package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapteCollectShopData;

import java.util.List;

/**
 * Created by sxt on 2016/8/18.
 */
public class CollectShopAdapter extends BaseAdapter {

    private Context context;
    private List<AdapteCollectShopData> mList;

    public CollectShopAdapter(Context context, List<AdapteCollectShopData> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AdapteCollectShopData data = mList.get(position);
        ViewHolder viewHolder = null;

        if (null == convertView){
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_collectshop, null);

            viewHolder.imageViewShop = (ImageView) convertView.findViewById(R.id.iv_adapter_collectshop);
            viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.rb_adapter_collectshop);
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.tv_adapter_collectshop_name);
            viewHolder.textViewInfo=(TextView) convertView.findViewById(R.id.tv_adapter_collectshop_info);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(data.getImgUrl()).into(viewHolder.imageViewShop);
        viewHolder.ratingBar.setRating(data.getRatingbar());
        viewHolder.textViewName.setText(data.getShopName());
        viewHolder.textViewInfo.setText("共"+data.getProductcount()+"件商品|月售"+data.getMonthlysales()+"单");

        return convertView;
    }
    private static class ViewHolder {
        RatingBar ratingBar;
        ImageView imageViewShop;
        TextView textViewName, textViewInfo;
    }
}

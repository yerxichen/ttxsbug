package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
 * Created by taozipc on 2017/11/25.
 */

public class HomeGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<HorizontaiListviewListData.StoresBean.DataBean.StoresProductsBean.SubdataBean> mList;
    LinearLayout view;

    public HomeGridViewAdapter(Context context, List<HorizontaiListviewListData.StoresBean.DataBean.StoresProductsBean.SubdataBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_homepage_shop_rx, null);
            viewHolder.iv_shop_rx = convertView.findViewById(R.id.iv_shop_rx);
            viewHolder.promotionIcon = convertView.findViewById(R.id.iv_adapter_fragmenthome_prefered_goods);
            viewHolder.mTextViewPrice = convertView.findViewById(R.id.tv_adapter_fragmenthome_prefered_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        Glide.with(context).load(FlagData.FLAG_IMGADDRESS + mList.get(position).getImg()).into(viewHolder.promotionIcon);
        if (mList.get(position).getIshot().equals("1")) {
            viewHolder.iv_shop_rx.setVisibility(View.VISIBLE);
        } else {
            viewHolder.iv_shop_rx.setVisibility(View.INVISIBLE);
        }
        viewHolder.mTextViewPrice.setText("¥ " + mList.get(position).getShopprice());
        return convertView;
    }


    private static class ViewHolder {
        ImageView iv_shop_rx, promotionIcon;
        TextView mTextViewPrice;

    }
}

package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterSettlementData;
import com.yundian.wudou.data.FlagData;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by cookie on 2016/8/15.
 */
public class SettlementAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterSettlementData> mList;
    private String total;

    public SettlementAdapter(Context context, List<AdapterSettlementData> mList) {
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

        ViewHolder viewHolder = null;

        AdapterSettlementData data = mList.get(position);

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_settlement_listview, null);

            viewHolder = new ViewHolder();

            viewHolder.mTextViewShopName = (TextView) convertView.findViewById(R.id.tv_adapter_settlement_shop_name);
            viewHolder.mTextViewCount = (TextView) convertView.findViewById(R.id.tv_adapter_settlement_count);
            viewHolder.mTextViewWeight = (TextView) convertView.findViewById(R.id.tv_adapter_settlement_weight);
            viewHolder.mTextViewCommodityPrice = (TextView) convertView.findViewById(R.id.tv_adapter_settlement_price_content);
            viewHolder.mTextViewSendPrice = (TextView) convertView.findViewById(R.id.tv_adapter_settlement_send_content);
            viewHolder.mLinearLayout = (LinearLayout) convertView.findViewById(R.id.ll_adapter_settlement_goods);
            viewHolder.mTextViewTotal = (TextView) convertView.findViewById(R.id.tv_adapter_settlement_total);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextViewShopName.setText(data.getShopName());
        viewHolder.mTextViewCount.setText("共" + data.getCommodityCount() + "件");
        viewHolder.mTextViewWeight.setText("" + data.getCommodityWeight());


        DecimalFormat decimalFormat = new DecimalFormat(".00");
        viewHolder.mTextViewCommodityPrice.setText("¥" + decimalFormat.format(Float.parseFloat(data.getCommodityPrice())));
        if (Float.parseFloat(data.getCommodityPrice()) >= Float.parseFloat(data.getStrSendPrice())) {
            total = decimalFormat.format(Float.parseFloat(data.getCommodityPrice()));
            viewHolder.mTextViewSendPrice.setText("¥" + 0.00);
        } else {

            viewHolder.mTextViewSendPrice.setText("¥" + data.getSendPrice());
            total = decimalFormat.format(Float.parseFloat(data.getCommodityPrice()) + Float.parseFloat(data.getSendPrice()));
        }
        viewHolder.mTextViewTotal.setText("¥" + total);

        viewHolder.mLinearLayout.removeAllViews();
        for (String url : data.getImgUrls()) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(5, 5, 5, 5);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            if (url.contains("http")) {

                Glide.with(context).load(url).into(imageView);
            } else {
                Glide.with(context).load(FlagData.FLAG_IMGADDRESS + url).into(imageView);

            }
            viewHolder.mLinearLayout.addView(imageView);
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView mTextViewShopName, mTextViewCount, mTextViewWeight, mTextViewCommodityPrice, mTextViewSendPrice, mTextViewTotal;
        LinearLayout mLinearLayout;
    }
}

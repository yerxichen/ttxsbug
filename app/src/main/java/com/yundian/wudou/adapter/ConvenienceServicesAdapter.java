package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterConvenienceServicesData;

import java.util.List;

/**
 * Created by Itachi on 2016/9/5.
 */
public class ConvenienceServicesAdapter extends BaseAdapter{

    private Context context;
    private List<AdapterConvenienceServicesData> mList;

    public ConvenienceServicesAdapter(Context context, List<AdapterConvenienceServicesData> mList) {
        super();
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

        AdapterConvenienceServicesData data = mList.get(position);
        ViewHolder viewHolder = null;

        if(null == convertView ){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_convenience_services_list,null);
            viewHolder = new ViewHolder();

            viewHolder.mImageViewGoods = (ImageView) convertView.findViewById(R.id.iv_adapter_convenience_service_goodsimage);
            viewHolder.mTextViewLocation = (TextView) convertView.findViewById(R.id.tv_adapter_convenience_service_location);
            viewHolder.mTextViewPubdate = (TextView) convertView.findViewById(R.id.tv_adapter_convenience_service_pubdate);
            viewHolder.mTextViewGoodsName = (TextView) convertView.findViewById(R.id.tv_adapter_convenience_service_goodsname);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        Picasso.with(context).load(data.getImgUrl()).into(viewHolder.mImageViewGoods);
        viewHolder.mTextViewGoodsName.setText(data.getGoodsName());
        viewHolder.mTextViewLocation.setText(data.getLocation());
        viewHolder.mTextViewPubdate.setText(data.getPubdate());

        return convertView;
    }


    private static class ViewHolder {
        ImageView mImageViewGoods;
        TextView mTextViewGoodsName, mTextViewLocation, mTextViewPubdate;
    }
}

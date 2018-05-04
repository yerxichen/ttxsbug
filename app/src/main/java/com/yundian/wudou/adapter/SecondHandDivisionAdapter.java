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
import com.yundian.wudou.data.AdapterSecondHandDivisionData;

import java.util.List;

/**
 * Created by Itachi on 2016/8/19.
 */
public class SecondHandDivisionAdapter extends BaseAdapter {


    private Context context;
    private List<AdapterSecondHandDivisionData> mList;

    public SecondHandDivisionAdapter(Context context, List<AdapterSecondHandDivisionData> mList){
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

        AdapterSecondHandDivisionData data = mList.get(position);

        ViewHolder viewHolder = null;

          if (null == convertView){
              convertView = LayoutInflater.from(context).inflate(R.layout.
                      adapter_secondhand_division_listview,null);

              viewHolder = new ViewHolder();

              viewHolder.mImageViewGoods = (ImageView) convertView.findViewById(R.id.iv_activity_secondhanddivision_goodsimage);
              viewHolder.mTextViewLocation = (TextView) convertView.findViewById(R.id.tv_activity_secondhanddivision_location);
              viewHolder.mTextViewPubdate = (TextView) convertView.findViewById(R.id.tv_activity_secondhanddivision_pubdate);
              viewHolder.mTextViewGoodsName = (TextView) convertView.findViewById(R.id.tv_activity_secondhanddivision_goodsname);

              convertView.setTag(viewHolder);
          }else{
              viewHolder = (ViewHolder) convertView.getTag();
          }

        Picasso.with(context).load(data.getImgUrl()).into(viewHolder.mImageViewGoods);
        viewHolder.mTextViewGoodsName.setText(data.getName());
        viewHolder.mTextViewLocation.setText(data.getRegion());
        viewHolder.mTextViewPubdate.setText(data.getTime());

        return convertView;
    }

    private  static class ViewHolder{

        ImageView mImageViewGoods;
        TextView mTextViewGoodsName,mTextViewLocation,mTextViewPubdate;

    }
}

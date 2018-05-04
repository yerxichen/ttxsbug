package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterHomepageShopData;
import com.yundian.wudou.data.HorizontaiListviewData;
import com.yundian.wudou.utils.DashedLineView;

import java.util.List;

/**
 * Created by taozipc on 2017/11/20.
 */

public class HorizontalListViewAdapter extends BaseAdapter {
    private Context context;
    private List<HorizontaiListviewData> datas;
    public HorizontalListViewAdapter(Context context, List<HorizontaiListviewData> datas) {
        this.context = context;
        this.datas = datas;
    }

    public List<HorizontaiListviewData> getDatas() {
        return datas;
    }

    public void setDatas(List<HorizontaiListviewData> dataList) {
        datas = dataList;
    }

    @Override
    public int getCount() {
        return datas.size();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.horizontallistview_item, null);
            viewHolder.mTVName = (TextView) convertView.findViewById(R.id.tv_name_1);
            viewHolder.mLine = convertView.findViewById(R.id.v_line);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.mTVName.setText(datas.get(position).getName());
        if (datas.get(position).isSelected() == true) {
            viewHolder.mLine.setVisibility(View.VISIBLE);
            viewHolder.mTVName.setTextColor(context.getResources().getColor(R.color.colorGreenBefore));
        } else {
            viewHolder.mLine.setVisibility(View.GONE);
            viewHolder.mTVName.setTextColor(context.getResources().getColor(R.color.type_checked));

        }

        return convertView;
    }


    private static class ViewHolder {

        TextView mTVName;
        View mLine;

    }
}

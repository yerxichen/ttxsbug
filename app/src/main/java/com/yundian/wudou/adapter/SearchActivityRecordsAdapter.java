package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterSearchRecordsData;

import java.util.List;

/**
 * Created by Itachi on 2016/8/12.
 */
public class SearchActivityRecordsAdapter extends BaseAdapter {
    private Context context;
    private List<AdapterSearchRecordsData> mList;

    public SearchActivityRecordsAdapter(Context context, List<AdapterSearchRecordsData> mList) {
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
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_searchactivity_records, null);

            viewHolder = new ViewHolder();

            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_activity_search_record_goodsname);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AdapterSearchRecordsData data = mList.get(position);
        viewHolder.mTextView.setText(data.getGoodsname());

        return convertView;
    }

    public static class ViewHolder {
        TextView mTextView;
    }
}

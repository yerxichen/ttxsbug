package com.yundian.wudou.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterClassificationRightListData;

import java.util.List;

/**
 * Created by cookie on 2016/8/4.
 */
public class ClassificationRightFragmentAdapter extends BaseAdapter {

    private List<AdapterClassificationRightListData> mList;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public ClassificationRightFragmentAdapter(Context context, List<AdapterClassificationRightListData> mList) {
        this.mList = mList;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
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
            convertView = mLayoutInflater.inflate(R.layout.adapter_classification_right, null);

            viewHolder = new ViewHolder();
            viewHolder.mTextViewTitle = (TextView) convertView.findViewById(R.id.tv_adapter_classification_right_title);
            //viewHolder.mTextViewMore = (TextView) convertView.findViewById(R.id.tv_adapter_classification_right_more);
            viewHolder.mRecyclerView = (RecyclerView) convertView.findViewById(R.id.rv_adapter_classification_right);
            viewHolder.mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AdapterClassificationRightListData data = mList.get(position);

        viewHolder.mTextViewTitle.setText(data.getTitle());
        if (data.isHead()) {
            viewHolder.mTextViewTitle.setTextColor(context.getResources().getColor(R.color.colorGreenBefore));
            //viewHolder.mTextViewMore.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mTextViewTitle.setTextColor(context.getResources().getColor(R.color.colorBlackLight));
           // viewHolder.mTextViewMore.setVisibility(View.INVISIBLE);
        }
        viewHolder.mRecyclerView.setAdapter(data.getmClassificationRightRecycleViewAdapter());

        return convertView;
    }

    private static class ViewHolder {
        TextView mTextViewTitle;
        //TextView mTextViewMore;
        RecyclerView mRecyclerView;
    }
}

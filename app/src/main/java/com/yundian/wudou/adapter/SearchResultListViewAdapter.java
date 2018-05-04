package com.yundian.wudou.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterSearchResultListViewData;

import java.util.List;

/**
 * Created by cookie on 2016/8/15.
 */
public class SearchResultListViewAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterSearchResultListViewData> mList;

    public SearchResultListViewAdapter(Context context, List<AdapterSearchResultListViewData> mList) {
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
        AdapterSearchResultListViewData data = mList.get(position);

        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_searchresult_listview, null);
            viewHolder = new ViewHolder();

            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_adapter_searchresult_listview);
            viewHolder.mTextViewName = (TextView) convertView.findViewById(R.id.tv_adapter_searchresult_listview_name);
            viewHolder.mTextViewSendPrice = (TextView) convertView.findViewById(R.id.tv_adapter_searchresult_listview_sendprice);
            viewHolder.mTextViewStartPrice = (TextView) convertView.findViewById(R.id.tv_adapter_searchresult_listview_startprice);
            viewHolder.mRecyclerView = (RecyclerView) convertView.findViewById(R.id.rv_adapter_searchresult_listview);
            viewHolder.mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(data.getImgUrl()).into(viewHolder.mImageView);
        viewHolder.mTextViewName.setText(data.getName());
        viewHolder.mTextViewSendPrice.setText("¥" + data.getSendPrice());
        viewHolder.mTextViewStartPrice.setText("¥" + data.getStartPrice());
        viewHolder.mRecyclerView.setAdapter(data.getSearchResultRecycleViewAdapter());

        return convertView;
    }

    private static class ViewHolder {
        TextView mTextViewName, mTextViewSendPrice, mTextViewStartPrice;
        ImageView mImageView;
        RecyclerView mRecyclerView;
    }
}

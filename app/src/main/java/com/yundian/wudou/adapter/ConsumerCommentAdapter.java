package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterConsumerCommentData;
import com.yundian.wudou.data.AdapterIntegralShopLeftData;

import java.util.List;

/**
 * Created by Itachi on 2016/8/29.
 */
public class ConsumerCommentAdapter extends BaseAdapter {

    private List<AdapterConsumerCommentData> mList;
    private Context context;

    public ConsumerCommentAdapter(Context context,List<AdapterConsumerCommentData> mList) {
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
        AdapterConsumerCommentData data = mList.get(position);

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_consumer_comment, null);
            viewHolder = new ViewHolder();

            viewHolder.mRatingBar = (RatingBar) convertView.findViewById(R.id.rb_adapter_consumer_comment);
            viewHolder.mTextViewName = (TextView) convertView.findViewById(R.id.tv_adapter_consumer_comment_name);
            viewHolder.mTextViewTime = (TextView) convertView.findViewById(R.id.tv_adapter_consumer_comment_time);
            viewHolder.mTextViewContent = (TextView) convertView.findViewById(R.id.tv_adapter_consumer_comment_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mRatingBar.setRating(Float.parseFloat(data.getStarNum()));
        viewHolder.mTextViewName.setText(data.getUserName());
        viewHolder.mTextViewTime.setText(data.getPubDate());
        viewHolder.mTextViewContent.setText(data.getUserComment());

        return convertView;
    }

    private final class ViewHolder {
        RatingBar mRatingBar;
        TextView mTextViewName, mTextViewTime, mTextViewContent;
    }
}

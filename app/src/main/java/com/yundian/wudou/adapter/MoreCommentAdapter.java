package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterMoreCommentData;
import com.yundian.wudou.data.AdapterMyCommentData;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

public class MoreCommentAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterMoreCommentData> mListMoreCommentData;

    public MoreCommentAdapter(Context context, List<AdapterMoreCommentData> mListMoreCommentData) {
        this.context = context;
        this.mListMoreCommentData = mListMoreCommentData;
    }

    @Override
    public int getCount() {
        return mListMoreCommentData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListMoreCommentData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AdapterMoreCommentData data = mListMoreCommentData.get(position);

        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_morecomment_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.mTextViewConsumerName = (TextView) convertView.findViewById(R.id.tv_adapter_morecomment_consumername);
            viewHolder.mTextViewCommentDate = (TextView) convertView.findViewById(R.id.tv_adapter_morecomment_commentdate);
            viewHolder.mRatingBarComment = (RatingBar) convertView.findViewById(R.id.rb_adapter_morecomment_commentrate);
            viewHolder.mTextViewCommentContent = (TextView) convertView.findViewById(R.id.tv_adapter_morecomment_commentcontent);
            viewHolder.mLinearLayoutProductImg = (LinearLayout) convertView.findViewById(R.id.ll_adapter_morecomment_productimg);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextViewConsumerName.setText(data.getConsumerName());
        viewHolder.mTextViewCommentDate.setText(data.getCommentDate());
        viewHolder.mRatingBarComment.setRating(Float.parseFloat(data.getCommentRate()));
        if(data.getCommentContent().equals("")){
            viewHolder.mTextViewCommentContent.setVisibility(View.GONE);
        }else{
            viewHolder.mTextViewCommentContent.setText(data.getCommentContent());
        }

        viewHolder.mLinearLayoutProductImg.removeAllViews();
        for (int i = 0; i < data.getImgUrls().size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
            imageView.setPadding(0,0,15,0);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(context).load(data.getImgUrls().get(i)).into(imageView);
            viewHolder.mLinearLayoutProductImg.addView(imageView);
        }
        return convertView;
    }

    private static class ViewHolder {
        LinearLayout mLinearLayoutProductImg;
        RatingBar mRatingBarComment;
        TextView mTextViewConsumerName, mTextViewCommentDate, mTextViewCommentContent;
    }
}

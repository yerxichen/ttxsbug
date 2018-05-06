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

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterMyCommentData;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

public class MyCommentAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterMyCommentData> mListMyCommentData;

    public MyCommentAdapter(Context context, List<AdapterMyCommentData> mListMyCommentData) {
        this.context = context;
        this.mListMyCommentData = mListMyCommentData;
    }

    @Override
    public int getCount() {
        return mListMyCommentData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListMyCommentData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AdapterMyCommentData data = mListMyCommentData.get(position);

        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_mycomment_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.mTextViewStoreName = (TextView) convertView.findViewById(R.id.tv_adapter_mycomment_storename);
            viewHolder.mTextViewCommentDate = (TextView) convertView.findViewById(R.id.tv_adapter_mycomment_commentdate);
            viewHolder.mRatingBarComment = (RatingBar) convertView.findViewById(R.id.rb_adapter_mycomment_commentrate);
            viewHolder.mTextViewCommentContent = (TextView) convertView.findViewById(R.id.tv_adapter_mycomment_commentcontent);
            viewHolder.mLinearLayoutProductImg = (LinearLayout) convertView.findViewById(R.id.ll_adapter_mycomment_productimg);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextViewStoreName.setText(data.getProductName());
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
            imageView.setLayoutParams(new ViewGroup.LayoutParams(195, 180));
            imageView.setPadding(0,0,15,0);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(data.getImgUrls().get(i)).into(imageView);
            viewHolder.mLinearLayoutProductImg.addView(imageView);
        }
        if(viewHolder.mLinearLayoutProductImg.getChildCount()==0){
            viewHolder.mLinearLayoutProductImg.setVisibility(View.GONE);
        }else {
            viewHolder.mLinearLayoutProductImg.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    private static class ViewHolder {
        LinearLayout mLinearLayoutProductImg;
        RatingBar mRatingBarComment;
        TextView mTextViewStoreName, mTextViewCommentDate, mTextViewCommentContent;
    }
}

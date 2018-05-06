package com.yundian.wudou.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterEvaluateData;
import com.yundian.wudou.data.EvaluateResult;
import com.yundian.wudou.datawork.SharedpreferencesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class EvaluateAdapter extends BaseAdapter {

    private OnSelectImageListener onSelectImageListener;
    private SharedpreferencesManager manager;

    private Context context;
    private List<AdapterEvaluateData> mList;
    private ListView mListView;

    private EvaluateResult evaluateResult;
    private List<EvaluateResult.DataBean> dataBeenList;

    private String strOid;
    private String tempPosition;

    public EvaluateAdapter(Context context, List<AdapterEvaluateData> mList, String strOid, ListView listView) {
        this.context = context;
        this.mList = mList;
        this.strOid = strOid;
        this.mListView = listView;

        manager = new SharedpreferencesManager(context);
        dataBeenList = new ArrayList<>();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (dataBeenList.size() == 0) {
            for (int i = 0; i < mList.size(); i++) {
                EvaluateResult.DataBean dataBean = new EvaluateResult.DataBean(mList.get(i).getPid(), "5", "5", "5", "", "");
                dataBeenList.add(dataBean);
            }
            evaluateResult = new EvaluateResult(manager.getToken(), strOid, dataBeenList);
        }

        AdapterEvaluateData data = mList.get(position);

        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_evaluate, null);
            viewHolder = new ViewHolder();
            viewHolder.ivProductImg = (ImageView) convertView.findViewById(R.id.iv_adapter_evaluate_productimg);
            viewHolder.tvProductName = (TextView) convertView.findViewById(R.id.tv_adapter_evaluate_productname);

            viewHolder.ratingBarOne = (RatingBar) convertView.findViewById(R.id.rb_adapter_evaluate_quality);
            viewHolder.ratingBarTwo = (RatingBar) convertView.findViewById(R.id.rb_adapter_evaluate_speed);
            viewHolder.ratingBarThree = (RatingBar) convertView.findViewById(R.id.rb_adapter_evaluate_services);
            viewHolder.etMessage = (EditText) convertView.findViewById(R.id.et_adapter_evaluate_details);
            viewHolder.imageViewOne = (ImageView) convertView.findViewById(R.id.iv_adapter_evaluate_upload_image_one);
            viewHolder.imageViewTwo = (ImageView) convertView.findViewById(R.id.iv_adapter_evaluate_upload_image_two);
            viewHolder.imageViewThree = (ImageView) convertView.findViewById(R.id.iv_adapter_evaluate_upload_image_three);
            viewHolder.relativeLayoutPhoto = (RelativeLayout) convertView.findViewById(R.id.rl_adapter_evaluate_photo);
            viewHolder.relativeLayoutUnreview = (RelativeLayout) convertView.findViewById(R.id.rl_adapter_evaluate_unreview);

            viewHolder.llReview = (LinearLayout) convertView.findViewById(R.id.ll_adapter_evaluate_review);
            viewHolder.llReviewProductImg = (LinearLayout) convertView.findViewById(R.id.ll_adapter_evaluate_review_productimg);
            viewHolder.tvReviewDate = (TextView) convertView.findViewById(R.id.tv_adapter_evaluate_review_date);
            viewHolder.tvReviewContent = (TextView) convertView.findViewById(R.id.tv_adapter_evaluate_review);
            viewHolder.rbReview = (RatingBar) convertView.findViewById(R.id.rb_adapter_evaluate_review);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(data.getImgUrl()).into(viewHolder.ivProductImg);
        viewHolder.tvProductName.setText(data.getProductName());

        if (data.getIsreviews().equals("1")) {
            viewHolder.tvReviewDate.setVisibility(View.VISIBLE);
            viewHolder.llReview.setVisibility(View.VISIBLE);

            viewHolder.tvReviewDate.setText(data.getTime());
            viewHolder.rbReview.setRating(Float.parseFloat(data.getPercentage()));
            viewHolder.tvReviewContent.setText(data.getMessage());

            viewHolder.llReviewProductImg.removeAllViews();
            for (int i = 0; i < data.getImgUrls().size(); i++) {
                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(150, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setPadding(0,0,15,0);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(context).load(data.getImgUrls().get(i)).into(imageView);
                viewHolder.llReviewProductImg.addView(imageView);
            }

            viewHolder.relativeLayoutUnreview.setVisibility(View.GONE);
        } else {
            viewHolder.tvReviewDate.setVisibility(View.GONE);
            viewHolder.llReview.setVisibility(View.GONE);

            viewHolder.relativeLayoutUnreview.setVisibility(View.VISIBLE);
        }

        viewHolder.ratingBarOne.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                evaluateResult.getData().get(position).setStar1(((int) rating) + "");
            }
        });
        viewHolder.ratingBarTwo.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                evaluateResult.getData().get(position).setStar2(((int) rating) + "");
            }
        });
        viewHolder.ratingBarThree.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                evaluateResult.getData().get(position).setStar3(((int) rating) + "");
            }
        });
        viewHolder.relativeLayoutPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSelectImageListener != null) {
                    onSelectImageListener.onSelectImage(position);
                }
            }
        });
        viewHolder.etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                evaluateResult.getData().get(position).setMessage(s.toString());
            }
        });
        return convertView;
    }

    public void saveJsonToSP() {
        Gson gson = new Gson();
        manager.saveEvaluateJson(gson.toJson(evaluateResult));
    }

    private static class ViewHolder {
        ImageView ivProductImg, imageViewOne, imageViewTwo, imageViewThree;
        TextView tvProductName;
        EditText etMessage;
        RatingBar ratingBarOne, ratingBarTwo, ratingBarThree;
        RelativeLayout relativeLayoutPhoto, relativeLayoutUnreview;

        TextView tvReviewDate, tvReviewContent;
        RatingBar rbReview;
        LinearLayout llReview, llReviewProductImg;
    }

    /**
     * 自定义请求本地图片的回调接口
     */
    public interface OnSelectImageListener {
        void onSelectImage(int position);
    }

    public void setOnSelectImageListener(OnSelectImageListener listener) {
        this.onSelectImageListener = listener;
    }

    int i = 0;

    public void updateItem(int position, Uri uri, String media_id) {
        Log.e("tag","-------------------->position="+position+",uri="+uri.toString()+",id="+media_id);
        if(tempPosition == null){
            tempPosition = position +"";
            evaluateResult.getData().get(position).setMedia_ids(media_id);
        }else{
            if(tempPosition.equals(position+"")){
                String temp = evaluateResult.getData().get(position).getMedia_ids();
                evaluateResult.getData().get(position).setMedia_ids(temp + "," + media_id);
            }else{
                tempPosition = position +"";
                i = 0;
                evaluateResult.getData().get(position).setMedia_ids(media_id);
            }
        }

        int firstVisiblePosition = mListView.getFirstVisiblePosition();
        int index = position - firstVisiblePosition;
        if (index >= 0) {
            View view = mListView.getChildAt(index);
            ViewHolder holder = (ViewHolder) view.getTag();
            if (i == 0) {
                holder.imageViewOne.setImageURI(uri);
                i++;
            } else if (i == 1) {
                holder.imageViewTwo.setImageURI(uri);
                i++;
            } else if (i == 2) {
                holder.imageViewThree.setImageURI(uri);
                i = 0;
            }
        }
    }
}

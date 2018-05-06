package com.yundian.wudou.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.activity.CommodityDetailsActivity;
import com.yundian.wudou.activity.VegetableShopActivity;
import com.yundian.wudou.activity.VegetableShopOldActivity;
import com.yundian.wudou.data.AdapterHomepageShopData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.data.HorizontaiListviewListData;
import com.yundian.wudou.data.LayoutStringData;
import com.yundian.wudou.utils.DashedLineView;
import com.yundian.wudou.utils.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cookie on 2016/8/8.
 */
public class HomepageShopAdapter extends BaseAdapter {
    private static final String TAG = "HomepageShopAdapter";

    private Context context;
    private List<HorizontaiListviewListData.StoresBean.DataBean> mList;


    private List<LayoutStringData> mListLayout = new ArrayList<>();

    public HomepageShopAdapter(Context context, List<HorizontaiListviewListData.StoresBean.DataBean> mList) {

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_homepage_shop, null);
            viewHolder.mImageViewShop = (ImageView) convertView.findViewById(R.id.iv_fragmenthome_shop);
            viewHolder.mTextViewName = (TextView) convertView.findViewById(R.id.tv_fragmenthome_shop_name);
            viewHolder.mImageViewExpert = (ImageView) convertView.findViewById(R.id.iv_fragmenthome_shop_expert);
            viewHolder.mTextViewDistance = (TextView) convertView.findViewById(R.id.tv_fragmenthome_shop_distance);
            viewHolder.mTextViewCount = (TextView) convertView.findViewById(R.id.tv_fragmenthome_shop_count);
            viewHolder.mTextViewStartPrice = (TextView) convertView.findViewById(R.id.tv_fragmenthome_shop_startprice);
            viewHolder.mTextViewSendPrice = (TextView) convertView.findViewById(R.id.tv_fragmenthome_shop_sendprice);
            viewHolder.promotion = (LinearLayout) convertView
                    .findViewById(R.id.restaurant_list_item_present_promotion_container);
            viewHolder.ly_shop_rx = (LinearLayout) convertView
                    .findViewById(R.id.ly_shop_rx);
            viewHolder.lineView = (ImageView) convertView
                    .findViewById(R.id.das_line);
            viewHolder.promotionIcon = (ImageView) convertView.findViewById(R.id.restaurant_promotion_icon);
            viewHolder.rl_shop_view = (RelativeLayout) convertView.findViewById(R.id.rl_shop_view);
            viewHolder.mGridView = (HorizontalListView) convertView.findViewById(R.id.gv_adapter_homepage_shop);
            viewHolder.mRatingBar = (RatingBar) convertView.findViewById(R.id.tv_fragmenthome_shop_rating_bar);
            viewHolder.restaurant_icon = (CheckBox) convertView.findViewById(R.id.restaurant_icon);
            viewHolder.tv_star_1 = (TextView) convertView.findViewById(R.id.tv_star_1);
            viewHolder.tv_star_2 = (TextView) convertView.findViewById(R.id.tv_star_2);
            viewHolder.tv_star_3 = (TextView) convertView.findViewById(R.id.tv_star_3);
            viewHolder.tv_star_4 = (TextView) convertView.findViewById(R.id.tv_star_4);
            viewHolder.tv_star_5 = (TextView) convertView.findViewById(R.id.tv_star_5);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.promotion.removeAllViews();
        }
        Glide.with(context).load(FlagData.FLAG_IMGADDRESS + mList.get(position).getImg()).into(viewHolder.mImageViewShop);
        viewHolder.mTextViewName.setText(mList.get(position).getName());
        viewHolder.mTextViewDistance.setText(mList.get(position).getDistance());
        viewHolder.mTextViewCount.setText("月售" + mList.get(position).getMonthlysales() + "份");
        viewHolder.mTextViewStartPrice.setText("¥ " + mList.get(position).getStartvalue());
        viewHolder.mTextViewSendPrice.setText("¥ " + mList.get(position).getStartfee());
        viewHolder.mRatingBar.setRating(Float.valueOf(mList.get(position).getStar()));

        Float star = Float.valueOf(mList.get(position).getStar());
        int star_i = Math.round(star);
        Log.d(TAG, "getView: star=");
        if (star_i < 1) {
            star_i = 1;
        } else if (star_i >= 5) {
            star_i = 5;
        }

        if (star_i == 1) {
            viewHolder.tv_star_1.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_2.setBackgroundResource(R.drawable.my_home_ratingbar_off);
            viewHolder.tv_star_3.setBackgroundResource(R.drawable.my_home_ratingbar_off);
            viewHolder.tv_star_4.setBackgroundResource(R.drawable.my_home_ratingbar_off);
            viewHolder.tv_star_5.setBackgroundResource(R.drawable.my_home_ratingbar_off);
        } else if (star_i == 2) {
            viewHolder.tv_star_1.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_2.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_3.setBackgroundResource(R.drawable.my_home_ratingbar_off);
            viewHolder.tv_star_4.setBackgroundResource(R.drawable.my_home_ratingbar_off);
            viewHolder.tv_star_5.setBackgroundResource(R.drawable.my_home_ratingbar_off);
        } else if (star_i == 3) {
            viewHolder.tv_star_1.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_2.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_3.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_4.setBackgroundResource(R.drawable.my_home_ratingbar_off);
            viewHolder.tv_star_5.setBackgroundResource(R.drawable.my_home_ratingbar_off);
        } else if (star_i == 4) {
            viewHolder.tv_star_1.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_2.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_3.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_4.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_5.setBackgroundResource(R.drawable.my_home_ratingbar_off);
        } else if (star_i == 5) {
            viewHolder.tv_star_1.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_2.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_3.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_4.setBackgroundResource(R.drawable.my_home_ratingbar_on);
            viewHolder.tv_star_5.setBackgroundResource(R.drawable.my_home_ratingbar_on);
        }
        if (mList.get(position).getIsdiscount().equals("1")) {
            String[] yh = mList.get(position).getDiscounttitle().split(",");
            viewHolder.lineView.setVisibility(View.VISIBLE);
            viewHolder.promotionIcon.setVisibility(View.VISIBLE);
            viewHolder.promotion.setVisibility(View.VISIBLE);

            LayoutStringData lsd = new LayoutStringData();
            LinearLayout myLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.restaurant_promotion_with_icon_text_view, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView[] pairs = new TextView[yh.length];
            for (int l = 0; l < yh.length; l++) {
                pairs[l] = new TextView(context);
                pairs[l].setTextSize(12);
                pairs[l].setLayoutParams(lp);
                pairs[l].setId(l);
                pairs[l].setText(yh[l] + "");
                pairs[l].setTextColor(Color.BLACK);
                pairs[l].setPadding(5, 10, 0, 0);
                myLayout.addView(pairs[l]);
                lsd.getTv_quan_list().add(pairs[l]);
            }

            viewHolder.promotion.addView(myLayout);

            lsd.setPosition(String.valueOf(position));
            lsd.setmListLayout(viewHolder.promotion);
            mListLayout.add(lsd);
            if (yh.length > 2) {
                viewHolder.restaurant_icon.setVisibility(View.VISIBLE);
                viewHolder.restaurant_icon.setTag(String.valueOf(position));
                for (int i = 0; i < pairs.length; i++) {
                    if (i > 1) {
                        pairs[i].setVisibility(View.GONE);
                    }
                }

            } else {
                viewHolder.restaurant_icon.setVisibility(View.GONE);
            }
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.restaurant_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str_p = view.getTag().toString();
                    Log.d(">>>>>", ">>>" + finalViewHolder.restaurant_icon.isChecked());
                    if (finalViewHolder.restaurant_icon.isChecked() == true) {

                        for (LayoutStringData ly : mListLayout) {
                            if (ly.getPosition().equals(str_p)) {
                                ly.getmListLayout().setVisibility(View.VISIBLE);
                                for (TextView tv : ly.getTv_quan_list()) {
                                    tv.setVisibility(View.VISIBLE);
                                }
                            }
                        }

                    } else {

                        for (LayoutStringData ly : mListLayout) {
                            if (ly.getPosition().equals(str_p)) {
                                int count=0;
                                for (TextView tv : ly.getTv_quan_list()) {
                                    if(count > 1)
                                    {
                                        tv.setVisibility(View.GONE);
                                    }
                                    count++;
                                }
                            }
                        }
                    }

                }
            });
        } else {
            viewHolder.lineView.setVisibility(View.GONE);
            viewHolder.ly_shop_rx.setVisibility(View.GONE);
            viewHolder.promotionIcon.setVisibility(View.GONE);
            viewHolder.rl_shop_view.setVisibility(View.GONE);
            viewHolder.restaurant_icon.setVisibility(View.GONE);

        }
        if (mList.get(position).getStores_products().getSubdata().size() > 0) {
            viewHolder.ly_shop_rx.setVisibility(View.VISIBLE);
            viewHolder.mGridView.setVisibility(View.VISIBLE);
            viewHolder.rl_shop_view.setVisibility(View.VISIBLE);
            HomeGridViewAdapter adapter = new HomeGridViewAdapter(context, mList.get(position).getStores_products().getSubdata());
            //   viewHolder.mGridView.setFocusableInTouchMode(false);

            viewHolder.mGridView.setAdapter(adapter);
            viewHolder.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    Toast.makeText(context,"i" +i,Toast.LENGTH_SHORT).show();

                    Intent intent;
                    if(mList.get(i).getStoreid().equals("14"))
                    {
                        intent = new Intent(context,VegetableShopActivity.class);
                        intent.putExtra(FlagData.FLAG_SHOP_ID, "14");
                        intent.putExtra(FlagData.FLAG_STATE, "1");
                    }
                    else
                    {
                        intent = new Intent(context, CommodityDetailsActivity.class);
                        intent.putExtra(FlagData.FLAG_PRODUCT_ID, mList.get(position).getStores_products().getSubdata().get(i).getPid());
                    }
                    context.startActivity(intent);
                }
            });
//            viewHolder.mGridView.set
        } else {
            viewHolder.ly_shop_rx.setVisibility(View.GONE);
            viewHolder.mGridView.setVisibility(View.GONE);
        }
        if (mList.get(position).getStoremodel().contains("加盟")) {

            viewHolder.mImageViewExpert.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mImageViewExpert.setVisibility(View.GONE);

        }
        return convertView;
    }

    private static class ViewHolder {
        ImageView mImageViewShop, mImageViewExpert, promotionIcon, lineView;
        TextView mTextViewName, mTextViewDistance, mTextViewCount, mTextViewStartPrice, mTextViewSendPrice;
        HorizontalListView mGridView;
        RatingBar mRatingBar;
        TextView tv_star_1, tv_star_2, tv_star_3, tv_star_4, tv_star_5;
        public LinearLayout promotion, ly_shop_rx;
        CheckBox restaurant_icon;
        RelativeLayout rl_shop_view;
    }
}

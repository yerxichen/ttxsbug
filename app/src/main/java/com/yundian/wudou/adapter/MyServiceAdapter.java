package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterMyConvenienceServicesData;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class MyServiceAdapter extends BaseAdapter {

    private Context context;
    private List<AdapterMyConvenienceServicesData> mList;

    public MyServiceAdapter(Context context, List<AdapterMyConvenienceServicesData> mList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        AdapterMyConvenienceServicesData data = mList.get(position);

        ViewHolder viewHolder = null;

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_myservice_listview, null);

            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_adapter_myservice);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_adapter_myservice_name);
            viewHolder.tvRegion = (TextView) convertView.findViewById(R.id.tv_adapter_myservice_region);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_adapter_myservice_time);
            viewHolder.tvReviewedSate = (TextView) convertView.findViewById(R.id.tv_adapter_myservice_reviewedsate);
            viewHolder.tvDelete = (TextView) convertView.findViewById(R.id.tv_adapter_myservice_delete);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(data.getImgUrl()).into(viewHolder.imageView);
        viewHolder.tvName.setText(data.getGoodsName());
        viewHolder.tvTime.setText("发布时间 : " + data.getPubdate());
        if (data.getLocation().equals("")) {
            viewHolder.tvRegion.setText("南通市");
        } else {
            viewHolder.tvRegion.setText(data.getLocation());
        }

        if (data.getStatus().equals("0")) {
            viewHolder.tvReviewedSate.setText("已审核");
        } else {
            viewHolder.tvReviewedSate.setText("未审核");
        }

        viewHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //回调
                if (mItemReduceListener != null) {
                    mItemReduceListener.itemReduceListener(mList.get(position).getNewsId());
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView tvName, tvRegion, tvTime, tvDelete, tvReviewedSate;
    }

    /**
     * 自定义监听的接口
     */
    private ItemReduceListener mItemReduceListener;

    public void setItemReduceListener(ItemReduceListener listener) {
        this.mItemReduceListener = listener;
    }

    public interface ItemReduceListener {
        void itemReduceListener(String newsid);
    }

}

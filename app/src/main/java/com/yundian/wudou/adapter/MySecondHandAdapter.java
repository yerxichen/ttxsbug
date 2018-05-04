package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.data.AdapterMySecondHandData;

import java.util.List;

/**
 * Created by Itachi on 2016/8/18.
 */
public class MySecondHandAdapter extends BaseAdapter {
    private Context context;
    private List<AdapterMySecondHandData> mList;

    public MySecondHandAdapter(Context context, List<AdapterMySecondHandData> mList) {
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

        AdapterMySecondHandData data = mList.get(position);

        ViewHolder viewHolder = null;

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_mysecondhand_listview, null);

            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_adapter_mysecondhand);
            viewHolder.tvRegion = (TextView) convertView.findViewById(R.id.tv_adapter_mysecondhand_region);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_adapter_mysecondhand_time);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_adapter_mysecondhand_name);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_adapter_mysecondhand_shopprice);
            viewHolder.tvReviewedSate = (TextView) convertView.findViewById(R.id.tv_adapter_mysecondhand_reviewedsate);
            viewHolder.tvDelete = (TextView) convertView.findViewById(R.id.tv_adapter_mysecondhand_delete);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(data.getImgUrl()).into(viewHolder.imageView);
        viewHolder.tvName.setText(data.getProductName());
        viewHolder.tvTime.setText("发布时间 : " + data.getPubdate());
        viewHolder.tvPrice.setText("¥ " + data.getPrice());

        if (data.getRegional().equals("")) {
            viewHolder.tvRegion.setText("南通市");
        } else {
            viewHolder.tvRegion.setText(data.getRegional());
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
                    mItemReduceListener.itemReduceListener(mList.get(position).getPid());
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView tvName, tvRegion, tvTime, tvPrice, tvDelete, tvReviewedSate;
    }

    /**
     * 自定义监听的接口
     */
    private ItemReduceListener mItemReduceListener;

    public void setItemReduceListener(ItemReduceListener listener) {
        this.mItemReduceListener = listener;
    }

    public interface ItemReduceListener {
        void itemReduceListener(String pid);
    }
}

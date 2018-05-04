package com.yundian.wudou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.customview.SlideListView;
import com.yundian.wudou.data.AdapterAddressSelectListData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.NetWorkOperate;

import java.util.List;

/**
 * Created by cookie on 2016/8/16.
 */
public class AddressSelectListAdapter extends BaseAdapter {

    private OnDeleteListener onDeleteListener;

    private List<AdapterAddressSelectListData> mList;
    private Context context;

    public AddressSelectListAdapter(Context context, List<AdapterAddressSelectListData> mList) {
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

        AdapterAddressSelectListData data = mList.get(position);

        if (null == convertView) {

            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_addressselectlist, null);

            viewHolder.mTextViewName = (TextView) convertView.findViewById(R.id.tv_adapter_addressselectlist_name);

            viewHolder.mTextViewPhone = (TextView) convertView.findViewById(R.id.tv_adapter_addressseclectlist_phone);

            viewHolder.mTextViewAddress = (TextView) convertView.findViewById(R.id.tv_adapter_addressseclectlist_address);

            viewHolder.mTextViewAddressDetail = (TextView) convertView.findViewById(R.id.tv_adapter_addressseclectlist_address_detail);

            viewHolder.mTextViewDelete = (TextView) convertView.findViewById(R.id.text_delete);

            viewHolder.mTextViewDefault = (TextView) convertView.findViewById(R.id.tv_adapter_addressselectlist_default);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == 0) {
            viewHolder.mTextViewDefault.setText("(默认)");
        }
        viewHolder.mTextViewName.setText(data.getName());
        viewHolder.mTextViewPhone.setText(data.getPhone());
        viewHolder.mTextViewAddress.setText(data.getLocate());
        viewHolder.mTextViewAddressDetail.setText(data.getDetails());

        //删除按钮
        viewHolder.mTextViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteListener != null) {
                    onDeleteListener.onDelete(mList.get(position).getSaid());
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView mTextViewName, mTextViewPhone, mTextViewAddress, mTextViewAddressDetail, mTextViewDefault, mTextViewDelete;
    }

    public interface OnDeleteListener {
        void onDelete(String addressid);
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }
}

package com.yundian.wudou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.activity.FragmentContainerActivity;
import com.yundian.wudou.data.AdapterRegionData;
import com.yundian.wudou.data.FlagData;

import java.util.List;

/**
 * Created by Administrator on 2016/12/17 0017.
 */

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.MyViewHolder>{

    private List<AdapterRegionData> list;
    private Context context;

    public RegionAdapter(List<AdapterRegionData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_region_recyclerview,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_adapter_region);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FragmentContainerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(FlagData.FLAG_REGION, list.get(getAdapterPosition()).getName());
                    context.startActivity(intent);
                }
            });
        }
    }
}

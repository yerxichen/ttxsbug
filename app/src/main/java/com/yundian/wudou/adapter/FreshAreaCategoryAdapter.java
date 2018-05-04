package com.yundian.wudou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.data.StoresinfoData;

import java.util.List;

/**
 * Created by chenlijin on 2016/3/17.
 */
public class FreshAreaCategoryAdapter extends RecyclerView.Adapter<FreshAreaCategoryAdapter.ViewHolder> {
    private List<StoresinfoData.StorescategoriesBean.DataBeanX> categoryList;
    private Context context;

    public FreshAreaCategoryAdapter(Context context, List<StoresinfoData.StorescategoriesBean.DataBeanX> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    public void setCategoryList(List<StoresinfoData.StorescategoriesBean.DataBeanX> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(categoryList.get(position).getName());
        if (null!=categoryList.get(position).getTag()){
        holder.textView.setTag(categoryList.get(position).getTag());
        if (categoryList.get(position).getTag().equals("1")){
            holder.textView.setTextColor(context.getResources().getColor(R.color.colorGreenBefore));

        }else {
            holder.textView.setTextColor(context.getResources().getColor(R.color.colorBlack));
        }}
        holder.linearLayout.setSelected(categoryList.get(position).isSeleted());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview_categoryname);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ly_textview_categoryname);
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}

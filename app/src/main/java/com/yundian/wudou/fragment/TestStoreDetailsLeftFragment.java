package com.yundian.wudou.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.adapter.ClassificationLeftFragmentAdapter;
import com.yundian.wudou.customview.DividerItemDecoration;
import com.yundian.wudou.data.AdapterClassificationLeftData;
import com.yundian.wudou.publicinterface.StoreDetailsLeftSelectedListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cookie on 2016/8/4.
 */
public class TestStoreDetailsLeftFragment extends Fragment {

    private StoreDetailsLeftSelectedListener mStoreDetailsLeftSelectedListener;

    private RecyclerView listView;
    private List<AdapterClassificationLeftData> list;
    private CommonAdapter adapter;

    private String cateId, cateName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment_classification_left, container, false);

        initialize(view);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        mStoreDetailsLeftSelectedListener = (StoreDetailsLeftSelectedListener) getActivity();
        super.onAttach(context);
    }

    private void initialize(View view) {
        listView = (RecyclerView) view.findViewById(R.id.lv_classification_left_t);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        list = new ArrayList<>();
    }

    //提供给店铺详情传递分类数据的方法
    public void update(List<AdapterClassificationLeftData> mAdapterStoreDetailsLeftDataList) {
        this.list = mAdapterStoreDetailsLeftDataList;
        Log.e("tag", "-----------------leftfragment>>list.size=" + list.size());
        adapter =
                new CommonAdapter<AdapterClassificationLeftData>(getActivity(), R.layout.adapter_classification_left, list) {

                    @Override
                    protected void convert(ViewHolder holder, AdapterClassificationLeftData adapterClassificationLeftData, int position) {
                        RelativeLayout mRelativeLayout = holder.getView(R.id.rl_adapter_classification_left);
                        ImageView mImageViewLeft = (ImageView) holder.getView(R.id.iv_adapter_classification_left);
                        TextView mTextView = (TextView) holder.getView(R.id.tv_adapter_classification_left);

                        if (adapterClassificationLeftData.isSelect()) {
                            mImageViewLeft.setVisibility(View.VISIBLE);
                            mTextView.setTextColor(getActivity().getResources().getColor(R.color.colorGreenBefore));
                            mRelativeLayout.setBackgroundResource(R.color.colorWrite);
                        } else {
                            mImageViewLeft.setVisibility(View.INVISIBLE);
                            mRelativeLayout.setBackgroundResource(R.color.colorGray_2);
                            mTextView.setTextColor(getActivity().getResources().getColor(R.color.colorGray));
                        }
                        mTextView.setText(adapterClassificationLeftData.getCateName());
                    }
                };
        listView.setAdapter(adapter);
        if (list.size() > 0) {
            list.get(0).setSelect(true);
            mStoreDetailsLeftSelectedListener.onSelected(list.get(0).getCateid(), list.get(0).getCateName());
        }
        adapter.notifyDataSetChanged();
        setEventListener();
    }

    private void setEventListener() {
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (list.size() > 0) {
                    for (AdapterClassificationLeftData data : list) {
                        data.setSelect(false);
                    }
                    list.get(position).setSelect(true);
                    adapter.notifyDataSetChanged();
                    cateId = list.get(position).getCateid();
                    cateName = list.get(position).getCateName();
                    //选中分类回调
                    mStoreDetailsLeftSelectedListener.onSelected(cateId, cateName);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }
}

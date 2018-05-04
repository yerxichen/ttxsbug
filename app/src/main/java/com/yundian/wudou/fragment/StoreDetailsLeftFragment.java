package com.yundian.wudou.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yundian.wudou.R;
import com.yundian.wudou.adapter.ClassificationLeftFragmentAdapter;
import com.yundian.wudou.data.AdapterClassificationLeftData;
import com.yundian.wudou.publicinterface.StoreDetailsLeftSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cookie on 2016/8/4.
 */
public class StoreDetailsLeftFragment extends Fragment {

    private StoreDetailsLeftSelectedListener mStoreDetailsLeftSelectedListener;

    private ListView listView;
    private List<AdapterClassificationLeftData> list;
    private ClassificationLeftFragmentAdapter adapter;

    private String cateId, cateName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classification_left, container, false);

        initialize(view);

        setEventListener();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        mStoreDetailsLeftSelectedListener = (StoreDetailsLeftSelectedListener) getActivity();
        super.onAttach(context);
    }

    private void initialize(View view) {
        listView = (ListView) view.findViewById(R.id.lv_classification_left);
        list = new ArrayList<>();
    }
    //提供给店铺详情传递分类数据的方法
    public void update(List<AdapterClassificationLeftData> mAdapterStoreDetailsLeftDataList) {
        this.list = mAdapterStoreDetailsLeftDataList;
        Log.e("tag","-----------------leftfragment>>list.size="+list.size());
        adapter = new ClassificationLeftFragmentAdapter(getContext(), list);
        listView.setAdapter(adapter);
        if (list.size() > 0) {
            list.get(0).setSelect(true);
            mStoreDetailsLeftSelectedListener.onSelected(list.get(0).getCateid(), list.get(0).getCateName());
        }
        adapter.notifyDataSetChanged();
    }

    private void setEventListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
        });
    }
}

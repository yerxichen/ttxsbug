package com.yundian.wudou.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yundian.wudou.R;
import com.yundian.wudou.adapter.ClassificationLeftFragmentAdapter;
import com.yundian.wudou.data.AdapterClassificationLeftData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanClassificationLeft;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.ClassificationLeftSelectedListener;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cookie on 2016/8/4.
 */
public class ClassificationLeftFragment extends Fragment implements NetWorkInterface.OnGetClassificationLeftDataListener {

    @Bind(R.id.lv_classification_left)
    ListView mListViewLeftFragment;

    private ClassificationLeftSelectedListener mClassificationLeftSelectedListener;

    private List<AdapterClassificationLeftData> mListAdapterClassificationLeftData;
    private ClassificationLeftFragmentAdapter mClassificationLeftFragmentAdapter;

    private SharedpreferencesManager sharedpreferencesManager;
    private NetWorkOperate netWorkOperate;
    private String strToken;

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
        super.onAttach(context);
        mClassificationLeftSelectedListener = (ClassificationLeftSelectedListener)getParentFragment();
    }

    private void initialize(View view) {
        ButterKnife.bind(ClassificationLeftFragment.this,view);
        sharedpreferencesManager = new SharedpreferencesManager(getContext());
        netWorkOperate = new NetWorkOperate(ClassificationLeftFragment.this);


        mListAdapterClassificationLeftData = new ArrayList<>();
        mClassificationLeftFragmentAdapter = new ClassificationLeftFragmentAdapter(getContext(), mListAdapterClassificationLeftData);
        mListViewLeftFragment.setAdapter(mClassificationLeftFragmentAdapter);
        strToken = sharedpreferencesManager.getToken();
        netWorkOperate.getClassificationLeftData(strToken);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void setEventListener() {
        mListViewLeftFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (AdapterClassificationLeftData data : mListAdapterClassificationLeftData) {
                    data.setSelect(false);
                }
                mListAdapterClassificationLeftData.get(position).setSelect(true);
                mClassificationLeftFragmentAdapter.notifyDataSetChanged();
                if(mClassificationLeftSelectedListener!=null){
                    mClassificationLeftSelectedListener.onSelected(mListAdapterClassificationLeftData.get(position).getCateid());
                }
            }
        });
    }

    @Override
    public void onGetClassificationLeftData(JsonBeanClassificationLeft jsonBeanClassificationLeft) {
        mListAdapterClassificationLeftData.clear();
        for (JsonBeanClassificationLeft.DataBean dataBean : jsonBeanClassificationLeft.getData()) {
            mListAdapterClassificationLeftData.add(new AdapterClassificationLeftData(false, dataBean.getCateid(), dataBean.getName()));
        }
        //初始化
        mListAdapterClassificationLeftData.get(0).setSelect(true);
        mClassificationLeftSelectedListener.onSelected(mListAdapterClassificationLeftData.get(0).getCateid());
        mClassificationLeftFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {

    }
}

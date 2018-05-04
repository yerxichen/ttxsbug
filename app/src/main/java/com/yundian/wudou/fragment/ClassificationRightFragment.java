package com.yundian.wudou.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yundian.wudou.R;
import com.yundian.wudou.adapter.ClassificationRightFragmentAdapter;
import com.yundian.wudou.adapter.ClassificationRightRecycleViewAdapter;
import com.yundian.wudou.data.AdapterClassificationRightListData;
import com.yundian.wudou.data.AdapterClassificationRightRecycleData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanClassificationRightData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cookie on 2016/8/4.
 */
public class ClassificationRightFragment extends Fragment implements NetWorkInterface.OnGetClassificationRightDataListener {

    @Bind(R.id.lv_classification_right)
    ListView mListViewRightFragment;

    private List<AdapterClassificationRightListData> mListAdapterClassificationRightData;
    private List<AdapterClassificationRightRecycleData> mRecycleAdapterClassificationRightData;
    private ClassificationRightFragmentAdapter mClassificationRightFragmentAdapter;
    private ClassificationRightRecycleViewAdapter mClassificationRightRecycleViewAdapter;

    private SharedpreferencesManager sharedpreferencesManager;
    private NetWorkOperate netWorkOperate;
    private String strToken, strCateId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classification_right, container, false);

        initialize(view);

        return view;
    }

    private void initialize(View view) {
        ButterKnife.bind(ClassificationRightFragment.this,view);
        sharedpreferencesManager = new SharedpreferencesManager(getContext());
        netWorkOperate = new NetWorkOperate(ClassificationRightFragment.this);
        strToken = sharedpreferencesManager.getToken();

        mListAdapterClassificationRightData = new ArrayList<>();
        mClassificationRightFragmentAdapter = new ClassificationRightFragmentAdapter(getContext(), mListAdapterClassificationRightData);
        mListViewRightFragment.setAdapter(mClassificationRightFragmentAdapter);
    }

    @Override
    public void onGetClassificationRightData(JsonBeanClassificationRightData jsonBeanClassificationRightData) {
        mListAdapterClassificationRightData.clear();
        for (JsonBeanClassificationRightData.DataBean dataBean : jsonBeanClassificationRightData.getData()) {
            mRecycleAdapterClassificationRightData = new ArrayList<>();
            for (JsonBeanClassificationRightData.DataBean.SubcategoryBean subcategoryBean : dataBean.getSubcategory()) {
                mRecycleAdapterClassificationRightData.add(new AdapterClassificationRightRecycleData(
                        FlagData.FLAG_IMGADDRESS+subcategoryBean.getImg(),subcategoryBean.getName(),
                        subcategoryBean.getCateid(),subcategoryBean.getCatenumber()));
            }
            mClassificationRightRecycleViewAdapter = new ClassificationRightRecycleViewAdapter(getContext(),mRecycleAdapterClassificationRightData);
            mListAdapterClassificationRightData.add(new AdapterClassificationRightListData(true, dataBean.getName(),mClassificationRightRecycleViewAdapter));
        }
        mClassificationRightFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        mListAdapterClassificationRightData.clear();
        mClassificationRightFragmentAdapter.notifyDataSetChanged();
    }

    public void setCateId(String cataid) {
        this.strCateId = cataid;
        mListAdapterClassificationRightData.clear();
        netWorkOperate.getClassificationRightData(strToken, strCateId);
    }
}

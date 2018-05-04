package com.yundian.wudou.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.yundian.wudou.R;
import com.yundian.wudou.activity.SearchResultActivity;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.publicinterface.ClassificationLeftSelectedListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cookie on 2016/7/6.
 */
public class ClassificationFragment extends Fragment implements ClassificationLeftSelectedListener {

    @Bind(R.id.et_fragment_classification_search)
    EditText editTextSearch;

    private BaseActivity baseActivity;

    private ClassificationLeftFragment leftFragment;
    private ClassificationRightFragment rightFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classification, container, false);
        ButterKnife.bind(ClassificationFragment.this,view);

        leftFragment = new ClassificationLeftFragment();
        rightFragment = new ClassificationRightFragment();

        FragmentManager mFragmentManager = getChildFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.fragment_container_left, leftFragment);
        mFragmentTransaction.add(R.id.fragment_container_right, rightFragment);
        mFragmentTransaction.commit();

        editTextSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能
                    // 先隐藏键盘
                    ((InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    Intent intent = new Intent(getContext(), SearchResultActivity.class);
                    intent.putExtra(FlagData.FLAG_SEARCH_TEXT,editTextSearch.getText().toString());
                    intent.putExtra(FlagData.FLAG_STATE,"1");
                    startActivity(intent);
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        baseActivity = (BaseActivity) context;
        baseActivity.setTitleBarVisibility(true);
        baseActivity.setTitle(R.string.classification);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            baseActivity.setTitleBarVisibility(true);
            baseActivity.setTitle(R.string.classification);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        editTextSearch.setText("");
    }

    /**
     * 监听左边分类列表接口的回调方法
     * */
    @Override
    public void onSelected(String cateid) {
        rightFragment.setCateId(cateid);
    }
}

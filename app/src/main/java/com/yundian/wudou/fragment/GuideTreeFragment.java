package com.yundian.wudou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yundian.wudou.R;
import com.yundian.wudou.activity.FragmentContainerActivity;
import com.yundian.wudou.datawork.SharedpreferencesManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cookie on 2016/6/29.
 */
public class GuideTreeFragment extends Fragment{

    @Bind(R.id.iv_fragmentguide_tree)
    ImageView imageView;

    private SharedpreferencesManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_tree, container, false);

        initialize(view);

        setEventListener();

        return view;
    }
    private void initialize(View view){
        ButterKnife.bind(GuideTreeFragment.this,view);
        manager = new SharedpreferencesManager(getContext());
    }

    private void setEventListener(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.saveFirstShow(true);
                Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}

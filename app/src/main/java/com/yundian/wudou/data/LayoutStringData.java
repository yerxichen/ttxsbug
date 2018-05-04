package com.yundian.wudou.data;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 乐阳 on 2018/2/6.
 */

public class LayoutStringData {

    private String position;
    private LinearLayout mListLayout;
    private List<TextView> tv_quan_list = new ArrayList<>();

    public List<TextView> getTv_quan_list() {
        return tv_quan_list;
    }

    public void setTv_quan_list(List<TextView> tv_quan_list) {
        this.tv_quan_list = tv_quan_list;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LinearLayout getmListLayout() {
        return mListLayout;
    }

    public void setmListLayout(LinearLayout mListLayout) {
        this.mListLayout = mListLayout;
    }
}

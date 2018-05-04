package com.yundian.wudou.data;

import com.yundian.wudou.adapter.ClassificationRightRecycleViewAdapter;

/**
 * Created by cookie on 2016/8/4.
 */
public class AdapterClassificationRightListData {

    private boolean isHead;
    private String title;
    private ClassificationRightRecycleViewAdapter mClassificationRightRecycleViewAdapter;

    public AdapterClassificationRightListData(boolean isHead, String title, ClassificationRightRecycleViewAdapter mClassificationRightRecycleViewAdapter) {
        this.isHead = isHead;
        this.title = title;
        this.mClassificationRightRecycleViewAdapter = mClassificationRightRecycleViewAdapter;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ClassificationRightRecycleViewAdapter getmClassificationRightRecycleViewAdapter() {
        return mClassificationRightRecycleViewAdapter;
    }

    public void setmClassificationRightRecycleViewAdapter(ClassificationRightRecycleViewAdapter mClassificationRightRecycleViewAdapter) {
        this.mClassificationRightRecycleViewAdapter = mClassificationRightRecycleViewAdapter;
    }
}

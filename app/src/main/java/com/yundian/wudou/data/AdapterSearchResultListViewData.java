package com.yundian.wudou.data;

import com.yundian.wudou.adapter.SearchResultRecycleViewAdapter;

/**
 * Created by cookie on 2016/8/15.
 */
public class AdapterSearchResultListViewData {

    private String storeid;
    private String imgUrl;
    private String name;
    private float sendPrice, startPrice;
    private SearchResultRecycleViewAdapter searchResultRecycleViewAdapter;

    public AdapterSearchResultListViewData(String storeid, String imgUrl, String name, float sendPrice, float startPrice, SearchResultRecycleViewAdapter searchResultRecycleViewAdapter) {
        this.storeid = storeid;
        this.imgUrl = imgUrl;
        this.name = name;
        this.sendPrice = sendPrice;
        this.startPrice = startPrice;
        this.searchResultRecycleViewAdapter = searchResultRecycleViewAdapter;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(float sendPrice) {
        this.sendPrice = sendPrice;
    }

    public float getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    public SearchResultRecycleViewAdapter getSearchResultRecycleViewAdapter() {
        return searchResultRecycleViewAdapter;
    }

    public void setSearchResultRecycleViewAdapter(SearchResultRecycleViewAdapter searchResultRecycleViewAdapter) {
        this.searchResultRecycleViewAdapter = searchResultRecycleViewAdapter;
    }
}

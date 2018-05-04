package com.yundian.wudou.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;
import com.yundian.wudou.R;
import com.yundian.wudou.activity.CommodityDetailsActivity;
import com.yundian.wudou.activity.VegetableShopActivity;
import com.yundian.wudou.adapter.CategoryAdapter;
import com.yundian.wudou.adapter.FreshAreaCategoryAdapter;
import com.yundian.wudou.adapter.FreshAreaTeamsAndHeaderAdapter;
import com.yundian.wudou.adapter.TeamsAndHeaderAdapter;
import com.yundian.wudou.adapter.TestStoreDetailsRightAdapter;
import com.yundian.wudou.customview.DividerItemDecoration;
import com.yundian.wudou.data.AdapterClassificationLeftData;
import com.yundian.wudou.data.AdapterStoreDetailsRightData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.data.StoresinfoData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanStoreDetailsCommodityData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;
import com.yundian.wudou.utils.ItemClickSupport;
import com.yundian.wudou.utils.MyItemDecoration;
import com.yundian.wudou.utils.RecyclerItemClickListener;
import com.yundian.wudou.utils.ViewUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taozipc on 2017/11/7.
 */

public class FreshAreaFragment extends Fragment {
    private VegetableShopActivity storeDetailsActivity;
    /**
     * 左侧菜单栏
     */
    private RecyclerView recyclerviewCategoryFreshArea;
    private LinearLayoutManager mCategoryLayoutManager;
    /**
     * 右侧菜单栏
     */
    private RecyclerView recyclerviewTeamsFreshArea;
    private LinearLayoutManager mTeamsLayoutManager;
    private List<StoresinfoData.StorescategoriesBean.DataBeanX> categoryList;
    private FreshAreaCategoryAdapter categoryAdapter;
    private boolean isChangeByCategoryClick = false;
    private FreshAreaTeamsAndHeaderAdapter teamsAndHeaderAdapter;
    private int oldSelectedPosition = 0;
    private boolean needMove = false;
    private int movePosition;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_store_fresh_area, container, false);

        initialize(viewRoot);
        return viewRoot;
    }

    private void initialize(View view) {
        storeDetailsActivity = (VegetableShopActivity) getActivity();
        recyclerviewCategoryFreshArea = (RecyclerView) view.findViewById(R.id.recyclerview_category_fresh_area);
        recyclerviewTeamsFreshArea = (RecyclerView) view.findViewById(R.id.recyclerview_teams_fresh_area);
        mTeamsLayoutManager = new LinearLayoutManager(getActivity());
        mCategoryLayoutManager = new LinearLayoutManager(getActivity());
        recyclerviewCategoryFreshArea.setLayoutManager(mCategoryLayoutManager);
        //   recyclerviewTeamsFreshArea.addItemDecoration(new MyItemDecoration());
        recyclerviewTeamsFreshArea.setLayoutManager(mTeamsLayoutManager);
        categoryList = new ArrayList<>();
        categoryAdapter = new FreshAreaCategoryAdapter(getActivity(), categoryList);
        categoryAdapter.setOnItemClickListener(new FreshAreaCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeSelected(position);
                moveToThisSortFirstItem(position);
                isChangeByCategoryClick = true;
            }
        });
        recyclerviewCategoryFreshArea.setAdapter(categoryAdapter);
        teamsAndHeaderAdapter = new FreshAreaTeamsAndHeaderAdapter(getActivity(), categoryList);
        recyclerviewTeamsFreshArea.setAdapter(teamsAndHeaderAdapter);

        // Add the sticky headers decoration,给球队添加标题
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(teamsAndHeaderAdapter);
        recyclerviewTeamsFreshArea.addItemDecoration(headersDecor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerviewTeamsFreshArea.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (needMove) {
                        needMove = false;
                        //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                        int n = movePosition - mTeamsLayoutManager.findFirstVisibleItemPosition();
                        if (0 <= n && n < recyclerviewTeamsFreshArea.getChildCount()) {
                            //获取要置顶的项顶部离RecyclerView顶部的距离
                            int top = recyclerviewTeamsFreshArea.getChildAt(n).getTop() - ViewUtils.dip2px(getActivity(), 28);
                            //最后的移动
                            recyclerviewTeamsFreshArea.scrollBy(0, top);
                        }
                    }
                    //第一个完全显示的item和最后一个item。
                    if (!isChangeByCategoryClick) {
                        int firstVisibleItem = mTeamsLayoutManager.findFirstCompletelyVisibleItemPosition();
                        int sort = teamsAndHeaderAdapter.getSortType(firstVisibleItem);
                        changeSelected(sort);
                    } else {
                        isChangeByCategoryClick = false;
                    }
                }
            });
        } else {
            recyclerviewTeamsFreshArea.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (needMove) {
                        needMove = false;
                        //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                        int n = movePosition - mTeamsLayoutManager.findFirstVisibleItemPosition();
                        if (0 <= n && n < recyclerviewTeamsFreshArea.getChildCount()) {
                            //获取要置顶的项顶部离RecyclerView顶部的距离
                            int top = recyclerviewTeamsFreshArea.getChildAt(n).getTop() - ViewUtils.dip2px(getActivity(), 28);
                            //最后的移动
                            recyclerviewTeamsFreshArea.scrollBy(0, top);
                        }
                    }
                    //第一个完全显示的item和最后一个item。
                    if (!isChangeByCategoryClick) {
                        int firstVisibleItem = mTeamsLayoutManager.findFirstCompletelyVisibleItemPosition();
                        int sort = teamsAndHeaderAdapter.getSortType(firstVisibleItem);
                        changeSelected(sort);
                    } else {
                        isChangeByCategoryClick = false;
                    }
                }
            });
        }
        setEventListener();
    }

    private void setEventListener() {

        ItemClickSupport.addTo(recyclerviewTeamsFreshArea).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getContext(), CommodityDetailsActivity.class);
                intent.putExtra(FlagData.FLAG_PRODUCT_ID, teamsAndHeaderAdapter.getTeamList().get(position).getPid());
                getContext().startActivity(intent);
            }
        });
        teamsAndHeaderAdapter.setItemAddListener(new FreshAreaTeamsAndHeaderAdapter.StoreItemAddListener() {
            @Override
            public void storeItemAddListener() {
                // 改变店铺详情底部状态
                storeDetailsActivity.refreshBottom();
            }
        });
        teamsAndHeaderAdapter.setItemReduceListener(new FreshAreaTeamsAndHeaderAdapter.StoreItemReduceListener() {
            @Override
            public void storeItemReduceListener() {
                //改变店铺详情底部状态
                storeDetailsActivity.refreshBottom();
            }
        });

    }

    public void update(List<StoresinfoData.StorescategoriesBean.DataBeanX> LeftData) {
        categoryList = LeftData;
        categoryList.get(0).setSeleted(true);
        categoryAdapter.setCategoryList(categoryList);
        categoryAdapter.notifyDataSetChanged();
        teamsAndHeaderAdapter.setCategoryList(categoryList);
        teamsAndHeaderAdapter.notifyDataSetChanged();
    }

    public void setData(String storeId, String storeName, String storeUrl, String startValue, String sendPrice, boolean isOpen) {
        teamsAndHeaderAdapter.setStoreId(storeId);
        teamsAndHeaderAdapter.setStoreName(storeName);
        teamsAndHeaderAdapter.setStoreUrl(storeUrl);
        teamsAndHeaderAdapter.setStartValue(startValue);
        teamsAndHeaderAdapter.setSendPrice(sendPrice);
        teamsAndHeaderAdapter.setisOpen(isOpen);
    }

    private void moveToPosition(int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = mTeamsLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mTeamsLayoutManager.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            recyclerviewTeamsFreshArea.scrollToPosition(n);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = recyclerviewTeamsFreshArea.getChildAt(n - firstItem).getTop();
            recyclerviewTeamsFreshArea.scrollBy(0, top - ViewUtils.dip2px(getActivity(), 28));
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            recyclerviewTeamsFreshArea.scrollToPosition(n);
            movePosition = n;
            needMove = true;
        }
    }


    private void moveToThisSortFirstItem(int position) {
        movePosition = 0;
        for (int i = 0; i < position; i++) {
            movePosition += teamsAndHeaderAdapter.getCategoryList().get(i).getProducts().getData().size();
        }
        moveToPosition(movePosition);
    }


    private void changeSelected(int position) {
        categoryList.get(oldSelectedPosition).setSeleted(false);
        categoryList.get(position).setSeleted(true);
        //增加左侧联动
        recyclerviewCategoryFreshArea.scrollToPosition(position);
        oldSelectedPosition = position;
        categoryAdapter.notifyDataSetChanged();

    }

    /**
     * 对外的方法,刷新adapter
     */
    public void refreshFragment() {
        teamsAndHeaderAdapter.notifyDataSetChanged();

    }
}



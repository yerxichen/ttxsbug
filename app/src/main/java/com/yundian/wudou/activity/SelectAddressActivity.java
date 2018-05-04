package com.yundian.wudou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yundian.wudou.R;
import com.yundian.wudou.adapter.AddressSelectListAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterAddressSelectListData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanDeleteAddress;
import com.yundian.wudou.network.JsonBeanLoadAddress;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SelectAddressActivity extends BaseActivity implements NetWorkInterface.OnLoadAddressListener, NetWorkInterface.OnDeleteAddressListener {

    @Bind(R.id.rl_activity_selectaddress_nodata)
    RelativeLayout rlNoData;
    @Bind(R.id.tv_activity_selectaddress_nodata)
    TextView tvNoData;
    @Bind(R.id.lv_activity_selectaddress)
    ListView listView;

    private ViewGroup mViewGroupChild;
    private RelativeLayout.LayoutParams mLayoutParams;
    private int mScreenWidth, mDeleteBtnWidth, mDownX, mDownY;
    private boolean isDeleteShow, isMove;

    private AddressSelectListAdapter mAddressListSeclectAdapter;
    private List<AdapterAddressSelectListData> mListAdapterAddressSelectData;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;
    private String strToken;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address_list);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(SelectAddressActivity.this);
        manager = new SharedpreferencesManager(SelectAddressActivity.this);
        netWorkOperate = new NetWorkOperate(SelectAddressActivity.this);

        this.setBackVisibility(true);
        this.setTitle(getString(R.string.my_address));
        this.setRightViewVisibility(true);
        this.setRightViewText(getResources().getString(R.string.add));

        Intent intent = getIntent();
        state = intent.getIntExtra("state", 0);

        mListAdapterAddressSelectData = new ArrayList<>();
        mAddressListSeclectAdapter = new AddressSelectListAdapter(SelectAddressActivity.this, mListAdapterAddressSelectData);
        listView.setAdapter(mAddressListSeclectAdapter);

        WindowManager windowManager = (WindowManager) SelectAddressActivity.this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListAdapterAddressSelectData.clear();
        strToken = manager.getToken();
        netWorkOperate.getAddress(strToken);
    }

    private void setEventListener() {
        this.setRightViewClickListener(new OnActionBarRightViewClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(SelectAddressActivity.this, EditAddressActivity.class);
                intent.putExtra("state", 2);
                startActivityForResult(intent, 2);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isDeleteShow) {
                    if (!isMove) {
                        if (state == 8) {//结算页面
                            Intent intent = new Intent(SelectAddressActivity.this, SettlementActivity.class);
                            intent.putExtra("name", mListAdapterAddressSelectData.get(position).getName());
                            intent.putExtra("phone", mListAdapterAddressSelectData.get(position).getPhone());
                            intent.putExtra("locate", mListAdapterAddressSelectData.get(position).getLocate());
                            intent.putExtra("locatedetail", mListAdapterAddressSelectData.get(position).getDetails());
                            intent.putExtra("addressid", mListAdapterAddressSelectData.get(position).getSaid());
                            setResult(8, intent);
                            SelectAddressActivity.this.finish();
                        } else if (state == 9) {
                            Intent intent = new Intent(SelectAddressActivity.this, IntegralCommodityDetailActivity.class);
                            intent.putExtra("name", mListAdapterAddressSelectData.get(position).getName());
                            intent.putExtra("phone", mListAdapterAddressSelectData.get(position).getPhone());
                            intent.putExtra("locate", mListAdapterAddressSelectData.get(position).getLocate());
                            intent.putExtra("addressid", mListAdapterAddressSelectData.get(position).getSaid());
                            setResult(9, intent);
                            SelectAddressActivity.this.finish();
                        } else {
                            Intent intent = new Intent(SelectAddressActivity.this, EditAddressActivity.class);
                            intent.putExtra("name", mListAdapterAddressSelectData.get(position).getName());
                            intent.putExtra("phone", mListAdapterAddressSelectData.get(position).getPhone());
                            intent.putExtra("locate", mListAdapterAddressSelectData.get(position).getLocate());
                            intent.putExtra("locatedetail", mListAdapterAddressSelectData.get(position).getDetails());
                            intent.putExtra("state", 1);
                            intent.putExtra("said", mListAdapterAddressSelectData.get(position).getSaid());
                            startActivityForResult(intent, 1);
                        }
                    } else {
                        isMove = false;
                    }
                }
            }
        });

        mAddressListSeclectAdapter.setOnDeleteListener(new AddressSelectListAdapter.OnDeleteListener() {
            @Override
            public void onDelete(String addressid) {
                netWorkOperate.deleteAddress(strToken, addressid);
            }
        });

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        performActionDown(motionEvent);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        return performActionMove(motionEvent);
                    case MotionEvent.ACTION_UP:
                        performActionUp();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        performActionUp();
                        break;
                }
                return SelectAddressActivity.super.onTouchEvent(motionEvent);
            }
        });
    }

    @Override
    public void onLoadAddress(JsonBeanLoadAddress jsonBeanLoadAddress) {
        rlNoData.setVisibility(View.GONE);
        for (JsonBeanLoadAddress.DataBean dataBean : jsonBeanLoadAddress.getData()) {
            mListAdapterAddressSelectData.add(new AdapterAddressSelectListData(dataBean.getSaid()
                    , dataBean.getConsignee(), dataBean.getMobile(), dataBean.getAddress(), dataBean.getAddressmark()));
        }
        mAddressListSeclectAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteAddress(JsonBeanDeleteAddress jsonBeanDeleteAddress) {
        turnToNormal();
        Toast.makeText(SelectAddressActivity.this, jsonBeanDeleteAddress.getMsg(), Toast.LENGTH_LONG).show();
        mListAdapterAddressSelectData.clear();
        netWorkOperate.getAddress(strToken);
    }

    @Override
    public void onFailure(String msg) {
        rlNoData.setVisibility(View.VISIBLE);
        tvNoData.setText(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            //修改地址信息
            Intent intent = new Intent(SelectAddressActivity.this, SelectAddressActivity.class);
            startActivity(intent);
        } else if (requestCode == 2 && resultCode == 2) {
            //新建地址信息
            Intent intent = new Intent(SelectAddressActivity.this, SelectAddressActivity.class);
            startActivity(intent);
        }
        SelectAddressActivity.this.finish();
    }

    private void performActionDown(MotionEvent ev) {
        if (isDeleteShow) {
            turnToNormal();
        }
        mDownX = (int) ev.getX();
        mDownY = (int) ev.getY();
        mViewGroupChild = (ViewGroup) listView.getChildAt(listView.pointToPosition(mDownX, mDownY) - listView.getFirstVisiblePosition());
        mDeleteBtnWidth = mViewGroupChild.getChildAt(1).getLayoutParams().width;

        mLayoutParams = (RelativeLayout.LayoutParams) mViewGroupChild.getChildAt(0).getLayoutParams();
        mLayoutParams.width = mScreenWidth;
        mViewGroupChild.getChildAt(0).setLayoutParams(mLayoutParams);
    }

    private boolean performActionMove(MotionEvent ev) {
        int nowX = (int) ev.getX();
        int nowY = (int) ev.getY();
        if (Math.abs(nowX - mDownX) > Math.abs(nowY - mDownY)) {
            isMove = true;
            if (nowX < mDownX) {
                int scroll = (nowX - mDownX) / 2;
                if (-scroll >= mDeleteBtnWidth) {
                    scroll = -mDeleteBtnWidth;
                }
                mLayoutParams.leftMargin = scroll;
                mViewGroupChild.getChildAt(0).setLayoutParams(mLayoutParams);
            }
            return true;
        }
        return SelectAddressActivity.super.onTouchEvent(ev);
    }

    private void performActionUp() {
        if (-mLayoutParams.leftMargin >= mDeleteBtnWidth / 2) {
            mLayoutParams.leftMargin = -mDeleteBtnWidth;
            mViewGroupChild.getChildAt(0).setLayoutParams(mLayoutParams);
            isDeleteShow = true;
        } else {
            turnToNormal();
        }
    }

    public void turnToNormal() {
        mLayoutParams.leftMargin = 0;
        mViewGroupChild.getChildAt(0).setLayoutParams(mLayoutParams);
        isDeleteShow = false;
    }
}

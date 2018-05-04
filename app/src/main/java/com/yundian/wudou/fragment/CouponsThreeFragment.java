package com.yundian.wudou.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yundian.wudou.R;
import com.yundian.wudou.adapter.CouponsAdapter;
import com.yundian.wudou.data.AdapterCouponsData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanDeleteCoupon;
import com.yundian.wudou.network.JsonBeanMyCouponsData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cookie on 2016/8/29.
 */
public class CouponsThreeFragment extends Fragment implements NetWorkInterface.OnGetMyCouponsListener
        , NetWorkInterface.OnGetDeleteCouponListener {

    @Bind(R.id.rl_fragment_couponsthree_nodata)RelativeLayout rlNoData;
    @Bind(R.id.tv_fragment_couponsthree_nodata)TextView tvNoData;
    @Bind(R.id.lv_fragment_couponsthree)ListView listView;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private CouponsAdapter adapter;
    private List<AdapterCouponsData> list;

    private String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_coupons_three, container, false);

        initialize(viewRoot);

        setEventListener();

        return viewRoot;
    }

    private void initialize(View view) {
        ButterKnife.bind(CouponsThreeFragment.this,view);
        manager = new SharedpreferencesManager(getContext());
        netWorkOperate = new NetWorkOperate(CouponsThreeFragment.this);

        list = new ArrayList<>();
        adapter = new CouponsAdapter(getContext(), list);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        token = manager.getToken();
        netWorkOperate.getMyCouponsData(token, "2");
    }

    private void setEventListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(position);
                return true;
            }
        });
    }

    @Override
    public void onGetMyCouponsData(JsonBeanMyCouponsData jsonBeanMyCouponsData) {
        rlNoData.setVisibility(View.GONE);
        for (JsonBeanMyCouponsData.DataBean dataBean : jsonBeanMyCouponsData.getData()) {
            list.add(
                    new AdapterCouponsData(
                            dataBean.getCouponsn()
                            , "满" + dataBean.getOrderamountlower() + "可以使用"
                            , dataBean.getUsestarttime()
                            , dataBean.getUseendtime()
                            , Float.parseFloat(dataBean.getMoney())
                            , "已过期"
                            , dataBean.getCouponid()));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetDeleteCoupon(JsonBeanDeleteCoupon jsonBeanDeleteCoupon) {
        Toast.makeText(getContext(),jsonBeanDeleteCoupon.getMsg(),Toast.LENGTH_SHORT).show();
        list.clear();
        netWorkOperate.getMyCouponsData(token, "2");
    }

    @Override
    public void onFailure(String msg) {
        if(list.size() == 0){
            adapter.notifyDataSetChanged();
            rlNoData.setVisibility(View.VISIBLE);
            tvNoData.setText(msg);
        }
    }

    private void showDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("是否删除优惠券");
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                netWorkOperate.deleteCoupon(token, list.get(position).getCouponid());
            }
        });
        builder.setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

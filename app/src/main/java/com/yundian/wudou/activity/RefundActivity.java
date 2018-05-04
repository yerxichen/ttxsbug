package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanApplyRefund;
import com.yundian.wudou.network.JsonBeanRefundReason;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class RefundActivity extends BaseActivity implements NetWorkInterface.OnGetApplyRefundListener, NetWorkInterface.OnGetRefundReasonListener {

    @Bind(R.id.sp_activity_refund)
    Spinner spinner;
    @Bind(R.id.et_activity_refund)
    EditText editText;
    @Bind(R.id.tv_activity_refund_apply)
    TextView tvApply;
    @Bind(R.id.tv_activity_refund_feedback)
    TextView tvFeedBack;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private ArrayAdapter<String> adapter;
    private List<String> list;

    private String strOid, reason, mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(RefundActivity.this);
        manager = new SharedpreferencesManager(RefundActivity.this);
        netWorkOperate = new NetWorkOperate(RefundActivity.this);

        this.setBackVisibility(true);
        this.setTitle(getString(R.string.refund));

        Intent intent = getIntent();
        strOid = intent.getStringExtra(FlagData.FLAG_OID);

        list = new ArrayList<>();
        adapter = new ArrayAdapter(RefundActivity.this, R.layout.item_spinselect, list);
        spinner.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        netWorkOperate.getRefundReason(manager.getToken(), strOid);
    }

    private void setEventListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reason = list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark = editText.getText().toString();
                netWorkOperate.getApplyRefund(manager.getToken(), strOid, reason, mark);
            }
        });
    }

    @Override
    public void onGetApplyRefund(JsonBeanApplyRefund jsonBeanApplyRefund) {
        Toast.makeText(RefundActivity.this, jsonBeanApplyRefund.getMsg(), Toast.LENGTH_SHORT).show();
        RefundActivity.this.finish();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(RefundActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetRefundReason(JsonBeanRefundReason jsonBeanRefundReason) {
        list.clear();
        for (JsonBeanRefundReason.DataBean dataBean : jsonBeanRefundReason.getData()) {
            list.add(dataBean.getText());
        }
        adapter.notifyDataSetChanged();

        if(!jsonBeanRefundReason.getId().equals("0")){

        }else{
            spinner.setSelection(Integer.parseInt(jsonBeanRefundReason.getId())-1);
        }

        if(!jsonBeanRefundReason.getRefund_mark().equals("")){
            editText.setText(jsonBeanRefundReason.getRefund_mark());
        }
        if(jsonBeanRefundReason.getRefund_feedback().equals("")){
            tvFeedBack.setVisibility(View.GONE);
        }else {
            tvFeedBack.setVisibility(View.VISIBLE);
            tvFeedBack.setText("商家反馈:"+jsonBeanRefundReason.getRefund_feedback());
        }
    }
}

package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanAddAddress;
import com.yundian.wudou.network.JsonBeanEditAddress;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditAddressActivity extends BaseActivity implements NetWorkInterface.OnAddAddressListener, NetWorkInterface.OnEditAddressListener {

    @Bind(R.id.iv_activity_editaddress_locate)
    ImageView ivLocate;
    @Bind(R.id.et_activity_editaddress_locate)
    EditText etLocate;
    @Bind(R.id.et_activity_editaddress_locatedetails)
    EditText etLocateDetails;
    @Bind(R.id.et_activity_editaddress_receivername)
    EditText etName;
    @Bind(R.id.et_activity_editaddress_receiverphone)
    EditText etPhone;
    @Bind(R.id.cb_activity_editaddress)
    CheckBox cbDefault;
    @Bind(R.id.tv_activity_editaddress_save)
    TextView tvSave;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private String token, said;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(this);
        manager = new SharedpreferencesManager(this);
        token = manager.getToken();
        netWorkOperate = new NetWorkOperate(this);

        this.setBackVisibility(true);
        this.setTitle(R.string.eidt_address);

        Intent intent = getIntent();
        state = intent.getIntExtra("state", -1);
        said = intent.getStringExtra("said");
        if (state == 1) {
            etName.setText(intent.getStringExtra("name"));
            etPhone.setText(intent.getStringExtra("phone"));
            etLocate.setText(intent.getStringExtra("locate"));
            etLocateDetails.setText(intent.getStringExtra("locatedetail"));
        }
    }

    private void setEventListener() {
        ivLocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditAddressActivity.this, NearbyAddressListActivity.class);
                startActivityForResult(intent, 3);
            }
        });

        //是否设为默认地址
        cbDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditAddressActivity.this, SelectAddressActivity.class);
                if (state == 1) {
                    //修改地址
                    netWorkOperate.editAddress(token, said, "0", etName.getText().toString(), etPhone.getText()
                            .toString(), etLocate.getText().toString(), etLocateDetails.getText().toString());
                    setResult(1, intent);
                } else if (state == 2) {
                    //创建地址
                    netWorkOperate.addAddress(token, "0", etName.getText().toString(), etPhone.getText().toString()
                            , etLocate.getText().toString(), etLocateDetails.getText().toString());
                    setResult(2, intent);
                }
                EditAddressActivity.this.finish();
            }
        });
    }

    @Override
    public void onAddAddress(JsonBeanAddAddress jsonBeanAddAddress) {
        Toast.makeText(EditAddressActivity.this, jsonBeanAddAddress.getMsg(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEditAddress(JsonBeanEditAddress jsonBeanEditAddress) {
        Toast.makeText(EditAddressActivity.this, jsonBeanEditAddress.getMsg(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(EditAddressActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3 && resultCode == 3) {
            etLocate.setText(data.getStringExtra("locate"));
            etLocateDetails.setText(data.getStringExtra("details"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

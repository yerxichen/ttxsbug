package com.yundian.wudou.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanUpdatePassWord;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ModifyPasswordActivity extends BaseActivity implements NetWorkInterface.OnGetUpdatePassWordListener {

    @Bind(R.id.et_modifypasswordactivity_old)EditText etOld;
    @Bind(R.id.et_modifypasswordactivity_new)EditText etNew;
    @Bind(R.id.et_modifypasswordactivity_confirm)EditText etConfirm;
    @Bind(R.id.tv_modifypasswordactivity_modify)TextView tvConfirm;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(ModifyPasswordActivity.this);
        manager = new SharedpreferencesManager(ModifyPasswordActivity.this);
        netWorkOperate = new NetWorkOperate(ModifyPasswordActivity.this);

        this.setTitle(R.string.modify_password);
        this.setBackVisibility(true);
    }

    private void setEventListener() {
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = etOld.getText().toString();
                String newPassword = etNew.getText().toString();
                String Confirm = etConfirm.getText().toString();

                if (oldPassword.equals("")) {
                    Toast.makeText(ModifyPasswordActivity.this,"请输入原密码",Toast.LENGTH_SHORT).show();
                } else if (newPassword.equals("")) {
                    Toast.makeText(ModifyPasswordActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
                }else if(oldPassword.equals(newPassword)){
                    Toast.makeText(ModifyPasswordActivity.this,"新密码不能和原密码相同",Toast.LENGTH_SHORT).show();
                } else if (Confirm.equals("")) {
                    Toast.makeText(ModifyPasswordActivity.this,"请输入确认密码",Toast.LENGTH_SHORT).show();
                } else if (!Confirm.equals(newPassword)) {
                    Toast.makeText(ModifyPasswordActivity.this,"两次输入的密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                    etNew.setText("");
                    etConfirm.setText("");
                } else {
                    netWorkOperate.getUpdatePassWord(manager.getToken(), oldPassword, newPassword);
                }
            }
        });
    }

    @Override
    public void onUpdatePassWord(JsonBeanUpdatePassWord jsonBeanUpdatePassWord) {
        Toast.makeText(ModifyPasswordActivity.this,jsonBeanUpdatePassWord.getMsg(),Toast.LENGTH_SHORT).show();
        ModifyPasswordActivity.this.finish();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(ModifyPasswordActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}

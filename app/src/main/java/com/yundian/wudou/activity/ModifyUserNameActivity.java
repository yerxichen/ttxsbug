package com.yundian.wudou.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanModifyUserName;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ModifyUserNameActivity extends BaseActivity implements NetWorkInterface.OnModifyUserNameListener{

    @Bind(R.id.et_activitymodifyusername_content)EditText etName;
    @Bind(R.id.tv_activitymodifyusername_modify) TextView tvModify;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_username);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(ModifyUserNameActivity.this);
        manager = new SharedpreferencesManager(ModifyUserNameActivity.this);
        netWorkOperate = new NetWorkOperate(ModifyUserNameActivity.this);

        this.setBackVisibility(true);
        this.setTitle(getString(R.string.modify_user_name));
    }

    private void setEventListener() {
        tvModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netWorkOperate.modifyUserName(manager.getToken(),etName.getText().toString());
            }
        });
    }

    @Override
    public void onModifyUserName(JsonBeanModifyUserName jsonBeanModifyUserName) {
        Toast.makeText(ModifyUserNameActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
        ModifyUserNameActivity.this.finish();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(ModifyUserNameActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}

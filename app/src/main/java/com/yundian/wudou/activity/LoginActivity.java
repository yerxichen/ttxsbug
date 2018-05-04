package com.yundian.wudou.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanGetToken;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class LoginActivity extends BaseActivity implements NetWorkInterface.OnGetNewTokenListener {

    @Bind(R.id.et_loginactivity_account)EditText etAccount;
    @Bind(R.id.et_loginactivity_password)EditText etPassword;
    @Bind(R.id.tv_loginactivity_forgetpassword)TextView tvFrogetPassword;
    @Bind(R.id.tv_loginactivity_login)TextView tvLogin;

    private NetWorkOperate netWorkOperate;
    private SharedpreferencesManager manager;
    private String strToken;

    private String strAccount,strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        setEventListener();
    }

    //初始化
    private void initialize() {
        ButterKnife.bind(LoginActivity.this);
        netWorkOperate = new NetWorkOperate(LoginActivity.this);
        manager = new SharedpreferencesManager(LoginActivity.this);
        strToken = manager.getToken();

        this.setTitle(R.string.login);
        this.setBackVisibility(true);
        this.setRightViewVisibility(true);
        this.setRightViewText(getString(R.string.register));

        // 调整EditText左边的搜索按钮的大小
        Drawable account = getResources().getDrawable(R.drawable.user);
        Drawable password = getResources().getDrawable(R.drawable.unlock);

        account.setBounds(0, 0, 50, 50);// 第一0是距左边距离，第二0是距上边距离，38分别是长宽
        password.setBounds(0, 0, 50, 50);// 第一0是距左边距离，第二0是距上边距离，38分别是长宽

        etAccount.setCompoundDrawables(account, null, null, null);// 只放左边
        etPassword.setCompoundDrawables(password, null, null, null);// 只放左边
    }

    private void setEventListener() {
        this.setRightViewClickListener(new OnActionBarRightViewClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        tvFrogetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, GetbackPasswordTerifyActivity.class);
                startActivity(intent);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strAccount  = etAccount.getText().toString();
                strPassword  = etPassword.getText().toString();

                if (strAccount.equals("")) {
                    Toast.makeText(LoginActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (strPassword.equals("")) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    netWorkOperate.getNewToken(strToken, strAccount, strPassword);
                }
            }
        });
    }

    @Override
    public void onGetNewToken(JsonBeanGetToken jsonBeanGetToken) {
        JPushInterface.setAliasAndTags(LoginActivity.this,jsonBeanGetToken.getPush_alias(),null,null);


        strToken = jsonBeanGetToken.getAccess_token();
        manager.saveToken(strToken);
        manager.saveIsLogin(true);

        //登录完成之后自动隐藏软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        LoginActivity.this.finish();
    }

    @Override
    public void onFailure(String msg) {
        showMessageDialog(msg);
    }

    private void showMessageDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

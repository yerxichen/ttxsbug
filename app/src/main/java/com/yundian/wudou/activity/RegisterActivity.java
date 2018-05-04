package com.yundian.wudou.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanRegisterCode;
import com.yundian.wudou.network.JsonBeanUserRegister;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity implements NetWorkInterface.OnGetUserRegisterListener,
        NetWorkInterface.OnGetUserRegistrCodeListener {

    @Bind(R.id.et_regiteractivity_phonenumber)EditText etPhoneNumber;
    @Bind(R.id.et_regiteractivity_password)EditText etPassword;
    @Bind(R.id.et_regiteractivity_terify)EditText etTerify;
    @Bind(R.id.tv_registeractivity_getterify)TextView tvGetTerify;
    @Bind(R.id.tv_registeractivity_agreement)TextView tvAgreement;
    @Bind(R.id.tv_registeractivity_register)TextView tvRegister;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private TimeCount time;
    private String strToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(RegisterActivity.this);
        netWorkOperate = new NetWorkOperate(RegisterActivity.this);
        manager = new SharedpreferencesManager(RegisterActivity.this);
        strToken = manager.getToken();
        time = new TimeCount(60000, 1000);

        this.setTitle(R.string.free_register);
        this.setBackVisibility(true);
    }

    private void setEventListener() {
        tvGetTerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPhone = etPhoneNumber.getText().toString();
                //获取验证码
                if (strPhone.equals("")) {
                    Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    netWorkOperate.getUserRegisterCode(strToken, strPhone);
                    time.start();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPhone = etPhoneNumber.getText().toString();
                String strPassword = etPassword.getText().toString();
                String strTerify = etTerify.getText().toString();

                if (strPhone.equals("")) {
                    Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (strPassword.equals("")) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (strTerify.equals("")) {
                    Toast.makeText(RegisterActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!(strTerify.equals(code))) {
                    etTerify.setText("");
                    Toast.makeText(RegisterActivity.this, "验证码错误,请重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    //完成注册
                    netWorkOperate.getUserRegister(strToken, strPhone, strPassword, strTerify);
                }
            }
        });

        tvAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, RegistrationAgreementActivity.class);
                startActivity(intent);
            }
        });
    }

    private String code;

    @Override
    public void onGetUserRegisterCode(JsonBeanRegisterCode jsonBeanRegisterCode) {
        showDialog("获取验证码成功");
        code = jsonBeanRegisterCode.getCode();
    }

    @Override
    public void onGetUserRegister(JsonBeanUserRegister jsonBeanUserRegister) {
        showSuccessDialog("注册成功");
    }

    @Override
    public void onFailure(String msg) {
        showDialog(msg);
        etTerify.setText("");
    }

    private void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSuccessDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RegisterActivity.this.finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            tvGetTerify.setText("重新验证");
            tvGetTerify.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tvGetTerify.setClickable(false);
            tvGetTerify.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}

package com.yundian.wudou.activity;

import android.content.DialogInterface;
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
import com.yundian.wudou.network.JsonBeanForgetPassword;
import com.yundian.wudou.network.JsonBeanForgetPasswordCode;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GetbackPasswordTerifyActivity extends BaseActivity implements NetWorkInterface.OnGetForgetPasswordCodeListener, NetWorkInterface.OnGetForgetPasswordListener {

    @Bind(R.id.tv_getbackpasswordterifyactivity_changepicture)
    TextView tvTerify;
    @Bind(R.id.tv_getbackpasswordterifyactivity_modify)
    TextView tvModify;
    @Bind(R.id.et_getbackpasswordterifyactivity_account)
    EditText etAccount;
    @Bind(R.id.et_getbackpasswordterifyactivity_terify)
    EditText etTerify;
    @Bind(R.id.et_getbackpasswordterifyactivity_new)
    EditText etNew;
    @Bind(R.id.et_getbackpasswordterifyactivity_confirm)
    EditText etConfirm;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    private TimeCount time;
    private String strToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getback_password_terify);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(GetbackPasswordTerifyActivity.this);
        netWorkOperate = new NetWorkOperate(GetbackPasswordTerifyActivity.this);
        manager = new SharedpreferencesManager(GetbackPasswordTerifyActivity.this);
        strToken = manager.getToken();
        time = new TimeCount(60000, 1000);

        this.setTitle(R.string.reset_password);
        this.setBackVisibility(true);
    }

    private void setEventListener() {

        tvTerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mobile = etAccount.getText().toString();
                if (Mobile.equals("")) {
                    Toast.makeText(GetbackPasswordTerifyActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    netWorkOperate.getForgetPasswordCode(strToken, etAccount.getText().toString());
                    time.start();
                }
            }
        });

        tvModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mobile = etAccount.getText().toString();
                String Code = etTerify.getText().toString();
                String newpassword = etNew.getText().toString();
                String confirm = etConfirm.getText().toString();

                if (Mobile.equals("")) {
                    Toast.makeText(GetbackPasswordTerifyActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                } else if (Code.equals("")) {
                    Toast.makeText(GetbackPasswordTerifyActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!(Code.equals(code))) {
                    etTerify.setText("");
                    Toast.makeText(GetbackPasswordTerifyActivity.this, "验证码错误,请重新输入", Toast.LENGTH_SHORT).show();
                } else if (newpassword.equals("")) {
                    Toast.makeText(GetbackPasswordTerifyActivity.this, "新密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (confirm.equals("")) {
                    Toast.makeText(GetbackPasswordTerifyActivity.this, "请确认新密码", Toast.LENGTH_SHORT).show();
                } else if (!(newpassword.equals(confirm))) {
                    etConfirm.setText("");
                    Toast.makeText(GetbackPasswordTerifyActivity.this, "两次密码输入不一致请重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    netWorkOperate.getForgetPassword(strToken, Mobile, newpassword, Code);
                }
            }
        });
    }

    private void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GetbackPasswordTerifyActivity.this);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String code;

    @Override
    public void onGetForGetPasswordCode(JsonBeanForgetPasswordCode jsonBeanForgetPasswordCode) {
        showDialog("获取验证码成功");
        code = jsonBeanForgetPasswordCode.getCode();
    }

    @Override
    public void onGetForgetPassword(JsonBeanForgetPassword jsonBeanForgetPassword) {
        Toast.makeText(GetbackPasswordTerifyActivity.this, "重置密码成功", Toast.LENGTH_SHORT).show();
        GetbackPasswordTerifyActivity.this.finish();
    }

    @Override
    public void onFailure(String msg) {
        showDialog(msg);
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            tvTerify.setText("重新验证");
            tvTerify.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tvTerify.setClickable(false);
            tvTerify.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}

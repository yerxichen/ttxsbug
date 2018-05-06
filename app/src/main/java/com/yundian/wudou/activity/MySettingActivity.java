package com.yundian.wudou.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.customview.ActionSheetDialog;
import com.yundian.wudou.customview.CircleImageView;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.DataClearManager;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanGetToken;
import com.yundian.wudou.network.JsonBeanModifyUserPhoto;
import com.yundian.wudou.network.JsonBeanUploadImage;
import com.yundian.wudou.network.JsonBeanUserInformationData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;
import com.yundian.wudou.utils.ImageCompressor;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MySettingActivity extends BaseActivity implements NetWorkInterface.OnGetUserInformationListener, NetWorkInterface.OnGetInitialTokenListener,
        NetWorkInterface.OnGetUploadImageListener, NetWorkInterface.OnModifyUserPhotoListener,ImageCompressor.CompressListener {

    @Bind(R.id.civ_activity_my_setting)CircleImageView circleImageView;
    @Bind(R.id.tr_activity_mysetting_username)TableRow trModifyName;
    @Bind(R.id.tr_activity_mysetting_loginpassword)TableRow trModifyPassword;
    @Bind(R.id.tr_activity_mysetting_clearcache)TableRow trClearCache;
    @Bind(R.id.tr_activity_mysetting_aboutus)TableRow trAboutus;
    @Bind(R.id.tv_activity_mysetting_clearcache_content)TextView tvCache;
    @Bind(R.id.tv_activity_mysetting_username_content)TextView tvUserName;
    @Bind(R.id.btn_activity_mysetting_exit)Button btnExit;

    private static final int REQUESTCODE_PICK = 1;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 2;

    private SharedpreferencesManager manager;
    private NetWorkOperate mNetWorkOperate;

    private String strMedia_ids, cacheSize, strToken, state;
    private String strCate = "4";

    private String model,imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);

        initialize();

        setEventListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        strToken = manager.getToken();
        mNetWorkOperate.getUserInformation(strToken,"2");
    }

    private void initialize() {
        ButterKnife.bind(MySettingActivity.this);
        manager = new SharedpreferencesManager(MySettingActivity.this);
        mNetWorkOperate = new NetWorkOperate(MySettingActivity.this);

        this.setTitle(R.string.my_setting);
        this.setBackVisibility(true);

        model = Build.MODEL;
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId();

        try {
            tvCache.setText(DataClearManager.getCacheSize(MySettingActivity.this.getCacheDir()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEventListener() {
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(MySettingActivity.this).Builder()
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.BULE, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int witch) {
                                if (ContextCompat.checkSelfPermission(MySettingActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    //申请拍照权限
                                    ActivityCompat.requestPermissions(MySettingActivity.this, new String[]{Manifest.permission.CAMERA}, 0);
                                } else {
                                    openCamera();
                                }
                            }
                        }).addSheetItem("打开相册", ActionSheetDialog.SheetItemColor.BULE, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int witch) {
                        if (ContextCompat.checkSelfPermission(MySettingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            //申请WRITE_EXTERNAL_STORAGE权限
                            ActivityCompat.requestPermissions(MySettingActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        } else {
                            openPic();
                        }
                    }
                }).show();
            }
        });

        trModifyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySettingActivity.this, ModifyUserNameActivity.class);
                startActivity(intent);
            }
        });

        trModifyPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySettingActivity.this, ModifyPasswordActivity.class);
                startActivity(intent);
                MySettingActivity.this.finish();
            }
        });

        trClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除内部缓存
                DataClearManager.cleanInternalCache(MySettingActivity.this);
                //获取内部缓存
                try {
                    //内部缓存大小
                    cacheSize = DataClearManager.getCacheSize(MySettingActivity.this.getCacheDir());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tvCache.setText("0MB");
            }
        });

        trAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySettingActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = "1";
                showMessageDialog("确定退出登录？");
            }
        });
    }

    @Override
    public void onGetToken(JsonBeanGetToken jsonBeanGetToken) {
        manager.saveToken(jsonBeanGetToken.getAccess_token());
        manager.saveIsLogin(false);
        Intent intent = new Intent(MySettingActivity.this, FragmentContainerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onGetUserInformationData(JsonBeanUserInformationData jsonBeanUserInformationData) {
        Glide.with(this).load(FlagData.FLAG_IMGADDRESS + jsonBeanUserInformationData.getAvatar()).into(circleImageView);
        tvUserName.setText(jsonBeanUserInformationData.getUsername());
    }

    @Override
    public void onGetUploadImage(JsonBeanUploadImage jsonBeanUploadImage) {
        strMedia_ids = jsonBeanUploadImage.getMedia_ids();
        mNetWorkOperate.modifyUserPhoto(strToken, strMedia_ids);
    }

    @Override
    public void onModifyUserPhoto(JsonBeanModifyUserPhoto jsonBeanModifyUserPhoto) {
        Toast.makeText(MySettingActivity.this, jsonBeanModifyUserPhoto.getMsg(), Toast.LENGTH_SHORT).show();
        mNetWorkOperate.getUserInformation(strToken,"2");
    }

    @Override
    public void onFailure(String msg) {
        showMessageDialog(msg);
    }

    private void showMessageDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MySettingActivity.this);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (state.equals("1")) {
                    mNetWorkOperate.getInitialToken(model,imei);
                }
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 打开相机
     */
    private File imageFile;

    private void openCamera() {
        if (ContextCompat.checkSelfPermission(MySettingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(MySettingActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String fileName = timeStamp + "_";
            File fileDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            imageFile = null;
            try {
                imageFile = File.createTempFile(fileName, ".jpg", fileDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
            startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
        }
    }

    /**
     * 打开相册
     */
    private void openPic() {
        if (ContextCompat.checkSelfPermission(MySettingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(MySettingActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else {
            Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
            pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(pickIntent, REQUESTCODE_PICK);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //相册
            case REQUESTCODE_PICK: {
                if (data == null || data.getData() == null) {
                    return;
                }
                Uri uri = data.getData();
                ImageCompressor.getInstance(this).withListener(this).starCompressWithDefault(uri.toString());
                break;
            }
            //拍照
            case PHOTO_REQUEST_TAKEPHOTO: {
                ImageCompressor.getInstance(this).withListener(this).
                        starCompressWithDefault(Uri.fromFile(imageFile).toString());
                break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //动态获取权限操作（Android6.0）
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openPic();
        } else if (requestCode == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String fileName = timeStamp + "_";
            File fileDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            imageFile = null;
            try {
                imageFile = File.createTempFile(fileName, ".jpg", fileDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
            startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
        } else {

        }
    }

    @Override
    public void onCompressStart() {

    }

    @Override
    public void onCompressEnd(ImageCompressor.CompressResult compressResult) {
        if (compressResult.getStatus() == ImageCompressor.CompressResult.RESULT_ERROR)//压缩失败
            return;
        File file = new File(compressResult.getOutPath());
        String path = file.getAbsolutePath();
        mNetWorkOperate.uploadPic(strToken, strCate, path);
    }
}

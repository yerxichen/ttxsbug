package com.yundian.wudou.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanMyServiceCategory;
import com.yundian.wudou.network.JsonBeanRegionalData;
import com.yundian.wudou.network.JsonBeanReleaseSecondHand;
import com.yundian.wudou.network.JsonBeanReleaseService;
import com.yundian.wudou.network.JsonBeanUploadImage;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

public class ReleaseGoodsActivity extends BaseActivity implements NetWorkInterface.OnGetReleaseSecondHandListener,
        NetWorkInterface.OnGetReleaseServiceListener, NetWorkInterface.OnGetUploadImageListener
        , NetWorkInterface.OnGetRegionalDataListener ,NetWorkInterface.OnGetMyServiceCategoryListener{

    private SharedpreferencesManager mSharedpreferencesManager;
    private NetWorkOperate mNetWorkOperate;

    private RelativeLayout mRlPrice, mRlUploadImg;
    private EditText mEdtTitle, mEdtPrice, mEdtContacts, mEdtMobile, mEdtDetails;
    private ImageView mIvOne, mIvTwo, mIvThree;
    private TextView mTvUploadHint;
    private Spinner mSpRegion,mSpCategory;
    private RadioGroup mRgRelease;
    private Button mBtnPersonal, mBtnCorporate, mBtnRelease;

    private ArrayAdapter mReginonAdapter,mCategoryAdapter;
    private List<String> mListRegionName, mListRegionCode,mListCategoryName,mListCategoryCode;
    // sate 1企业  2个人
    private String strToken, strTitleBar, strCate, strRegionCode,strCategoryCode, imgPathOne, imgPathTwo, imgPathThree, strMedia_ids = "",
            strTitle, strPrice, strSate = "1", strContacts, strMobile, strContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_goods);

        initialize();

        setEventListener();
    }

    private void initialize() {
        Intent intent = getIntent();
        strTitleBar = intent.getStringExtra(FlagData.FLAG_TITLE);
        // cate 1二手品  2便民服务
        strCate = intent.getIntExtra(FlagData.FLAG_RELEASE, 0) + "";

        setBackVisibility(true);
        setTitle(strTitleBar);

        mEdtTitle = $(R.id.et_activity_releasegoods_title);
        mEdtPrice = $(R.id.et_activity_releasegoods_price);
        mEdtContacts = $(R.id.et_activity_releasegoods_contacts);
        mEdtMobile = $(R.id.et_activity_releasegoods_mobile);
        mEdtDetails = $(R.id.et_activity_releasegoods_introduce);
        mSpRegion = $(R.id.sp_activity_releasegoods_region);
        mSpCategory = $(R.id.sp_activity_releasegoods_category);

        mRgRelease = $(R.id.rg_activity_releasegoods);
        mBtnCorporate = $(R.id.radioButton_corporate);
        mBtnPersonal = $(R.id.radioButton_personal);
        mBtnRelease = $(R.id.btn_activity_releasegoods_release);

        mRlUploadImg = $(R.id.rl_activity_releasegoods_upload);
        mTvUploadHint = $(R.id.tv_activity_releasegoods_upload_hint);
        mIvOne = $(R.id.iv_activity_releasegoods_upload_one);
        mIvTwo = $(R.id.iv_activity_releasegoods_upload_two);
        mIvThree = $(R.id.iv_activity_releasegoods_upload_three);

        mRlPrice = $(R.id.rl_activity_releasegoods_price);
        if (strCate.equals("1")) {
            mSpCategory.setVisibility(View.GONE);
        }else if(strCate.equals("2")){
            mRlPrice.setVisibility(View.GONE);
        }

        mListRegionCode = new ArrayList<>();
        mListRegionName = new ArrayList<>();
        mListCategoryCode = new ArrayList<>();
        mListCategoryName = new ArrayList<>();

        mReginonAdapter = new ArrayAdapter<String>(this, R.layout.item_spinselect, mListRegionName);
        mReginonAdapter.setDropDownViewResource(R.layout.item_dialogspinselect);
        mSpRegion.setAdapter(mReginonAdapter);

        mCategoryAdapter = new ArrayAdapter<String>(this, R.layout.item_spinselect,mListCategoryName);
        mCategoryAdapter.setDropDownViewResource(R.layout.item_dialogspinselect);
        mSpCategory.setAdapter(mCategoryAdapter);

        mSharedpreferencesManager = new SharedpreferencesManager(ReleaseGoodsActivity.this);
        mNetWorkOperate = new NetWorkOperate(ReleaseGoodsActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        strToken = mSharedpreferencesManager.getToken();
        mNetWorkOperate.getRegionalData(strToken);
        mNetWorkOperate.getMyServiceCategory(strToken);
    }

    private void setEventListener() {
        //选择发布人
        mRgRelease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId ==mBtnCorporate.getId()) {
                    strSate = "1";
                } else if (checkedId ==  mBtnPersonal.getId()) {
                    strSate = "2";
                }
            }
        });
        //选择区域
        mSpRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strRegionCode = mListRegionCode.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //选择类别
        mSpCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCategoryCode = mListCategoryCode.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //发布
        mBtnRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strTitle = mEdtTitle.getText().toString();
                strPrice = mEdtPrice.getText().toString();
                strContacts = mEdtContacts.getText().toString();
                strMobile = mEdtMobile.getText().toString();
                strContent = mEdtDetails.getText().toString();
                if (strCate.equals("1")) {
                    mNetWorkOperate.getReleaseSecondHand(strToken, strTitle, strRegionCode, strPrice, strSate, strContacts, strMobile, strMedia_ids, strContent);
                } else if (strCate.equals("2")) {
                    mNetWorkOperate.getReleaseServices(strToken, strTitle, strRegionCode, strSate,strCategoryCode, strContacts, strMobile, strMedia_ids, strContent);
                }
            }
        });
        //上传图片
        mRlUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvUploadHint.setVisibility(View.INVISIBLE);
                if (ContextCompat.checkSelfPermission(ReleaseGoodsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(ReleaseGoodsActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, null);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, 0);
                }
            }
        });
    }

    private int i = 0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
            int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            if (i == 0) {
                mIvOne.setImageURI(uri);
                imgPathOne = actualimagecursor.getString(actual_image_column_index);
                mNetWorkOperate.uploadPic(strToken, strCate, imgPathOne);
                i++;
            } else if (i == 1) {
                mIvTwo.setImageURI(uri);
                imgPathTwo = actualimagecursor.getString(actual_image_column_index);
                mNetWorkOperate.uploadPic(strToken, strCate, imgPathOne, imgPathTwo);
                i++;
            } else {
                mIvThree.setImageURI(uri);
                imgPathThree = actualimagecursor.getString(actual_image_column_index);
                mNetWorkOperate.uploadPic(strToken, strCate, imgPathOne, imgPathTwo, imgPathThree);
                i = 0;
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onGetRegionalData(JsonBeanRegionalData jsonBeanRegionalData) {
        mListRegionCode.clear();
        mListRegionName.clear();
        for (JsonBeanRegionalData.DataBean data : jsonBeanRegionalData.getData()) {
            mListRegionName.add(data.getName());
            mListRegionCode.add(data.getCode());
        }
        strRegionCode = mListRegionCode.get(0);
        mReginonAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetMyServiceCategory(JsonBeanMyServiceCategory jsonBeanMyServiceCategory) {
        mListCategoryCode.clear();
        mListCategoryName.clear();
        for(JsonBeanMyServiceCategory.DataBean dataBean:jsonBeanMyServiceCategory.getData()){
            mListCategoryName.add(dataBean.getName());
            mListCategoryCode.add(dataBean.getCode());
        }
        strCategoryCode = mListCategoryCode.get(0);
        mCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetReleaseSecondHand(JsonBeanReleaseSecondHand jsonBeanReleaseSecondHand) {
        Toast.makeText(ReleaseGoodsActivity.this, "发布二手品成功", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ReleaseGoodsActivity.this, MySecondHandActivity.class);
        startActivity(intent);
        ReleaseGoodsActivity.this.finish();
    }

    @Override
    public void onGetReleaseService(JsonBeanReleaseService jsonBeanReleaseService) {
        Toast.makeText(ReleaseGoodsActivity.this, "发布我的便民服务成功", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ReleaseGoodsActivity.this, MyServiceActivity.class);
        startActivity(intent);
        ReleaseGoodsActivity.this.finish();
    }

    @Override
    public void onGetUploadImage(JsonBeanUploadImage jsonBeanUploadImage) {
        strMedia_ids = jsonBeanUploadImage.getMedia_ids();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(ReleaseGoodsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    //获取读取sd卡权限后的操作（Android6.0）
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, 0);
        }
    }
}

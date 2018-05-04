package com.yundian.wudou.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yundian.wudou.R;
import com.yundian.wudou.adapter.EvaluateAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.customview.ListView;
import com.yundian.wudou.data.AdapterEvaluateData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanEvaluateProduct;
import com.yundian.wudou.network.JsonBeanSubmitEvaluation;
import com.yundian.wudou.network.JsonBeanUploadImage;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;
import com.yundian.wudou.utils.ImageCompressor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EvaluateActivity extends BaseActivity implements NetWorkInterface.OnGetEvaluateProductListener,
        NetWorkInterface.OnSubmitEvaluationListener, NetWorkInterface.OnGetUploadImageListener,ImageCompressor.CompressListener {

    @Bind(R.id.lv_activity_evaluate)ListView listView;
    @Bind(R.id.tv_activity_evaluate_submit)TextView tvSubmit;

    private static final int PHOTO_GALLERY_CODE = 100;
    private static final int WHITE_STORAGE_CODE = 200;

    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;
    private EvaluateAdapter adapter;

    private List<AdapterEvaluateData> list;
    private List<String> imgUrls;

    private Uri uri;
    private int position;
    private String order_id, token, media_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);

        initalize();

        setEventListener();
    }

    private void initalize() {
        ButterKnife.bind(EvaluateActivity.this);
        manager = new SharedpreferencesManager(getApplicationContext());
        netWorkOperate = new NetWorkOperate(EvaluateActivity.this);

        this.setTitle(R.string.evaluate);
        this.setBackVisibility(true);

        Intent intent = getIntent();
        order_id = intent.getStringExtra(FlagData.FLAG_OID);

        list = new ArrayList<>();
        adapter = new EvaluateAdapter(EvaluateActivity.this, list, order_id, listView);
        listView.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(EvaluateActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EvaluateActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WHITE_STORAGE_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == WHITE_STORAGE_CODE){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            }else{
                Toast.makeText(this, "需要允许写入权限来存储图片", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        token = manager.getToken();
        netWorkOperate.getEvaluateProduct(token, order_id);
    }

    private void setEventListener() {

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.saveJsonToSP();
                netWorkOperate.submitEvaluation(manager.getEvaluateJson());
                Log.e("tag", "--------------------------json=" + manager.getEvaluateJson());
            }
        });

        adapter.setOnSelectImageListener(new EvaluateAdapter.OnSelectImageListener() {
            @Override
            public void onSelectImage(int position) {
                EvaluateActivity.this.position = position;
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PHOTO_GALLERY_CODE);
            }
        });
    }

    @Override
    public void onGetEvaluateProduct(JsonBeanEvaluateProduct jsonBeanEvaluateProduct) {
        list.clear();
        for (JsonBeanEvaluateProduct.DataBean dataBean : jsonBeanEvaluateProduct.getData()) {
            imgUrls = new ArrayList<>();
            for (int i = 0; i < dataBean.getReviews().getImgs().size(); i++) {
                imgUrls.add(FlagData.FLAG_IMGADDRESS + dataBean.getReviews().getImgs().get(i).getImg());
            }
            list.add(new AdapterEvaluateData(dataBean.getPid(), FlagData.FLAG_IMGADDRESS + dataBean.getImg(),
                    dataBean.getName(), dataBean.getIsreviews(), dataBean.getReviews().getTime(), dataBean.getReviews().getPercentage(),
                    dataBean.getReviews().getMessage(), imgUrls));
        }
        adapter.notifyDataSetChanged();

        for (AdapterEvaluateData data:list){
            if(data.getIsreviews().equals("0")){
                tvSubmit.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onGetUploadImage(JsonBeanUploadImage jsonBeanUploadImage) {
        media_id = jsonBeanUploadImage.getMedia_ids();
        adapter.updateItem(position, uri, media_id);
    }

    @Override
    public void onSubmitEvaluation(JsonBeanSubmitEvaluation jsonBeanSubmitEvaluation) {
        Toast.makeText(EvaluateActivity.this, jsonBeanSubmitEvaluation.getMsg(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EvaluateActivity.this,MyCommentActivity.class);
        startActivity(intent);
        EvaluateActivity.this.finish();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(EvaluateActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PHOTO_GALLERY_CODE:
            {
                uri = data.getData();
                ImageCompressor.getInstance(this).withListener(this).starCompressWithDefault(uri.toString());
                break;
            }
            default:
                break;
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
        netWorkOperate.uploadPic(token, "3", path);
    }
}

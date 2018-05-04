package com.yundian.wudou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.FlagData;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends BaseActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);

        setContentView(scannerView);

        initialize();
    }

    private void initialize(){
        this.setTitle("二维码扫描");
        this.setBackVisibility(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    Intent intent;
    @Override
    public void handleResult(Result result) {

        String str = result.getText();
        String key = str.substring(str.indexOf("-")+1,str.length()-1);

        if(str.contains("http://wudoll.com/wodollewm/Products-")){
            intent = new Intent(ScannerActivity.this,CommodityDetailsActivity.class);
            intent.putExtra(FlagData.FLAG_PRODUCT_ID,key);
            startActivity(intent);
        }else if(str.contains("http://wudoll.com/wodollewm/Stores-")){
            intent = new Intent(ScannerActivity.this,VegetableShopActivity.class);
            intent.putExtra(FlagData.FLAG_SHOP_ID,key);
            startActivity(intent);
        }else if(str.contains("http://wudoll.com/wodollewm/SearchLoad-")){
            intent = new Intent(ScannerActivity.this,SearchResultActivity.class);
            intent.putExtra(FlagData.FLAG_STATE,"1");
            intent.putExtra(FlagData.FLAG_SEARCH_TEXT,key);
            startActivity(intent);
        }else if(str.contains("http://wudoll.com/wodollewm/CategoriesSearch-")){
            intent = new Intent(ScannerActivity.this,SearchResultActivity.class);
            intent.putExtra(FlagData.FLAG_STATE,"2");
            intent.putExtra(FlagData.FLAG_SEARCH_CATENUMBER,key);
            startActivity(intent);
        }else if(str.contains("http://wudoll.com/wodollewm/ProductsAttributes-")){
            intent = new Intent(ScannerActivity.this,WeekSaleActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"二维码识别失败",Toast.LENGTH_SHORT).show();
        }
        ScannerActivity.this.finish();
    }
}

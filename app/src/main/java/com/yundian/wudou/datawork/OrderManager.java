package com.yundian.wudou.datawork;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yundian.wudou.data.AdapterShoopingcartData;
import com.yundian.wudou.data.ShoppingCartData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/22 0022.
 */
public class OrderManager {

    private static final String TABLE_NAME = "orders";
    private static final String STORE_ID = "storeid";
    private static final String STORE_NAME = "storename";
    private static final String STORE_URL = "storeurl";
    private static final String PRODUCT_ID = "productid";
    private static final String PRODUCT_NAME = "productname";
    private static final String PRODUCT_URL = "producturl";
    private static final String PRODUCT_PRICE = "productprice";
    private static final String PRODUCT_WEIGHT = "productweight";
    private static final String PRODUCT_COUNT = "productcount";
    private static final String START_VALUE = "startvalue";
    private static final String SEND_PRICE = "sendprice";

    private OrderSQLiteOpenHelper mSQLiteOpenHelper;
    private SQLiteDatabase db;
    //自定义sqllite数据库位置
    private MyContextWrapper myContextWrapper;

    public OrderManager(Context context) {
//        this.mSQLiteOpenHelper = new OrderSQLiteOpenHelper(myContextWrapper=new MyContextWrapper(context,"ttsx"));
        this.mSQLiteOpenHelper = new OrderSQLiteOpenHelper(context);
        db = mSQLiteOpenHelper.getWritableDatabase();
    }

    //添加商品，返回该商品数量
    public int addProduct(String storeId, String storeName, String storeUrl, String productId, String productName, String productUrl,
                          String productPrice, String productWeight, String startValue, String sendPrice) {
        if (isHadProduct(productId)) {
            int count = this.getProductCount(productId);
            count++;
            this.updateTable(productId, count + "");
            return count;
        } else {
            this.insertIntoTable(storeId, storeName, storeUrl, productId, productName, productUrl, productPrice, productWeight, startValue, sendPrice);
            return 1;
        }
    }

    //减少商品，返回该商品数量
    public int reduceProduct(String productId) {
        if (isHadProduct(productId)) {
            int count = this.getProductCount(productId);
            if (count > 1) {
                count--;
                this.updateTable(productId, count + "");
            } else {
                this.deleteFromTable(productId);
            }
            return count;
        } else {
            return 0;
        }
    }

    //获取商品数量
    public int getProductCount(String productId) {

        String count = "0";
        Cursor cursor = this.selectTable();
        boolean hadProduct = false;

        if (cursor.moveToFirst()) {
            hadProduct = cursor.getString(cursor.getColumnIndex(PRODUCT_ID)).equals(productId);
            if (hadProduct) {
                count = cursor.getString(cursor.getColumnIndex(PRODUCT_COUNT));
            } else {
                while (cursor.moveToNext()) {
                    hadProduct = cursor.getString(cursor.getColumnIndex(PRODUCT_ID)).equals(productId);
                    if (hadProduct) {
                        count = cursor.getString(cursor.getColumnIndex(PRODUCT_COUNT));
                        cursor.close();
                        return Integer.valueOf(count);
                    }
                }
            }
        }
        return Integer.valueOf(count);
    }

    //购物车页面移除商品
    public void removeProduct(String productId) {
        if (isHadProduct(productId)) {
            this.deleteFromTable(productId);
        }
    }

    //购物车页面移除店铺
    public void reduceProductBySid(String storeId) {
        for (ShoppingCartData data : getShoppingCartDataList()) {
            if (data.getStoreId().equals(storeId)) {
                deleteFromTable(data.getProductId());
            }
        }
    }

    //商品详情传值到结算页面
    public List<AdapterShoopingcartData> getCartDataByPid(String productId) {
        List<AdapterShoopingcartData> mAdapterShoopingcartDataList = new ArrayList<>();
        for (AdapterShoopingcartData data : getCartList()) {
            if (data.getProductId().equals(productId)) {
                mAdapterShoopingcartDataList.add(data);
            }
        }
        return mAdapterShoopingcartDataList;
    }

    //根据StoreId获取商品列表，原始数据
    public List<ShoppingCartData> getShoppingCartDataBySid(String storeId) {
        List<ShoppingCartData> mShoppingCartDataList = new ArrayList<>();
        for (ShoppingCartData data : getShoppingCartDataList()) {
            if (data.getStoreId().equals(storeId)) {
                mShoppingCartDataList.add(data);
            }
        }
        return mShoppingCartDataList;
    }

    //根据StoreId获取商品列表，购物车数据
    public List<AdapterShoopingcartData> getCartDataBySid(String storeId) {
        List<AdapterShoopingcartData> mAdapterShoopingcartDataList = new ArrayList<>();
        for (AdapterShoopingcartData data : getCartList()) {
            if (data.getStoreId().equals(storeId)) {
                mAdapterShoopingcartDataList.add(data);
            }
        }
        return mAdapterShoopingcartDataList;
    }

    //根据StoreId获取商品数量
    public int getShoppingCartNumBySid(String storeId) {
        int num = 0;
        List<ShoppingCartData> mShoppingCartDataList = new ArrayList<>();

        for (ShoppingCartData data : getShoppingCartDataList()) {
            if (data.getStoreId().equals(storeId)) {
               mShoppingCartDataList.add(data);
            }
        }
        for (ShoppingCartData data : mShoppingCartDataList) {
            num += Integer.parseInt(data.getProductCount());
        }
        return num;
    }

    //根据StoreId获取商品价格
    public float getStorePriceBySid(String storeId) {
        float price = 0;
        List<ShoppingCartData> mShoppingCartDataList = new ArrayList<>();

        for (ShoppingCartData data : getShoppingCartDataList()) {
            if (data.getStoreId().equals(storeId)) {
                mShoppingCartDataList.add(data);
            }
        }
        for (ShoppingCartData data : mShoppingCartDataList) {
            price += (Integer.parseInt(data.getProductCount())) * (Float.parseFloat(data.getProductPrice()));
        }
        return price;
    }

    //获取全部商品列表,原始数据
    public List<ShoppingCartData> getShoppingCartDataList() {

        List<ShoppingCartData> mShoppingCartDataList = new ArrayList<>();
        String storeId, storeName, storeUrl, productId, productName, productUrl, productPrice, productWeight, productCount, startValue, sendPrice;

        Cursor cursor = this.selectTable();
        if (cursor.moveToFirst()) {
            storeId = cursor.getString(cursor.getColumnIndex(STORE_ID));
            storeName = cursor.getString(cursor.getColumnIndex(STORE_NAME));
            storeUrl = cursor.getString(cursor.getColumnIndex(STORE_URL));
            productId = cursor.getString(cursor.getColumnIndex(PRODUCT_ID));
            productName = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME));
            productUrl = cursor.getString(cursor.getColumnIndex(PRODUCT_URL));
            productPrice = cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE));
            productWeight = cursor.getString(cursor.getColumnIndex(PRODUCT_WEIGHT));
            productCount = cursor.getString(cursor.getColumnIndex(PRODUCT_COUNT));
            startValue = cursor.getString(cursor.getColumnIndex(START_VALUE));
            sendPrice = cursor.getString(cursor.getColumnIndex(SEND_PRICE));

            mShoppingCartDataList.add(new ShoppingCartData(storeId, storeName, storeUrl, productId, productName, productUrl
                    , productPrice, productWeight, productCount, startValue, sendPrice));
            while (cursor.moveToNext()) {
                storeId = cursor.getString(cursor.getColumnIndex(STORE_ID));
                storeName = cursor.getString(cursor.getColumnIndex(STORE_NAME));
                storeUrl = cursor.getString(cursor.getColumnIndex(STORE_URL));
                productId = cursor.getString(cursor.getColumnIndex(PRODUCT_ID));
                productName = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME));
                productUrl = cursor.getString(cursor.getColumnIndex(PRODUCT_URL));
                productPrice = cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE));
                productWeight = cursor.getString(cursor.getColumnIndex(PRODUCT_WEIGHT));
                productCount = cursor.getString(cursor.getColumnIndex(PRODUCT_COUNT));
                startValue = cursor.getString(cursor.getColumnIndex(START_VALUE));
                sendPrice = cursor.getString(cursor.getColumnIndex(SEND_PRICE));

                mShoppingCartDataList.add(new ShoppingCartData(storeId, storeName, storeUrl, productId, productName, productUrl
                        , productPrice, productWeight, productCount, startValue, sendPrice));
            }
        }
        return mShoppingCartDataList;
    }

    //获取全部商品列表，购物车数据
    public List<AdapterShoopingcartData> getCartList() {

        List<AdapterShoopingcartData> mAdapterShoopingcartDataList = new ArrayList<>();
        List<ShoppingCartData> mShoppingCartDataList = new ArrayList<>();
        String tempStoreId = "";

        String storeId, storeName, storeUrl, productId, productName, productUrl, productPrice, productWeight, productCount, startValue, sendPrice;

        Cursor cursor = this.selectTable();
        if (cursor.moveToFirst()) {
            storeId = cursor.getString(cursor.getColumnIndex(STORE_ID));
            storeName = cursor.getString(cursor.getColumnIndex(STORE_NAME));
            storeUrl = cursor.getString(cursor.getColumnIndex(STORE_URL));
            productId = cursor.getString(cursor.getColumnIndex(PRODUCT_ID));
            productName = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME));
            productUrl = cursor.getString(cursor.getColumnIndex(PRODUCT_URL));
            productPrice = cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE));
            productWeight = cursor.getString(cursor.getColumnIndex(PRODUCT_WEIGHT));
            productCount = cursor.getString(cursor.getColumnIndex(PRODUCT_COUNT));
            startValue = cursor.getString(cursor.getColumnIndex(START_VALUE));
            sendPrice = cursor.getString(cursor.getColumnIndex(SEND_PRICE));

            mShoppingCartDataList.add(new ShoppingCartData(storeId, storeName, storeUrl, productId, productName, productUrl
                    , productPrice, productWeight, productCount, startValue, sendPrice));
            while (cursor.moveToNext()) {
                storeId = cursor.getString(cursor.getColumnIndex(STORE_ID));
                storeName = cursor.getString(cursor.getColumnIndex(STORE_NAME));
                storeUrl = cursor.getString(cursor.getColumnIndex(STORE_URL));
                productId = cursor.getString(cursor.getColumnIndex(PRODUCT_ID));
                productName = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME));
                productUrl = cursor.getString(cursor.getColumnIndex(PRODUCT_URL));
                productPrice = cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE));
                productWeight = cursor.getString(cursor.getColumnIndex(PRODUCT_WEIGHT));
                productCount = cursor.getString(cursor.getColumnIndex(PRODUCT_COUNT));
                startValue = cursor.getString(cursor.getColumnIndex(START_VALUE));
                sendPrice = cursor.getString(cursor.getColumnIndex(SEND_PRICE));

                mShoppingCartDataList.add(new ShoppingCartData(storeId, storeName, storeUrl, productId, productName, productUrl
                        , productPrice, productWeight, productCount, startValue, sendPrice));
            }

            for (ShoppingCartData data : mShoppingCartDataList) {
                if (tempStoreId.equals(data.getStoreId())) {
                    mAdapterShoopingcartDataList.add(new AdapterShoopingcartData(false, false
                            , data.getStoreId()
                            , data.getStoreName()
                            , data.getStoreUrl()
                            , data.getProductId()
                            , data.getProductName()
                            , data.getProductUrl()
                            , Float.valueOf(data.getProductPrice())
                            , data.getProductWeight()
                            , Integer.valueOf(data.getProductCount())
                            , data.getStartValue()
                            , data.getSendPrice()));
                } else {
                    mAdapterShoopingcartDataList.add(new AdapterShoopingcartData(false, true, data.getStoreId(), data.getStoreName()
                            , data.getStoreUrl(), "", "", "", 0, "", 0, data.getStartValue(), data.getSendPrice()));
                    mAdapterShoopingcartDataList.add(new AdapterShoopingcartData(false, false
                            , data.getStoreId()
                            , data.getStoreName()
                            , data.getStoreUrl()
                            , data.getProductId()
                            , data.getProductName()
                            , data.getProductUrl()
                            , Float.valueOf(data.getProductPrice())
                            , data.getProductWeight()
                            , Integer.valueOf(data.getProductCount())
                            , data.getStartValue()
                            , data.getSendPrice()));
                    tempStoreId = data.getStoreId();
                }
            }
        }
        return mAdapterShoopingcartDataList;
    }

    /**
     * 增、删、改、查，私有方法
     **/
    //增加行
    private long insertIntoTable(String storeId, String storeName, String storeUrl, String productId, String productName, String productUrl,
                                 String productPrice, String productWeight, String startValue, String sendPrice) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(STORE_ID, storeId);
        contentValues.put(STORE_NAME, storeName);
        contentValues.put(STORE_URL, storeUrl);
        contentValues.put(PRODUCT_ID, productId);
        contentValues.put(PRODUCT_NAME, productName);
        contentValues.put(PRODUCT_URL, productUrl);
        contentValues.put(PRODUCT_PRICE, productPrice);
        contentValues.put(PRODUCT_WEIGHT, productWeight);
        contentValues.put(PRODUCT_COUNT, "1");  //为什么是1，因为这是新增一行，必定是1。
        contentValues.put(START_VALUE, startValue);
        contentValues.put(SEND_PRICE, sendPrice);
        return db.insert(TABLE_NAME, null, contentValues);
    }

    //删除行
    private int deleteFromTable(String productId) {
        return db.delete(TABLE_NAME, PRODUCT_ID + " like ?", new String[]{productId});
    }

    //改
    private int updateTable(String productId, String productCount) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_COUNT, productCount);
        return db.update(TABLE_NAME, contentValues, PRODUCT_ID + " like ?", new String[]{productId});
    }

    //查询
    private Cursor selectTable() {
        return db.query(
                TABLE_NAME
                , new String[]{"storeid", "storename", "storeurl", "productid", "productname", "producturl", "productprice", "productweight", "productcount", "startvalue", "sendprice"}
                , null, null, null, null, STORE_ID);
    }

    /**
     * 功能方法
     **/
    //是否已有该商品
    private boolean isHadProduct(String productId) {
        Cursor cursor = this.selectTable();
        boolean hadProduct = false;
        if (cursor.moveToFirst()) {
            hadProduct = cursor.getString(cursor.getColumnIndex(PRODUCT_ID)).equals(productId);
            if (!hadProduct) {
                while (cursor.moveToNext()) {
                    hadProduct = cursor.getString(cursor.getColumnIndex(PRODUCT_ID)).equals(productId);
                    if (hadProduct) {
                        break;
                    }
                }
            }
        }
        cursor.close();
        return hadProduct;
    }

    // close database
    public void close() {
        if (db != null) {
            db.close();
        }
    }
}

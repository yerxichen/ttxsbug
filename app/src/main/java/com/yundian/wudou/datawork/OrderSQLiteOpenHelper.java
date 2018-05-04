package com.yundian.wudou.datawork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class OrderSQLiteOpenHelper extends SQLiteOpenHelper {

    private static String name = "myorder.db";
    private static Integer version = 1;

    public OrderSQLiteOpenHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //存放本地购物车信息的表
        db.execSQL("create table if not exists orders(_id integer primary key autoincrement,"
                +"storeid varchar(200),"
                +"storename varchar(200),"
                +"storeurl varchar(200),"
                +"productid varchar(200),"
                +"productname varchar(200),"
                +"producturl varchar(200),"
                +"productprice varchar(200),"
                +"productweight varchar(200),"
                +"productcount varchar(200),"
                +"startvalue varchar(200),"
                +"sendprice varchar(200))"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

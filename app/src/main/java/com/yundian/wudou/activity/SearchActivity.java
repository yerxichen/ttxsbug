package com.yundian.wudou.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.yundian.wudou.R;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.customview.ListView;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.RecordSQLiteOpenHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {

    @Bind(R.id.tv_activity_search)TextView tvSearch;
    @Bind(R.id.et_activity_search)EditText etSearch;
    @Bind(R.id.tv_activity_search_title)TextView tvTitle;
    @Bind(R.id.tv_activity_search_clear)TextView tvClear;
    @Bind(R.id.lv_activity_search)ListView listView;

    private RecordSQLiteOpenHelper sqLiteOpenHelper = new RecordSQLiteOpenHelper(this);
    private SQLiteDatabase db;
    private BaseAdapter adapter;

    String searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();

        setEventListener();
    }

    private void initView() {
        ButterKnife.bind(SearchActivity.this);

        this.setBackVisibility(true);
        this.setTitle(R.string.search);

        Drawable drawable = getResources().getDrawable(R.drawable.search_gray);
        drawable.setBounds(0, 0, 48, 48);
        etSearch.setCompoundDrawables(drawable, null, null, null);

        queryData("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        etSearch.setText("");
    }

    private void setEventListener() {
        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                queryData("");
            }
        });

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                boolean hasData = hasData(etSearch.getText().toString().trim());
                if (!hasData) {
                    insertData(etSearch.getText().toString().trim());
                    queryData("");
                }
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra(FlagData.FLAG_SEARCH_TEXT,searchText);
                intent.putExtra(FlagData.FLAG_STATE,"1");
                startActivity(intent);
            }
        });

        etSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    boolean hasData = hasData(etSearch.getText().toString().trim());
                    if (!hasData) {
                        insertData(etSearch.getText().toString().trim());
                        queryData("");
                    }
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra(FlagData.FLAG_SEARCH_TEXT,searchText);
                    intent.putExtra(FlagData.FLAG_STATE,"1");
                    startActivity(intent);
                }
                return false;
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchText = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() == 0) {
                    tvTitle.setText("搜索记录");
                } else {
                    tvTitle.setText("搜索结果");
                }
                String tempName = etSearch.getText().toString();
                queryData(tempName);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String name = textView.getText().toString();
                etSearch.setText(name);
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra(FlagData.FLAG_SEARCH_TEXT,name);
                intent.putExtra(FlagData.FLAG_STATE,"1");
                startActivity(intent);
            }
        });
    }

    /**
     * 插入数据
     */
    private void insertData(String tempName) {
        db = sqLiteOpenHelper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /**
     * 查询数据
     */
    private void queryData(String tempName) {
        Cursor cursor = sqLiteOpenHelper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 创建adapter适配器对象
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"name"},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * 查询是否存在
     */
    private boolean hasData(String tempName) {
        Cursor cursor = sqLiteOpenHelper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /**
     * 清空数据
     */
    private void deleteData() {
        db = sqLiteOpenHelper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }
}
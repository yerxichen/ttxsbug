package com.yundian.wudou.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigButton;
import com.mylhyl.circledialog.callback.ConfigText;
import com.mylhyl.circledialog.callback.ConfigTitle;
import com.mylhyl.circledialog.params.ButtonParams;
import com.mylhyl.circledialog.params.TextParams;
import com.mylhyl.circledialog.params.TitleParams;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.SettlementAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterSettlementData;
import com.yundian.wudou.data.AdapterShoopingcartData;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.data.SettlemCoupons;
import com.yundian.wudou.data.StoresBaseInfoData;
import com.yundian.wudou.data.StoresinfoData;
import com.yundian.wudou.datawork.OrderManager;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanAddOrder;
import com.yundian.wudou.network.JsonBeanDeliveryTime;
import com.yundian.wudou.network.JsonBeanLoadAddress;
import com.yundian.wudou.network.JsonBeanStoreDetailsData;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettlementActivity extends BaseActivity implements NetWorkInterface.OnLoadAddressListener,
        NetWorkInterface.OnAddOrderListener, NetWorkInterface.OnGetCouponListener, NetWorkInterface.OnGetStoreDetails3DataListener,
        NetWorkInterface.OnGetDeliveryTimeListener, View.OnClickListener {

    @Bind(R.id.rl_activity_settlement_adress)
    RelativeLayout rlAddress;
    @Bind(R.id.tv_activity_settlement_adress_name)
    TextView tvName;
    @Bind(R.id.tv_activity_settlement_adress_phone)
    TextView tvPhone;
    @Bind(R.id.tv_activity_settlement_adress_adress)
    TextView tvAddress;
    @Bind(R.id.tv_activity_settlement_pay)
    TextView tvPay;
    @Bind(R.id.tv_coupons)
    TextView tvCoupons;
    @Bind(R.id.tv_activity_settlement_price)
    TextView tvPrice;
    @Bind(R.id.sp_activity_settlement)
    Spinner mSpTime;
    @Bind(R.id.et_activity_settlement_remarkers)
    EditText etRemarks;
    @Bind(R.id.lv_activity_settlement)
    ListView mListViewSettlement;
    @Bind(R.id.iv_next)
    ImageView iv_next;
    @Bind(R.id.ly_activity_settlement)
    RelativeLayout ly_activity_settlement;
    @Bind(R.id.rl_yhq)
    RelativeLayout rl_yhq;
    @Bind(R.id.tv_select_data)
    TextView tv_select_data;

    private ArrayAdapter<String> mTimeAdapter;
    private List<String> mListTime, mListTimeId, mListCoupons, mListCouponsId;
    private List<SettlemCoupons.DataBean> tempListCoupons;

    private SettlementAdapter mSettlementAdapter;
    private List<String> mImgUrls;
    private List<AdapterSettlementData> mListAdapterSettlementData;
    private List<AdapterShoopingcartData> mListAdapterShoopingcartData;

    private SharedpreferencesManager manager;
    private OrderManager orderManager;
    private NetWorkOperate netWorkOperate;

    private String storeId, strToken, strStoreName, startValue, strAddressId, strOrderInfo = "", strSendPrice, timeid, buyerremark = "", couponid = "";
    private float productPrice, total, sopPrice,sumTotal;
    private Boolean flag = true;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(SettlementActivity.this);
        orderManager = new OrderManager(SettlementActivity.this);
        netWorkOperate = new NetWorkOperate(SettlementActivity.this);
        manager = new SharedpreferencesManager(SettlementActivity.this);
        rl_yhq.setOnClickListener(this);
        this.setBackVisibility(true);
        this.setTitle(R.string.settlement);

        //从购物车页面传过来的list
        mListAdapterShoopingcartData = (List<AdapterShoopingcartData>) this.getIntent().getSerializableExtra("list");

        if (mListAdapterShoopingcartData.size() > 0) {
            //传过来的商家信息
            netWorkOperate.getStoreDetails3Data(manager.getToken(), mListAdapterShoopingcartData.get(0).getStoreId());
        }
        mListTime = new ArrayList<>();
        mListTimeId = new ArrayList<>();
        mListCoupons = new ArrayList<>();
        mListCouponsId = new ArrayList<>();
        tempListCoupons = new ArrayList<>();
        mTimeAdapter = new ArrayAdapter(this, R.layout.item_spinselect, mListTime);
        mTimeAdapter.setDropDownViewResource(R.layout.item_dialogspinselect);
        mSpTime.setAdapter(mTimeAdapter);

        mListAdapterSettlementData = new ArrayList<>();
        mImgUrls = new ArrayList<>();
        storeId = mListAdapterShoopingcartData.get(0).getStoreId();
        for (int i = 0; i < mListAdapterShoopingcartData.size(); i++) {
            AdapterShoopingcartData data = mListAdapterShoopingcartData.get(i);
            Log.d(">>>data.getStar>>>", data.getStartValue());
            if (flag) {
                strStoreName = data.getStoreName();
                count = data.getProductCount();

                productPrice = data.getProductPrice() * data.getProductCount();
                strSendPrice = data.getSendPrice();
                startValue = data.getStartValue();
                mImgUrls.add(data.getProductUrl());
                flag = false;
            } else {
                if (data.getStoreName().equals(strStoreName)) {
                    mImgUrls.add(data.getProductUrl());
                    productPrice += data.getProductPrice() * data.getProductCount();
                    strSendPrice = data.getSendPrice();
                    startValue = data.getStartValue();

                    count += data.getProductCount();
                } else {
                    mListAdapterSettlementData.add(new AdapterSettlementData(strStoreName, count + "", "25.00kg", productPrice + "", startValue, strSendPrice, mImgUrls));
                    strStoreName = data.getStoreName();
                    count = data.getProductCount();
                    productPrice = data.getProductPrice() * data.getProductCount();
                    mImgUrls = new ArrayList<>();
                    mImgUrls.add(data.getProductUrl());
                }
            }
            if (i == (mListAdapterShoopingcartData.size() - 1)) {
                mListAdapterSettlementData.add(new AdapterSettlementData(strStoreName, count + "", "25.00kg", productPrice + "", startValue, strSendPrice, mImgUrls));
            }
        }
        if (productPrice >= Float.parseFloat(startValue)) {
            for (int i = 0; i < mListAdapterSettlementData.size(); i++) {
                AdapterSettlementData data = mListAdapterSettlementData.get(i);

                total += Float.parseFloat(data.getCommodityPrice());
            }
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            sumTotal = total;
            tvPrice.setText("应付 ¥" + decimalFormat.format(sumTotal));
        } else {
            for (int i = 0; i < mListAdapterSettlementData.size(); i++) {
                AdapterSettlementData data = mListAdapterSettlementData.get(i);

                total += Float.parseFloat(data.getCommodityPrice()) + Float.parseFloat(data.getSendPrice());
            }
            sumTotal = total;
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            tvPrice.setText("应付 ¥" + decimalFormat.format(sumTotal));
        }


        mSettlementAdapter = new SettlementAdapter(SettlementActivity.this, mListAdapterSettlementData);
        mListViewSettlement.setAdapter(mSettlementAdapter);

        /*for (AdapterShoopingcartData data : mListAdapterShoopingcartData) {
            Log.e("tag", "---------->>storename=" + data.getStoreName() + ",storeId=" + data.getStoreId() + ",productName=" + data.getProductName() +
                    ",productId=" + data.getProductId() + ",productUrl=" + data.getProductUrl() + ",productPrice=" + data.getProductPrice() +
                    ",productCount=" + data.getProductCount() + ",type" + data.getType() + ",isParent=" + data.isParent() + ",isChecked=" + data.isChecked() +
                    ",productWeight=" + data.getProductWeight() + ",startValue=" + data.getStartValue() + ",sendPrice=" + data.getSendPrice());
        }*/

        strToken = manager.getToken();
        netWorkOperate.getAddress(strToken);
        netWorkOperate.getDeliveryTime(strToken, storeId);
        netWorkOperate.getCoupon(strToken, storeId);

    }

    private void setEventListener() {
        rlAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettlementActivity.this, SelectAddressActivity.class);
                intent.putExtra("state", 8);
                startActivityForResult(intent, 8);
            }
        });

        mSpTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeid = mListTimeId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etRemarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                buyerremark = s.toString();
            }
        });

        tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeid == null && (mListTimeId.size() != 0)) {
                    timeid = mListTimeId.get(0);
                }
                for (int i = 0; i < mListAdapterShoopingcartData.size(); i++) {
                    AdapterShoopingcartData data = mListAdapterShoopingcartData.get(i);
                    strOrderInfo += data.getProductId() + "," + data.getProductCount() + "$";
                }
                strOrderInfo = strOrderInfo.substring(0, strOrderInfo.length() - 1);
                netWorkOperate.addOrder(strToken, strAddressId, timeid, strOrderInfo, buyerremark, couponid);
                for (int i = 0; i < mListAdapterShoopingcartData.size(); i++) {
                    AdapterShoopingcartData data = mListAdapterShoopingcartData.get(i);
                    orderManager.removeProduct(data.getProductId());
                }
            }
        });
        ly_activity_settlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(SettlementActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String s = mListTime.get(options1);
                        //     button4.setText(s);
                        tv_select_data.setText(s);
                        timeid = mListTimeId.get(options1);
                    }
                })
                        .setSubmitText("确定")//确定按钮文字
                        .setCancelText("取消")//取消按钮文字
                        .setTitleText("选择配送时间")//标题
                        .setTitleColor(SettlementActivity.this.getResources().getColor(R.color.colorGreenBefore))
                        .setSubCalSize(14)//确定和取消文字大小
                        .setTitleSize(14)//标题文字大小
//                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(SettlementActivity.this.getResources().getColor(R.color.colorGreenBefore))//确定按钮文字颜色

                        .setCancelColor(SettlementActivity.this.getResources().getColor(R.color.colorGreenBefore))//取消按钮文字颜色

//                        .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
//                        .setContentTextSize(18)//滚轮文字大小
//                        .setTextColorCenter(Color.BLUE)//设置选中项的颜色
                        .setTextColorCenter(Color.BLACK)//设置选中项的颜色
//                        .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
//                        .setLinkage(false)//设置是否联动，默认true
//                        .setLabels("省", "市", "区")//设置选择的三级单位
//                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .setCyclic(false, false, false)//循环与否
//                        .setSelectOptions(1, 1, 1)  //设置默认选中项
//                        .setOutSideCancelable(false)//点击外部dismiss default true
//                        .isDialog(true)//是否显示为对话框样式
                        .build();
                pvOptions.setPicker(mListTime);
                pvOptions.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 8 && resultCode == 8) {
            tvPhone.setVisibility(View.VISIBLE);
            tvAddress.setVisibility(View.VISIBLE);
            tvName.setText(data.getStringExtra("name"));
            tvPhone.setText(data.getStringExtra("phone"));
            tvAddress.setText(data.getStringExtra("locate") + "  " + data.getStringExtra("locatedetail"));
            strAddressId = data.getStringExtra("addressid");
            Log("name=" + data.getStringExtra("name"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onLoadAddress(JsonBeanLoadAddress jsonBeanLoadAddress) {
        JsonBeanLoadAddress.DataBean data = jsonBeanLoadAddress.getData().get(0);
        tvName.setText(data.getConsignee());
        tvPhone.setText(data.getMobile());
        tvAddress.setText(data.getAddress() + "  " + data.getAddressmark());

        strAddressId = data.getSaid();
    }

    @Override
    public void onAddOrder(JsonBeanAddOrder jsonBeanAddOrder) {

        Intent intent = new Intent(SettlementActivity.this, PaymentActivity.class);
        intent.putExtra(FlagData.FLAG_SNTYPE, "2");
        intent.putExtra(FlagData.FLAG_SN, jsonBeanAddOrder.getPaysn());
        startActivity(intent);
        SettlementActivity.this.finish();
    }

    @Override
    public void onGetDeliveryTime(JsonBeanDeliveryTime jsonBeanDeliveryTime) {
        mListTime.clear();
        mListTimeId.clear();
        for (JsonBeanDeliveryTime.DataBean dataBean : jsonBeanDeliveryTime.getData()) {
            mListTime.add(dataBean.getDistributionDesn());
            mListTimeId.add(dataBean.getAutotimeid());
        }
        if (mListTime.size() > 0) {

            tv_select_data.setText(mListTime.get(0));
            timeid = mListTimeId.get(0);
        }
        mTimeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        //  Toast.makeText(SettlementActivity.this, msg, Toast.LENGTH_SHORT).show();
        tvName.setText("请选择收获地址");
        tvPhone.setVisibility(View.GONE);
        tvAddress.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        orderManager.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_yhq:
                if (mListCoupons.size() > 0) {
                    OptionsPickerView pvOptions = new OptionsPickerView.Builder(SettlementActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3, View v) {
                            //返回的分别是三个级别的选中位置
                            String s = mListCoupons.get(options1);
                            //     button4.setText(s);
                            tvCoupons.setText(s);

                            couponid = mListCouponsId.get(options1);

                            DecimalFormat decimalFormat = new DecimalFormat("0");
                            String temp_strLowerPrice="";
                            String temp_getMoney="";
                            temp_strLowerPrice = tempListCoupons.get(options1).getOrderamountlower();
                            temp_getMoney = tempListCoupons.get(options1).getMoney();

                            sumTotal = total;
                            if (sumTotal >= Float.valueOf(temp_strLowerPrice)) {
                                sumTotal = sumTotal - Float.valueOf(temp_getMoney);
                            }
                            tvPrice.setText("应付 ¥" + decimalFormat.format(sumTotal));


                            //  timeid = mListCoupons.get(options1);
                        }
                    })
                            .setSubmitText("确定")//确定按钮文字
                            .setCancelText("取消")//取消按钮文字
                            .setTitleText("选择优惠券")//标题
                            .setTitleColor(SettlementActivity.this.getResources().getColor(R.color.colorGreenBefore))
                            .setSubCalSize(14)//确定和取消文字大小
                            .setTitleSize(14)//标题文字大小
//                        .setTitleColor(Color.BLACK)//标题文字颜色
                            .setSubmitColor(SettlementActivity.this.getResources().getColor(R.color.colorGreenBefore))//确定按钮文字颜色

                            .setCancelColor(SettlementActivity.this.getResources().getColor(R.color.colorGreenBefore))//取消按钮文字颜色

//                        .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
//                        .setContentTextSize(18)//滚轮文字大小
//                        .setTextColorCenter(Color.BLUE)//设置选中项的颜色
                            .setTextColorCenter(Color.BLACK)//设置选中项的颜色
//                        .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
//                        .setLinkage(false)//设置是否联动，默认true
//                        .setLabels("省", "市", "区")//设置选择的三级单位
//                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .setCyclic(false, false, false)//循环与否
//                        .setSelectOptions(1, 1, 1)  //设置默认选中项
//                        .setOutSideCancelable(false)//点击外部dismiss default true
//                        .isDialog(true)//是否显示为对话框样式
                            .build();
                    pvOptions.setPicker(mListCoupons);
                    pvOptions.show();
//                Intent intent = new Intent(SettlementActivity.this, CouponsActivity.class);
//                startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onGetCoupon(SettlemCoupons settlemCoupons) {
        mListCoupons.clear();
        mListCouponsId.clear();
        tempListCoupons.clear();
        String temp_strLowerPrice = "";
        String temp_getMoney = "";
        Float temp_topLower = 0.0f;

        DecimalFormat decimalFormat = new DecimalFormat("0");
        int j = 0;
        boolean find_b = false;
        for (int i = 0; i < settlemCoupons.getData().size(); i++) {
            if (productPrice >= Float.parseFloat(settlemCoupons.getData().get(i).getOrderamountlower())) {
                find_b = true;
                tempListCoupons.add(settlemCoupons.getData().get(i));
                mListCoupons.add("满" + decimalFormat.format(Float.valueOf(settlemCoupons.getData().get(i).getOrderamountlower())) + "减" + settlemCoupons.getData().get(i).getMoney());
                mListCouponsId.add(settlemCoupons.getData().get(i).getCouponid());

                if (i == 0) {
                    temp_topLower = Float.parseFloat(settlemCoupons.getData().get(i).getOrderamountlower());
                } else if (Float.parseFloat(settlemCoupons.getData().get(i).getOrderamountlower()) > temp_topLower) {
                    temp_topLower = Float.parseFloat(settlemCoupons.getData().get(i).getOrderamountlower());

                    j = i;
                }
            }
        }

        if (find_b) {
            //tvCoupons.setText("满" + decimalFormat.format(Float.valueOf(strSendPrice)) + "减" + settlemCoupons.getData().get(i).getMoney());
            temp_strLowerPrice = settlemCoupons.getData().get(j).getOrderamountlower();
            temp_getMoney = settlemCoupons.getData().get(j).getMoney();

            sumTotal = total;

            if (sumTotal >= Float.valueOf(temp_strLowerPrice)) {
                sumTotal = sumTotal - Float.valueOf(temp_getMoney);
            }
            tvPrice.setText("应付 ¥" + decimalFormat.format(sumTotal));
        }
        if (mListCoupons.size() > 0) {
            tvCoupons.setText(mListCoupons.get(0));
            couponid = mListCouponsId.get(0);
        } else {
            tvCoupons.setText("暂无优惠券");
        }

    }


    @Override
    public void onGetStoreDetails3Data(StoresBaseInfoData jsonBeanStoreDetailsData) {

        storeId = jsonBeanStoreDetailsData.getStoresinfo().getStoreid();
        startValue = jsonBeanStoreDetailsData.getStoresinfo().getStartvalue();

        if ("1".equals(jsonBeanStoreDetailsData.getStoresinfo().getIsopen())) {
            new CircleDialog.Builder(this)
                    .setTitle("提示")
                    .setWidth(0.7f)
                    .setCanceledOnTouchOutside(false)
                    .configTitle(new ConfigTitle() {
                        @Override
                        public void onConfig(TitleParams params) {
                            params.height = 115;
                            params.textSize = 50;
                            params.textColor = SettlementActivity.this.getResources().getColor(R.color.colorBlack);
                            params.gravity = Gravity.BOTTOM | Gravity.CENTER;

                        }
                    })
                    .setWidth(Float.valueOf("0.7"))
                    .setText("本店已打烊")
                    .configText(new ConfigText() {
                        @Override
                        public void onConfig(TextParams params) {
                            params.height = 120;
                            params.textSize = 45;
                            params.textColor = SettlementActivity.this.getResources().getColor(R.color.colorBlack);
                            int[] a = {0, 15, 0, 0};
                            params.padding = a;
                            params.gravity = Gravity.TOP | Gravity.CENTER;
                        }
                    })
                    .setPositive("确定", null)
                    .configPositive(new ConfigButton() {
                        @Override
                        public void onConfig(ButtonParams params) {
                            params.height = 130;
                        }
                    })
                    .show();

        } else if ("1".equals(jsonBeanStoreDetailsData.getStoresinfo().getIsDistributioning())) {
            new CircleDialog.Builder(this)
                    .setTitle("提示")
                    .setWidth(0.7f)
                    .setCanceledOnTouchOutside(false)
                    .configTitle(new ConfigTitle() {
                        @Override
                        public void onConfig(TitleParams params) {
                            params.height = 115;
                            params.textSize = 50;
                            params.textColor = SettlementActivity.this.getResources().getColor(R.color.colorBlack);
                            params.gravity = Gravity.BOTTOM | Gravity.CENTER;

                        }
                    })
                    .setWidth(Float.valueOf("0.7"))
                    .setText("超出配送范围")
                    .configText(new ConfigText() {
                        @Override
                        public void onConfig(TextParams params) {
                            params.height = 120;
                            params.textSize = 45;
                            params.textColor = SettlementActivity.this.getResources().getColor(R.color.colorBlack);
                            int[] a = {0, 15, 0, 0};
                            params.padding = a;
                            params.gravity = Gravity.TOP | Gravity.CENTER;
                        }
                    })
                    .setPositive("确定", null)
                    .configPositive(new ConfigButton() {
                        @Override
                        public void onConfig(ButtonParams params) {
                            params.height = 130;
                        }
                    })
                    .show();

        }

    }
}

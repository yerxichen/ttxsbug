package com.yundian.wudou.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.yundian.wudou.R;
import com.yundian.wudou.adapter.NearbyAddressListAdapter;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.data.AdapterNearbyAddressListData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanGetToken;
import com.yundian.wudou.network.JsonBeanNearbyAddress;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NearbyAddressListActivity extends BaseActivity implements NetWorkInterface.OnGetNearbyAddressListener, NetWorkInterface.OnGetNewTokenListener, BDLocationListener {

    @Bind(R.id.lv_activity_addresslist)
    ListView listView;

    private View footerView;
    private FrameLayout footerLayoutHolder;
    private LocationClient mLocationClient = null;
    private NearbyAddressListAdapter mNearbyAddressListAdapter;

    private SharedpreferencesManager manager;
    private NetWorkOperate mNetWorkOperate;

    private List<AdapterNearbyAddressListData> mAdapterNearbyAddressListDataList;

    private String strToken, latitude, longitude;
    private int currentPage = 1;
    private boolean isBottom, hasMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);

        initialize();

        setEventListener();
    }

    private void initialize() {
        ButterKnife.bind(NearbyAddressListActivity.this);
        mNetWorkOperate = new NetWorkOperate(NearbyAddressListActivity.this);
        manager = new SharedpreferencesManager(NearbyAddressListActivity.this);
        strToken = manager.getToken();

        this.setBackVisibility(true);
        this.setTitle(R.string.address_list);

        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(NearbyAddressListActivity.this);    //注册监听函数
        initLocation();

        if (ContextCompat.checkSelfPermission(NearbyAddressListActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NearbyAddressListActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            mLocationClient.start();
        }

        if (footerView == null) {
            footerView = LayoutInflater.from(NearbyAddressListActivity.this).inflate(R.layout.listview_footer, null);
            footerLayoutHolder = new FrameLayout(NearbyAddressListActivity.this);
            footerLayoutHolder.addView(footerView, 0, new FrameLayout.LayoutParams(ListView.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        }
        listView.addFooterView(footerLayoutHolder);

        mAdapterNearbyAddressListDataList = new ArrayList<>();
        mNearbyAddressListAdapter = new NearbyAddressListAdapter(NearbyAddressListActivity.this, mAdapterNearbyAddressListDataList);
        listView.setAdapter(mNearbyAddressListAdapter);
    }

    private void setEventListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(NearbyAddressListActivity.this, EditAddressActivity.class);
                intent.putExtra("locate", mAdapterNearbyAddressListDataList.get(position).getLocate());
                intent.putExtra("details", mAdapterNearbyAddressListDataList.get(position).getDetails());
                intent.putExtra("addressinfo", mAdapterNearbyAddressListDataList.get(position).getAddressinfo());
                intent.putExtra("state", 3);
                setResult(3, intent);
                finish();
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isBottom = ((firstVisibleItem + visibleItemCount) == totalItemCount);
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isBottom && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    footerView.setVisibility(View.VISIBLE);
                    if (hasMore) {
                        currentPage++;
                    }
                    mNetWorkOperate.getNearbyAddress(strToken, currentPage + "");
                }
            }
        });
    }

    @Override
    public void onGetNearbyAddress(JsonBeanNearbyAddress jsonBeanNearbyAddress) {
        for (JsonBeanNearbyAddress.DataBean dataBean : jsonBeanNearbyAddress.getData()) {
            mAdapterNearbyAddressListDataList.add(new AdapterNearbyAddressListData(dataBean.getName(), dataBean.getAddress(), dataBean.getAddressinfo(), false));
        }
        mAdapterNearbyAddressListDataList.get(0).setRecommend(true);
        mNearbyAddressListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        footerView.setVisibility(View.GONE);
        hasMore = false;
        Toast.makeText(NearbyAddressListActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetNewToken(JsonBeanGetToken jsonBeanGetToken) {
        strToken = jsonBeanGetToken.getAccess_token();
        manager.saveToken(strToken);
        mNetWorkOperate.getNearbyAddress(strToken, currentPage + "");
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        //Receive Location
        StringBuffer sb = new StringBuffer(256);
        sb.append("time : ");
        sb.append(location.getTime());
        sb.append("\nerror code : ");
        sb.append(location.getLocType());
        sb.append("\nlatitude : ");
        sb.append(location.getLatitude());
        sb.append("\nlontitude : ");
        sb.append(location.getLongitude());
        sb.append("\nradius : ");
        sb.append(location.getRadius());
        if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());// 单位：公里每小时
            sb.append("\nsatellite : ");
            sb.append(location.getSatelliteNumber());
            sb.append("\nheight : ");
            sb.append(location.getAltitude());// 单位：米
            sb.append("\ndirection : ");
            sb.append(location.getDirection());// 单位度
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            sb.append("\ndescribe : ");
            sb.append("gps定位成功");

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            //运营商信息
            sb.append("\noperationers : ");
            sb.append(location.getOperators());
            sb.append("\ndescribe : ");
            sb.append("网络定位成功");
        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            sb.append("\ndescribe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");
        } else if (location.getLocType() == BDLocation.TypeServerError) {
            sb.append("\ndescribe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            sb.append("\ndescribe : ");
            sb.append("网络不同导致定位失败，请检查网络是否通畅");
        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            sb.append("\ndescribe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
        }
        sb.append("\nlocationdescribe : ");
        sb.append(location.getLocationDescribe());// 位置语义化信息
        List<Poi> list = location.getPoiList();// POI数据
        if (list != null) {
            sb.append("\npoilist size = : ");
            sb.append(list.size());
            for (Poi p : list) {
                sb.append("\npoi= : ");
                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            }
        }
        Log.i("BaiduLocationApiDem", sb.toString());
        latitude = location.getLatitude() + "";
        longitude = location.getLongitude() + "";
        if (latitude != null && longitude != null) {
            mNetWorkOperate.getCoordinateToken(strToken, latitude, longitude);
        }
    }

    //获取读取定位权限后的操作（Android6.0）
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mLocationClient.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }
}
